package fr;

import java.io.*;

/**
 * Classe Menu : La classe Menu implements Serializable.
 * Elle définit un menu.
 */
public class Menu implements Serializable {
    @Serial
    private static final long serialVersionUID = -2757956060455371466L;

    private String nom;
    private int numero_menu;
    private Boisson boisson;
    private Plat plat;
    private Accompagnement accompagnement;
    private double prix;

    public Menu(String nom, Boisson boisson, Plat plat, Accompagnement accompagnement, double prix) {
        this.nom = nom;
        this.boisson = boisson;
        this.plat = plat;
        this.accompagnement = accompagnement;
        this.prix = prix;
        this.numero_menu = Menu.get_nombres_menus();
    }

    public void sauvegarder_menu() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream( System.getProperty("user.dir") + "/bdd/menus/" + this.numero_menu + ".ser");
            ObjectOutput objectOutput = new ObjectOutputStream(fileOutputStream);
            objectOutput.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public static Menu get_menu_by_id(int numero_menu) {
//        File file = new File(System.getProperty("user.dir") + "/bdd/menus/" + numero_menu + ".ser");
//        try {
//            FileInputStream fileInputStream = new FileInputStream(file);
//            ObjectInput objectInput = new ObjectInputStream(fileInputStream);
//            return (Menu) objectInput.readObject();
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public static int get_nombres_menus() {
        File directory = new File(System.getProperty("user.dir") + "/bdd/menus/");
        File[] content_files = directory.listFiles();
        return content_files.length == 0 ? 1 : content_files.length + 1;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "nom='" + nom + '\'' +
                ", numero_menu=" + numero_menu +
                ", boisson=" + boisson +
                ", plat=" + plat +
                ", accompagnement=" + accompagnement +
                ", prix=" + prix +
                '}';
    }

    public String getNom() {
        return nom;
    }

    public double getPrix() {
        return prix;
    }

    public Boisson getBoisson() {
        return boisson;
    }

    public Plat getPlat() {
        return plat;
    }

    public Accompagnement getAccompagnement() {
        return accompagnement;
    }
}
