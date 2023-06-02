package voyages;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TeenagerTest {

	public Teenager t1, t2, t3, t4, t5, t6;

    @BeforeEach
    void initialization() {
        t1 = new Teenager("Nicolas", "Dagneaux", LocalDate.now(), CountryName.FRANCE);
        t2 = new Teenager("Paul", "Degraëve", LocalDate.now(), CountryName.ITALY);
        t3 = new Teenager("Alexandre", "Martel", LocalDate.now(), CountryName.SPAIN);
		t4 = new Teenager("Maxime", "Blot", LocalDate.now(), CountryName.GERMANY);
		t5 = new Teenager("Kais", "Granjon", LocalDate.now(), CountryName.ITALY);
		t6 = new Teenager("Damand", "Simplet", LocalDate.now(), CountryName.FRANCE);
    }

	@Test
    void TestAnimalAllergy() {
		t2.addCriterion(CriterionName.HOST_HAS_ANIMAL, "yes");
		t3.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY, "yes");
		assertFalse(t2.compatibleWithGuest(t3));
		t3.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY, "no");
		assertTrue(t2.compatibleWithGuest(t3));
		assertTrue(t2.compatibleWithGuest(t4)); //t4 --> GUEST_ANIMAL_ALLERGY is null
		
		t2.addCriterion(CriterionName.HOST_HAS_ANIMAL, "no");
		t3.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY, "yes");
		assertTrue(t2.compatibleWithGuest(t3));
		t3.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY, "no");
		assertTrue(t2.compatibleWithGuest(t3));
		assertTrue(t2.compatibleWithGuest(t4)); //t4 --> GUEST_ANIMAL_ALLERGY is null
		
		t3.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY, "yes");
		assertFalse(t4.compatibleWithGuest(t3)); //t4 --> HOST_ANIMAL_ALLERGY is null
		t3.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY, "no");
		assertTrue(t4.compatibleWithGuest(t3)); //t4 --> HOST_ANIMAL_ALLERGY is null
		assertTrue(t4.compatibleWithGuest(t5)); //t4 and t5--> _ANIMAL_ALLERGY is null
    }
	
	@Test
	void TestDietCompatibility() {
		t2.addCriterion(CriterionName.HOST_FOOD, "");
		t3.addCriterion(CriterionName.GUEST_FOOD, "");
		assertTrue(t2.compatibleWithGuest(t3));
		t3.addCriterion(CriterionName.GUEST_FOOD, "vegetarian");
		assertFalse(t2.compatibleWithGuest(t3));
		t3.addCriterion(CriterionName.GUEST_FOOD, "vegetarian,nonuts");
		assertFalse(t2.compatibleWithGuest(t3));
		assertTrue(t2.compatibleWithGuest(t4)); //t4 --> GUEST_FOOD is null
		
		
		t2.addCriterion(CriterionName.HOST_FOOD, "vegetarian");
		t3.addCriterion(CriterionName.GUEST_FOOD, "");
		assertTrue(t2.compatibleWithGuest(t3));
		t3.addCriterion(CriterionName.GUEST_FOOD, "vegetarian");
		assertTrue(t2.compatibleWithGuest(t3));
		t3.addCriterion(CriterionName.GUEST_FOOD, "vegetarian,nonuts");
		assertFalse(t2.compatibleWithGuest(t3));
		assertTrue(t2.compatibleWithGuest(t4)); //t4 --> GUEST_FOOD is null
		
		t2.addCriterion(CriterionName.HOST_FOOD, "vegetarian,nonuts");
		t3.addCriterion(CriterionName.GUEST_FOOD, "");
		assertTrue(t2.compatibleWithGuest(t3));
		t3.addCriterion(CriterionName.GUEST_FOOD, "vegetarian");
		assertTrue(t2.compatibleWithGuest(t3));
		t3.addCriterion(CriterionName.GUEST_FOOD, "vegetarian,nonuts");
		assertTrue(t2.compatibleWithGuest(t3));
		assertTrue(t2.compatibleWithGuest(t4)); //t4 --> GUEST_FOOD is null
		
		t3.addCriterion(CriterionName.GUEST_FOOD, "");
		assertTrue(t5.compatibleWithGuest(t3)); //t5 --> HOST_FOOD is null
		t3.addCriterion(CriterionName.GUEST_FOOD, "vegetarian");
		assertFalse(t5.compatibleWithGuest(t3)); //t5 --> HOST_FOOD is null
		t3.addCriterion(CriterionName.GUEST_FOOD, "vegetarian,nonuts");
		assertFalse(t5.compatibleWithGuest(t3)); //t5 --> HOST_FOOD is null
		assertTrue(t5.compatibleWithGuest(t4)); //t4 and T5 --> _FOOD is null
	}
	
	@Test
	void TestFrenchCompatibility() {
		t1.addCriterion(CriterionName.HOBBIES, "");
		t2.addCriterion(CriterionName.HOBBIES, "");
		assertFalse(t1.compatibleWithGuest(t2));
		t2.addCriterion(CriterionName.HOBBIES, "tennis");
		assertFalse(t1.compatibleWithGuest(t2));
		t2.addCriterion(CriterionName.HOBBIES, "tennis,squash");
		assertFalse(t1.compatibleWithGuest(t2));
		assertFalse(t1.compatibleWithGuest(t3)); //t3 --> HOBBIES is null
		
		t1.addCriterion(CriterionName.HOBBIES, "tennis");
		t2.addCriterion(CriterionName.HOBBIES, "");
		assertFalse(t1.compatibleWithGuest(t2));
		t2.addCriterion(CriterionName.HOBBIES, "tennis");
		assertTrue(t1.compatibleWithGuest(t2));
		t2.addCriterion(CriterionName.HOBBIES, "tennis,squash");
		assertTrue(t1.compatibleWithGuest(t2));
		assertFalse(t1.compatibleWithGuest(t3)); //t3 --> HOBBIES is null
		
		t1.addCriterion(CriterionName.HOBBIES, "tennis,squash");
		t2.addCriterion(CriterionName.HOBBIES, "");
		assertFalse(t1.compatibleWithGuest(t2));
		t2.addCriterion(CriterionName.HOBBIES, "tennis");
		assertTrue(t1.compatibleWithGuest(t2));
		t2.addCriterion(CriterionName.HOBBIES, "tennis,squash");
		assertTrue(t1.compatibleWithGuest(t2));
		assertFalse(t1.compatibleWithGuest(t3)); //t3 --> HOBBIES is null
		
		t2.addCriterion(CriterionName.HOBBIES, "");
		assertFalse(t6.compatibleWithGuest(t2)); //t6 --> HOBBIES is null
		t2.addCriterion(CriterionName.HOBBIES, "tennis");
		assertFalse(t6.compatibleWithGuest(t2)); //t6 --> HOBBIES is null
		t2.addCriterion(CriterionName.HOBBIES, "tennis,squash");
		assertFalse(t6.compatibleWithGuest(t2)); //t6 --> HOBBIES is null
		assertFalse(t6.compatibleWithGuest(t3)); //t3 and t6 --> HOBBIES is null
	}

	@Test
	public void TestPurgeInvalidRequirement() {
		/*CAS 1 : Tous les critères sont valides*/
		t1.addCriterion(CriterionName.GENDER, "male");
		t1.addCriterion(CriterionName.GUEST_FOOD, "vegetarian");
		t1.addCriterion(CriterionName.HOBBIES, "reading,science,culture");
		HashMap<CriterionName, Criterion> c1 = t1.getRequirement();
		t1.purgeInvalidRequirement();
		assertEquals(3, c1.size());
		assertEquals("male", c1.get(CriterionName.GENDER).getValue());
		assertEquals("vegetarian", c1.get(CriterionName.GUEST_FOOD).getValue());
		assertEquals("reading,science,culture", c1.get(CriterionName.HOBBIES).getValue());
		
		/*CAS 2 : Aucun critère valide*/
		t2.addCriterion(CriterionName.GENDER, "no");
		t2.addCriterion(CriterionName.GUEST_FOOD, "male");
		t2.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY, "cat");
		t2.purgeInvalidRequirement();
		System.out.println(t2.getRequirement().size());
		assertTrue(t2.getRequirement().isEmpty());
		
		/*CAS 3 : Une partie des critères est invalide*/
		t3.addCriterion(CriterionName.GENDER, "female");
		t3.addCriterion(CriterionName.GUEST_FOOD, "other");
		t3.addCriterion(CriterionName.HOBBIES, "male");
		HashMap<CriterionName, Criterion> c2 = t3.getRequirement();
		t3.purgeInvalidRequirement();
		assertEquals(2, c2.size());
	}
}