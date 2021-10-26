package fr;

import java.io.*;

public class Ingredient implements Serializable {
    @Serial
    private static final long serialVersionUID = 6695211179522399290L;

    private int numero_ingredient;
    private String nom;
    private boolean cuit;
    private String type_cuisson;
    private int temps_preparation;

    public Ingredient(String nom, boolean cuit, String type_cuisson, int temps_preparation) {
        this.nom = nom;
        this.cuit = cuit;
        this.type_cuisson = type_cuisson;
        this.temps_preparation = temps_preparation;
        this.numero_ingredient = this.get_nombres_ingredients(System.getProperty("user.dir") + "/bdd/ingredients/");
    }

    public void sauvegarder_ingredient() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream( System.getProperty("user.dir") + "/bdd/ingredients/" + this.numero_ingredient + ".ser");
            ObjectOutput objectOutput = new ObjectOutputStream(fileOutputStream);
            objectOutput.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static Ingredient get_ingredient_by_id(int numero_ingredient)  {
        File file = new File(System.getProperty("user.dir") + "/bdd/ingredients/" + numero_ingredient + ".ser");
        System.out.println(file.getPath());
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInput objectInput = new ObjectInputStream(fileInputStream);
            return (Ingredient) objectInput.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int get_nombres_ingredients(String path) {
        File directory = new File(path);
        File[] content_files = directory.listFiles();
        return content_files.length == 0 ? 1 : content_files.length + 1;
    }

    public int getNumero_ingredient() {
        return numero_ingredient;
    }

    public String getNom() {
        return nom;
    }

    public boolean isCuit() {
        return cuit;
    }

    public String getType_cuisson() {
        return type_cuisson;
    }

    public int getTemps_preparation() {
        return temps_preparation;
    }

    @Override
    public String toString() {
        return this.nom;
    }
}
