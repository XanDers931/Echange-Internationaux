package voyages;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
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
	 * The {@code Exchange} of that affectation.
	 */
	private Exchange currentExchange;
	
	/**
	 * Affectation constructor
	 * @param host, a {@code Teenager} representing the host.
	 * @param guest, a {@code Teenager} representing the guest.
	 * @param e, the {@code Exchange} of that affectation.
	 * @throws SameTeenagerException
	 */
	public Affectation(Teenager host, Teenager guest, Exchange e) throws SameTeenagerException {
		if (host.equals(guest)) {
			throw new SameTeenagerException("Une affectation ne peut pas avoir lieu  avec la même personne.");
		}
		this.couple = new Tuple<Teenager>(host, guest);
		this.locked = false;
		this.goodRequirements = new ArrayList<String>();
		this.badRequirements = new ArrayList<String>();
		this.currentExchange = e;
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
		//s'il y avait quelqu'un avant, on l'ajoute aux non affectés
		if (this.getGuest() != null) {
			this.currentExchange.getNonAffectedTeens().add(this.getGuest());
		}
		//autrement, si le nouveau guest n'est pas null, on l'enlève des non affectés
		if (guest != null) {
			this.currentExchange.getNonAffectedTeens().remove(guest);
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
		this.goodRequirements.clear();
		this.badRequirements.clear();
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
			this.goodRequirements.add(totalCommonHobbies + " hobbie(s) en commun");
		} else {
			this.badRequirements.add("Aucun hobbie en commun");
		}
	}
	
	/**
	 * This function build the host's description (age, genre, criterions...).
	 * @return A {@code String} representing the the host's description.
	 */
	public String getHostDetail() {
		Teenager host = this.couple.getFirst();
		StringBuilder result = new StringBuilder();
		int age = Period.between(host.getBirthday(), LocalDate.now()).getYears();
		result.append(age + " ans");
		//genre
		if (!host.getRequirement().containsKey(CriterionName.GENDER) || host.getRequirement().get(CriterionName.GENDER).getValue().isEmpty()) {
			result.append("\nGenre non communiquée");
		} else {
			result.append("\n" + host.getRequirement().get(CriterionName.GENDER).getValue());
		}
		//paire genre
		if (!host.getRequirement().containsKey(CriterionName.PAIR_GENDER) || host.getRequirement().get(CriterionName.PAIR_GENDER).getValue().isEmpty()) {
			result.append("\nAucun souhait sur le genre de l'invité");
		} else {
			result.append("\nSouhaite que le genre de l'invité soit : " + host.getRequirement().get(CriterionName.PAIR_GENDER).getValue());
		}
		//history
		if (!host.getRequirement().containsKey(CriterionName.HISTORY) || host.getRequirement().get(CriterionName.HISTORY).getValue().isEmpty()) {
			result.append("\nAucun souhait selon l'historique");
		} else {
			result.append("\nSouhait selon l'historique : " + host.getRequirement().get(CriterionName.HISTORY).getValue());
		}
		//animal
		if (host.getRequirement().containsKey(CriterionName.HOST_HAS_ANIMAL) && host.getRequirement().get(CriterionName.HOST_HAS_ANIMAL).getValue().equals("yes")) {
			result.append("\nPossède un animal");
		} else {
			result.append("\nNe possède pas d'animal");
		}
		//food
		if (!host.getRequirement().containsKey(CriterionName.HOST_FOOD) || host.getRequirement().get(CriterionName.HOST_FOOD).getValue().isEmpty()) {
			result.append("\nN'est compaible avec aucun régime alimentaire");
		} else {
			result.append("\nEst compatible avec le(s) régime(s) suivant(s) : " + host.getRequirement().get(CriterionName.HOST_FOOD).getValue());
		}
		//hobbies
		if (!host.getRequirement().containsKey(CriterionName.HOBBIES) || host.getRequirement().get(CriterionName.HOBBIES).getValue().isEmpty()) {
			result.append("\nAucun hobbie connu.");
		} else {
			result.append("\nHobbie(s) : " + host.getRequirement().get(CriterionName.HOBBIES).getValue());
		}
		return result.toString();
	}
	
	/**
	 * This function build the guest's description (age, genre, criterions...).
	 * @return A {@code String} representing the the guest's description.
	 */
	public String getGuestDetail() {
		Teenager guest = this.couple.getSecond();
		if (guest == null) {
			return "";
		}
		StringBuilder result = new StringBuilder();
		int age = Period.between(guest.getBirthday(), LocalDate.now()).getYears();
		result.append(age + " ans");
		//genre
		if (!guest.getRequirement().containsKey(CriterionName.GENDER) || guest.getRequirement().get(CriterionName.GENDER).getValue().isEmpty()) {
			result.append("\nGenre non communiquée");
		} else {
			result.append("\n" + guest.getRequirement().get(CriterionName.GENDER).getValue());
		}
		//paire genre
		if (!guest.getRequirement().containsKey(CriterionName.PAIR_GENDER) || guest.getRequirement().get(CriterionName.PAIR_GENDER).getValue().isEmpty()) {
			result.append("\nAucun souhait sur le genre de l'hôte");
		} else {
			result.append("\nSouhaite que le genre de l'hôte soit : " + guest.getRequirement().get(CriterionName.PAIR_GENDER).getValue());
		}
		//history
		if (!guest.getRequirement().containsKey(CriterionName.HISTORY) || guest.getRequirement().get(CriterionName.HISTORY).getValue().isEmpty()) {
			result.append("\nAucun souhait selon l'historique");
		} else {
			result.append("\nSouhait selon l'historique : " + guest.getRequirement().get(CriterionName.HISTORY).getValue());
		}
		//animal
		if (guest.getRequirement().containsKey(CriterionName.GUEST_ANIMAL_ALLERGY) && guest.getRequirement().get(CriterionName.GUEST_ANIMAL_ALLERGY).getValue().equals("yes")) {
			result.append("\nAllergique aux animaux");
		} else {
			result.append("\nN'est pas allergique aux animaux");
		}
		//food
		if (!guest.getRequirement().containsKey(CriterionName.GUEST_FOOD) || guest.getRequirement().get(CriterionName.GUEST_FOOD).getValue().isEmpty()) {
			result.append("\nN'a pas de régime alimentaire particulier");
		} else {
			result.append("\nRégime(s) alimentaire(s) particulier(s) : " + guest.getRequirement().get(CriterionName.GUEST_FOOD).getValue());
		}
		//hobbies
		if (!guest.getRequirement().containsKey(CriterionName.HOBBIES) || guest.getRequirement().get(CriterionName.HOBBIES).getValue().isEmpty()) {
			result.append("\nAucun hobbie connu.");
		} else {
			result.append("\nHobbie(s) : " + guest.getRequirement().get(CriterionName.HOBBIES).getValue());
		}
		return result.toString();
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
