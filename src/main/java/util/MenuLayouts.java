package util;

import java.io.File;
import java.util.Scanner;
import fr.Client;
import fr.Plat;
import fr.Produit;

public class MenuLayouts {
    public Scanner sc = new Scanner(System.in);
    public void logo() {
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

    public int choix_accueil() {
        System.out.println("\t 1 - Inscription");
        System.out.println("\t 2 - Connexion");
        System.out.print("Choix ? ");
        String choix = this.sc.nextLine();
        return verification_choix(choix);
    }

    public void page_accueil() {
        int choix = this.choix_accueil();
        while (choix != 1 && choix != 2) {
            choix = choix_accueil();
        }
        switch (choix) {
            case 1 -> this.incription();
            case 2 -> this.connexion();
        }
    }

    public void incription() {
        MenuLayouts.clear_screen();
        System.out.print("Nom : ");
        String nom_client = this.sc.nextLine();

        Client client = new Client(nom_client);
        client.sauvegarder_client();
        System.out.println("Votre numéro client est : " + client.get_numero_client());
        System.out.println("Appuyer sur entrée pour continuer ! ");
        this.sc.nextLine();
        this.page_accueil();
    }

    public void connexion() {
        MenuLayouts.clear_screen();
        System.out.print("Numéro client : ");
        int numero_client = verification_choix(this.sc.nextLine());
        if (!Client.exist(numero_client)) {
            System.out.println("Numéro de client invalide !");
            System.out.println("Appuyer sur entrée pour continuer ! ");
            this.sc.nextLine();
            this.page_accueil();
        }
        else {
            Client client = Client.get_client_by_id(numero_client);
            System.out.println("Bonjour " + client.get_nom() + " :)");
            this.menu();
        }
    }

    public static void clear_screen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void choix_menu() {
        int choix = verification_choix(this.sc.nextLine());
        switch (choix) {
            case 1 -> passer_commande();
            case 2 -> historique_commandes();
            case 3 -> quitter();

        }
    }


    public int verification_choix(String choix) {
        if (choix.length() > 1) return 0;
        else {
            for (int i = 0; i < choix.length(); i++) {
                if (choix.charAt(i) < '1' || choix.charAt(i) > '9') {
                    return 0;
                }
            }
        }
        return Integer.parseInt(choix);
    }

    public void menu() {
        System.out.println("\t 1 - Passer une commande");
        System.out.println("\t 2 - Historique des commandes");
        System.out.println("\t 3 - Quitter");
        System.out.print("Choix ? ");
        choix_menu();

    }

    public void quitter() {
        System.out.println("Merci de votre visite ;)");
        System.exit(0);
    }

    public void historique_commandes() {
        System.out.println("Historique de vos commandes : ");
    }

    public void passer_commande() {
        System.out.println("Passer commande : ");
        this.afficher_plats();
    }

    public void afficher_plats() {
        File directory = new File(System.getProperty("user.dir") + "/bdd/produits/plats/");
        File[] content_files = directory.listFiles();
        if (Produit.exist("plats", 1)) {
            System.out.println("je suis la");
            System.out.println(Plat.get_produit_by_id("plats", 1));
        }
        /*for (int i = 0; i < content_files.length; i++) {
            System.out.println(Produit.get_produit_by_id("plats", ++i));
        }*/
    }

    public void afficher_boissons() {

    }

    public void afficher_accompagnements() {

    }

}
