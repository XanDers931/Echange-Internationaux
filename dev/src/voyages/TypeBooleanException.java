package voyages;

/**Thrown when the value of a criterion using the {@code GUEST_ANIMAL_ALLERGY} or {@code HOST_HAS_ANIMAL} {@code CriterionName} is invalid.
 * @see CriterionName#GUEST_ANIMAL_ALLERGY
 * @see CriterionName#HOST_HAS_ANIMAL
 */
public class TypeBooleanException extends Exception {
    
    /**
     * Constructs a {@code TypeBooleanException} with no detail message.
     */
    public TypeBooleanException() { super(); }

    /**
     * Constructs a {@code TypeBooleanException} with the specified detail message.
     * @param msg the detail message.
     */
    public TypeBooleanException(String msg) { super(msg); }
}