package voyages;

import java.time.LocalDate;
import java.util.Map;

/**The Teenager class, it represents a teenager with requirements.
 * It uses the Criterion class.
 * @author Dagneaux Nicolas
 * @author Degraeve Paul
 * @author Martel Alexandre
 * @see Criterion
 */
public class Teenager {
    
    // private Map<> requirements;

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

    public Teenager(String firstName, String lastName, LocalDate birthday, CountryName country) {
        Teenager.count = count++;
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

    }
}