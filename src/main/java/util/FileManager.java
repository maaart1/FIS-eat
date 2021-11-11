package util;

import fr.Accompagnement;
import fr.Boisson;
import fr.Plat;

import java.io.*;

/**
 * Classe FileManager : Cette classe permet de gérer les appels aux fichiers binaires.
 */
public class FileManager {

    // PLATS
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

    public static Plat get_plat_by_id_hors_menu(int numero_produit) {
        File file = new File(System.getProperty("user.dir") + "/bdd/produits/plats/" + numero_produit + ".ser");
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInput objectInput = new ObjectInputStream(fileInputStream);
            Plat plat = (Plat) objectInput.readObject();
            if (plat.isHors_menu())  {
                return plat;
            }
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // BOISSONS
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

    // ACCOMPAGNEMENTS
    public static Accompagnement get_accompagnement_by_id(int numero_accompagnement) {
        File file = new File(System.getProperty("user.dir") + "/bdd/produits/accompagnements/" + numero_accompagnement + ".ser");
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInput objectInput = new ObjectInputStream(fileInputStream);
            return (Accompagnement) objectInput.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
