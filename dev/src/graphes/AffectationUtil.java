package graphes;

import java.util.List;

import voyages.*;

/**The {@code AffectationUtil} class, it represents a utility class for the affectation problem.
 * @author Dagneaux Nicolas
 * @author Degraeve Paul
 * @author Martel Alexandre
 * @version 0.0.5, 06/13/23
 * @see Teenager
 */
public class AffectationUtil {

	/** The default weight of an edge. */
	private static final int DEFAULT_WEIGHT = 1;

	/** The coefficient of the history affinity. */
	private static int coefficientHistory = 1;

	/** The coefficient of the age affinity. */
	private static int coefficientAge = 1;

	/** The coefficient of the gender affinity */
	private static int coefficientGender = 1;

	/** The coefficient of the hobbies affinity. */
	private static int coefficientHobbies = 1;

	/** The coefficient of the animal allergy affinity. */
	private static int coefficientAnimalAllergy = 5;

	/** The coefficient of the diet affinity. */
	private static int coefficientDiet = 5;

	/**
	 * Setter for the coefficient of the history affinity.
	 * @param coefficientHistory the new coefficient.
	 */
	public static void setCoefficientHistory(int coefficientHistory) {
		AffectationUtil.coefficientHistory = coefficientHistory;
	}

	/**
	 * Getter for the coefficient of the history affinity.
	 * @return the coefficient.
	 */
	public static int getCoefficientHistory() {
		return coefficientHistory;
	}

	/**
	 * Setter for the coefficient of the age affinity.
	 * @param coefficientAge the new coefficient.
	 */
	public static void setCoefficientAge(int coefficientAge) {
		AffectationUtil.coefficientAge = coefficientAge;
	}

	/**
	 * Getter for the coefficient of the age affinity.
	 * @return the coefficient.
	 */
	public static int getCoefficientAge() {
		return coefficientAge;
	}

	/**
	 * Setter for the coefficient of the gender affinity.
	 * @param coefficientGender the new coefficient.
	 */
	public static void setCoefficientGender(int coefficientGender) {
		AffectationUtil.coefficientGender = coefficientGender;
	}

	/**
	 * Getter for the coefficient of the gender affinity.
	 * @return
	 */
	public static int getCoefficientGender() {
		return coefficientGender;
	}

	/**
	 * Setter for the coefficient of the hobbies affinity.
	 * @param coefficientHobbies the new coefficient.
	 */
	public static void setCoefficientHobbies(int coefficientHobbies) {
		AffectationUtil.coefficientHobbies = coefficientHobbies;
	}

	/**
	 * Getter for the coefficient of the hobbies affinity.
	 * @return the coefficient.
	 */
	public static int getCoefficientHobbies() {
		return coefficientHobbies;
	}

	/**
	 * Setter for the coefficient of the animal allergy affinity.
	 * @param coefficientAnimalAllergy the new coefficient.
	 */
	public static void setCoefficientAnimalAllergy(int coefficientAnimalAllergy) {
		AffectationUtil.coefficientAnimalAllergy = coefficientAnimalAllergy;
	}

	/**
	 * Getter for the coefficient of the animal allergy affinity.
	 * @return the coefficient.
	 */
	public static int getCoefficientAnimalAllergy() {
		return coefficientAnimalAllergy;
	}

	/**
	 * Setter for the coefficient of the diet affinity.
	 * @param coefficientDiet the new coefficient.
	 */
	public static void setCoefficientDiet(int coefficientDiet) {
		AffectationUtil.coefficientDiet = coefficientDiet;
	}

	/**
	 * Getter for the coefficient of the diet affinity.
	 * @return the coefficient.
	 */
	public static int getCoefficientDiet() {
		return coefficientDiet;
	}
	
	/** This method return the weight of the edge between the host and the visitor, based on their affinity.
	 * @param host the {@code Teenager} host.
	 * @param visitor the {@code Teenager} visitor.
	 * @return a double that represents the weight of the edge.
	 */
	public static double weight (Teenager host, Teenager visitor, List<Tuple<Teenager>> history) {
		double weight = DEFAULT_WEIGHT;
		if (host.compatibleWithGuestAnimalAllergy(visitor)) {
			weight -= 0.1 * coefficientAnimalAllergy;
		} else {
			weight += 0.1 * coefficientAnimalAllergy;
		}
		if (host.compatibleWithGuestDiet(visitor)) {
			weight -= 0.1 * coefficientDiet;
		} else {
			weight += 0.1 * coefficientDiet;
		}
		weight += historyWeight(host, visitor, history);
		weight += host.totalCompatibleHobbies(visitor) * -0.1 * coefficientHobbies;
		weight += ageWeight(host, visitor) * coefficientAge;
		weight += genderWeight(host, visitor) * coefficientGender;
		return weight;
	}

	/** This method return a modifier for the weight of the edge between the host and the visitor, based on their gender requirements.
	 * @param host the {@code Teenager} host.
	 * @param visitor the {@code Teenager} visitor.
	 * @return a double that represents the modifier of the weight of the edge.
	 */
	private static double genderWeight(Teenager host, Teenager visitor) {
		try {
			if (host.getRequirement().get(CriterionName.PAIR_GENDER).equals(visitor.getRequirement().get(CriterionName.GENDER)) && visitor.getRequirement().get(CriterionName.PAIR_GENDER).equals(host.getRequirement().get(CriterionName.GENDER))) {
				return -0.1;
			} else if (host.getRequirement().get(CriterionName.PAIR_GENDER).equals(visitor.getRequirement().get(CriterionName.GENDER)) || visitor.getRequirement().get(CriterionName.PAIR_GENDER).equals(host.getRequirement().get(CriterionName.GENDER))) {
				return -0.1;
			}
		} catch (NullPointerException e) {
			return 0;
		}
		return 0;
	}

	/** This method return a modifier for the weight of the edge between the host and the visitor, based on their age difference.
	 * @param host the {@code Teenager} host.
	 * @param visitor the {@code Teenager} visitor.
	 * @return a double that represents the modifier of the weight of the edge.
	 */
	private static double ageWeight(Teenager host, Teenager visitor) {
		if (host.getBirthday().until(visitor.getBirthday(), java.time.temporal.ChronoUnit.MONTHS) <= 18) {
			return -0.1;
		}
		return 0;
	}

	/** This method return a modifier for the weight of the edge between the host and the visitor, based on their history requirements.
	 * @param host the {@code Teenager} host.
	 * @param visitor the {@code Teenager} visitor.
	 * @param history the list of the previous affectations.
	 * @return a double that represents the modifier of the weight of the edge.
	 */
	private static double historyWeight(Teenager host, Teenager visitor, List<Tuple<Teenager>> history) {
		if (host.getRequirement().get(CriterionName.HISTORY).getValue().equals("same") && visitor.getRequirement().get(CriterionName.HISTORY).getValue().equals("same")) {
			for (Tuple<Teenager> tuple : history) {
				try {
					if (tuple.get(host).equals(visitor)) return -10;
				} catch (NullPointerException e1) {
					try {
						if (tuple.get(visitor).equals(host)) return -10;
					} catch (NullPointerException e2) {
						continue;
					}
				}
			}
		} else if (host.getRequirement().get(CriterionName.HISTORY).getValue().equals("other") || visitor.getRequirement().get(CriterionName.HISTORY).getValue().equals("other")) {
			for (Tuple<Teenager> tuple : history) {
				try {
					if (tuple.get(host).equals(visitor)) return Double.MAX_VALUE;
				} catch (NullPointerException e1) {
					try {
						if (tuple.get(visitor).equals(host)) return Double.MAX_VALUE;
					} catch (NullPointerException e2) {
						continue;
					}
				}
			}
		}
		else if (host.getRequirement().get(CriterionName.HISTORY).getValue().equals("same") || visitor.getRequirement().get(CriterionName.HISTORY).getValue().equals("same")) {
			for (Tuple<Teenager> tuple : history) {
				try {
					if (tuple.get(host).equals(visitor)) return -0.1 * coefficientHistory;
				} catch (NullPointerException e1) {
					try {
						if (tuple.get(visitor).equals(host)) return -0.1 * coefficientHistory;
					} catch (NullPointerException e2) {
						continue;
					}
				}
			}
		}
		return 0;
	}
}