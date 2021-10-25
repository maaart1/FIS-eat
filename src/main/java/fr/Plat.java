package fr;

import java.io.*;
import java.util.List;

public class Plat implements Serializable {
    @Serial
    private static final long serialVersionUID = -2203171846142637339L;

    private String nom;
    private List<Ingredient> ingredients;
    private int numero_plat;
    private boolean hors_menu;
    private double prix;

    public Plat(String nom, List<Ingredient> ingredients, boolean hors_menu, double prix) {
        this.nom = nom;
        this.ingredients = ingredients;
        this.numero_plat = Plat.get_nombres_plats();
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

    public static Plat get_plat_by_id(int numero_produit) {
        File file = new File(System.getProperty("user.dir") + "/bdd/produits/plats/" + numero_produit + ".ser");
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInput objectInput = new ObjectInputStream(fileInputStream);
            return (Plat) objectInput.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public String getNom() {
        return nom;
    }

    public static int get_nombres_plats() {
        File directory = new File(System.getProperty("user.dir") + "/bdd/produits/plats/");
        File[] content_files = directory.listFiles();
        return content_files.length == 0 ? 1 : content_files.length + 1;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder =  new StringBuilder();
        stringBuilder.append(this.numero_plat + " : " + this.nom + " (" + this.prix + ") : ");
        for (Ingredient ingredient : this.ingredients) {
            stringBuilder.append(ingredient + ", ");
        }
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length() - 1);
        return stringBuilder.toString();
    }
}
