package graphes;

import java.util.List;

import voyages.*;

/**The {@code AffectationUtil} class, it represents a utility class for the affectation problem.
 * @author Dagneaux Nicolas
 * @author Degraeve Paul
 * @author Martel Alexandre
 * @version 0.0.2, 05/12/23
 * @see Teenager
 */
public class AffectationUtil {

	static final int DEFAULT_WEIGHT = 1;
	
	/** This method return the weight of the edge between the host and the visitor, based on their affinity.
	 * @param host the {@code Teenager} host.
	 * @param visitor the {@code Teenager} visitor.
	 * @return a double that represents the weight of the edge.
	 */
	public static double weight (Teenager host, Teenager visitor, List<Tuple<Teenager>> history) {
		double weight = DEFAULT_WEIGHT;
		if (host.compatibleWithGuestAnimalAllergy(visitor)) {
			weight -= 0.5;
		} else {
			weight += 0.5;
		}
		if (host.compatibleWithGuestDiet(visitor)) {
			weight -= 0.5;
		} else {
			weight += 0.5;
		}
		weight += historyWeight(host, visitor, history);
		weight += host.totalCompatibleHobbies(visitor) * -0.1;
		weight += ageWeight(host, visitor);
		weight += genderWeight(host, visitor);
		return weight;
	}

	private static double genderWeight(Teenager host, Teenager visitor) {
		try {
			if (host.getRequirement().get(CriterionName.PAIR_GENDER).equals(visitor.getRequirement().get(CriterionName.GENDER)) && visitor.getRequirement().get(CriterionName.PAIR_GENDER).equals(host.getRequirement().get(CriterionName.GENDER))) {
				return -0.5;
			} else if (host.getRequirement().get(CriterionName.PAIR_GENDER).equals(visitor.getRequirement().get(CriterionName.GENDER)) || visitor.getRequirement().get(CriterionName.PAIR_GENDER).equals(host.getRequirement().get(CriterionName.GENDER))) {
				return -0.1;
			}
		} catch (NullPointerException e) {
			return 0;
		}
		return 0;
	}

	private static double ageWeight(Teenager host, Teenager visitor) {
		if (host.getBirthday().until(visitor.getBirthday(), java.time.temporal.ChronoUnit.MONTHS) <= 18) {
			return -0.1;
		}
		return 0;
	}

	private static double historyWeight(Teenager host, Teenager visitor, List<Tuple<Teenager>> history) {
		if (host.getRequirement().get(CriterionName.HISTORY).getValue().equals("same") && visitor.getRequirement().get(CriterionName.HISTORY).getValue().equals("same")) {
			for (Tuple<Teenager> tuple : history) {
				try {
					if (tuple.get(host).equals(visitor)) return -1;
				} catch (NullPointerException e1) {
					try {
						if (tuple.get(visitor).equals(host)) return -1;
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
					if (tuple.get(host).equals(visitor)) return -0.1;
				} catch (NullPointerException e1) {
					try {
						if (tuple.get(visitor).equals(host)) return -0.1;
					} catch (NullPointerException e2) {
						continue;
					}
				}
			}
		}
		return 0;
	}
}