package voyages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
     * Platform constructor
     */
    public Platform() {
    	this.teenagers = new HashMap<CountryName,ArrayList<Teenager>>();
    }
    
    /**
     * Import teenagers from a .csv file
     * @param path a {@code String} representing the csv file's path.
     */
    public void importTeenagerFromCsv(String path) {
    	try {
    		HashMap<CountryName,ArrayList<Teenager>> importedResult = CsvFileImportator.importFromCsv(path);
    		//for each country, add the list
    		for (Map.Entry<CountryName,ArrayList<Teenager>> list : importedResult.entrySet()) {
				if (!this.teenagers.containsKey(list.getKey())) {
					this.teenagers.put(list.getKey(), new ArrayList<Teenager>());
				}
				this.teenagers.get(list.getKey()).addAll(list.getValue());
			}
    		this.teenagers = CsvFileImportator.importFromCsv(path);
    		//ajouter une boucle pour ajouter la liste à chaque pays
		} catch (CsvRowInvalidStructureException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
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
    	Platform p1 = new Platform();
    	p1.importTeenagerFromCsv("./dev/res/adosAleatoires-with-errors.csv");
    	System.out.println(p1.getTeenagersByCountry(CountryName.FRANCE).size());
    	System.out.println(p1.getTeenagersByCountry(CountryName.GERMANY).size());
    	System.out.println(p1.getTeenagersByCountry(CountryName.ITALY).size());
    	System.out.println(p1.getTeenagersByCountry(CountryName.SPAIN).size());
	}
}