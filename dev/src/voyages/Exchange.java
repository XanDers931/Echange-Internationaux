package voyages;

import java.io.Serializable;
import java.util.Objects;
import java.util.List;
import java.util.ArrayList;

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
		this.couples.add(new Affectation(host, guest));
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
