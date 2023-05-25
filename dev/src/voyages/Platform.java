package voyages;

import java.util.ArrayList;

/**The Platform class. It represents the platform that contains all the teenagers.
 * @author Dagneaux Nicolas
 * @author Degraeve Paul
 * @author Martel Alexandre
 * @version 0.0.2, 05/12/23
 */
public class Platform {
    
    /**
     * The list of teenagers.
     */
    public ArrayList<Teenager> teenagers;
    
    /**
     * Platform constructor
     */
    Platform() {
    	this.teenagers = new ArrayList<Teenager>();
    }
    
    /**
     * Import teenagers from a .csv file
     * @param path a {@code String} representing the csv file's path.
     */
    public void importTeenagerFromCsv(String path) {
    	try {
    		this.teenagers.addAll(CsvFileImportator.importFromCsv(path));
		} catch (CsvRowInvalidStructureException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public static void main(String[] args) {
    	Platform p1 = new Platform();
    	p1.importTeenagerFromCsv("./dev/src/voyages/adosAleatoires-with-errors.csv");
    	System.out.println(p1.teenagers.size());
	}
}