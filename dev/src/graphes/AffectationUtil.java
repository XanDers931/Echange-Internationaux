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
		double weight = host.compatibleWithGuest(visitor) ? DEFAULT_WEIGHT : Double.MAX_VALUE;
		if (weight == DEFAULT_WEIGHT) {
			weight += historyWeight(host, visitor, history);
			weight += affinityHobbies(host, visitor);
			weight += ageWeight(host, visitor);
			weight += genderWeight(host, visitor);
		}
		return weight;
	}

	private static double genderWeight(Teenager host, Teenager visitor) {
		if (host.getRequirement().get(CriterionName.PAIR_GENDER).equals(visitor.getRequirement().get(CriterionName.GENDER)) && visitor.getRequirement().get(CriterionName.PAIR_GENDER).equals(host.getRequirement().get(CriterionName.GENDER))) {
			return -0.5;
		} else if (host.getRequirement().get(CriterionName.PAIR_GENDER).equals(visitor.getRequirement().get(CriterionName.GENDER)) || visitor.getRequirement().get(CriterionName.PAIR_GENDER).equals(host.getRequirement().get(CriterionName.GENDER))) {
			return -0.1;
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
				if (tuple.get(host).equals(visitor) && tuple.get(visitor).equals(host)) {
					return -1;
				}
			}
		} else if (host.getRequirement().get(CriterionName.HISTORY).getValue().equals("other") || visitor.getRequirement().get(CriterionName.HISTORY).getValue().equals("other")) {
			for (Tuple<Teenager> tuple : history) {
				if (tuple.get(host).equals(visitor) && tuple.get(visitor).equals(host)) {
					return Double.MAX_VALUE;
				}
			}
		}
		return -0.1;
	}

	/** This method return a double representing the affinity between the host and the visitor, based on their hobbies.
	 * @param host the {@code Teenager} host.
	 * @param visitor the {@code Teenager} visitor.
	 * @return a double that represents the affinity between the host and the visitor.
	 */
	private static double affinityHobbies(Teenager host, Teenager visitor) {
		double affinity = 0;
		String[] hostHobbies = host.getRequirement().get(CriterionName.HOBBIES).getValue().split("" + CriterionName.MULTIPLE_VALUES_SEPARATOR);
		String[] visitorHobbies = visitor.getRequirement().get(CriterionName.HOBBIES).getValue().split("" + CriterionName.MULTIPLE_VALUES_SEPARATOR);
		for (String hostHobby : hostHobbies) {
			for (String visitorHobby : visitorHobbies) {
				if (hostHobby.equals(visitorHobby)) {
					affinity -= 0.1;
				}
			}
		}
		return affinity;
	}
}