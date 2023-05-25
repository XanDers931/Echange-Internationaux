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
    GUEST_ANIMAL_ALLERGY('B', "^yes$|^no$", "Expected 'yes' or 'no' but was : "),
    /**
     * The HOST_HAS_ANIMAL constant, it's a boolean type.
     */
    HOST_HAS_ANIMAL('B', "^yes$|^no$", "Expected 'yes' or 'no' but was : "),
    /**
     * The GUEST_FOOD constant, it's a text type.
     */
    GUEST_FOOD('T', "(^vegetarian$|^nonuts$|$)|((^vegetarian|^nonuts),{1}(vegetarian$|nonuts$))", "Expected one or more of 'vegetarian', 'nonuts' or empty but was : "),
    /**
     * The HOST_FOOD constant, it's a text type.
     */
    HOST_FOOD('T', "(^vegetarian$|^nonuts$|$)|((^vegetarian|^nonuts),{1}(vegetarian$|nonuts$))", "Expected one or more of 'vegetarian', 'nonuts' or empty but was : "),
    /**
     * The HOBBIES constant, it's a text type.
     */
    HOBBIES('T'),
    /**
     * The GENDER constant, it's a text type.
     */
    GENDER('T', "^male$|^female$|^other$", "Expected 'male', 'female', or 'other' but was : "),
    /**
     * The PAIR_GENDER constant, it's a text type.
     */
    PAIR_GENDER('T', "^male$|^female$|^other$|$", "Expected 'male', 'female', 'other' or empty but was : "),
    /**
     * The HISTORY constant, it's a text type.
     */
    HISTORY('T', "^same$|^other$|$", "Expected 'same', 'other' or empty but was : ");

    /**
     *The constant defining the type of this enum constant. Either 'B' for boolean or 'T' for text.
     */
    private final char TYPE;
    
    /**
     *The constant defining the regular expression used to know if value is valid.
     */
    private final String REGEX;
    
    /**
     *The constant defining the message displayed by the exception.
     */
    private final String EXCEPTION_MESSAGE;
 
    

    /**
     * The constant defining the separator for fields with multiple values.
     */
    public static final char MULTIPLE_VALUES_SEPARATOR = ',';

    /**The constructor of the CriterionName enum.
     * @param type The character defining the type of the enum constant. Either 'B' for boolean or 'T' for text.
     * @param regex The String representation of regular expression used to know if value is valid.
     * @param exceptionMessage The message displayed by the exception.
     */
    CriterionName(char type, String regex, String exceptionMessage) {
        this.TYPE = type;
        this.REGEX = regex;
        this.EXCEPTION_MESSAGE = "[" + this.name() + "] " + exceptionMessage;
    }
    
    /**The constructor of the CriterionName enum.
     * @param type The character defining the type of the enum constant. Either 'B' for boolean or 'T' for text.
     */
    CriterionName(char type) {
    	this(type, ".*", "");
    }

    /**Return the type of this enum constant.
     * @return the character defining the type of the enum constant. Either 'B' for boolean or 'T' for text.
     */
    public char getType() {
        return this.TYPE;
    }
    
    /**Return the regex String representation.
     * @return the regex String representation.
     */
    public String getRegex() {
        return this.REGEX;
    }
    
    /**Return the message for exception.
     * @return the message for exception.
     */
    public String getExceptionMessage() {
        return this.EXCEPTION_MESSAGE;
    }
    
    
    /**Return all the criterion names in a {@code String[]}.
     * @return all the criterion names in a {@code String[]}.
     */
    public static String[] getCriterionNames() {
    	CriterionName[] allCriterion = CriterionName.values();
    	String[] result = new String[allCriterion.length];
    	for (int i = 0; i < allCriterion.length; i++) {
			result[i] = allCriterion[i].name();
		}
    	return result;
    }
    
    
}