package graphes;

import voyages.*;

/**
 * @author Dagneaux Nicolas
 * @author Degraeve Paul
 * @author Martel Alexandre
 * @version 0.0.2, 05/12/23
 */
public class AffectationUtil {
	/** Calcule le poids de l’arête entre host et visitor dans le graphe modèle.
	* Doit faire appel à la méthode compatibleWithGuest(Teenager) de Teenager.
	* Peut avoir d’autres paramètres si nécessaire.
	*/
	public static double weight (Teenager host, Teenager visitor) {
		double weight = host.compatibleWithGuest(visitor) ? 1 : -1;
		weight += affinityHobbies(host, visitor);
		return weight;
	}

	private static double affinityHobbies(Teenager host, Teenager visitor) {
		double affinity = 0;
		String[] hostHobbies = host.getRequirement().get(CriterionName.HOBBIES).getValue().split("" + CriterionName.MULTIPLE_VALUES_SEPARATOR);
		String[] visitorHobbies = visitor.getRequirement().get(CriterionName.HOBBIES).getValue().split("" + CriterionName.MULTIPLE_VALUES_SEPARATOR);
		for (String hostHobby : hostHobbies) {
			for (String visitorHobby : visitorHobbies) {
				if (hostHobby.equals(visitorHobby)) {
					affinity += 0.1;
				}
			}
		}
		return affinity;
	}
}