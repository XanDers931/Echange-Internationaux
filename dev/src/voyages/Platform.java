package voyages;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.beans.property.SimpleStringProperty;

/**The Platform class. It represents the platform that contains all the teenagers.
 * @author Dagneaux Nicolas
 * @author Degraeve Paul
 * @author Martel Alexandre
 * @version 0.0.2, 05/12/23
 */
public class Platform {
    
    /**
     * The list of teenagers, grouped by country.
     */
    public HashMap<CountryName,ArrayList<Teenager>> teenagers;
    
    /**
     * Csv file importator.
     */
    private final CsvFileImportator importator;
    
    /**
     * Platform constructor
     */
    public Platform() {
    	this.teenagers = new HashMap<CountryName,ArrayList<Teenager>>();
    	this.importator = new CsvFileImportator();
    }
    
    /**
     * Import teenagers from a .csv file
     * @param path a {@code String} representing the csv file's path.
     * @param generateLogFile a {@code boolean} to know if the controller has to generate log files.
     * @return log content.
     */
    public String importTeenagerFromCsv(File fileToImport, boolean generateLogFile) {
    	try {
    		HashMap<CountryName,ArrayList<Teenager>> importedResult = this.importator.importFromCsv(fileToImport, generateLogFile);
    		//for each country, add the list
    		for (Map.Entry<CountryName,ArrayList<Teenager>> list : importedResult.entrySet()) {
				if (!this.teenagers.containsKey(list.getKey())) {
					this.teenagers.put(list.getKey(), new ArrayList<Teenager>());
				}
				this.teenagers.get(list.getKey()).addAll(list.getValue());
			}
		} catch (CsvRowInvalidStructureException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return this.importator.getLogContent();
    }
    
    /**
     * Gett all tenagers from a specified country
     * @param country a {@code CountryName}.
     * @return a list of teenager grouped of a country.
     */
    public ArrayList<Teenager> getTeenagersByCountry(CountryName country) {
    	return this.teenagers.get(country);
    }
    
    public static void main(String[] args) {
        //Application.launch(args);
	System.out.println(System.getProperty("user.dir"));
}
}