package voyages;

import java.io.Serializable;
import java.util.Map;
import java.util.HashMap;

public class Exchange implements Serializable {
	
	/**
	 * 
	 */
	private final CountryName hostCountry;
	private final CountryName guestCountry;
	private Map<Tuple<Teenager>, Boolean> affectations;
	
	public Exchange(CountryName host, CountryName guest) throws SameCountryException {
		if (host == guest) {
			throw new SameCountryException("Il est impossible de créer un échange entre un même pays");
		}
		this.hostCountry = host;
		this.guestCountry = guest;
		this.affectations = new HashMap<Tuple<Teenager>, Boolean>();
	}
	
	

	/**
	 * @return the hostCountry
	 */
	public CountryName getHostCountry() {
		return hostCountry;
	}

	/**
	 * @return the guestCountry
	 */
	public CountryName getGuestCountry() {
		return guestCountry;
	}

	/**
	 * @return the affectations
	 */
	public Map<Tuple<Teenager>, Boolean> getAffectations() {
		return affectations;
	}
	
	public String toString() {
		return this.hostCountry + " <-- " + this.guestCountry;
	}
	
	

}
