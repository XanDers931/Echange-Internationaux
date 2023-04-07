/**The enum defining the criterion for the Criterion class
 * @author Dagneaux Nicolas
 * @author Degraeve Paul
 * @author Martel Alexandre
 * @version 0.0.1, 04/07/23
 * @see Criterion
 */
public enum CriterionName {
    
    GUEST_ANIMAL_ALLERGY('B'),
    HOST_HAS_ANIMAL('B'),
    GUEST_FOOD('T'),
    HOST_FOOD('T'),
    HOBBIES('T'),
    GENDER('T'),
    PAIR_GENDER('T'),
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