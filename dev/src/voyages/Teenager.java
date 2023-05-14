package voyages;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**The teenager class, it represents a teenager with requirements.
 * It uses the Criterion class.
 * @author Dagneaux Nicolas
 * @author Degraeve Paul
 * @author Martel Alexandre
 * @version 0.0.5, 05/12/23
 * @see Criterion
 * @see CriterionName
 */
public class Teenager {
    
    /**
     * The list of requirements of the teenager
     */
    private HashMap<CriterionName, Criterion> requirements;
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
    /**
     * The last name of the teenager
     */
    private String lastName;
    /**
     * The date of birth of the teenager
     */
    private LocalDate birthday;
    /**
     * The country of the teenager
     */
    private CountryName country;

    /** The constructor for the Teenager class.
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
    public CountryName getCountry() {
        return country;
    }

    /**
     * @return a HashMap containing the list of requirements for this teenager
     */
    public HashMap<CriterionName, Criterion> getRequirement() {
        return this.requirements;
    }

    /** This function determines if two teenagers are compatible.
     * @param teen a teenager
     * @return true or false whether they are compatible or not
     */
    public boolean compatibleWithGuest(Teenager teen) {
        /*if(this.country.equals(CountryName.FRANCE)||teen.getCountry().equals(CountryName.FRANCE)){
            if(!compatibleFrench(teen)){return false;}
        }*/
        if(!guestAnimalAllergy(teen)){
            return false;
        }
        /*if(!guestFood(teen)){
            return false;
        }*/
        return true;
    }

    /** This method determines if the guest is allergic to animals and the host has animals.
     * It is used in the compatibleWithGuest() method.
     * @param teen a Teenager
     * @param crit a Criterion
     * @return true or false whether they are compatible or not true/compatible false/not compatible
     * @see compatibleWithGuest()
     */
    public boolean guestAnimalAllergy(Teenager teen){
        if (teen.getRequirement().get(CriterionName.GUEST_ANIMAL_ALLERGY) != null) {
            if(this.requirements.get(CriterionName.HOST_HAS_ANIMAL).getValue().equals("yes")){
                return false;
            }
        }
        return true;
    }

    /** This method determines if the guest and the host have the same dietary preferences.
     * It is used in the compatibleWithGuest() method.
     * @param teen a Teenager
     * @param crit a Criterion
     * @return true or false whether they are compatible or not
     * @see compatibleWithGuest()
     */
    public boolean guestFood(Teenager teen){
        if(!this.requirements.get(CriterionName.HOST_FOOD).getValue().equals(teen.getRequirement().get(CriterionName.GUEST_FOOD).getValue())){
            return false;
        }
        return true;
    }

    public boolean compatibleFrench(Teenager teen){ 
        for(Map.Entry<CriterionName,Criterion> crit : requirements.entrySet()) {
            try {
                if(this.requirements.get(crit.getKey()).getValue().equals(teen.getRequirement().get(crit.getKey()).getValue())){
                    return true;
                }   
            } catch (NullPointerException e) {
                return false;
            }
        }
        return false;
    }

    public void purgeInvalidRequirement() {
        Map<CriterionName, Criterion> requirementsCopy = Map.copyOf(requirements);
        for (Entry<CriterionName, Criterion> set : requirementsCopy.entrySet()) {
            if(!set.getValue().isValid()) {
                requirements.remove(set.getKey());
            }
        }
    }

    /**
     * This function add a criterion to the list of requirements for this teenager.
     * @param criterion an enum constant of CriterionName
     * @param value the value associated with the criterion
     */
    public void addCriterion(CriterionName criterion, String value) {
        Criterion critere = new Criterion(criterion,value);
        requirements.put(criterion,critere);
    }
}