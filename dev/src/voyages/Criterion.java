package voyages;

/**The Criterion class, it represents a criterion for a teenager.
 * It uses the CriterionName enum.
 * @author Dagneaux Nicolas
 * @author Degraeve Paul
 * @author Martel Alexandre
 * @version 0.0.1, 04/07/23
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


	/** Determine if this criterion is valid
     * @return true or false whether it is valid or not
     */
    public boolean isValid() {
    	boolean result;
        switch (this.label.getType()) {
        //booléen
		case 'B':
			return this.value.equals("yes") || this.value.equals("no");
			
		//texte
		case 'T' :
			switch (this.label) {
			case GUEST_FOOD:
				if (this.value == null) {
					result = true;
					break;
				}
				result = this.value.equals("vegetarian") || this.value.equals("nonuts");
				break;
			
			case HOST_FOOD:
				if (this.value == null) {
					result = true;
					break;
				}
				result = this.value.equals("vegetarian") || this.value.equals("nonuts");
				break;
			
			case GENDER:
				if (this.value == null) {
					result = false;
					break;
				}
				result = this.value.equals("male") || this.value.equals("female") || this.value.equals("other");
				break;
				
			case PAIR_GENDER:
				if (this.value == null) {
					result = false;
					break;
				}
				result = this.value.equals("male") || this.value.equals("female") || this.value.equals("other");
				break;
			
			case HISTORY:
				if (this.value == null) {
					result = true;
					break;
				}
				result = this.value.equals("same") || this.value.equals("other");
				break;
			default:
				result = true;
				break;
			}
			break;			
		default:
			result = false;
			break;
        }
        return result;
    }
}