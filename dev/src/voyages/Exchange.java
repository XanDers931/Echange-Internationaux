package voyages;

import java.io.Serializable;
import java.util.Objects;

import graphes.Graph;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

public class Exchange implements Serializable {
	
	/**
	 * A {@code Tuple} of {@code CountryName}.
	 */
	private final Tuple<CountryName> countries;
	
	/**
	 * A {@code List<Affectation>} of couples.
	 */
	private List<Affectation> couples;
	
	/**
	 * A {@code ObservableListBase<Teenager>} of non affected teen.
	 */
	private ObservableList<Teenager> nonAffectedTeens;
	
	
	/**
	 * Exchange constructor
	 * @param hostCountry, a {@code CountryName} representing the host country.
	 * @param guestCountry, a {@code CountryName} representing the guest country.
	 * @throws SameCountryException if hostCountry and guestCountry are equals
	 */
	public Exchange(CountryName hostCountry, CountryName guestCountry) throws SameCountryException {
		if (hostCountry == guestCountry) {
			throw new SameCountryException("Il est impossible de créer un échange entre un même pays");
		}
		this.countries = new Tuple<CountryName>(hostCountry, guestCountry);
		this.couples = new ArrayList<Affectation>();
		this.nonAffectedTeens = FXCollections.observableArrayList();
	}
	
	/**
	 * This method initalize the list of non affected teens.
	 * @param nonAffectedTeens the list of non affected teens.
	 */
	public void initNonAffectedTeens(Collection<? extends Teenager> nonAffectedTeens) {
		this.nonAffectedTeens.addAll(nonAffectedTeens);
	}
	
	/**
	 * This method generate an optimal affectation
	 * @param p, a {@code Platform} to get teenagers
	 */
	public void setOptimalAffectation(Platform p) throws SameTeenagerException {
		ArrayList<Teenager> teens = new ArrayList<Teenager>();
		for (Affectation couple : this.couples) {
			if (!couple.isLocked()) {
				teens.add(couple.getHost());
			}
		}
		for (Teenager guest : p.getTeenagersByCountry(this.getGuestCountry())) {
			boolean isLocked = false;
			int i = 0;
			while (isLocked & i < this.couples.size()) {
				if (this.couples.get(i).getGuest() != null & guest != null) {
					if (this.couples.get(i).getGuest().equals(guest)) {
						isLocked = this.couples.get(i).isLocked();
					}
				}
				i++;
			}
			if (!isLocked) {
				teens.add(guest);
			}
		}
		List<Tuple<Teenager>> result = Graph.pairing(teens, this.getHostCountry(), this.getGuestCountry(), p.getHistory().getTeenagers());
		for (Affectation couple : this.couples) {
			boolean found = false;
			int i = 0;
			while (!found && i < result.size()) {
				if (couple.getHost().equals(result.get(i).getFirst())) {
					found = true;
				} else {
					i++;
				}
			}
			if (found) {
				couple.setGuest(result.get(i).getSecond());
			}
		}
	}
	
	/**
	 * @return the affectations
	 */
	public List<Affectation> getAffectations() {
		return this.couples;
	}

	/**
	 * @return the hostCountry
	 */
	public CountryName getHostCountry() {
		return this.countries.getFirst();
	}

	/**
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
		if (guest != null && this.nonAffectedTeens.contains(guest)) {
			this.nonAffectedTeens.remove(guest);
		}
	}



	/**
	 * @return the nonAffectedTeens
	 */
	public ObservableList<Teenager> getNonAffectedTeens() {
		return nonAffectedTeens;
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
