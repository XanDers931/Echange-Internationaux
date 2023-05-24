package voyages;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

/**The Platform class. It represents the platform that contains all the teenagers.
 * @author Dagneaux Nicolas
 * @author Degraeve Paul
 * @author Martel Alexandre
 * @version 0.0.2, 05/12/23
 */
public class Platform {
	
	/**
     * The number of csv column expected
     */
	public static final int TOTAL_COLUMN_EXPECTED = 12;
	
	/**
     * The separator to use for csv parsing.
     */
	public static final String CSV_SEPARATOR = ";";
	
	/**
     * The Map of CriterionName and Column Index
     */
	private Map<CriterionName, Integer> columnsMap;
    
    /**
     * The list of teenagers.
     */
    public ArrayList<Teenager> teenagers;

    /**The constructor for the {@code Platform} class.
     */
    public Platform() {
        this.teenagers = new ArrayList<Teenager>();
        this.columnsMap = new HashMap<CriterionName, Integer>();
        this.columnsMap.put(CriterionName.HOBBIES, 4);
        this.columnsMap.put(CriterionName.GUEST_ANIMAL_ALLERGY, 5);
        this.columnsMap.put(CriterionName.HOST_HAS_ANIMAL, 6);
        this.columnsMap.put(CriterionName.GUEST_FOOD, 7);
        this.columnsMap.put(CriterionName.HOST_FOOD, 8);
        this.columnsMap.put(CriterionName.GENDER, 9);
        this.columnsMap.put(CriterionName.PAIR_GENDER, 10);
        this.columnsMap.put(CriterionName.HISTORY, 11);
    }
    
    
    /**
     * Create a Teenager Object from a csv splitted row.
     * @param rowContent a {@code String} representing a csv row.
     * @return a Teenager instantiated object.
     */
    private Teenager createTeenagerFromCsvRow(String rowContent) throws CriterionValueException, CountryValueException, CsvRowInvalidStructureException, BirthDateValueException {
    	//add "NULL (0x0) character at the end to manage empty column
    	rowContent += "\0";
    	String[] rowSplittedContent = rowContent.split(Platform.CSV_SEPARATOR);
    	//delete null character
    	rowSplittedContent[rowSplittedContent.length - 1] = rowSplittedContent[rowSplittedContent.length - 1].replace("\0", ""); //rowSplittedContent[rowSplittedContent.length - 1].substring(0, rowSplittedContent[rowSplittedContent.length - 1].length() - 1);
    	//Test if the number of column is ok
    	if (rowSplittedContent.length != Platform.TOTAL_COLUMN_EXPECTED) {
			throw new CsvRowInvalidStructureException("Expected " + Platform.TOTAL_COLUMN_EXPECTED + " columns but was " + rowSplittedContent.length);
		}
    	
    	//Getting forename
    	String foreName = rowSplittedContent[0];
    	if (foreName.isEmpty()) {
    		throw new CsvRowInvalidStructureException("Expected Forename but was empty");
		}
    	
    	//Getting name
    	String name = rowSplittedContent[1];
    	if (name.isEmpty()) {
    		throw new CsvRowInvalidStructureException("Expected Name but was empty");
		}
    	
    	//Getting country
    	CountryName currentCountry;
    	if (rowSplittedContent[2].isEmpty()) {
    		throw new CsvRowInvalidStructureException("Expected Country name but was empty");
		}
    	try {
    		currentCountry = CountryName.valueOf(rowSplittedContent[2]);
		} catch (Exception e) {
			throw new CountryValueException(rowSplittedContent[2] + " is not supported as a country");
		}
    	
    	//Getting birthDate
    	if (rowSplittedContent[3].isEmpty()) {
    		throw new CsvRowInvalidStructureException("Expected Birthdate but was empty");
		}
    	LocalDate currentBirthDate;
    	try {
    		currentBirthDate = LocalDate.parse(rowSplittedContent[3]);
		} catch (Exception e) {
			throw new BirthDateValueException("Wrong date format. Excepted YYYY-MM-DD but was " + rowSplittedContent[3]);
		}
    	
    	//we can now create a Teenager
    	Teenager currentTennager = new Teenager(foreName, name, currentBirthDate, currentCountry);
    	
    	//Getting all criterion
    	for (Entry<CriterionName, Integer> set : this.columnsMap.entrySet()) {
			String currentCiterionValue = rowSplittedContent[set.getValue()];
			Criterion currentCriterion = new Criterion(set.getKey(), currentCiterionValue);
			if (currentCriterion.isValid()) {
				currentTennager.addCriterion(currentCriterion);
			}
		}
    	return currentTennager;
    }
    
    /**
     * Import teenagers from a .csv file
     * @param path a {@code String} representing the csv file's path.
     * @param skipFirstRow a {@code boolean} to know if it is needed to skip first row.
     * @return true if the import work, false if not.
     */
    public boolean importFromCsv(String path, boolean skipFirstRow) {
    	int i = 1;
    	//opening file
    	try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
    		if (skipFirstRow) {
				reader.readLine();
				i++;
			}
    		while (reader.ready()) {
    			String currentRow = reader.readLine();
    			try {
					this.teenagers.add(this.createTeenagerFromCsvRow(currentRow));
				} catch (CriterionValueException | CountryValueException | CsvRowInvalidStructureException | BirthDateValueException e) {
					System.out.println(e.getClass().getName() + " : " + e.getMessage() + " --> row " + i);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					i++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return true;
    }
    
    public static void main(String[] args) {
    	Platform p1 = new Platform();
    	p1.importFromCsv("./dev/src/voyages/adosAleatoires.csv", true);
    	System.out.println(p1.teenagers.size());
	}
}