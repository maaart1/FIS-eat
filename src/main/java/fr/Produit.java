package fr;

import java.io.*;

/**
 * Classe Produit : Classe mère qui implements Serializable.
 */
public class Produit implements Serializable {
    @Serial
    private static final long serialVersionUID = -7331025968631394285L;

    private String nom;

    public Produit(String nom) {
        this.nom = nom;
    }

    public static int get_nombres_produits(String path) {
        File directory = new File(path);
        File[] content_files = directory.listFiles();
        return content_files.length == 0 ? 1 : content_files.length + 1;
    }

    public String getNom() {
        return nom;
    }
}
