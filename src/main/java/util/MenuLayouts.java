package util;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import fr.*;

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
        System.out.println("\t --------------------------------------------------------");
        System.out.println("Bonjour " + client.get_nom() + " :)");
        System.out.println(client.getHistorique_commandes(client));
        System.out.println("\t 1 - Passer une commande");
        System.out.println("\t 2 - Historique des commandes");
        System.out.println("\t 3 - Déconnecter");
        System.out.print("Choix ? ");
        int choix = verification_choix(this.sc.nextLine());
        switch (choix) {
            case 1 -> passer_commande(client);
            case 2 -> historique_commandes();
            case 3 -> deconnecter();

        }
    }

    public void deconnecter() {
        System.out.println("Merci de votre visite ;)");
        this.page_accueil();
    }

    public void historique_commandes() {
        System.out.println("Historique de vos commandes : ");
    }

    public void passer_commande(Client client) {
        Commande commande = new Commande(client);
        System.out.println("\t --------------------------------------------------------");
        System.out.println("Passer commande : ");
        // Choix menu ou hors menu
        System.out.println("\t 1 - Commander un menu");
        System.out.println("\t 2 - Commander en hors menu");
        System.out.println("\t 3 - Annuler");
        System.out.print("Choix ? ");
        int choix = verification_choix(this.sc.nextLine());
        switch (choix) {
            // TODO
            case 1 -> {
                System.out.println("\t --------------------------------------------------------");
                System.out.println("\t 1 - ");
                System.out.println("\t 2 - ");
                System.out.println("\t 3 - ");
                System.out.println("\t 4 - Commander un menu en choix libre (8.00 €)");
                System.out.println("\t 5 - Annuler");
                System.out.print("Choix ? ");
                int next_choix = verification_choix(this.sc.nextLine());
                switch (next_choix) {
                    case 4 -> {
                        System.out.println("Choisir un plat :");
                        // TODO Afficher que les plats qui sont possibles d'acheter en menu
                        int nombres_plats = Plat.get_nombres_plats();
                        for (int i = 1; i <= nombres_plats - 1; i++) {
                            Plat p = Plat.get_plat_by_id(i);
                            System.out.println(p);
                        }
                        System.out.print("Choix ? ");
                        int next_next_choix = verification_choix(this.sc.nextLine());

                    }
                    case 5 -> {
                        this.menu(client);
                    }
                    default -> {
                        System.out.println("Erreur de saisie !");
                        System.out.println("Appuyer sur entrée pour continuer ! ");
                        this.sc.nextLine();
                        this.menu(client);
                    }
                }
            }
            // TODO
            case 2 -> {
                System.out.println("Historique de vos commandes : ");
                client.getHistorique_commandes(client);
            }
            case 3 -> {
                this.menu(client);
            }
            default -> {
                System.out.println("Erreur de saisie !");
                System.out.println("Appuyer sur entrée pour continuer ! ");
                this.sc.nextLine();
                this.menu(client);
            }
        }

    }

    public void afficher_boissons() {

    }

    public void afficher_accompagnements() {

    }

    public static void main(String[] args) {
        Plat p = new Plat("Burger classique", new ArrayList<>(){
            {
                add(Ingredient.get_ingredient_by_id(1));
                add(Ingredient.get_ingredient_by_id(2));
                add(Ingredient.get_ingredient_by_id(3));
                add(Ingredient.get_ingredient_by_id(4));
                add(Ingredient.get_ingredient_by_id(5));
                add(Ingredient.get_ingredient_by_id(6));
                add(Ingredient.get_ingredient_by_id(7));
            }
        }, true, 6.00);
        p.sauvegarder_plat();
        Plat p1 = new Plat("Nuggets", new ArrayList<>(){
            {
                add(Ingredient.get_ingredient_by_id(8));
            }
        }, true, 6.00);
        p1.sauvegarder_plat();
        Plat p2 = new Plat("Croque Monsieur", new ArrayList<>(){
            {
                add(Ingredient.get_ingredient_by_id(10));
                add(Ingredient.get_ingredient_by_id(9));
                add(Ingredient.get_ingredient_by_id(11));
            }
        }, true, 12.50);
        p2.sauvegarder_plat();
        Plat p3 = new Plat("Big Burger", new ArrayList<>(){
            {
                add(Ingredient.get_ingredient_by_id(1));
                add(Ingredient.get_ingredient_by_id(2));
                add(Ingredient.get_ingredient_by_id(2));
                add(Ingredient.get_ingredient_by_id(2));
                add(Ingredient.get_ingredient_by_id(4));
                add(Ingredient.get_ingredient_by_id(4));
                add(Ingredient.get_ingredient_by_id(4));
                add(Ingredient.get_ingredient_by_id(4));
                add(Ingredient.get_ingredient_by_id(3));
                add(Ingredient.get_ingredient_by_id(5));
                add(Ingredient.get_ingredient_by_id(6));
                add(Ingredient.get_ingredient_by_id(7));
            }
        }, false, 0);
        p3.sauvegarder_plat();
        Plat p4 = new Plat("Fish Burger", new ArrayList<>(){
            {
                add(Ingredient.get_ingredient_by_id(1));
                add(Ingredient.get_ingredient_by_id(7));
                add(Ingredient.get_ingredient_by_id(12));
            }
        }, true, 8.00);
        p4.sauvegarder_plat();
        Plat p5 = new Plat("Chicken Burger", new ArrayList<>(){
            {
                add(Ingredient.get_ingredient_by_id(1));
                add(Ingredient.get_ingredient_by_id(8));
                add(Ingredient.get_ingredient_by_id(5));
                add(Ingredient.get_ingredient_by_id(7));
            }
        }, true, 8.00);
        p5.sauvegarder_plat();
        Plat p6 = new Plat("Salade Veggie", new ArrayList<>(){
            {
                add(Ingredient.get_ingredient_by_id(5));
                add(Ingredient.get_ingredient_by_id(15));
                add(Ingredient.get_ingredient_by_id(16));
                add(Ingredient.get_ingredient_by_id(13));
                add(Ingredient.get_ingredient_by_id(14));
                add(Ingredient.get_ingredient_by_id(17));
            }
        }, false, 0.00);
        p6.sauvegarder_plat();
    }

}
