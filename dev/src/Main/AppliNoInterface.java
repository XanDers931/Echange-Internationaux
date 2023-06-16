package Main;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.FutureTask;

import javax.lang.model.util.ElementScanner14;
import javax.swing.text.html.HTMLDocument.BlockElement;

import graphes.AffectationUtil;
import voyages.CountryName;
import voyages.Criterion;
import voyages.Teenager;
import voyages.Tuple;
import voyages.CsvFileImportator;
import voyages.Exchange;
import voyages.Platform;
import voyages.SameCountryException;
import voyages.SameTeenagerException;

public class AppliNoInterface {
    private static Scanner read = new Scanner(System.in);
    private static Platform p = new Platform();

    private static void mainMenu() {
        
        System.out.println("Main Menu");
        boolean isFileOk = false;
        File csvToImport = null;
        do {
        	csvToImport = askChemin();
        	System.out.println("Importation en cours...");
	        System.out.println(p.importTeenagerFromCsv(csvToImport, false));
        	if (p.getTeenagersByCountry(CountryName.FRANCE).size() == 0 && p.getTeenagersByCountry(CountryName.GERMANY).size() == 0 && p.getTeenagersByCountry(CountryName.ITALY).size() == 0 && p.getTeenagersByCountry(CountryName.SPAIN).size() == 0) {
				System.out.println("Aucun étudiant n'a été importé, veuillez sélectionner un autre fichier.");
			} else {
				isFileOk = true;
			}
		} while (!isFileOk);

        System.out.println("Importation terminée\n");

        System.out.println("Vous allez maintenant pouvoir sélectionner deux pays avec lesquels vous souhaitez faire les associations d'étudiants");
        askForSomething();
    }

    public static File askChemin(){
        System.out.println("Veuillez entrer le chemin d'accés au fichier csv que vous souhaitez importer :");
        String chemin;
        File result = null;
        boolean stop = false;
        do {
        	Scanner read = new Scanner(System.in);
        	chemin = read.nextLine();
        	if (!chemin.contains(".csv")) {
        		System.out.println("Le chemin ne mène pas vers un fichier csv");
                System.out.println("Veuillez saisir un chemin correct");
			} else {
				result = new File(chemin);
				if (!result.exists()) {
					System.out.println("Le fichier n'existe pas, veuillez recommencer...");
				} else {
					stop = true;
				}
			}
		} while (!stop);
        return result;
    }

    public static void askForSomething(){
        System.out.println("Que souhaitez vous faire ?\n");
        System.out.println("1. Forcer une association entre deux étudiants");
        System.out.println("2. Associer de manière automatique et optimisé les étudiants");
        System.out.println("3. Modifier les coefficients d'affectations");
        System.out.println("4. Quitter l'application");
        
        String reponse;
        do{
            reponse = read.nextLine();
            if(reponse.equals("1")){
                associationForced();
            }
            else if(reponse.equals("2")){
                associationAuto();
            }
            else if(reponse.equals("3")){
                coeffChange();
            }
            else if(reponse.equals("4")){
                break;
            }
            else{
                System.out.println("Veuillez rentrer une réponse valide");
            }
        }while(!(reponse.equals("1"))||reponse.equals("2")||reponse.equals("3")||reponse.equals("4"));
    }

    public static Tuple<CountryName> askPays(){
    	Tuple<CountryName> assoPays = new Tuple<CountryName>();
        System.out.println("Sélectionner Pays hôte :");
        System.out.println("1. France");
        System.out.println("2. Italy");
        System.out.println("3. Spain");
        System.out.println("4. Germany");

        String readHote;
        do{
            readHote = read.nextLine();
            if(readHote.equals("1")){
                assoPays.setFirst(CountryName.FRANCE);
            }
            else if(readHote.equals("2")){
                assoPays.setFirst(CountryName.ITALY);
            }
            else if(readHote.equals("3")){
                assoPays.setFirst(CountryName.SPAIN);
            }
            else if(readHote.equals("4")){
                assoPays.setFirst(CountryName.GERMANY);
            }
            else{
                System.out.println("Veuillez rentrer une réponse valide");
            }
        }while(!((readHote.equals("1"))||readHote.equals("2")||readHote.equals("3")||readHote.equals("4")));

        System.out.println(assoPays.getFirst());
        
        System.out.println("Sélectionner Pays visiteur:");
        System.out.println("1. France");
        System.out.println("2. Italy");
        System.out.println("3. Spain");
        System.out.println("4. Germany");

        String readVisiteur;
        do{
            if(assoPays.getFirst().equals(assoPays.getSecond())){
                System.out.println("Attention vous ne pouvez pas associer deux fois le même pays !");
            }
            readVisiteur = read.nextLine();
            if(readVisiteur.equals("1")){
                assoPays.setSecond(CountryName.FRANCE);
            }
            else if(readVisiteur.equals("2")){
                assoPays.setSecond(CountryName.ITALY);
            }
            else if(readVisiteur.equals("3")){
                assoPays.setSecond(CountryName.SPAIN);
            }
            else if(readVisiteur.equals("4")){
                assoPays.setSecond(CountryName.GERMANY);
            }
            else{
                System.out.println("Veuillez rentrer une réponse valide");
            }

        }while(!((readVisiteur.equals("1"))||readVisiteur.equals("2")||readVisiteur.equals("3")||readVisiteur.equals("4"))||assoPays.getFirst().equals(assoPays.getSecond()));
        return assoPays;
    }
    

    public static void associationForced(){
        System.out.println("Dans cette partie de l'application vous choisirez deux étudiant dont vous souhaitez forcer l'association\n");
        System.out.println("Dans un premier temps veuillez choisir les pays des deux étudiant de léchange :");
        Tuple<CountryName> countryForced =  askPays();
        
        String student1 ="";
        String student2="";
        boolean trouve1 = false;
        boolean trouve2 = false;
        System.out.println("Entrez le nom de l'étudiant hôte :");
        do{
            student1 = read.nextLine();
            for (Teenager teen1 : p.getTeenagersByCountry(countryForced.getFirst())) {
                if(teen1.getLastName().equals(student1)){
                    do{
                        System.out.println("Entrez le nom de l'étudiant invitéoui :");
                        student2 = read.nextLine();
                        for (Teenager teen2 : p.getTeenagersByCountry(countryForced.getSecond())) {
                            if(teen2.getLastName().equals(student2)){
                                try {
                                    Exchange currentExchange = p.addExchange(countryForced.getFirst(), countryForced.getSecond());
                                    currentExchange.addAffectations(teen1, teen2);
                                } catch (SameTeenagerException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                } catch (SameCountryException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                                trouve2 = true;
                            }
                        }
                        if(trouve2 = false){
                            System.out.println("Cet étudiant n'existe pas dans la base de donné");
                        }
            
                    }while(!trouve2);
                    trouve1 = true;
                }
            }
            if(trouve1 = false){
                System.out.println("Cet étudiant n'existe pas dans la base de donné");
            }
        }while(!trouve1);
    }


    public static void associationAuto() {
    	Tuple<CountryName> countries = askPays();
    	try {
			Exchange currentExchange = p.addExchange(countries.getFirst(), countries.getSecond());
			currentExchange.setOptimalAffectation();
			System.out.println("Vous avez bien affecté de manière optimal les étudiants de l'échange " + currentExchange.getGuestCountry() + " - " + currentExchange.getGuestCountry());
			askForSomething();
		} catch (SameCountryException | SameTeenagerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public static void coeffChange(){
        System.out.println("Dans cette partie vous pouvez modifier les coefficients du calculateur qui permet de faire des associations d'étudiants de manière automatique");
        System.out.println("Quel coefficient souhaitez-vous modifier ?\n");
        System.out.println("1. Coefficient de l'historique");
        System.out.println("2. Coefficient de l'âge");
        System.out.println("3. Coefficient du genre");
        System.out.println("4. Coefficient des hobbies");
        System.out.println("5. Coefficient des allergies aux animaux");
        System.out.println("6. Coefficient des contraintes d'alimentation");
        System.out.println("7. Retour au menu de sélection d'action");
        System.out.println("8. Quitter l'application");

        String coeffNb;
        String newValue;
        do{
            coeffNb = read.nextLine();
            if(coeffNb.equals("1")){
                System.out.println("Veuillez entrer la nouvelle valeur souhaitée : (la valeur doit être entière)");
                newValue = read.nextLine();
                AffectationUtil.setCoefficientHistory(Integer.parseInt(newValue));
            }
            else if(coeffNb.equals("2")){
                System.out.println("Veuillez entrer la nouvelle valeur souhaitée : (la valeur doit être entière)");
                newValue = read.nextLine();
                AffectationUtil.setCoefficientAge(Integer.parseInt(newValue));
            }
            else if(coeffNb.equals("3")){
                System.out.println("Veuillez entrer la nouvelle valeur souhaitée : (la valeur doit être entière)");
                newValue = read.nextLine();
                AffectationUtil.setCoefficientGender(Integer.parseInt(newValue));
            }
            else if(coeffNb.equals("4")){
                System.out.println("Veuillez entrer la nouvelle valeur souhaitée : (la valeur doit être entière)");
                newValue = read.nextLine();
                AffectationUtil.setCoefficientHobbies(Integer.parseInt(newValue));
            }
            else if(coeffNb.equals("5")){
                System.out.println("Veuillez entrer la nouvelle valeur souhaitée : (la valeur doit être entière)");
                newValue = read.nextLine();
                AffectationUtil.setCoefficientAnimalAllergy(Integer.parseInt(newValue));
            }
            else if(coeffNb.equals("6")){
                System.out.println("Veuillez entrer la nouvelle valeur souhaitée : (la valeur doit être entière)");
                newValue = read.nextLine();
                AffectationUtil.setCoefficientDiet(Integer.parseInt(newValue));
            }
            else if(coeffNb.equals("7")){
                askForSomething();
            }
            else if(coeffNb.equals("8")){
                break;
            }
            else{
                System.out.println("Veuillez rentrer une réponse valide");
            }
        }while(!((coeffNb.equals("1"))||coeffNb.equals("2")||coeffNb.equals("3")||coeffNb.equals("4")||coeffNb.equals("5")||coeffNb.equals("6")||coeffNb.equals("7")||coeffNb.equals("8")));
        System.out.println("Valeur modifiée ! Retour au menu principal");
        askForSomething();
    }
    
    
    public static void main(String[] args) {
        System.out.println(" \u2588\u2588\u2588\u2588\u2588\u2557 \u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2557\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2557\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2557 \u2588\u2588\u2588\u2588\u2588\u2588\u2557\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2557 \u2588\u2588\u2588\u2588\u2588\u2557 \u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2557 \u2588\u2588\u2588\u2588\u2588\u2588\u2557 \u2588\u2588\u2588\u2588\u2588\u2588\u2557     \u2588\u2588\u2588\u2588\u2588\u2588\u2557  \u2588\u2588\u2588\u2588\u2588\u2588\u2557  \u2588\u2588\u2588\u2588\u2588\u2588\u2557  \u2588\u2588\u2588\u2588\u2588\u2588\u2557 \r\n" + //
                "\u2588\u2588\u2554\u2550\u2550\u2588\u2588\u2557\u2588\u2588\u2554\u2550\u2550\u2550\u2550\u255D\u2588\u2588\u2554\u2550\u2550\u2550\u2550\u255D\u2588\u2588\u2554\u2550\u2550\u2550\u2550\u255D\u2588\u2588\u2554\u2550\u2550\u2550\u2550\u255D\u255A\u2550\u2550\u2588\u2588\u2554\u2550\u2550\u255D\u2588\u2588\u2554\u2550\u2550\u2588\u2588\u2557\u255A\u2550\u2550\u2588\u2588\u2554\u2550\u2550\u255D\u2588\u2588\u2554\u2550\u2550\u2550\u2588\u2588\u2557\u2588\u2588\u2554\u2550\u2550\u2588\u2588\u2557    \u255A\u2550\u2550\u2550\u2550\u2588\u2588\u2557\u2588\u2588\u2554\u2550\u2588\u2588\u2588\u2588\u2557\u2588\u2588\u2554\u2550\u2588\u2588\u2588\u2588\u2557\u2588\u2588\u2554\u2550\u2588\u2588\u2588\u2588\u2557\r\n" + //
                "\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2551\u2588\u2588\u2588\u2588\u2588\u2557  \u2588\u2588\u2588\u2588\u2588\u2557  \u2588\u2588\u2588\u2588\u2588\u2557  \u2588\u2588\u2551        \u2588\u2588\u2551   \u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2551   \u2588\u2588\u2551   \u2588\u2588\u2551   \u2588\u2588\u2551\u2588\u2588\u2588\u2588\u2588\u2588\u2554\u255D     \u2588\u2588\u2588\u2588\u2588\u2554\u255D\u2588\u2588\u2551\u2588\u2588\u2554\u2588\u2588\u2551\u2588\u2588\u2551\u2588\u2588\u2554\u2588\u2588\u2551\u2588\u2588\u2551\u2588\u2588\u2554\u2588\u2588\u2551\r\n" + //
                "\u2588\u2588\u2554\u2550\u2550\u2588\u2588\u2551\u2588\u2588\u2554\u2550\u2550\u255D  \u2588\u2588\u2554\u2550\u2550\u255D  \u2588\u2588\u2554\u2550\u2550\u255D  \u2588\u2588\u2551        \u2588\u2588\u2551   \u2588\u2588\u2554\u2550\u2550\u2588\u2588\u2551   \u2588\u2588\u2551   \u2588\u2588\u2551   \u2588\u2588\u2551\u2588\u2588\u2554\u2550\u2550\u2588\u2588\u2557     \u255A\u2550\u2550\u2550\u2588\u2588\u2557\u2588\u2588\u2588\u2588\u2554\u255D\u2588\u2588\u2551\u2588\u2588\u2588\u2588\u2554\u255D\u2588\u2588\u2551\u2588\u2588\u2588\u2588\u2554\u255D\u2588\u2588\u2551\r\n" + //
                "\u2588\u2588\u2551  \u2588\u2588\u2551\u2588\u2588\u2551     \u2588\u2588\u2551     \u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2557\u255A\u2588\u2588\u2588\u2588\u2588\u2588\u2557   \u2588\u2588\u2551   \u2588\u2588\u2551  \u2588\u2588\u2551   \u2588\u2588\u2551   \u255A\u2588\u2588\u2588\u2588\u2588\u2588\u2554\u255D\u2588\u2588\u2551  \u2588\u2588\u2551    \u2588\u2588\u2588\u2588\u2588\u2588\u2554\u255D\u255A\u2588\u2588\u2588\u2588\u2588\u2588\u2554\u255D\u255A\u2588\u2588\u2588\u2588\u2588\u2588\u2554\u255D\u255A\u2588\u2588\u2588\u2588\u2588\u2588\u2554\u255D\r\n" + //
                "\u255A\u2550\u255D  \u255A\u2550\u255D\u255A\u2550\u255D     \u255A\u2550\u255D     \u255A\u2550\u2550\u2550\u2550\u2550\u2550\u255D \u255A\u2550\u2550\u2550\u2550\u2550\u255D   \u255A\u2550\u255D   \u255A\u2550\u255D  \u255A\u2550\u255D   \u255A\u2550\u255D    \u255A\u2550\u2550\u2550\u2550\u2550\u255D \u255A\u2550\u255D  \u255A\u2550\u255D    \u255A\u2550\u2550\u2550\u2550\u2550\u255D  \u255A\u2550\u2550\u2550\u2550\u2550\u255D  \u255A\u2550\u2550\u2550\u2550\u2550\u255D  \u255A\u2550\u2550\u2550\u2550\u2550\u255D ");
        System.out.println("\n \noiBonjour et bienvenue dans cette application !\n" +
                "Chaque étape sera guidée pas à pas\n"+
                "Aller au menu principal ? oui/non\n"+
                "Si vous rentrez \"non\" vous fermerez l'application");
        
        try (Scanner reponse = new Scanner(System.in)) {
            String read;
            do{
                read = reponse.nextLine();
                if(read.toLowerCase().equals("oui")){
                    mainMenu();
                }
                else if(read.toLowerCase().equals("non")){
                    break;
                }
                else{
                    System.out.println("Veuillez rentrer une réponse valide (oui , non)");
                }

            }while(!(read.toLowerCase().equals("oui"))||read.toLowerCase().equals("non"));
        }
    }
}