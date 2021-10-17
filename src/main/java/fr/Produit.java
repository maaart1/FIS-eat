package fr;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

abstract public class Produit implements Serializable {
    @Serial
    private static final long serialVersionUID = -7331025968631394285L;

    private String nom;
    private List<Ingredient> ingredients;
    private boolean hors_menu;

    public Produit(String nom, List<Ingredient> ingredients) {
        this.nom = nom;
        this.ingredients = ingredients;
    }

    abstract public void sauvegarder_produit();

    public static Produit get_produit_by_id(String path, int numero_produit) {
        File file = new File(System.getProperty("user.dir") + "/bdd/produits/" + path + "/" + numero_produit + ".ser");
        System.out.println(file.getPath());
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInput objectInput = new ObjectInputStream(fileInputStream);
            return (Produit) objectInput.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean exist(String path, int numero_produit) {
        File file = new File(System.getProperty("user.dir") + "/bdd/produits/" + path  + "/" + numero_produit + ".ser");
        //return file.exists() ? true : false;
        if (file.exists()) return true;
        else return false;
    }

    public int get_nombres_produits(String path) {
        File directory = new File(path);
        File[] content_files = directory.listFiles();
        return content_files.length == 0 ? 1 : content_files.length + 1;
    }
}
