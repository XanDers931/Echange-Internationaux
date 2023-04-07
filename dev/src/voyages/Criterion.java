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
     * The value of the criterion
     */
    private String value;
    /**
     * The name of cretarion
     * @see CritarionName
     */
    private CriterionName label;

    /** Determine if this criterion is valid
     * @return true or false whether it is valid or not
     */
    public boolean isValid() {
        return true;
    }
}