package fr;

import java.io.*;
import java.util.List;

/**
 * Classe Boisson : La classe Boisson hérite de Produit et impléments Serializable.
 * Elle définit une boisson.
 */
public class Boisson extends Produit implements Serializable {
    @Serial
    private static final long serialVersionUID = 8431741446491892204L;

    private int numero_boisson;
    private double prix;

    public Boisson(String nom, double prix) {
        super(nom);
        this.prix = prix;
        this.numero_boisson = Produit.get_nombres_produits(System.getProperty("user.dir") + "/bdd/produits/boissons/");
    }

    public void sauvegarder_boisson() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream( System.getProperty("user.dir") + "/bdd/produits/boissons/" + this.numero_boisson + ".ser");
            ObjectOutput objectOutput = new ObjectOutputStream(fileOutputStream);
            objectOutput.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getNom() {
        return super.getNom();
    }

    @Override
    public String toString() {
        return this.numero_boisson + " : " + super.getNom() + " (" + this.prix + " €)";
    }

    public double getPrix() {
        return prix;
    }
}
