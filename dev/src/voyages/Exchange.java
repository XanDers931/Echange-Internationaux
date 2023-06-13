package voyages;

import java.io.Serializable;
import java.util.Objects;

import graphes.Graph;

import java.util.List;
import java.util.ArrayList;

/** The {@code Exchange} class, it represents an exchange between two countries.
 * @author Dagneaux Nicolas
 * @author Degraeve Paul
 * @author Martel Alexandre
 */
public class Exchange implements Serializable {
	
	/**
	 * A {@code Tuple} of {@code CountryName}.
	 */
	private Tuple<CountryName> countries;
	
	/**
	 * A {@code List<Affectation>} of couples.
	 */
	private List<Affectation> couples;
	
	/**
	 * A {@code Platform} linked to this exchange.
	 */
	private Platform currentPlatform;	
	
	/**
	 * Exchange constructor
	 * @param hostCountry, a {@code CountryName} representing the host country.
	 * @param guestCountry, a {@code CountryName} representing the guest country.
	 * @param p, a {@code Platform} linked to this exchange.
	 * @throws SameCountryException if hostCountry and guestCountry are equals
	 */
	public Exchange(CountryName hostCountry, CountryName guestCountry, Platform p) throws SameCountryException {
		if (hostCountry == guestCountry) {
			throw new SameCountryException("Il est impossible de créer un échange entre un même pays");
		}
		this.countries = new Tuple<CountryName>(hostCountry, guestCountry);
		this.couples = new ArrayList<Affectation>();
		this.currentPlatform = p;
	}

	/**
	 * This method generate an optimal affectation
	 */
	public void setOptimalAffectation() throws SameTeenagerException {
		ArrayList<Teenager> teens = new ArrayList<Teenager>();
		//On ajoute uniquement l'hôte si son couple n'est pas bloqué
		for (Affectation couple : this.couples) {
			if (!couple.isLocked()) {
				teens.add(couple.getHost());
			}
		}
		for (Teenager guest : this.currentPlatform.getTeenagersByCountry(this.getGuestCountry())) {
			//Pour chaque invité potentiel
			boolean isLocked = false;
			int i = 0;
			//on regarde tous les couples, s'il est dans un couple verrouillé, on ne l'ajoute pas.
			while (!isLocked && i < this.couples.size()) {
				//si l'hôte du couplé testé est bien lié au guest courant, on regarde si verrouillage
				if (this.couples.get(i).get(guest) != null) {
					isLocked = this.couples.get(i).isLocked();
				}
				i++;
			}
			if (!isLocked) {
				teens.add(guest);
			}
		}
		List<Tuple<Teenager>> result = Graph.pairing(teens, this.getHostCountry(), this.getGuestCountry(), this.currentPlatform.getHistory().getTeenagers());
		for (Affectation couple : this.couples) {
			//Pour chaque couple non verrouillés
			if (!couple.isLocked()) {
				//On parcourt tous les résultats
				int i = 0;
				Teenager guest = null;
				while (guest == null && i < result.size()) {
					guest = result.get(i).get(couple.getHost());
					i++;
				}
				if (guest != null && guest.isGhost()) {
					guest = null;
				}
				couple.setGuest(guest);
			}
		}
	}
	
	/** The affectations getter.
	 * @return the affectations
	 */
	public List<Affectation> getAffectations() {
		return this.couples;
	}

	/** The hostCountry getter.
	 * @return the hostCountry
	 */
	public CountryName getHostCountry() {
		return this.countries.getFirst();
	}

	/** The guestCountry getter.
	 * @return the guestCountry
	 */
	public CountryName getGuestCountry() {
		return this.countries.getSecond();
	}
	
	/**
	 * This method add an affectation
	 * @param host, a {@code Teenager} representing the host.
	 * @param guest, a {@code Teenager} representing the guest.
	 * @throws SameTeenagerException if host and guest are equals.
	 */
	public void addAffectations(Teenager host, Teenager guest) throws SameTeenagerException {
		this.couples.add(new Affectation(host, guest, this));
	}
	
	/**
	 * This method if a teen is currently affected in any of couples
	 * @param teen, a {@code Teenager}.
	 * @return true or false, if teen is affected or not.
	 */
	public boolean isAffected(Teenager teen) {
		boolean affected = false;
		int i = 0;
		//look all couples, and stop when found an affectation
		while (!affected && i < this.couples.size()) {
			affected = this.couples.get(i).get(teen) != null;
			i++;
		}
		return affected;
	}
	
	/**
	 * This moethod return all teenager associated with teen.
	 * @param teen
	 * @return all teenager associated with teen, or null if no one found
	 */
	public List<Teenager> alreadyAffectedWith(Teenager teen) {
		List<Teenager> result = new ArrayList<Teenager>();
		for (Affectation affectation : couples) {
			Teenager currentAffected = affectation.get(teen);
			if (currentAffected != null) {
				result.add(currentAffected);
			}
		}
		return result.isEmpty() ? null : result;
	}

	@Override
	public int hashCode() {
		return Objects.hash(countries);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Exchange other = (Exchange) obj;
		return Objects.equals(countries, other.countries);
	}
}
