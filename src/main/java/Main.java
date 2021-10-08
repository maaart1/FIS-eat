import java.io.Console;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.logo();
        main.page_accueil();
        main.sc.close();
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

    public int choix() {
        System.out.println("\t 1 - Inscription");
        System.out.println("\t 2 - Connexion");
        int choix = Integer.parseInt(this.sc.nextLine());
        return choix;
    }
    public void page_accueil() throws IOException {
        int choix = choix();
        while (choix != 1 && choix != 2) {
            choix = choix();
        }
        switch (choix) {
            case 1 -> incription();
            case 2 -> connexion();
        }
    }

    public void incription() {
        System.out.println("Nom : ");
        String nom_client = this.sc.nextLine();

        Client client = new Client(nom_client);
    }

    public void connexion() {
        System.out.println("Numéro client : ");
        int numero_client = Integer.parseInt(this.sc.nextLine());

        Client client = Client.get_client_by_id(numero_client);
        if (client == null) System.out.println("Numéro de client invalide");
        else System.out.println("Bonjour " + client.getNom());
    }
}
