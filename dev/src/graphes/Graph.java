package graphes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import fr.ulille.but.sae2_02.graphes.*;
import voyages.CountryName;
import voyages.Teenager;
import voyages.Tuple;

/**The {@code Graph} class, it represents a graph for the affectation problem.
 * @author Dagneaux Nicolas
 * @author Degraeve Paul
 * @author Martel Alexandre
 * @version 0.0.1, 05/12/23
 * @see Teenager
 */
public class Graph {
    
    /** This method return a map of the optimal affectation of the teenagers, based on their affinities.
     * @param teenagers the list of teenagers to be paired.
     * @param countryHost the {@code CountryName} of the host country.
     * @param countryGuest the {@code CountryName} of the guest country.
     * @return a {@link Map} of the affectation of the teenagers.
     */
    public static List<Tuple<Teenager>> pairing(List<Teenager> teenagers, CountryName countryHost, CountryName countryGuest) {
        List<Tuple<Teenager>> result = new ArrayList<>();
        List<Arete<Teenager>> calcul = generateGraphAndCalculation(teenagers, countryHost, countryGuest);
        for (Arete<Teenager> arete : calcul) {
            result.add(new Tuple<Teenager>(arete.getExtremite1(), arete.getExtremite2()));
        }
        return result;
    }

    /** This method return a list of the optimal affectation of the teenagers, based on their affinities.
     * It creates a graph using the {@link GrapheNonOrienteValue} class, and then uses the {@link CalculAffectation} class to calculate the affectation.
     * @param teenagers the list of teenagers to be paired.
     * @param countryHost the {@code CountryName} of the host country.
     * @param countryGuest the {@code CountryName} of the guest country.
     * @return a {@link List} of {@link Arete} of the affectation of the teenagers.
     */
    private static List<Arete<Teenager>> generateGraphAndCalculation(List<Teenager> teenagers, CountryName countryHost, CountryName countryGuest) {
        List<Teenager> host = new ArrayList<>();
        List<Teenager> guest = new ArrayList<>();
        for (Teenager teenager : teenagers) {
            if (teenager.getCountry().equals(countryHost)) {
                host.add(teenager);
            } else if (teenager.getCountry().equals(countryGuest)) {
                guest.add(teenager);
            }
        }
        GrapheNonOrienteValue<Teenager> graph = new GrapheNonOrienteValue<Teenager>();
        for (Teenager hostTeenager : host) {
            for (Teenager guestTeenager : guest) {
                graph.ajouterSommet(hostTeenager);
                graph.ajouterSommet(guestTeenager);
                graph.ajouterArete(hostTeenager, guestTeenager, AffectationUtil.weight(hostTeenager, guestTeenager));
            }
        }
        CalculAffectation<Teenager> calcul = new CalculAffectation<Teenager>(graph, host, guest);
        return calcul.calculerAffectation();
    }
}