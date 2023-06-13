package voyages;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

/**The {@code Criterion} class, it represents a criterion for a teenager.
 * It uses the {@link CriterionName} enum.
 * @author Dagneaux Nicolas
 * @author Degraeve Paul
 * @author Martel Alexandre
 * @version 0.0.3, 05/10/23
 * @see Teenager
 */
public class Criterion implements Serializable {
    
    /**
     * The name of the critarion
     * @see CritarionName
     */
    private CriterionName label;
    
    /**
     * The value of the criterion
     */
    private String value;
    

    /** The constructor for the {@code Criterion} class.
	 * @param label The name of the criterion, it uses the {@code CriterionName} enum.
	 * @param value The value of the criterion.
	 * @see CriterionName
	 */
	public Criterion(CriterionName label, String value) {
		this.label = label;
		//value cannot be null
		this.value = value == null ? "" : value;
	}
	
	
	/** Return the {@code CriterionName} of this criterion.
	 * @return the {@code CriterionName} label that represents this criterion.
	 */
	public CriterionName getLabel() {
		return label;
	}


	/** Return the value of this criterion.
	 * @return a string that represents the value of this criterion.
	 */
	public String getValue() {
		return value;
	}
	
	/** Return the values of this criterion as a list.
	 * @return a {@code ArrayList<String>} with all values.
	 */
	public ArrayList<String> getValuesAsList() {
		return new ArrayList<>(Arrays.asList(this.value.split("" + CriterionName.MULTIPLE_VALUES_SEPARATOR)));
	}

    
    /** This function check if this criterion is valid.
	 * @return {@code true} if this criterion is valid, false otherwise.
	 * @throws CriterionValueException if the value is not valid.
	 */
    public boolean isValid() throws CriterionValueException {
    	boolean isValid = Pattern.matches(this.label.getRegex(), this.value);
    	if (!isValid) {
			throw new CriterionValueException(this.label.getExceptionMessage() + (this.value.isEmpty() ? "empty" : this.value));
		}
    	return true;
	}
}