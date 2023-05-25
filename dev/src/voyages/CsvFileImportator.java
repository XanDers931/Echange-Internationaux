package voyages;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class CsvFileImportator {
	/**
     * The number of csv column expected
     */
	public static final int TOTAL_COLUMN_EXPECTED = 12;
	
	
	/**
     * The separator to use for csv parsing.
     */
	public static final String CSV_SEPARATOR = ";";
	
	
	/**
	 * The columns' name of Teenager attributes
	 */
	public static final String[] TEENAGERS_COLUMNS_NAMES = new String[]{"FORENAME", "NAME", "BIRTH_DATE", "COUNTRY"};
	
	
	/**
	 * The columns' name of Criterions, associated with criterion
	 */
	public static final String[] CRITERIONS_COLUMNS_NAMES = CriterionName.getCriterionNames();
	
	
    /**
     * Construct the csv header row;
     * @return the csv header row as {@code String}.
     */
	private static String getRowHeader() {
		String result = String.join(CSV_SEPARATOR, TEENAGERS_COLUMNS_NAMES);
		result = String.join(CSV_SEPARATOR, result, String.join(CSV_SEPARATOR, CRITERIONS_COLUMNS_NAMES));
		return result;
	}
	
	
    /**
     * Split a csv row using the separator static attribute
     * @param rowContent a {@code String} representing a csv row.
     * @return {@code String[]} representing the splitted row.
     */
	private static String[] splitCsvRow(String rowContent) {
		//add "NULL (0x0) character at the end to manage empty column
    	rowContent += "\0";
    	String[] rowSplittedContent = rowContent.split(CSV_SEPARATOR);
    	//delete null character
    	rowSplittedContent[rowSplittedContent.length - 1] = rowSplittedContent[rowSplittedContent.length - 1].replace("\0", "");
    	return rowSplittedContent;
	}
	
	
    /**
     * Generate a HashMap with columns' name as key, index as value.
     * @param headerRow a {@code String} representing the csv header row.
     * @return a Teenager instantiated object.
     */
	private static HashMap<String,Integer> getCsvStructure(String headerRow) throws CsvRowInvalidStructureException {
		String[] splittedHeaderRow = splitCsvRow(headerRow);
		if (splittedHeaderRow.length != TOTAL_COLUMN_EXPECTED) {
			throw new CsvRowInvalidStructureException("Expected " + TOTAL_COLUMN_EXPECTED + " columns but was : " + splittedHeaderRow.length + "\nExpected structure : " + getRowHeader());
		}
		//initialization with default value : -1
		HashMap<String,Integer> csvStructure = new HashMap<String,Integer>();
		for (int i = 0; i < TEENAGERS_COLUMNS_NAMES.length; i++) {
			csvStructure.put(TEENAGERS_COLUMNS_NAMES[i], -1);
		}
		for (int i = 0; i < CRITERIONS_COLUMNS_NAMES.length; i++) {
			csvStructure.put(CRITERIONS_COLUMNS_NAMES[i], -1);
		}
		
		//getting columns' number
		for (int i = 0; i < splittedHeaderRow.length; i++) {
			if (csvStructure.containsKey(splittedHeaderRow[i])) {
				if (csvStructure.get(splittedHeaderRow[i]) == -1) {
					csvStructure.put(splittedHeaderRow[i], i);
				} else {
					throw new CsvRowInvalidStructureException(splittedHeaderRow[i] + " (column " + (i + 1) + ") is already defined at " + (csvStructure.get(splittedHeaderRow[i]) + 1));
				}
			} else {
				throw new CsvRowInvalidStructureException(splittedHeaderRow[i] + " : invalid column name.\nExpected structure : " + getRowHeader());
			}
		}
		return csvStructure;
	}
	
    /**
     * Create a Teenager Object from a csv splitted row.
     * @param rowContent a {@code String} representing a csv row.
     * @param csvStructure a {@code HashMap<String,Integer>} representating the csv structure.
     * @return a Teenager instantiated object.
     */
	private static Teenager createTeenagerFromCsvRow(String rowContent, HashMap<String,Integer> csvStructure) throws CsvRowInvalidStructureException, TeenagerAttributeException, CriterionValueException {
		String[] currentSplittedRow = splitCsvRow(rowContent);
		
		//Test if the number of column is ok
    	if (currentSplittedRow.length != TOTAL_COLUMN_EXPECTED) {
			throw new CsvRowInvalidStructureException("Expected " + TOTAL_COLUMN_EXPECTED + " columns but was " + currentSplittedRow.length);
		}
    	
    	//Getting forename
    	int foreNameColumn = csvStructure.get(TEENAGERS_COLUMNS_NAMES[0]);
    	String foreName = currentSplittedRow[foreNameColumn];
    	if (foreName.isEmpty()) {
    		throw new TeenagerAttributeException("(Column " + (foreNameColumn + 1) + ") : Expected Forename but was empty.");
		}
    	
    	//Getting name
    	int nameColumn = csvStructure.get(TEENAGERS_COLUMNS_NAMES[1]);
    	String name = currentSplittedRow[nameColumn];
    	if (name.isEmpty()) {
    		throw new TeenagerAttributeException("(Column " + (nameColumn + 1) + ") : Expected Name but was empty.");
		}
    	
    	//Getting birthday
    	int birthdayColumn = csvStructure.get(TEENAGERS_COLUMNS_NAMES[2]);
    	String birthdayStr = currentSplittedRow[birthdayColumn];
    	LocalDate birthday;
    	if (birthdayStr.isEmpty()) {
    		throw new TeenagerAttributeException("(Column " + (birthdayColumn + 1) + ") : Expected Birthday but was empty.");
		}
    	try {
    		birthday = LocalDate.parse(birthdayStr);
		} catch (DateTimeParseException e) {
			throw new TeenagerAttributeException("(Column " + (birthdayColumn + 1) + ") : Wrond date format. Excepted YYYY-MM-DD but was '" + birthdayStr + "'.");
		} catch (Exception e) {
			throw e;
		}
    	
    	//Getting country
    	int countryColumn = csvStructure.get(TEENAGERS_COLUMNS_NAMES[3]);
    	String countryStr = currentSplittedRow[countryColumn];
    	CountryName country;
    	if (countryStr.isEmpty()) {
    		throw new TeenagerAttributeException("(Column " + (countryColumn + 1) + ") : Expected Country but was empty.");
		}
    	try {
    		country = CountryName.valueOf(countryStr);
		} catch (IllegalArgumentException e) {
			throw new TeenagerAttributeException("(Column " + (countryColumn + 1) + ") : '" + countryStr + "' is not supported as country");
		} catch (Exception e) {
			throw e;
		}
    	
    	//we can now create a Teenager
    	Teenager currentTennager = new Teenager(foreName, name, birthday, country);
    	
    	//Getting all criterion
    	for (CriterionName currentCriterionName : CriterionName.values()) {
    		int currentCriterionColumn = csvStructure.get(currentCriterionName.name());
    		String currentCiterionValue = currentSplittedRow[currentCriterionColumn];
			Criterion currentCriterion = new Criterion(currentCriterionName, currentCiterionValue);
			try {
				currentCriterion.isValid();
				currentTennager.addCriterion(currentCriterion);
			} catch (CriterionValueException e) {
				throw new CriterionValueException("(Column " + (currentCriterionColumn + 1) + ") : " + e.getMessage());
			} catch (Exception e) {
				throw e;
			}
		}
    	
    	return currentTennager;
	}
	
	/**
	 * Generate log file for inavlid rows.
	 * @param inputCsvPath a {@code String} representing the input file path
	 * @param logContent a {@code StringBuilder} representing the content of log file to write.
	 * @throws Exception 
	 */
	private static void generateLogFile(String inputCsvPath, String logContent) throws Exception {
		//Getting input file's folder
		File inputFile = new File(inputCsvPath);
		String folderParent = inputFile.getParentFile().getAbsolutePath();
		String logFileName = inputFile.getName() + "---" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM:dd:yyyy::H:m:s")).toString() + ".csv";
		String logFileFullPath = Paths.get(folderParent, logFileName).toString();
		
		//Save log file
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFileFullPath))) {
			writer.write(logContent, 0, logContent.length());
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	/**
     * Import teenagers from a .csv file
     * @param path a {@code String} representing the csv file's path.
     * @return a list of teenager as {@code ArrayList<Teenager>}.
	 * @throws CsvRowInvalidStructureException Exception 
     */
	public static ArrayList<Teenager> importFromCsv(String path) throws CsvRowInvalidStructureException, Exception {
		HashMap<String,Integer> csvStructure;
		ArrayList<Teenager> result = new ArrayList<Teenager>();
		StringBuilder logFileContent = new StringBuilder();
		//openning file
		try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
			//try to get csv structure
			try {
				csvStructure = getCsvStructure(reader.readLine());
			} catch (CsvRowInvalidStructureException e) {
				//cannot set csv structure
				throw e;
			} catch (Exception e) {
				throw e;
			}
			//lecture de toutes les lignes
			int currentRowNumber = 2;
			while (reader.ready()) {
				String currentRowContent = reader.readLine();
				try {
					result.add(createTeenagerFromCsvRow(currentRowContent, csvStructure));
				} catch (CsvRowInvalidStructureException e) {
					System.out.println("(Invalid CSV Structcture)(Row " + currentRowNumber + ")" + e.getMessage());
					logFileContent.append(currentRowContent + "\n");
				} catch (TeenagerAttributeException e) {
					System.out.println("(Invalid Teenager attribute)(Row " + currentRowNumber + ")" + e.getMessage());
					logFileContent.append(currentRowContent + "\n");
				} catch (CriterionValueException e) {
					System.out.println("(Invalid Criterion Value)(Row " + currentRowNumber + ")" + e.getMessage());
					logFileContent.append(currentRowContent + "\n");
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					currentRowNumber++;
				}
			}
			
		} catch (CsvRowInvalidStructureException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
		
		//generate log file
		if (!logFileContent.isEmpty()) {
			logFileContent.insert(0, getRowHeader() + "\n");
			generateLogFile(path, logFileContent.toString());
		}
		return result;
	}
	
}
