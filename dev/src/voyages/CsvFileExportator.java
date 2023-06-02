package voyages;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.ParsePosition;
import java.time.chrono.ThaiBuddhistChronology;
import java.util.ArrayList;
import java.util.List;

import org.junit.platform.engine.support.discovery.SelectorResolver.Match;
import org.opentest4j.TestAbortedException;

import graphes.Graph;

public class CsvFileExportator {


    public static void Exportator(ArrayList<Teenager> teen, CountryName countryHost, CountryName countryGuest){
        String sortie = "";
        
        List<Tuple<Teenager>> match = new ArrayList<Tuple<Teenager>>();
        match = Graph.pairing(teen, countryHost, countryGuest);
    
        try(BufferedWriter fileOutput = new BufferedWriter(new FileWriter("./dev/res/adosMatchGraphOutput.csv"))){
            for (Tuple<Teenager> tuple : match) {
                sortie = ""+ tuple.getFirst()+" est associé à "+tuple.getSecond();
                fileOutput.write(sortie,0,sortie.length());
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    /*
    public static void main(String[] args) {
        ArrayList<Teenager> list = new ArrayList<Teenager>();
        try {
            list = CsvFileImportator.importFromCsv("./dev/res/adosAleatoires.csv");
        } catch (CsvRowInvalidStructureException invalid) {
            invalid.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        CsvFileExportator.Exportator(list, CountryName.FRANCE, CountryName.GERMANY);
    }*/
}
