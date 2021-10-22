package util;

import java.io.File;
import java.util.Scanner;

import fr.Boisson;
import fr.Client;
import fr.Commande;
import fr.Plat;

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
        System.out.println("\t 3 - Connexion en invité");
        System.out.print("Choix ? ");
        String choix = this.sc.nextLine();
        return verification_choix(choix);
    }

    public void page_accueil() {
        int choix = this.choix_accueil();
        while (choix != 1 && choix != 2 && choix != 3) {
            choix = choix_accueil();
        }
        switch (choix) {
            case 1 -> this.incription();
            case 2 -> this.connexion();
            case 3 -> this.connexion_invite();
        }
    }

    public void connexion_invite() {
        System.out.println("Bonjour invité :)");
        this.menu(new Client("Invité"));
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
            this.menu(client);
        }
    }

    public static void clear_screen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
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

    public void menu(Client client) {
        System.out.println("\t 1 - Passer une commande");
        System.out.println("\t 2 - Historique des commandes");
        System.out.println("\t 3 - Quitter");
        System.out.print("Choix ? ");
        int choix = verification_choix(this.sc.nextLine());
        switch (choix) {
            case 1 -> passer_commande(client);
            case 2 -> historique_commandes();
            case 3 -> quitter();

        }
    }

    public void quitter() {
        System.out.println("Merci de votre visite ;)");
        System.exit(0);
    }

    public void historique_commandes() {
        System.out.println("Historique de vos commandes : ");
    }

    public void passer_commande(Client client) {
        Commande commande = new Commande(client.get_numero_client());
        System.out.println("Passer commande : ");
        // Choix menu ou hors menu
        System.out.println("\t 1 - Commande en menu");
        System.out.println("\t 2 - Commande en hors menu");
        System.out.print("Choix ? ");
        int choix = verification_choix(this.sc.nextLine());
        switch (choix) {
            case 1 -> {
                System.out.println("Choisir un plat : ");
                int nombres_plats = Plat.get_nombres_plats();
                for (int i = 1; i <= nombres_plats - 1; i++) {
                    Plat p = Plat.get_plat_by_id(i);
                    System.out.println(p);
                }
                System.out.print("Choix ? ");
            }
        }

    }

    public void afficher_boissons() {

    }

    public void afficher_accompagnements() {

    }

}
