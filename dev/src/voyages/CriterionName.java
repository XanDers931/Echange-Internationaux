package voyages;

/**The enum defining the criterion for the Criterion class.
 * @author Dagneaux Nicolas
 * @author Degraeve Paul
 * @author Martel Alexandre
 * @version 0.0.2, 05/05/23
 * @see Criterion
 */
public enum CriterionName {
	
    
    /**
     * The GUEST_ANIMAL_ALLERGY constant, it's a boolean type.
     */
    GUEST_ANIMAL_ALLERGY('B', false),
    /**
     * The HOST_HAS_ANIMAL constant, it's a boolean type.
     */
    HOST_HAS_ANIMAL('B', false),
    /**
     * The GUEST_FOOD constant, it's a text type.
     */
    GUEST_FOOD('T', true),
    /**
     * The HOST_FOOD constant, it's a text type.
     */
    HOST_FOOD('T', true),
    /**
     * The HOBBIES constant, it's a text type.
     */
    HOBBIES('T', true),
    /**
     * The GENDER constant, it's a text type.
     */
    GENDER('T', false),
    /**
     * The PAIR_GENDER constant, it's a text type.
     */
    PAIR_GENDER('T', true),
    /**
     * The HISTORY constant, it's a text type.
     */
    HISTORY('T', true);

    /**
     *The constant defining the type of this enum constant. Either 'B' for boolean or 'T' for text.
     */
    private final char TYPE;
    
    /**
     *The constant defining if the value can be null
     */
    private final boolean CAN_BE_NULL;

    /**
     * The constant defining the separator for fields with multiple values.
     */
    public static final char MULTIPLE_VALUES_SEPARATOR = ',';

    /**The constructor of the CriterionName enum.
     * @param type The character defining the type of the enum constant. Either 'B' for boolean or 'T' for text.
     */
    CriterionName(char type, boolean canBeNull) {
        this.TYPE = type;
        this.CAN_BE_NULL = canBeNull;
    }

    /**Return the type of this enum constant.
     * @return the character defining the type of the enum constant. Either 'B' for boolean or 'T' for text.
     */
    public char getType() {
        return this.TYPE;
    }
    
    /**Return if the value of that criterion can be null.
     * @return if the value of that criterion can be null.
     */
    public boolean canBeNull() {
        return this.CAN_BE_NULL;
    }
    
    
}