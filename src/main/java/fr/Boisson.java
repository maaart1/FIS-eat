package fr;

import java.io.*;
import java.util.List;

public class Boisson implements Serializable {
    @Serial
    private static final long serialVersionUID = 8431741446491892204L;

    private String nom;
    private int numero_boisson;
    private double prix;

    public Boisson(String nom, double prix) {
        this.nom = nom;
        this.prix = prix;
        this.numero_boisson = Boisson.get_nombres_boissons();
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

    public static Boisson get_boisson_by_id(int numero_boisson) {
        File file = new File(System.getProperty("user.dir") + "/bdd/produits/boissons/" + numero_boisson + ".ser");
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInput objectInput = new ObjectInputStream(fileInputStream);
            return (Boisson) objectInput.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int get_nombres_boissons() {
        File directory = new File(System.getProperty("user.dir") + "/bdd/produits/boissons/");
        File[] content_files = directory.listFiles();
        return content_files.length == 0 ? 1 : content_files.length + 1;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return this.numero_boisson + " : " + this.nom + " (" + this.prix + " €)";
    }
}
