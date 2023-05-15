package graphes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.ulille.but.sae2_02.graphes.*;
import voyages.CountryName;
import voyages.Teenager;

/**
 * @author Dagneaux Nicolas
 * @author Degraeve Paul
 * @author Martel Alexandre
 * @version 0.0.1, 05/12/23
 */
public class Graph {
    
    public static Map<Teenager, Teenager> pairing(List<Teenager> teenagers, CountryName countryHost, CountryName countryGuest) {
        Map<Teenager, Teenager> result = new HashMap<>();
        List<Arete<Teenager>> calcul = generateGraphAndCalculation(teenagers, countryHost, countryGuest);
        for (Arete<Teenager> arete : calcul) {
            result.put(arete.getExtremite1(), arete.getExtremite2());
        }
        return result;
    }

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