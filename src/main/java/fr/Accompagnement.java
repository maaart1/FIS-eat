package fr;

import java.io.*;
import java.util.List;

public class Accompagnement extends Produit implements Serializable {
    @Serial
    private static final long serialVersionUID = 7072625591871715646L;

    // private String nom;
    private int numero_accompagnement;
    private double prix;
    private int temps_preparation;

    public Accompagnement(String nom, double prix, int temps_preparation) {
        super(nom);
        //this.nom = nom;
        this.prix = prix;
        this.temps_preparation = temps_preparation;
        this.numero_accompagnement = Produit.get_nombres_produits(System.getProperty("user.dir") + "/bdd/produits/accompagnements/");
    }

    public void sauvegarder_accompagnement() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream( System.getProperty("user.dir") + "/bdd/produits/accompagnements/" + this.numero_accompagnement + ".ser");
            ObjectOutput objectOutput = new ObjectOutputStream(fileOutputStream);
            objectOutput.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*public static Accompagnement get_accompagnement_by_id(int numero_accompagnement) {
        File file = new File(System.getProperty("user.dir") + "/bdd/produits/accompagnements/" + numero_accompagnement + ".ser");
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInput objectInput = new ObjectInputStream(fileInputStream);
            return (Accompagnement) objectInput.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }*/

//    public static int get_nombres_accompagnements() {
//        File directory = new File(System.getProperty("user.dir") + "/bdd/produits/accompagnements/");
//        File[] content_files = directory.listFiles();
//        return content_files.length == 0 ? 1 : content_files.length + 1;
//    }

    public String getNom() {
        return super.getNom();
    }

    @Override
    public String toString() {
        return this.numero_accompagnement + " : " + super.getNom() + " (" + this.prix + " €) ";
    }

    public double getPrix() {
        return prix;
    }

    public int getTemps_preparation() {
        return temps_preparation;
    }
}
