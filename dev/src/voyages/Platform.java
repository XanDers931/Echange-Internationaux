package voyages;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDate;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import front.FXMLScene;

/**The Platform class. It represents the platform that contains all the teenagers.
 * @author Dagneaux Nicolas
 * @author Degraeve Paul
 * @author Martel Alexandre
 */
public class Platform implements Serializable {
    
    /**
     * The path to save the file.
     */
    public static String SAVE_PATH = System.getProperty("user.dir") + File.separator + "res" + File.separator + "platform.bin";

	/**
     * The list of teenagers, grouped by country.
     */
    private Map<CountryName, ArrayList<Teenager>> teenagers;
    
    /**
     * A list of exchanges.
     */
    public List<Exchange> exchanges;
    
    /**
     * The history containing the last affectations.
     */
    private History history;
    
    /**
     * Csv file importator.
     */
    private transient CsvFileImportator importator;
    
    /**
     * Platform constructor
     */
    public Platform() {
    	this.teenagers = new HashMap<CountryName,ArrayList<Teenager>>();
    	this.exchanges = new ArrayList<Exchange>();
    	this.importator = new CsvFileImportator();
    	this.history = new History(LocalDate.now());
    }
    
    /**
     * Import teenagers from a .csv file
     * @param fileToImport the file to import.
     * @param generateLogFile a {@code boolean} to know if the controller has to generate log files.
     * @return log content.
     */
    public String importTeenagerFromCsv(File fileToImport, boolean generateLogFile) {
    	try {
    		HashMap<CountryName,ArrayList<Teenager>> importedResult = this.importator.importFromCsv(fileToImport, generateLogFile);
    		this.teenagers = this.importator.importFromCsv(fileToImport, generateLogFile);
    		//for each country, sort teenagers
    		for (Map.Entry<CountryName,ArrayList<Teenager>> list : importedResult.entrySet()) {
				Collections.sort(this.teenagers.get(list.getKey()));
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
     * Get all represented countries in this platform.
     * @return all represented countries in this platform as an {@code List<CountryName>}.
     */
    public List<CountryName> getAllRepresentedCountry() {
    	ArrayList<CountryName> result = new ArrayList<CountryName>();
    	for (Entry<CountryName, ArrayList<Teenager>> entry : this.teenagers.entrySet()) {
    		result.add(entry.getKey());
		}
    	return result;
    }

	/**
	 * This method add an exchange to the platform.
	 * @param hostCountry a {@code CountryName} representing the host country.
	 * @param guestCountry a {@code CountryName} representing the guest country.
	 * @return A new {@code Exchange} if it was not already present, or unchanged Exchange if already here.
	 * @throws SameCountryException
     * @throws SameTeenagerException
	 */
	public Exchange addExchange(CountryName hostCountry, CountryName guestCountry) throws SameCountryException, SameTeenagerException {
		Exchange toAdd = new Exchange(hostCountry, guestCountry, this);
		if (!this.exchanges.contains(toAdd)) {
			this.exchanges.add(toAdd);
			//initialize all affectations
			for (Teenager hostTeenager : this.teenagers.get(hostCountry)) {
				this.exchanges.get(this.exchanges.indexOf(toAdd)).addAffectations(hostTeenager, null);
			}
		}
		return this.exchanges.get(this.exchanges.indexOf(toAdd));
    }
    
    /** The history getter.
	 * @return the history.
	 */
	public History getHistory() {
		return history;
	}

	/** Writes the object to the specified output stream.
     * @param oos, the output stream.
     * @throws IOException if an I/O error occurs while writing to the stream.
     */
    private void writeObject(java.io.ObjectOutputStream oos) throws IOException {
        oos.writeObject(this.teenagers);
        oos.writeObject(this.exchanges);
        oos.writeObject(this.history);
    }
    
    /** Reads the object from the specified input stream.
     * @param ois the input stream.
     * @throws IOException if an I/O error occurs while reading from the stream.
     * @throws ClassNotFoundException if the class of the serialized object cannot be found.
     */
    private void readObject(java.io.ObjectInputStream ois) throws IOException, ClassNotFoundException {
        this.teenagers = (Map<CountryName,ArrayList<Teenager>>) ois.readObject();
        this.exchanges = (List<Exchange>) ois.readObject();
        this.history = (History) ois.readObject();
        this.importator = new CsvFileImportator();
    }
    
    /**
     * Save the platform using the {@code writeObject} method.
     */
    public void save() {
    	try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(Platform.SAVE_PATH)))) {
			oos.writeObject(this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static void main(String[] args) {
		Platform p = new Platform();
		p.importTeenagerFromCsv(new File("./res/adosAleatoires.csv"), false);
		ArrayList<Tuple<Teenager>> x = new ArrayList<Tuple<Teenager>>();
    	x.add(new Tuple<Teenager>(p.getTeenagersByCountry(CountryName.FRANCE).get(0), p.getTeenagersByCountry(CountryName.ITALY).get(0)));
    	p.history = new History(x, null);
		p.save();
	}
}