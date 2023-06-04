package voyages;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import graphes.Graph;

/**This class allows the export in csv format of teenager associations
 * @author Dagneaux Nicolas
 * @author Degraeve Paul
 * @author Martel Alexandre
 * @see Graph
 */
public class CsvFileExportator {
    /**
     *Use to delimit the space between two columns
     */
    private static final String DELIMITER = ",";
    /**
     *Use to skip a line after each import
     */
    private static final String SEPARATOR = "\n";
    /**
     *Create table columns
     */
    private static final String HEADER = "Etudiant Hôte,Etudiant acceuilli";


    /**Create and fill a csv file with the association from Graph.pairing() in the directory res
     * @param teen
     * @param countryHost
     * @param countryGuest
     */
    public static void Exportator(ArrayList<Teenager> teen, CountryName countryHost, CountryName countryGuest){        
        //Creation of the list that will contain the expected result
        List<Tuple<Teenager>> match = new ArrayList<Tuple<Teenager>>();
        match = Graph.pairing(teen, countryHost, countryGuest);
    
        try(BufferedWriter fileOutput = new BufferedWriter(new FileWriter("./dev/res/adosMatchGraphOutput.csv"))){
            //Addition of the header
            fileOutput.append(HEADER);
            fileOutput.append(SEPARATOR);
            for (Tuple<Teenager> tuple : match) {
                //Addition assoication by assocition into the file
                fileOutput.append(tuple.getFirst().toString());
                fileOutput.append(DELIMITER);
                fileOutput.append(tuple.getSecond().toString());
                fileOutput.append(SEPARATOR);
            }
            //make sure the file is closed
            fileOutput.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
