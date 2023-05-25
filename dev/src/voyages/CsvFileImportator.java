package voyages;

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
	public static final String[] TEENAGERS_COLUMNS_NAMES = new String[]{"FORENAME", "NAME", "COUNTRY", "BIRTH_DATE"};
	
	
	/**
	 * The columns' name of Criterions
	 */
	public static final String[] CRITERIONS_COLUMNS_NAMES = new String[]{"GUEST_ANIMAL_ALLERGY", "HOST_HAS_ANIMAL", "GUEST_FOOD_CONSTRAINT", "HOST_FOOD", "HOBBIES", "GENDER", "PAIR_GENDER", "HISTORY"};
	
	
	private static String[] splitCsvRow(String rowContent) {
		//add "NULL (0x0) character at the end to manage empty column
    	rowContent += "\0";
    	String[] rowSplittedContent = rowContent.split(CsvFileImportator.CSV_SEPARATOR);
    	//delete null character
    	rowSplittedContent[rowSplittedContent.length - 1] = rowSplittedContent[rowSplittedContent.length - 1].replace("\0", "");
    	return rowSplittedContent;
	}
	
	private static HashMap<String,Integer> initColumnOrder(String[] currentSplittedRow) {
		//initialization with default value : -1
		HashMap<String,Integer> result = new HashMap<String,Integer>();
		for (int i = 0; i < CsvFileImportator.TEENAGERS_COLUMNS_NAMES.length; i++) {
			result.put(CsvFileImportator.TEENAGERS_COLUMNS_NAMES[i], -1);
		}
		
		for (int i = 0; i < CsvFileImportator.CRITERIONS_COLUMNS_NAMES.length; i++) {
			result.put(CsvFileImportator.CRITERIONS_COLUMNS_NAMES[i], -1);
		}
		
		
	}
	
}
