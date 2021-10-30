package util;

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
        Client client = new Client("Invité");
        Commande commande = new Commande(client);
        this.menu(client, commande);
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
        return Integer.parseInt(choix);
    }

    public void menu(Client client, Commande commande) {
        System.out.println("\t --------------------------------------------------------");
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
        }
    }

    public void deconnecter() {
        System.out.println("Merci de votre visite ;)");
        this.page_accueil();
    }

    // TODO Afficher l'historique des commandes => il faut avoir finit la validation de la commande pour le faire
    public void historique_commandes(Client client, Commande commande) {
        System.out.println("Historique de vos commandes : ");
        System.out.print(client.getHistorique_commandes());
        this.sc.nextLine();
        this.menu(client, commande);
    }

    public void passer_commande(Client client, Commande commande) {
        System.out.println("\t --------------------------------------------------------");
        System.out.println("Passer commande : ");
        // Choix menu ou hors menu
        System.out.println("\t 1 - Commander un menu");
        System.out.println("\t 2 - Commander en hors menu");
        System.out.println("\t 3 - Afficher la commande en cours");
        System.out.println("\t 4 - Annuler");
        System.out.print("Choix ? ");
        int choix = verification_choix(this.sc.nextLine());
        switch (choix) {
            case 1 -> {
                System.out.println("\t --------------------------------------------------------");
                System.out.println("\t 1 - Le classique (7.00 €)");
                System.out.println("\t 2 - Le veggie (9.00 €)");
                System.out.println("\t 3 - La grande faim (12.00 €)");
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
                        commande.ajouter_plat(Plat.get_plat_by_id(plat));

                        commande.ajouter_boisson(Boisson.get_boisson_by_id(this.choix_boisson()));

                        commande.ajouter_accompagnement(Accompagnement.get_accompagnement_by_id(this.choix_accompagnements()));

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
                System.out.println("\t 3 - Un accomagnement");
                System.out.print("Choix ? ");
                int next_next_choix = verification_choix(this.sc.nextLine());
                switch (next_next_choix) {
                    case 1 -> {
                        commande.ajouter_plat(Plat.get_plat_by_id(this.choix_plat_hors_menu()));
                        this.fin_commande(client, commande, "Produit");
                    }
                    case 2 -> {
                        commande.ajouter_boisson(Boisson.get_boisson_by_id(this.choix_boisson()));
                        this.fin_commande(client, commande,"Produit");
                    }
                    case 3 -> {
                        commande.ajouter_accompagnement(Accompagnement.get_accompagnement_by_id(this.choix_accompagnements()));
                        this.fin_commande(client, commande, "Produit");
                    }
                }
            }
            case 3 -> {
                System.out.println(commande.afficher_commande_en_cours());
                this.fin_commande(client, commande, "non");
            }
            case 4 -> this.menu(client, commande);
            default -> {
                System.out.println("Erreur de saisie !");
                System.out.println("Appuyer sur entrée pour continuer ! ");
                this.sc.nextLine();
                this.passer_commande(client, commande);
            }
        }

    }

    // TODO Changer la route de la réponse lorsque le client dit "oui" (faire une validation de commande)
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
        System.out.println("\t --------------------------------------------------------");
        System.out.println("Récapitulatif de la commande :");
        System.out.println(commande.afficher_commande_en_cours());
        System.out.println("\t Valider et payer ? (oui/non)");
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
        int nombres_boissons = Boisson.get_nombres_boissons();
        for (int i = 1; i <= nombres_boissons - 1; i++) {
            Boisson b = Boisson.get_boisson_by_id(i);
            System.out.println("\t" + b);
        }
    }

    public void afficher_plats(boolean hors_menu) {
        System.out.println("Choisir un plat :");
        int nombres_plats = Plat.get_nombres_plats();
        for (int i = 1; i <= nombres_plats - 1; i++) {
            Plat p;
            if (hors_menu) p = Plat.get_plat_by_id_hors_menu(i);
            else p = Plat.get_plat_by_id(i);
            if (p != null) System.out.println("\t" + p);
        }
    }

    public void afficher_accompagnements() {
        System.out.println("Choisir un accomgnement :");
        int nombres_accompagnements = Accompagnement.get_nombres_accompagnements();
        for (int i = 1; i <= nombres_accompagnements - 1; i++) {
            Accompagnement a = Accompagnement.get_accompagnement_by_id(i);
            System.out.println("\t" + a);
        }
    }


}
