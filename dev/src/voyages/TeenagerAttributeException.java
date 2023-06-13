package voyages;

/**Thrown when the value of a Teenager's attribute is invalid.
 */
public class TeenagerAttributeException extends Exception {
    
    /**
     * Constructs a {@code FoodValueException} with no detail message.
     */
    public TeenagerAttributeException() { super(); }

    /**
     * Constructs a {@code FoodValueException} with the specified detail message.
     * @param msg the detail message.
     */
    public TeenagerAttributeException(String msg) { super(msg); }
}