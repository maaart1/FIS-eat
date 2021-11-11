package fr;

import java.io.*;
import java.util.List;

/**
 * Classe Plat : La classe Plat hérite de Produit et impléments Serializable.
 * Elle définit un plat.
 */
public class Plat extends Produit implements Serializable {
    @Serial
    private static final long serialVersionUID = -2203171846142637339L;

    private List<Ingredient> ingredients;
    private int numero_plat;
    private boolean hors_menu;
    private double prix;

    public Plat(String nom, List<Ingredient> ingredients, boolean hors_menu, double prix) {
        super(nom);
        this.ingredients = ingredients;
        this.numero_plat = Produit.get_nombres_produits(System.getProperty("user.dir") + "/bdd/produits/plats/");
        this.hors_menu = hors_menu;
        this.prix = prix;
    }

    public void sauvegarder_plat() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream( System.getProperty("user.dir") + "/bdd/produits/plats/" + this.numero_plat + ".ser");
            ObjectOutput objectOutput = new ObjectOutputStream(fileOutputStream);
            objectOutput.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public String getIngredients_toString() {
        StringBuilder stringBuilder =  new StringBuilder();
        for (Ingredient ingredient : this.ingredients) {
            stringBuilder.append(ingredient + ", ");
        }
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    public boolean isHors_menu() {
        return hors_menu;
    }

    public String toString(boolean with_prix) {
        StringBuilder stringBuilder =  new StringBuilder();
        stringBuilder.append(this.numero_plat)
                .append(" : ")
                .append(getNom());
        if (with_prix) {
            stringBuilder
                    .append(" (")
                    .append(this.prix)
                    .append(" €)");
        }
        stringBuilder.append(" : ");
        for (Ingredient ingredient : this.ingredients) {
            stringBuilder.append(ingredient)
                    .append(", ");
        }
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length() - 1);
        return stringBuilder.toString();
    }


    public double getPrix() {
        return prix;
    }
}
