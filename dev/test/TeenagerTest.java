/**
 * 
 */
package test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.HashMap;

import voyages.Criterion;
import voyages.CriterionName;

/**
 * @author nicolas.dagneaux.etu
 *
 */
public class TeenagerTest {

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
