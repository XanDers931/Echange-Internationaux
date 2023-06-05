package graphes;

import java.time.LocalDate;
import java.util.ArrayList;

import voyages.CountryName;
import voyages.CriterionName;
import voyages.CsvFileExportator;
import voyages.Teenager;
import voyages.Tuple;

public class TestHistoryScenario {
	
	
	
	public static void main(String[] args) {
		Teenager t1, t2, t3, t4, t5, t6, t7, t8;
		ArrayList<Tuple<Teenager>> history = new ArrayList<Tuple<Teenager>>();
		
		t1 = new Teenager("Nicolas", "Dagneaux", LocalDate.now(), CountryName.ITALY);
        t2 = new Teenager("Paul", "Degraëve", LocalDate.now(), CountryName.ITALY);
        t3 = new Teenager("Alexandre", "Martel", LocalDate.now(), CountryName.ITALY);
		t4 = new Teenager("Maxime", "Blot", LocalDate.now(), CountryName.ITALY);
		t5 = new Teenager("Kais", "Granjon", LocalDate.now(), CountryName.GERMANY);
		t6 = new Teenager("Damand", "Simplet", LocalDate.now(), CountryName.GERMANY);
		t7 = new Teenager("Eric", "Lepretre", LocalDate.now(), CountryName.GERMANY);
		t8 = new Teenager("Léa", "Demory", LocalDate.now(), CountryName.GERMANY);
		
		history.add(new Tuple<Teenager>(t1, t5));
		history.add(new Tuple<Teenager>(t2, t6));
		history.add(new Tuple<Teenager>(t3, t7));
		history.add(new Tuple<Teenager>(t4, t8));
		
		t1.addCriterion(CriterionName.HISTORY, "same");
		t5.addCriterion(CriterionName.HISTORY, "same");
		t2.addCriterion(CriterionName.HISTORY, "other");
		t3.addCriterion(CriterionName.HISTORY, "other");
		
		ArrayList<Teenager> teenList = new ArrayList<Teenager>();
		teenList.add(t1);
		teenList.add(t2);
		teenList.add(t3);
		teenList.add(t4);
		teenList.add(t5);
		teenList.add(t6);
		teenList.add(t7);
		teenList.add(t8);
		
		CsvFileExportator.Exportator(Graph.pairing(teenList, CountryName.ITALY, CountryName.GERMANY, history));
	}

}
