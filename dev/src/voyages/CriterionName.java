package voyages;

/**The enum defining the criterion for the Criterion class.
 * @author Dagneaux Nicolas
 * @author Degraeve Paul
 * @author Martel Alexandre
 * @version 0.0.1, 04/07/23
 * @see Criterion
 */
public enum CriterionName {
    
    /**
     * The GUEST_ANIMAL_ALLERGY constant, it's a boolean type
     */
    GUEST_ANIMAL_ALLERGY('B'),
    /**
     * The HOST_HAS_ANIMAL constant, it's a boolean type
     */
    HOST_HAS_ANIMAL('B'),
    /**
     * The GUEST_FOOD constant, it's a text type
     */
    GUEST_FOOD('T'),
    /**
     * The HOST_FOOD constant, it's a text type
     */
    HOST_FOOD('T'),
    /**
     * The HOBBIES constant, it's a text type
     */
    HOBBIES('T'),
    /**
     * The GENDER constant, it's a text type
     */
    GENDER('T'),
    /**
     * The PAIR_GENDER constant, it's a text type
     */
    PAIR_GENDER('T'),
    /**
     * The HISTORY constant, it's a text type
     */
    HISTORY('T');

    /**
     *The constant defining the type of this enum constant
     */
    private final char TYPE;

    /**The constructor of the CriterionName enum
     * @param type The character defining the type of the enum constant
     */
    CriterionName(char type) {
        this.TYPE = type;
    }

    /**Getter for the criterion type
     * @return the type of this enum constant
     */
    public char getType() {
        return this.TYPE;
    }
}