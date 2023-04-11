/**
 * 
 */
package voyages;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author nicolas.dagneaux.etu
 *
 */
public class TeenagerTest {

	public Teenager t1, t2, t3;

    @BeforeEach
    void initialization() {
        t1 = new Teenager("Nicolas", "Dagneaux", LocalDate.now(), CountryName.FRANCE);
        t2 = new Teenager("Paul", "Degraëve", LocalDate.now(), CountryName.ITALY);
        t3 = new Teenager("Alexandre", "Martel", LocalDate.now(), CountryName.SPAIN);
    }

	@Test
    void TestCompatibleWithGuest() {
        t1.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY, "yes");
        t2.addCriterion(CriterionName.HOST_HAS_ANIMAL, "yes");
        assertFalse(t1.compatibleWithGuest(t2));
        t1.addCriterion();
    }

	@Test
	public void TestPurgeInvalidRequirement() {
		/*CAS 1 : Tous les critères sont valides*/
		t1.addCriterion(CriterionName.GENDER, "male");
		t1.addCriterion(CriterionName.GUEST_FOOD, "vegetarian");
		t1.addCriterion(CriterionName.HOBBIES, "reading,science,culture")
		HashMap<CriterionName, Criterion> c1 = t1.getRequirement();
		t1.purgeInvalidRequirement();
		assertEquals(c1.size(), 3);
		assertEquals(c1.get(CriterionName.GENDER), "male");
		assertEquals(c1.get(CriterionName.GUEST_FOOD), "vegetarian");
		assertEquals(c1.get(CriterionName.HOBBIES), "reading,science,culture");
		
		/*CAS 2 : Aucun critère valide*/
		t2.addCriterion(CriterionName.GENDER, "no");
		t2.addCriterion(CriterionName.GUEST_FOOD, "male");
		t2.addCriterion(CriterionName.HOBBIES, "yes");
		t2.purgeInvalidRequirement();
		assertTrue(t2.getRequirement().isEmpty());
		
		/*CAS 3 : Une partie des critères sont invalides*/
		t3.addCriterion(CriterionName.GENDER, "yes");
		t3.addCriterion(CriterionName.GUEST_FOOD, "vegetarian");
		t3.addCriterion(CriterionName.HOBBIES, "male")
		HashMap<CriterionName, Criterion> c2 = t3.getRequirement();
		t3.purgeInvalidRequirement();
		assertEquals(c2.size(), 1);
		assertEquals(c2.get(CriterionName.GUEST_FOOD), "vegetarian");
	}

}