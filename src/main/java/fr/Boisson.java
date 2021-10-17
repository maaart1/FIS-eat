package fr;

import java.io.*;
import java.util.List;

public class Boisson extends Produit implements Serializable {
    @Serial
    private static final long serialVersionUID = 8431741446491892204L;

    private int numero_boisson;

    public Boisson(String nom, List<Ingredient> ingredients) {
        super(nom, ingredients);
        this.numero_boisson = this.get_nombres_produits(System.getProperty("user.dir") + "/bdd/produits/boissons/");
    }

    @Override
    public void sauvegarder_produit() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream( System.getProperty("user.dir") + "/bdd/produits/boissons/" + this.numero_boisson + ".ser");
            ObjectOutput objectOutput = new ObjectOutputStream(fileOutputStream);
            objectOutput.writeObject(this);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
