package fr;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe Client : La classe Client implements Serializable.
 * Elle définit un client.
 */
public class Client implements Serializable {
    @Serial
    private static final long serialVersionUID = -3822866514056828473L;

    private String nom;
    private int numero_client;
    private List<Commande> historique_commandes;

    public Client(String nom) {
        this.nom = nom;
        this.historique_commandes = new ArrayList<>();
        this.numero_client = Client.get_nombres_clients();
    }

    public void sauvegarder_client() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream( System.getProperty("user.dir") + "/bdd/clients/" + this.numero_client + ".ser");
            ObjectOutput objectOutput = new ObjectOutputStream(fileOutputStream);
            objectOutput.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Client get_client_by_id(int numero_client) {
        File file = new File(System.getProperty("user.dir") + "/bdd/clients/" + numero_client + ".ser");
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInput objectInput = new ObjectInputStream(fileInputStream);
            return (Client) objectInput.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean exist(int numero_client) {
        File file = new File(System.getProperty("user.dir") + "/bdd/clients/" + numero_client + ".ser");
        if (file.exists()) return true;
        else return false;
    }

    public static int get_nombres_clients() {
        File directory = new File(System.getProperty("user.dir") + "/bdd/clients/");
        File[] content_files = directory.listFiles();
        return content_files.length == 0 ? 1 : content_files.length + 1;
    }

    public String get_nom() {
        return this.nom;
    }

    public int get_numero_client() {
        return this.numero_client;
    }

    public List<Commande> getHistorique_commandes() {
        return historique_commandes;
    }

    public String getHistorique_commandes_to_String() {
        if (!this.historique_commandes.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder();
            for (Commande commande : this.historique_commandes) {
                stringBuilder.append(commande).append(", ");
            }
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length() - 1);
            return stringBuilder.toString();
        }else {
            return "Pas de commandes !";
        }
    }
}
