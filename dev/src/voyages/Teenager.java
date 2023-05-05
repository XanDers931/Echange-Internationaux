package voyages;

import java.time.LocalDate;
import java.util.Map;
import java.util.Map.Entry;

/**The Teenager class, it represents a teenager with requirements.
 * It uses the Criterion class.
 * @author Dagneaux Nicolas
 * @author Degraeve Paul
 * @author Martel Alexandre
 * @see Criterion
 * @see CriterionName
 */
public class Teenager {
    
    /**
     * The list of requirements of the teenager
     */
    private Map<CriterionName, Criterion> requirements;
    /**
     * A counter for auto incrementing the id
     */
    private static int count = 0;
    /**
     * The id of the teenager
     */
    private int id;
    /**
     * The first name of the teenager
     */
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private CountryName country;

    /** The constructor for the Teenager class
     * @param firstName
     * @param lastName
     * @param birthday
     * @param country
     */
    public Teenager(String firstName, String lastName, LocalDate birthday, CountryName country) {
        Teenager.count++;
        this.id = count;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.country = country;
    }

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
        Map<CriterionName, Criterion> requirementsCopy = Map.copyOf(requirements);
        for (Entry<CriterionName, Criterion> set : requirementsCopy.entrySet()) {
            if(!set.getValue().isValid()) {
                requirements.remove(set.getKey());
            }
        }
    }

    /**
     * This function add a criterion to the list of requirements for this teenager
     * @param criterion an enum constant of CriterionName
     * @param value the value associated with the criterion
     */
    public void addCriterion(CriterionName criterion, String value) {
        
    }
}