package voyages;

/**Thrown when the value of a criterion using the {@code HISTORY} {@code CriterionName} is invalid.
 * @see CriterionName#HISTORY
 */
public class CriterionValueException extends Exception {
    
    /**
     * Constructs a {@code HistoryValueException} with no detail message.
     */
    public CriterionValueException() { super(); }

    /**
     * Constructs a {@code HistoryValueException} with the specified detail message.
     * @param msg the detail message.
     */
    public CriterionValueException(String msg) { super(msg); }
}