package voyages;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class CriterionTest {
	
	public Criterion crit;
	public CriterionValueException CritException;
	
	@ParameterizedTest
	@EnumSource(value = CriterionName.class, names = {"GUEST_ANIMAL_ALLERGY", "HOST_HAS_ANIMAL"})
	void TestAnimal(CriterionName animal) throws CriterionValueException {
		//valid criterions
		crit = new Criterion(animal, "yes");
		assertTrue(crit.isValid());
		crit = new Criterion(animal, "no");
		assertTrue(crit.isValid());
		
		//invalid criterions
		crit = new Criterion(animal, "maybe");
		CritException = Assertions.assertThrows(CriterionValueException.class, () -> {
    		crit.isValid();
    	}, "");
		crit = new Criterion(animal, "");
		CritException = Assertions.assertThrows(CriterionValueException.class, () -> {
    		crit.isValid();
    	}, "");
		crit = new Criterion(animal, null);
		CritException = Assertions.assertThrows(CriterionValueException.class, () -> {
    		crit.isValid();
    	}, "");
	}
	
	@ParameterizedTest
	@EnumSource(value = CriterionName.class, names = {"GUEST_FOOD", "HOST_FOOD"})
	void TestFood(CriterionName food) throws CriterionValueException {
    	//valid criterions
    	crit = new Criterion(food, "vegetarian");
    	assertTrue(crit.isValid());
    	crit = new Criterion(food, "nonuts");
    	assertTrue(crit.isValid());
    	crit = new Criterion(food, "vegetarian,nonuts");
    	assertTrue(crit.isValid());
    	crit = new Criterion(food, "nonuts,vegetarian");
    	assertTrue(crit.isValid());
    	crit = new Criterion(food, "");
    	assertTrue(crit.isValid());
    	crit = new Criterion(food, null);
    	assertTrue(crit.isValid());
    	
    	//inavlid criterions
    	crit = new Criterion(CriterionName.GUEST_FOOD, "nonuts,vegetarian,nonuts");
    	CritException = Assertions.assertThrows(CriterionValueException.class, () -> {
    		crit.isValid();
    	}, "");
    	crit = new Criterion(CriterionName.GUEST_FOOD, "homnivore");
    	CritException = Assertions.assertThrows(CriterionValueException.class, () -> {
    		crit.isValid();
    	}, "");
	}
	
	@Test
	void TestHobbies() throws CriterionValueException {
		//valid criterions
		crit = new Criterion(CriterionName.HOBBIES, "tennis");
		assertTrue(crit.isValid());
		crit = new Criterion(CriterionName.HOBBIES, "tennis,squash");
		assertTrue(crit.isValid());
		crit = new Criterion(CriterionName.HOBBIES, "");
		assertTrue(crit.isValid());
		crit = new Criterion(CriterionName.HOBBIES, null);
		assertTrue(crit.isValid());
		
		//invalid criterions
		crit = new Criterion(CriterionName.HOBBIES, "TeNnIs");
		CritException = Assertions.assertThrows(CriterionValueException.class, () -> {
    		crit.isValid();
    	}, "");
		crit = new Criterion(CriterionName.HOBBIES, "SQUASH");
		CritException = Assertions.assertThrows(CriterionValueException.class, () -> {
    		crit.isValid();
    	}, "");
		crit = new Criterion(CriterionName.HOBBIES, "tennis,squash,");
		CritException = Assertions.assertThrows(CriterionValueException.class, () -> {
    		crit.isValid();
    	}, "");
		crit = new Criterion(CriterionName.HOBBIES, "tennis,,squash");
		CritException = Assertions.assertThrows(CriterionValueException.class, () -> {
    		crit.isValid();
    	}, "");
		crit = new Criterion(CriterionName.HOBBIES, "tennis,,squash,basminton");
		CritException = Assertions.assertThrows(CriterionValueException.class, () -> {
    		crit.isValid();
    	}, "");
	}
	
	@ParameterizedTest
	@EnumSource(value = CriterionName.class, names = {"GENDER", "PAIR_GENDER"})
	void TestGender(CriterionName gender) throws CriterionValueException {
		//valid criterions
		crit = new Criterion(gender, "male");
		assertTrue(crit.isValid());
		crit = new Criterion(gender, "female");
		assertTrue(crit.isValid());
		crit = new Criterion(gender, "other");
		assertTrue(crit.isValid());
		
		//inavlid criterions
		crit = new Criterion(gender, "patate");
		CritException = Assertions.assertThrows(CriterionValueException.class, () -> {
    		crit.isValid();
    	}, "");
		
		//depend on criterion name
		crit = new Criterion(gender, "");
		if (gender == CriterionName.GENDER) {
			CritException = Assertions.assertThrows(CriterionValueException.class, () -> {
	    		crit.isValid();
	    	}, "");
		} else {
			assertTrue(crit.isValid());
		}
		crit = new Criterion(gender, null);
		if (gender == CriterionName.GENDER) {
			CritException = Assertions.assertThrows(CriterionValueException.class, () -> {
	    		crit.isValid();
	    	}, "");
		} else {
			assertTrue(crit.isValid());
		}
	}
	
	@Test
	void TestHistory() throws CriterionValueException {
		//valid criterions
		crit = new Criterion(CriterionName.HISTORY, "same");
		assertTrue(crit.isValid());
		crit = new Criterion(CriterionName.HISTORY, "other");
		assertTrue(crit.isValid());
		crit = new Criterion(CriterionName.HISTORY, "");
		assertTrue(crit.isValid());
		crit = new Criterion(CriterionName.HISTORY, null);
		assertTrue(crit.isValid());
		
		//invalid criterions
		crit = new Criterion(CriterionName.HISTORY, "yes");
		CritException = Assertions.assertThrows(CriterionValueException.class, () -> {
    		crit.isValid();
    	}, "");
	}
}