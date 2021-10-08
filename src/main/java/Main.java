import java.io.Console;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Main.logo();
        Main.page_accueil();
    }

    public static void logo() {
        System.out.println(" .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------. \n" +
                "| .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. |\n" +
                "| |  _________   | || |     _____    | || |    _______   | || |              | || |  _________   | || |      __      | || |  _________   | |\n" +
                "| | |_   ___  |  | || |    |_   _|   | || |   /  ___  |  | || |              | || | |_   ___  |  | || |     /  \\     | || | |  _   _  |  | |\n" +
                "| |   | |_  \\_|  | || |      | |     | || |  |  (__ \\_|  | || |    ______    | || |   | |_  \\_|  | || |    / /\\ \\    | || | |_/ | | \\_|  | |\n" +
                "| |   |  _|      | || |      | |     | || |   '.___`-.   | || |   |______|   | || |   |  _|  _   | || |   / ____ \\   | || |     | |      | |\n" +
                "| |  _| |_       | || |     _| |_    | || |  |`\\____) |  | || |              | || |  _| |___/ |  | || | _/ /    \\ \\_ | || |    _| |_     | |\n" +
                "| | |_____|      | || |    |_____|   | || |  |_______.'  | || |              | || | |_________|  | || ||____|  |____|| || |   |_____|    | |\n" +
                "| |              | || |              | || |              | || |              | || |              | || |              | || |              | |\n" +
                "| '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' |\n" +
                " '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------' ");
    }

    public static void page_accueil() throws IOException {
        System.out.println("\t 1 - Inscription");
        System.out.println("\t 2 - Connexion");
        Scanner sc =  new Scanner(System.in);
        String choix = sc.nextLine();
        sc.close();
        if (choix.equals("1")) {
            Main.incription();
        } else if (choix.equals("2")) {
            Main.connexion();
        } else {
            System.out.println("Choix Impossible");
            Main.page_accueil();
        }
    }

    public static void incription() {
        Scanner sc =  new Scanner(System.in);
        System.out.println("Nom : ");
        String nom_client = sc.nextLine();
        System.out.println("Mot de passe : ");
        String mdp_client = sc.nextLine();

        Client client = new Client(nom_client, mdp_client);
    }

    public static void connexion() {
        System.out.println("connexion");

    }
}
