package voyages;

/**The Criterion class, it represents a criterion for a teenager.
 * It uses the CriterionName enum.
 * @author Dagneaux Nicolas
 * @author Degraeve Paul
 * @author Martel Alexandre
 * @version 0.0.3, 05/10/23
 * @see Teenager
 */
public class Criterion {
    
    /**
     * The name of cretarion
     * @see CritarionName
     */
    private CriterionName label;
    
    /**
     * The value of the criterion
     */
    private String value;
    

    /** The constructor for the Criterion class.
	 * @param label The name of the criterion, it uses the {@code CriterionName} enum.
	 * @param value The value of the criterion.
	 * @see CriterionName
	 */
	public Criterion(CriterionName label, String value) {
		this.label = label;
		this.value = value;
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

	/** This function check if this criterion is valid.
	 * @return {@code true} if this criterion is valid, {@code false} otherwise.
	 *//*
    public boolean isValid() {
		if (this.getLabel().getType() == 'B') {
			try {
				booleanIsValid();
				return true;
			} catch (TypeBooleanException e) {
				e.printStackTrace();
			}
		} else {
			if (this.getLabel().equals(CriterionName.HOST_FOOD) || this.getLabel().equals(CriterionName.GUEST_FOOD)) {
				try {
					foodValueIsValid();
					return true;
				} catch (FoodValueException e) {
					e.printStackTrace();
				}
			} else if (this.getLabel().equals(CriterionName.HISTORY)) {
				try {
					historyValueIsValid();
					return true;
				} catch (HistoryValueException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}*/
    
    /** This function check if this criterion is valid.
	 * @return {@code true} if this criterion is valid, {@code false} otherwise.
	 */
    public boolean isValid() throws TypeBooleanException, FoodValueException, HistoryValueException {
    	if (this.getLabel().getType() == 'B') {
			return this.booleanIsValid();
		} else if (this.getLabel().equals(CriterionName.HOST_FOOD) || this.getLabel().equals(CriterionName.GUEST_FOOD)) {
			return this.foodValueIsValid();
		} else if (this.getLabel().equals(CriterionName.HISTORY)) {
			return this.historyValueIsValid();
		}
    	return false;
	}

	/** If this criterion is of type boolean, the function will check if the value is either "yes" or "no", or will raise an exception otherwise.
	 * @throws TypeBooleanException if the value of this criterion is not "yes" or "no".
	 */
	public boolean booleanIsValid() throws TypeBooleanException {
		if (!(this.getValue().equals("yes") || this.getValue().equals("no"))) {
			throw new TypeBooleanException("Expected 'yes' or 'no' but was " + this.getValue());
		}
		return true;
	}

	/** If this criterion is of type food, the function will check if the value is either "vegetarian" or "nonuts", or will raise an exception otherwise.
	 * @throws FoodValueException if the value of this criterion is not "vegetarian" or "nonuts".
	 */
	public boolean foodValueIsValid() throws FoodValueException {
		if (!(this.getValue() == null || this.getValue().equals("vegetarian") || this.getValue().equals("nonuts"))) {
			throw new FoodValueException("Expected 'vegetarian', 'nonuts' or null but was " + this.getValue());
		}
		return true;
	}

	/** If this criterion is of type history, the function will check if the value is either "same", "other" or null, or will raise an exception otherwise.
	 * @throws HistoryValueException if the value of this criterion is not "same", "other" or null.
	 */
	public boolean historyValueIsValid() throws HistoryValueException {
		if (!(this.getValue() == null || this.getValue().equals("same") || this.getValue().equals("other"))) {
			throw new HistoryValueException("Expected 'same', 'other' or null but was " + this.getValue());
		}
		return true;
	}
}