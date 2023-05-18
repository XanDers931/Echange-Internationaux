package voyages;

/**Thrown when the value of a criterion using the {@code HISTORY} {@code CriterionName} is invalid.
 * @see CriterionName#HISTORY
 */
public class HistoryValueException extends Exception {
    
    /**
     * Constructs a {@code HistoryValueException} with no detail message.
     */
    public HistoryValueException() { super(); }

    /**
     * Constructs a {@code HistoryValueException} with the specified detail message.
     * @param msg the detail message.
     */
    public HistoryValueException(String msg) { super(msg); }
}