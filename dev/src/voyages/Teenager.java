package voyages;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**this teenager class, it represents a teenager with requirements.
 * It uses the Criterion class.
 * @author Dagneaux Nicolas
 * @author Degraeve Paul
 * @author Martel Alexandre
 * @see Criterion
 * @see CriterionName
 */
public class Teenager {
    
    /**
     * The list of requirements of a teenager
     */
    private HashMap<CriterionName, Criterion> requirements;
    /**
     * A counter for auto incrementing the id
     */
    private static int count = 0;
    /**
     * The id of a teenager
     */
    private int id;
    /**
     * The first name of a teenager
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
        this.requirements = new HashMap<CriterionName, Criterion>();
    }

    /**
     * @return the id of this teenager
     */
    public int getId() {
        return id;
    }

    /**
     * @return the first name of this teenager
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @return the last name of this teenager
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @return the date of birth of this teenager
     */
    public LocalDate getBirthday() {
        return birthday;
    }

    /**
     * @return the country of this teenager
     */
    public CountryName getcountry() {
        return country;
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
    
    /**
     * @return a HashMap containing the list of requirements for this teenager
     */
    public HashMap<CriterionName, Criterion> getRequirement() {
        return this.requirements;
    }
}