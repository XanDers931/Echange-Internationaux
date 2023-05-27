package voyages;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**The {@code Teenager} class, it represents a teenager with requirements.
 * @author Dagneaux Nicolas
 * @author Degraeve Paul
 * @author Martel Alexandre
 * @version 0.0.5, 05/12/23
 * @see Criterion
 * @see CriterionName
 */
public class Teenager implements java.io.Serializable {
    
    /**
     * The list of requirements of the teenager.
     */
    private HashMap<CriterionName, Criterion> requirements;
    /**
     * A counter for auto incrementing the id.
     */
    private static int count = 0;
    /**
     * The id of the teenager.
     */
    private int id;
    /**
     * The first name of the teenager.
     */
    private String firstName;
    /**
     * The last name of the teenager.
     */
    private String lastName;
    /**
     * The date of birth of the teenager.
     */
    private LocalDate birthday;
    /**
     * The country of the teenager.
     */
    private CountryName country;

    /** The constructor for the Teenager class.
     * @param firstName a {@code String} representing the first name of the teenager.
     * @param lastName a {@code String} representing the last name of the teenager.
     * @param birthday a {@code LocalDate} representing the date of birth of the teenager.
     * @param country a {@code CountryName} representing the country of the teenager.
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

    /**Return the id of this teenager.
     * @return the id of this teenager.
     */
    public int getId() {
        return id;
    }

    /**Return the first name of this teenager.
     * @return the first name of this teenager.
     */
    public String getFirstName() {
        return firstName;
    }

    /**Return the last name of this teenager.
     * @return the last name of this teenager.
     */
    public String getLastName() {
        return lastName;
    }

    /**Return the date of birth of this teenager.
     * @return the date of birth of this teenager
     */
    public LocalDate getBirthday() {
        return birthday;
    }

    /**Return the country of this teenager.
     * @return the country of this teenager.
     * @see CountryName
     */
    public CountryName getCountry() {
        return country;
    }

    /**Return the list of requirements for this teenager.
     * @return a HashMap of CriterionName and Criterion representing the requirements of this teenager.
     * @see Criterion
     * @see CriterionName
     */
    public HashMap<CriterionName, Criterion> getRequirement() {
        return this.requirements;
    }

    /** This method determines if two teenagers are compatible by comparing their requirements.
     * @param teen a {@code Teenager}
     * @return {@code true} if the two teenagers are compatible, {@code false} otherwise.
     */
    public boolean compatibleWithGuest(Teenager teen) {
        /*if(this.country.equals(CountryName.FRANCE)||teen.getCountry().equals(CountryName.FRANCE)){
            if(!compatibleFrench(teen)){return false;}
        }*/
        if(!guestAnimalAllergy(teen)){
            return false;
        }
        if(!guestFood(teen)){
            return false;
        }
        if(!compatibleFrench(teen)){
            return false;
        }
        return true;
    }

    /** This method checks, if the guest has an animal allergy, if the host has an animal.
     * @param teen a {@code Teenager}
     * @return {@code true} if the guest has an animal allergy and the host has an animal, {@code false} otherwise.
     * @see Teenager#compatibleWithGuest
     */
    public boolean guestAnimalAllergy(Teenager teen){
        try {
            if (teen.getRequirement().get(CriterionName.GUEST_ANIMAL_ALLERGY) != null) {
                if(this.requirements.get(CriterionName.HOST_HAS_ANIMAL).getValue().equals("yes")){
                    return false;
                }
            }
        } catch (NullPointerException e) {
            return false;
        }
        
        return true;
    }

    /** This method checks, if the guest has a food requirement, if the host is able to provide it.
     * @param teen a {@code Teenager}
     * @return {@code true} if the guest has a food requirement and the host is able to provide it, {@code false} otherwise.
     * @see Teenager#compatibleWithGuest
     */
    public boolean guestFood(Teenager teen){
        try {
            if(!this.requirements.get(CriterionName.HOST_FOOD).getValue().equals(teen.getRequirement().get(CriterionName.GUEST_FOOD).getValue())){
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**This method checks if the two teenagers are compatible according to the french requirements.
     * @param teen a {@code Teenager}
     * @return {@code true} if the two teenagers are compatible, {@code false} otherwise.
     * @see Teenager#compatibleWithGuest
     */
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

    /**This method purge the requirements of this teenager if they are not valid.
     * @see Criterion#isValid
     */
    public void purgeInvalidRequirement() {
        Map<CriterionName, Criterion> requirementsCopy = Map.copyOf(requirements);
        for (Entry<CriterionName, Criterion> set : requirementsCopy.entrySet()) {
        	try {
        		set.getValue().isValid();
			} catch (CriterionValueException e) {
				requirements.remove(set.getKey());
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
    }
    
    /**This method add a criterion to the list of requirements of this teenager.
     * @param criterion the {@code Criterion} to add.
     */
    public void addCriterion(Criterion criterion) {
        requirements.put(criterion.getLabel(), criterion);
    }

    /**This method add a criterion to the list of requirements of this teenager.
     * @param criterion the {@code criterionName} to add.
     * @param value the value of the criterion to be added.
     */
    public void addCriterion(CriterionName criterion, String value) {
        Criterion critere = new Criterion(criterion,value);
        this.addCriterion(critere);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Teenager other = (Teenager) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Teenager [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
    }
}