package voyages;

import voyages.Criterion;
import voyages.CriterionName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.accessibility.AccessibleAttributeSequence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CriterionTest {
    @Test
    void TestCriterion(){
        //criterion Gender
        Criterion gender1 = new Criterion(CriterionName.GENDER,"male");
        assertTrue(gender1.isValid());
        Criterion gender2 = new Criterion(CriterionName.GENDER,"female");
        assertTrue(gender2.isValid());
        Criterion gender3 = new Criterion(CriterionName.GENDER,"other");
        assertTrue(gender3.isValid());
        Criterion gender4 = new Criterion(CriterionName.GENDER,"patate");
        assertFalse(gender4.isValid());

        //criterion Pair_Gender
        Criterion pair_gender1 = new Criterion(CriterionName.PAIR_GENDER,"male");
        assertTrue(pair_gender1.isValid());
        Criterion pair_gender2 = new Criterion(CriterionName.PAIR_GENDER,"female");
        assertTrue(pair_gender2.isValid());
        Criterion pair_gender3 = new Criterion(CriterionName.PAIR_GENDER,"other");
        assertTrue(pair_gender3.isValid());
        Criterion pair_gender4 = new Criterion(CriterionName.PAIR_GENDER,"carotte");
        assertFalse(pair_gender4.isValid());

        //Criterion Host_Has_Animal
        Criterion host_has_animal1 = new Criterion(CriterionName.HOST_HAS_ANIMAL, "yes");
        assertTrue(host_has_animal1.isValid());
        Criterion host_has_animal2 = new Criterion(CriterionName.HOST_HAS_ANIMAL, "no");
        assertTrue(host_has_animal2.isValid());
        Criterion host_has_animal3 = new Criterion(CriterionName.HOST_HAS_ANIMAL, "3");
        assertFalse(host_has_animal3.isValid());

        //Pour le rest test si c'est un txt
        Criterion other = new Criterion(CriterionName.HOBBIES, "skate");
        assertTrue(other.isValid());
    }
}