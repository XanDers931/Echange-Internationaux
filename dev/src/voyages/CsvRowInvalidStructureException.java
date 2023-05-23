package voyages;

/**Thrown when a csv row does not respect the structure like the number of column.
 */
public class CsvRowInvalidStructureException extends Exception {
    
    /**
     * Constructs a {@code CsvRowInvalidStructureException} with no detail message.
     */
    public CsvRowInvalidStructureException() { super(); }

    /**
     * Constructs a {@code FoodValueException} with the specified detail message.
     * @param msg the detail message.
     */
    public CsvRowInvalidStructureException(String msg) { super(msg); }
}