package voyages;

/**Thrown when the value of a criterion using the {@code HOST_FOOD} or {@code GUEST_FOOD} {@code CriterionName} is invalid.
 * @see CriterionName#HOST_FOOD
 * @see CriterionName#GUEST_FOOD
 */
public class FoodValueException extends Exception {
    
    /**
     * Constructs a {@code FoodValueException} with no detail message.
     */
    public FoodValueException() { super(); }

    /**
     * Constructs a {@code FoodValueException} with the specified detail message.
     * @param msg the detail message.
     */
    public FoodValueException(String msg) { super(msg); }
}