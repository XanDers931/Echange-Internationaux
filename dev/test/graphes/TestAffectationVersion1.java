package graphes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import voyages.CountryName;
import voyages.CriterionName;
import voyages.Teenager;
import voyages.Tuple;

public class TestAffectationVersion1 {

    public Teenager t1, t2, t3, t4, t5, t6;
    public List<Teenager> listTeenager;

    @BeforeEach
    void initialization() {
        t1 = new Teenager("Adonia", "A", null, CountryName.FRANCE);
        t2 = new Teenager("Bellatrix", "B", null, CountryName.FRANCE);
        t3 = new Teenager("Callista", "C", null, CountryName.FRANCE);
        t4 = new Teenager("Xolag", "X", null, CountryName.ITALY);
        t5 = new Teenager("Yak", "Y", null, CountryName.ITALY);
        t6 = new Teenager("Zander", "Z", null, CountryName.ITALY);
        t1.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY, "no");
        t1.addCriterion(CriterionName.HOBBIES, "sports,technology");
        t2.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY, "yes");
        t2.addCriterion(CriterionName.HOBBIES, "culture,science");
        t3.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY, "no");
        t3.addCriterion(CriterionName.HOBBIES, "science,reading");
        t4.addCriterion(CriterionName.HOST_HAS_ANIMAL, "no");
        t4.addCriterion(CriterionName.HOBBIES, "culture,technology");
        t5.addCriterion(CriterionName.HOST_HAS_ANIMAL, "yes");
        t5.addCriterion(CriterionName.HOBBIES, "science,reading");
        t6.addCriterion(CriterionName.HOST_HAS_ANIMAL, "no");
        t6.addCriterion(CriterionName.HOBBIES, "technology");
        listTeenager = new ArrayList<>();
        listTeenager.add(t1);
        listTeenager.add(t2);
        listTeenager.add(t3);
        listTeenager.add(t4);
        listTeenager.add(t5);
        listTeenager.add(t6);
    }

    @Test
    void TestGraph() {
        List<Tuple<Teenager>> result = Graph.pairing(listTeenager, CountryName.ITALY, CountryName.FRANCE);
        System.out.println(result);
        System.out.println(Tuple.equals(t2, result.get(1).get(t5)));
        assertEquals(t1, result.get(1).get(t6));
        assertEquals(t2, result.get(2).get(t4));
        assertEquals(t3, result.get(0).get(t5));
    }
}