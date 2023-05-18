package graphes;

import voyages.*;

/**The {@code AffectationUtil} class, it represents a utility class for the affectation problem.
 * @author Dagneaux Nicolas
 * @author Degraeve Paul
 * @author Martel Alexandre
 * @version 0.0.2, 05/12/23
 * @see Teenager
 */
public class AffectationUtil {
	
	/** This method return the weight of the edge between the host and the visitor, based on their affinity.
	 * @param host the {@code Teenager} host.
	 * @param visitor the {@code Teenager} visitor.
	 * @return a double that represents the weight of the edge.
	 */
	public static double weight (Teenager host, Teenager visitor) {
		double weight = host.compatibleWithGuest(visitor) ? 1 : Double.MAX_VALUE;
		if (weight == 1) {
			weight += affinityHobbies(host, visitor);
		}
		return weight;
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