package graphes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import voyages.CountryName;
import voyages.CriterionName;
import voyages.Teenager;
import voyages.Tuple;

public class TestAffectation {

    public Teenager t1, t2, t3, t4, t5, t6, t7, t8;
    public List<Teenager> listTeenager;
    public List<Tuple<Teenager>> history;

    @BeforeEach
    void initialization() {
        t1 = new Teenager("Nicolas", "Dagneaux", LocalDate.now(), CountryName.FRANCE);
        t2 = new Teenager("Paul", "Degraeve", LocalDate.now(), CountryName.FRANCE);
        t3 = new Teenager("Maxime", "Blot", LocalDate.now(), CountryName.FRANCE);
        t4 = new Teenager("Damand", "Simplet", LocalDate.now(), CountryName.FRANCE);
        t5 = new Teenager("Kais", "Granjon", LocalDate.now(), CountryName.ITALY);
        t6 = new Teenager("Alexandre", "Martel", LocalDate.now(), CountryName.ITALY);
        t7 = new Teenager("Eric", "Leprêtre", LocalDate.now(), CountryName.ITALY);
        t8 = new Teenager("Léa", "Demory", LocalDate.now(), CountryName.ITALY);
        history = new ArrayList<>();
        listTeenager = new ArrayList<>();
        Tuple<Teenager> tuple1 = new Tuple<>(t1, t5);
        Tuple<Teenager> tuple2 = new Tuple<>(t2, t6);
        Tuple<Teenager> tuple3 = new Tuple<>(t3, t7);
        Tuple<Teenager> tuple4 = new Tuple<>(t4, t8);
        history.add(tuple1);
        history.add(tuple2);
        history.add(tuple3);
        history.add(tuple4);
        t1.addCriterion(CriterionName.HISTORY, "same");
        t2.addCriterion(CriterionName.HISTORY, "other");
        t3.addCriterion(CriterionName.HISTORY, "");
        t4.addCriterion(CriterionName.HISTORY, "same");
        t5.addCriterion(CriterionName.HISTORY, "same");
        t6.addCriterion(CriterionName.HISTORY, "other");
        t7.addCriterion(CriterionName.HISTORY, null);
        t8.addCriterion(CriterionName.HISTORY, "");
        listTeenager.add(t1);
        listTeenager.add(t2);
        listTeenager.add(t3);
        listTeenager.add(t4);
        listTeenager.add(t5);
        listTeenager.add(t6);
        listTeenager.add(t7);
        listTeenager.add(t8);
    }

    @Test
    void TestGraph() {
        List<Tuple<Teenager>> listTuple = Graph.pairing(listTeenager, CountryName.FRANCE, CountryName.ITALY, history);
        assertEquals(4, listTuple.size());
        assertEquals(t1, listTuple.get(0).getFirst());
        assertEquals(t5, listTuple.get(0).getSecond());
        assertEquals(t3, listTuple.get(1).getFirst());
        assertEquals(t6, listTuple.get(1).getSecond());
        assertEquals(t4, listTuple.get(2).getFirst());
        assertEquals(t8, listTuple.get(2).getSecond());
        assertEquals(t2, listTuple.get(3).getFirst());
        assertEquals(t7, listTuple.get(3).getSecond());
    }
}