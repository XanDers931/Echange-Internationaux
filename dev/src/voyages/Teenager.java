package voyages;

import java.util.Map;

/**The Teenager class, it represents a teenager with requirements.
 * It uses the Criterion class.
 * @author Dagneaux Nicolas
 * @author Degraeve Paul
 * @author Martel Alexandre
 * @version 0.0.1, 04/07/23
 * @see Criterion
 */
public class Teenager {
    
    // private Map<> requirements;
    /**
     * The id of the teenager
     */
    private int id;
    /**
     * The name of the teenager
     */
    private int name;

    /** This function determines if two teenagers are compatible
     * @param teen a teenager
     * @return true or false whether they are compatible or not
     */
    public boolean compatibleWithGuest(Teenager teen) {
        return true;
    }

    /**
     * This function deletes requirements that are invalid
     */
    public void purgeInvalidRequirement() {

    }

    public void addCriterion(CriterionName criterion, String value) {
        
    }
}