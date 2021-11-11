package util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fr.*;
import thread.Cuisine;

public class MenuLayouts {
    public Scanner sc = new Scanner(System.in);

    public List<Commande> en_attente = new ArrayList<>();

    public Cuisine cuisine = new Cuisine(2, this);


    public void logo() {
        System.out.println("███████╗██╗███████╗  ██   ███████╗ █████╗ ████████╗\n" +
                           "██╔════╝██║██╔════╝  █    ██╔════╝██╔══██╗╚══██╔══╝\n" +
                           "█████╗  ██║███████╗       █████╗  ███████║   ██║   \n" +
                           "██╔══╝  ██║╚════██║       ██╔══╝  ██╔══██║   ██║   \n" +
                           "██║     ██║███████║       ███████╗██║  ██║   ██║   \n" +
                           "╚═╝     ╚═╝╚══════╝       ╚══════╝╚═╝  ╚═╝   ╚═╝   \n");
    }

    public int choix_accueil() {
        MenuLayouts.clear_screen();
        this.logo();
        // cuisine.start();
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
        Client client = new Client("Invité");
        Commande commande = new Commande(client);
        this.menu(client, commande);
    }

    public void incription() {
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
            Commande commande = new Commande(client);
            this.menu(client, commande);
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
        if (choix.equals("")) return Integer.parseInt("10");
        return Integer.parseInt(choix);
    }

    public void menu(Client client, Commande commande) {
        MenuLayouts.clear_screen();
        System.out.println("Bonjour " + client.get_nom() + " :)");
        System.out.println("\t 1 - Passer une commande");
        System.out.println("\t 2 - Historique des commandes");
        System.out.println("\t 3 - Déconnecter");
        System.out.print("Choix ? ");
        int choix = verification_choix(this.sc.nextLine());
        switch (choix) {
            case 1 -> passer_commande(client, commande);
            case 2 -> historique_commandes(client, commande);
            case 3 -> deconnecter();
            default -> {
                System.out.println("\t --------------------------------------------------------");
                System.out.println("Erreur de saisie !");
                System.out.println("Appuyer sur entrée pour continuer ! ");
                this.sc.nextLine();
                this.menu(client, commande);
            }
        }
    }

    public void deconnecter() {
        System.out.println("Merci de votre visite ;)");
        this.page_accueil();
    }

    public void historique_commandes(Client client, Commande commande) {
        MenuLayouts.clear_screen();
        System.out.println("Historique de vos commandes : \n");
        System.out.print(client.getHistorique_commandes_to_String());
        this.sc.nextLine();
        this.menu(client, commande);
    }

    public void passer_commande(Client client, Commande commande) {
        //System.out.println("\t --------------------------------------------------------");
        MenuLayouts.clear_screen();
        System.out.println("Passer commande : ");
        System.out.println("\t 1 - Commander un menu");
        System.out.println("\t 2 - Commander en hors menu");
        System.out.println("\t 3 - Afficher la commande en cours");
        System.out.println("\t 4 - Annuler");
        System.out.print("Choix ? ");
        int choix = verification_choix(this.sc.nextLine());
        switch (choix) {
            case 1 -> {
                Plat burger_classique = FileManager.get_plat_by_id(1);
                Plat salade_veggie = FileManager.get_plat_by_id(7);
                Plat grande_faim = FileManager.get_plat_by_id(4);
                System.out.println("\t --------------------------------------------------------");
                System.out.println("\t 1 - " + burger_classique.getNom() + " (7.00 €) : " + burger_classique.getIngredients_toString());
                System.out.println("\t 2 - " + salade_veggie.getNom() + " (9.00 €) : " + salade_veggie.getIngredients_toString());
                System.out.println("\t 3 - " + grande_faim.getNom() + " (12.00 €) : " + grande_faim.getIngredients_toString());
                System.out.println("\t 4 - Commander un menu en choix libre (8.00 €)");
                System.out.println("\t 5 - Annuler");
                System.out.print("Choix ? ");
                int next_choix = verification_choix(this.sc.nextLine());
                switch (next_choix) {
                    case 1 -> {
                        commande.ajouter_menu(Menu.get_menu_by_id(1));
                        this.fin_commande(client, commande, "Menu");
                    }
                    case 2 -> {
                        commande.ajouter_menu(Menu.get_menu_by_id(2));
                        this.fin_commande(client, commande, "Menu");
                    }
                    case 3 -> {
                        commande.ajouter_menu(Menu.get_menu_by_id(3));
                        this.fin_commande(client, commande, "Menu");
                    }
                    case 4 -> {
                        this.afficher_plats(false);
                        System.out.print("Choix ? ");
                        int plat = verification_choix(this.sc.nextLine());
                        while (plat < 1 || plat > 7) {
                            System.out.println("\t --------------------------------------------------------");
                            System.out.println("Erreur de saisie !");
                            System.out.println("Appuyer sur entrée pour continuer ! ");
                            this.sc.nextLine();
                            this.afficher_plats(false);
                            System.out.print("Choix ? ");
                            plat = verification_choix(this.sc.nextLine());
                        }

                        commande.ajouter_menu(new Menu("Menu choix libre",
                                FileManager.get_boisson_by_id(this.choix_boisson()),
                                FileManager.get_plat_by_id(plat),
                                FileManager.get_accompagnement_by_id(this.choix_accompagnements()), 8.00));
                        /*commande.ajouter_plat(Plat.get_plat_by_id(plat));
                        commande.ajouter_boisson(Boisson.get_boisson_by_id(this.choix_boisson()));
                        commande.ajouter_accompagnement(Accompagnement.get_accompagnement_by_id(this.choix_accompagnements()));*/

                        this.fin_commande(client, commande, "Menu");
                    }
                    case 5 -> this.menu(client, commande);
                    default -> {
                        System.out.println("Erreur de saisie !");
                        System.out.println("Appuyer sur entrée pour continuer ! ");
                        this.sc.nextLine();
                        this.passer_commande(client, commande);
                    }
                }
            }
            case 2 -> {
                System.out.println("\t --------------------------------------------------------");
                System.out.println("\t 1 - Un plat");
                System.out.println("\t 2 - Une boisson");
                System.out.println("\t 3 - Un accompagnement");
                System.out.print("Choix ? ");
                int next_next_choix = verification_choix(this.sc.nextLine());
                switch (next_next_choix) {
                    case 1 -> {
                        commande.ajouter_plat(FileManager.get_plat_by_id(this.choix_plat_hors_menu()));
                        this.fin_commande(client, commande, "Produit");
                    }
                    case 2 -> {
                        commande.ajouter_boisson(FileManager.get_boisson_by_id(this.choix_boisson()));
                        this.fin_commande(client, commande,"Produit");
                    }
                    case 3 -> {
                        commande.ajouter_accompagnement(FileManager.get_accompagnement_by_id(this.choix_accompagnements()));
                        this.fin_commande(client, commande, "Produit");
                    }
                }
            }
            case 3 -> {
                MenuLayouts.clear_screen();
                System.out.println(commande.afficher_commande_en_cours());
                this.fin_commande(client, commande, "non");
            }
            case 4 -> {
                commande = new Commande(client);
                this.menu(client, commande);
            }
            default -> {
                System.out.println("Erreur de saisie !");
                System.out.println("Appuyer sur entrée pour continuer ! ");
                this.sc.nextLine();
                this.passer_commande(client, commande);
            }
        }
    }

    public void fin_commande(Client client, Commande commande, String type) {
        if (!type.equals("non")) System.out.println("\t " + type  + " ajouté :)");
        System.out.print("\t Fin de la commande ? (oui/non) ");
        String fin = this.sc.nextLine();
        switch (fin) {
            case "oui" -> this.validation_commande(client, commande);
            case "non" -> this.passer_commande(client, commande);
            default -> {
                System.out.println("\t --------------------------------------------------------");
                this.fin_commande(client, commande, type);
            }
        }
    }

    public void validation_commande(Client client, Commande commande) {
        //System.out.println("\t --------------------------------------------------------");
        MenuLayouts.clear_screen();
        System.out.println("Récapitulatif de la commande :");
        System.out.println(commande.afficher_commande_en_cours());
        System.out.print("Valider et payer ? (oui/non) ");
        String valider_et_payer = this.sc.nextLine();
        switch (valider_et_payer) {
            case "oui" -> {
                System.out.println("\t Sauvegarde de la commande...");
                client.getHistorique_commandes().add(commande);
                client.sauvegarder_client();
                System.out.println("\t Votre commande numéro " + commande.getNumero_commande() + " a été envoyée à la cuisine !");
                this.en_attente.add(commande);
                System.out.println("\t Temps de préparation estimé à " + commande.get_duree_commande() + " secondes");
                cuisine.start();
                System.out.println("Appuyer sur entrée pour continuer ! ");
                this.sc.nextLine();
                commande = new Commande(client);
                //this.menu(client, commande);
                this.page_accueil();
            }
            case "non" -> {
                System.out.println("Abandon de la commande...");
                commande = new Commande(client);
                this.menu(client, commande);
            }
            default -> {
                this.validation_commande(client, commande);
            }
        }
    }

    public int choix_plat_hors_menu() {
        this.afficher_plats(true);
        System.out.print("Choix ? ");
        int plat = verification_choix(this.sc.nextLine());
        while (plat != 1 && plat != 2 && plat != 3 && plat != 5 && plat != 6) {
            System.out.println("\t --------------------------------------------------------");
            System.out.println("Erreur de saisie !");
            System.out.println("Appuyer sur entrée pour continuer ! ");
            this.sc.nextLine();
            this.afficher_plats(true);
            System.out.print("Choix ? ");
            plat = verification_choix(this.sc.nextLine());
        }
        return plat;
    }

    public int choix_boisson() {
        this.afficher_boissons();
        System.out.print("Choix ? ");
        int boisson = verification_choix(this.sc.nextLine());
        while (boisson < 1 || boisson > 9) {
            System.out.println("\t --------------------------------------------------------");
            System.out.println("Erreur de saisie !");
            System.out.println("Appuyer sur entrée pour continuer ! ");
            this.sc.nextLine();
            this.afficher_boissons();
            System.out.print("Choix ? ");
            boisson = verification_choix(this.sc.nextLine());
        }
        return boisson;
    }

    public int choix_accompagnements() {
        this.afficher_accompagnements();
        System.out.print("Choix ? ");
        int accompagnement = verification_choix(this.sc.nextLine());
        while (accompagnement != 1 && accompagnement != 2) {
            System.out.println("\t --------------------------------------------------------");
            System.out.println("Erreur de saisie !");
            System.out.println("Appuyer sur entrée pour continuer ! ");
            this.sc.nextLine();
            this.afficher_accompagnements();
            System.out.print("Choix ? ");
            accompagnement = verification_choix(this.sc.nextLine());
        }
        return accompagnement;
    }

    public void afficher_boissons() {
        System.out.println("Choisir une boisson :");
        int nombres_boissons = Produit.get_nombres_produits(System.getProperty("user.dir") + "/bdd/produits/boissons/");
        for (int i = 1; i <= nombres_boissons - 1; i++) {
            Boisson b = FileManager.get_boisson_by_id(i);
            System.out.println("\t" + b);
        }
    }

    public void afficher_plats(boolean hors_menu) {
        System.out.println("Choisir un plat :");
        int nombres_plats = Produit.get_nombres_produits(System.getProperty("user.dir") + "/bdd/produits/plats/");
        for (int i = 1; i <= nombres_plats - 1; i++) {
            Plat p;
            if (hors_menu) {
                p = FileManager.get_plat_by_id_hors_menu(i);
                if (p != null) System.out.println("\t" + p.toString(true));
            }
            else {
                p = FileManager.get_plat_by_id(i);
                if (p != null) System.out.println("\t" + p.toString(false));
            }
        }
    }

    public void afficher_accompagnements() {
        System.out.println("Choisir un accomgnement :");
        int nombres_accompagnements = Produit.get_nombres_produits(System.getProperty("user.dir") + "/bdd/produits/accompagnements/");
        for (int i = 1; i <= nombres_accompagnements - 1; i++) {
            Accompagnement a = FileManager.get_accompagnement_by_id(i);
            System.out.println("\t" + a);
        }
    }


}
