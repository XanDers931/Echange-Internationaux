package voyages;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**The Platform class. It represents the platform that contains all the teenagers.
 * @author Dagneaux Nicolas
 * @author Degraeve Paul
 * @author Martel Alexandre
 * @version 0.0.2, 05/12/23
 */
public class Platform implements Serializable {
    
    
    public static final String SAVE_PATH = "./dev/res/platform.bin";

	/**
     * The list of teenagers, grouped by country.
     */
    private HashMap<CountryName,ArrayList<Teenager>> teenagers;
    
    /**
     * Map of exchanges. Key is a tuple of country, and an Exchange as Object.
     */
    private Map<Tuple<CountryName>, Exchange> exchanges;
    
    /**
     * Csv file importator.
     */
    private transient CsvFileImportator importator;
    
    /**
     * Platform constructor
     */
    public Platform() {
    	this.teenagers = new HashMap<CountryName,ArrayList<Teenager>>();
    	this.exchanges = new HashMap<Tuple<CountryName>, Exchange>();
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
    
    /**
	 * @return the exchanges
	 */
	public Map<Tuple<CountryName>, Exchange> getExchanges() {
		return exchanges;
	}

	public void addExchange(CountryName host, CountryName guest) throws SameCountryException {
    	Tuple<CountryName> toAdd = new Tuple<CountryName>();
    	this.exchanges.put(new Tuple<CountryName>(host, guest), new Exchange(host, guest));
    }
    
    /** Writes the object to the specified output stream.
     * @param oos, the output stream.
     * @throws IOException if an I/O error occurs while writing to the stream.
     */
    private void writeObject(java.io.ObjectOutputStream oos) throws IOException {
        oos.writeObject(this.teenagers);
        oos.writeObject(this.exchanges);
    }
    
    /** Reads the object from the specified input stream.
     * @param ois the input stream.
     * @throws IOException if an I/O error occurs while reading from the stream.
     * @throws ClassNotFoundException if the class of the serialized object cannot be found.
     */
    private void readObject(java.io.ObjectInputStream ois) throws IOException, ClassNotFoundException {
        this.teenagers = (HashMap<CountryName,ArrayList<Teenager>>) ois.readObject();
        this.exchanges = (Map<Tuple<CountryName>, Exchange>) ois.readObject();
        this.importator = new CsvFileImportator(); 
    }
    
    public void save() {
    	try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(Platform.SAVE_PATH))){
			oos.writeObject(this);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static void main(String[] args) {
		Platform p = new Platform();
		p.importTeenagerFromCsv(new File("./dev/res/adosAleatoires.csv"), false);
		try {
			p.addExchange(CountryName.GERMANY, CountryName.ITALY);
		} catch (SameCountryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		p.save();
	}
}