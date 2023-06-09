package voyages;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;


/** 
 * This class is describe an affectation between two {@code Teenager}.
 * @author Dagneaux Nicolas
 * @author Degraeve Paul
 * @author Martel Alexandre
 */
public class Affectation implements Serializable {
	
	/**
	 * A {@code Tuple} of {@code Teenager}.
	 */
	private Tuple<Teenager> couple;
	
	/**
	 * A {@boolean} value representing if the current affectation is locked or not.
	 */
	private boolean locked;
	
	/**
	 * A {@code List<String>} of compatibles requirements.
	 */
	private List<String> goodRequirements;
	
	/**
	 * A {@code List<String>} of incompatibles requirements.
	 */
	private List<String> badRequirements;
	
	
	/**
	 * Affectation constructor
	 * @param host, a {@code Teenager} representing the host.
	 * @param guest, a {@code Teenager} representing the guest.
	 * @throws SameTeenagerException
	 */
	public Affectation(Teenager host, Teenager guest) throws SameTeenagerException {
		if (host.equals(guest)) {
			throw new SameTeenagerException("Une affectation ne peut pas avoir lieu  avec la même personne.");
		}
		this.couple = new Tuple<Teenager>(host, guest);
		this.locked = false;
		this.goodRequirements = new ArrayList<String>();
		this.badRequirements = new ArrayList<String>();
	}
	
	/**
	 * The host getter.
	 * @return the host {@code Teenager}.
	 */
	public Teenager getHost() {
		return this.couple.getFirst();
	}
	
	/**
	 * The guest getter.
	 * @return the guest {@code Teenager}.
	 */
	public Teenager getGuest() {
		return this.couple.getSecond();
	}

	/**
	 * The guest setter.
	 * @param a {@code Teenager} representing the guest to set.
	 * @throws SameTeenagerException if guest equals host.
	 */
	public void setGuest(Teenager guest) throws SameTeenagerException {
		if (this.getHost().equals(guest)) {
			throw new SameTeenagerException("Une affectation ne peut pas avoir lieu  avec la même personne : " + guest);
		}
		this.couple.setSecond(guest);
		this.setRequirements();
	}

	/**
	 * The {@code boolean} locked setter.
	 * @param locked the locked to set
	 */
	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	/**
	 * The {@code boolean} locked getter.
	 * @return if the affectation is locked or not.
	 */
	public boolean isLocked() {
		return this.locked;
	}
	
	
	
	/**
	 * @return the compatibles requirements
	 */
	public List<String> getGoodRequirements() {
		return goodRequirements;
	}

	/**
	 * @return the incompatibles requirements
	 */
	public List<String> getBadRequirements() {
		return badRequirements;
	}

	/**
	 * This method set good and bad requirements
	 */
	private void setRequirements() {
		if (this.getGuest() == null | this.getHost() == null) {
			return;
		}
		//Animal allergy
		if (this.getHost().compatibleWithGuestAnimalAllergy(this.getGuest())) {
			this.goodRequirements.add("Allergies aux animaux");
		} else {
			this.badRequirements.add("Allergies aux animaux");
		}
		//Diet
		if (this.getHost().compatibleWithGuestDiet(this.getGuest())) {
			this.goodRequirements.add("Régime alimentaire");
		} else {
			this.badRequirements.add("Régime alimentaire");
		}
		//hobbies
		int totalCommonHobbies = this.getHost().totalCompatibleHobbies(this.getGuest());
		if (totalCommonHobbies > 0) {
			this.goodRequirements.add(totalCommonHobbies + "hobbie(s) en commun");
		} else {
			this.badRequirements.add("Aucun hobbie en commun");
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(couple, locked);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Affectation other = (Affectation) obj;
		return Objects.equals(couple, other.couple) && locked == other.locked;
	}
	
	

}
