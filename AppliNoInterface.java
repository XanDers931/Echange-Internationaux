import java.io.File;
import java.util.Scanner;

import voyages.CountryName;
import voyages.Teenager;
import voyages.Tuple;
import voyages.CsvFileImportator;
import voyages.Platform;

public class AppliNoInterface {
    
    private static void mainMenu() {
        //Déclaration des varibes utilisées plus loin
        Platform p = new Platform();
        
        System.out.println("Main Menu");
        String chemin = askChemin();

        System.out.println("Importation en cours...");        
        
        p.importTeenagerFromCsv(new File(chemin), false);

        System.out.println("Importation terminée\n");

        System.out.println("Vous allez maintenant pouvoir sélectionner deux pays avec lesquels vous souhaitez faire les associations d'étudiants");
        Tuple<CountryName> assoPays =  askPays();

        askForSomething(assoPays,p);

    }

    public static String askChemin(){
        System.out.println("Veuillez entrer le chemin d'accés au fichier csv que vous souhaitez importer :");
        String chemin;
        try(Scanner reponse = new Scanner(System.in)){
            do{
                chemin = reponse.nextLine();
                if(!chemin.contains(".csv")){
                    System.out.println("Le chemin ne mène pas vers un fichier csv");
                    System.out.println("Veuillez saisir un chemin correct");
                }
                
            }while(!chemin.contains(".csv"));
        }
        System.out.print('\u000C');
        return chemin;
    }

    public static void askForSomething(Tuple<CountryName> assoPays, Platform p){
        System.out.println("Que souhaitez vous faire ?\n");
        System.out.println("1. Forcer une association entre deux étudiants");
        System.out.println("2. Empecher une association entre deux étudiants");
        System.out.println("3. Associer de manière automatique est optimlisé les étudiants");
        System.out.println("4. Modifier les coefficients d'affectations");
        System.out.println("5. Modifier les deux pays d'échanges");
        System.out.println("6. Quitter l'application");

        try (Scanner reponse = new Scanner(System.in)) {
            String read;
            do{
                read = reponse.nextLine();
                if(read.equals("1")){
                    associationForced();
                }
                else if(read.equals("2")){
                    associationForbidden();
                }
                else if(read.equals("3")){
                    associationAuto();
                }
                else if(read.equals("4")){
                    coeffChange();
                }
                else if(read.equals("5")){
                    askPays();
                }
                else if(read.equals("6")){
                    break;
                }
                else{
                    System.out.println("Veuillez rentrer une réponse valide");
                }

            }while(!(read.equals("1"))||read.equals("2")||read.equals("3")||read.equals("4")||read.equals("5")||read.equals("6"));
        }
    }

    public static Tuple<CountryName> askPays(){
        Tuple<CountryName> assoPays = new Tuple<>();

        System.out.println("Sélectionner Pays hôte :");
        System.out.println("1. France");
        System.out.println("2. Italy");
        System.out.println("3. Spain");
        System.out.println("4. Germany");

        try (Scanner hote = new Scanner(System.in)) {
            String readHote;
            do{
                readHote = hote.nextLine();
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

            }while(!(readHote.equals("1"))||readHote.equals("2")||readHote.equals("3")||readHote.equals("4"));
        }
        
        System.out.println("Sélectionner Pays visiteur:");
        System.out.println("1. France");
        System.out.println("2. Italy");
        System.out.println("3. Spain");
        System.out.println("4. Germany");

        try (Scanner visiteur = new Scanner(System.in)) {
            String readVisiteur;
            do{
                readVisiteur = visiteur.nextLine();
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

                if(assoPays.getFirst().equals(assoPays.getSecond())){
                    System.out.println("Attention vous ne pouvez pas associer deux fois le même pays");
                }

            }while(!(readVisiteur.equals("1"))||readVisiteur.equals("2")||readVisiteur.equals("3")||readVisiteur.equals("4")||assoPays.getFirst().equals(assoPays.getSecond()));
        }
            return assoPays;
    }
    

    public static void associationForced(){

    }

    public static void associationForbidden(){

    }

    public static void associationAuto(){
    }

    public static void coeffChange(){

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
                    System.out.print('\u000C');
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