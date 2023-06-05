package voyages;

import java.io.BufferedWriter;
import java.io.FileWriter;
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
    private static final String HEADER = "nom hote, prénom hote, pays hote, nom invité, prénom invité, pays invité";


    /**Create and fill a csv file with the association from Graph.pairing() in the directory res
     * @param teen
     * @param countryHost
     * @param countryGuest
     */
    public static void Exportator(List<Tuple<Teenager>> teen, CountryName countryHost, CountryName countryGuest, String chemin){        
          
        try(BufferedWriter fileOutput = new BufferedWriter(new FileWriter(chemin))){
            //Addition of the header
            fileOutput.append(HEADER);
            fileOutput.append(SEPARATOR);
            for (Tuple<Teenager> tuple : teen) {
                //Addition assoication by assocition into the file
                fileOutput.append(tuple.getFirst().getLastName());
                fileOutput.append(DELIMITER);
                fileOutput.append(tuple.getFirst().getFirstName());
                fileOutput.append(DELIMITER);
                fileOutput.append(tuple.getFirst().getCountry().toString());
                fileOutput.append(DELIMITER);
                fileOutput.append(tuple.getFirst().getLastName());
                fileOutput.append(DELIMITER);
                fileOutput.append(tuple.getFirst().getFirstName());
                fileOutput.append(DELIMITER);
                fileOutput.append(tuple.getFirst().getCountry().toString());
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
