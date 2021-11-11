package fr;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Classe Commande : La classe Commande implements Serializable.
 * ELle définit une commande.
 */
public class Commande implements Serializable {
    @Serial
    private static final long serialVersionUID = -3804444274228500039L;

    private int numero_commande;
    private Client client;
    private LocalDate date;
    private double prix;
    private List<Menu> menus;
    private List<Plat> plats;
    private List<Accompagnement> accompagnements;
    private List<Boisson> boissons;

    public Commande(Client client) {
        this.client = client;
        this.date = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth());
        this.plats = new ArrayList<>();
        this.boissons = new ArrayList<>();
        this.accompagnements = new ArrayList<>();
        this.menus = new ArrayList<>();
        this.numero_commande = this.client.getHistorique_commandes().size();
    }

    public int getNumero_commande() {
        return numero_commande;
    }

    public Client getClient() {
        return client;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Commande n°").append(numero_commande)
                .append(" : \n")
                .append("\t Date : ")
                .append(date)
                .append("\n");
        stringBuilder.append(this.afficher_commande_list())
                .append("\n");
        return stringBuilder.toString();
    }

    public void ajouter_menu(Menu menu) {
        this.menus.add(menu);
    }

    public void ajouter_plat(Plat plat) {
        this.plats.add(plat);
    }

    public void ajouter_boisson(Boisson boisson) {
        this.boissons.add(boisson);
    }

    public void ajouter_accompagnement(Accompagnement accompagnement) {
        this.accompagnements.add(accompagnement);
    }

    public String afficher_commande_en_cours() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.afficher_commande_list());
        return stringBuilder.toString();
    }

    private String afficher_commande_list() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t Menus : ");
        for (Menu menu: this.menus) {
            sb.append(menu.getNom()).append(", ");
        }

        sb.append("\n\t Boissons : ");
        for (Boisson boisson: this.boissons) {
            sb.append(boisson.getNom()).append(", ");
        }

        sb.append("\n\t Plats : ");
        for (Plat plat: this.plats) {
            sb.append(plat.getNom()).append(", ");
        }

        sb.append("\n\t Accompagnements : ");
        for (Accompagnement accompagnement: this.accompagnements) {
            sb.append(accompagnement.getNom()).append(", ");
        }
        sb.append("\n")
                .append("\n\t Prix de la commande : ")
                .append(this.get_commande_prix())
                .append(" €");
        sb.append("\n\t --------------------------------------------------------");
        return sb.toString();
    }


    public double get_commande_prix() {
        double prix_tmp = 0;
        for (Menu menu: this.menus) {
            prix_tmp += menu.getPrix();
        }

        for (Boisson boisson: this.boissons) {
            prix_tmp += boisson.getPrix();
        }

        for (Plat plat: this.plats) {
            prix_tmp += plat.getPrix();
        }

        for (Accompagnement accompagnement: this.accompagnements) {
            prix_tmp += accompagnement.getPrix();
        }
        this.setPrix(prix_tmp);
        return this.prix;
    }

    public int get_duree_commande() {
        int temps = 0;
        for (Menu menu: this.menus) {
            temps += menu.getAccompagnement().getTemps_preparation();
            for (Ingredient ingredient: menu.getPlat().getIngredients()) {
                temps += ingredient.getTemps_preparation();
            }
        }

        for (Plat plat: this.plats) {
            for (Ingredient ingredient: plat.getIngredients() ) {
                temps += ingredient.getTemps_preparation();
            }
        }

        for (Accompagnement accompagnement: this.accompagnements) {
            temps += accompagnement.getTemps_preparation();
        }
        return temps;
    }
}
