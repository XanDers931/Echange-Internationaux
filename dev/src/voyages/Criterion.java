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
    

    /**
	 * @param value
	 * @param label
	 */
	public Criterion(CriterionName label, String value) {
		this.label = label;
		this.value = value;
	}
	
	
	/**
	 * @return the label
	 */
	public CriterionName getLabel() {
		return label;
	}


	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

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
	}

	public void booleanIsValid() throws TypeBooleanException {
		if (!(this.getValue().equals("yes") || this.getValue().equals("no"))) {
			throw new TypeBooleanException("Expected 'yes' or 'no' but was " + this.getValue());
		}
	}

	public void foodValueIsValid() throws FoodValueException {
		if (!(this.getValue() == null || this.getValue().equals("vegetarian") || this.getValue().equals("nonuts"))) {
			throw new FoodValueException("Expected 'vegetarian', 'nonuts' or null but was " + this.getValue());
		}
	}

	public void historyValueIsValid() throws HistoryValueException {
		if (!(this.getValue() == null || this.getValue().equals("same") || this.getValue().equals("other"))) {
			throw new HistoryValueException("Expected 'same', 'other' or null but was " + this.getValue());
		}
	}
}