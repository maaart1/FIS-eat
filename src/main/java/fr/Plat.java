package fr;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Plat extends Produit implements Serializable {
    @Serial
    private static final long serialVersionUID = -2203171846142637339L;

    private int numero_plat;

    public Plat(String nom, List<Ingredient> ingredients) {
        super(nom, ingredients);
        this.numero_plat = this.get_nombres_produits(System.getProperty("user.dir") + "/bdd/produits/plats/");
    }

    @Override
    public void sauvegarder_produit() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream( System.getProperty("user.dir") + "/bdd/produits/plats/" + this.numero_plat + ".ser");
            ObjectOutput objectOutput = new ObjectOutputStream(fileOutputStream);
            objectOutput.writeObject(this);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Plat get_produit_by_id(String path, int numero_produit) {
        File file = new File(System.getProperty("user.dir") + "/bdd/produits/" + path + "/" + numero_produit + ".ser");
        System.out.println(file.getPath());
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInput objectInput = new ObjectInputStream(fileInputStream);
            return (Plat) objectInput.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*public static void main(String[] args) {
        Plat p = new Plat("Burger classique", new ArrayList<>(){
            {
                add(new Ingredient("Pain burger", true, "Au grill", 10));
                add(new Ingredient("Steak", true, "Au grill", 20));
                add(new Ingredient("Salade", false, "", 5));
                add(new Ingredient("Tomate", false, "", 5));
                add(new Ingredient("Oignons", false, "", 5));
                add(new Ingredient("Ketchup", false, "", 3));
            }
        });
        p.sauvegarder_produit();
    }*/
}
