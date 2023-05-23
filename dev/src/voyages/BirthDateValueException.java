package voyages;

/**Thrown when the value of a the birthdate is invalid.
 * @see CriterionName#HOST_FOOD
 * @see CriterionName#GUEST_FOOD
 */
public class BirthDateValueException extends Exception {
    
    /**
     * Constructs a {@code FoodValueException} with no detail message.
     */
    public BirthDateValueException() { super(); }

    /**
     * Constructs a {@code FoodValueException} with the specified detail message.
     * @param msg the detail message.
     */
    public BirthDateValueException(String msg) { super(msg); }
}