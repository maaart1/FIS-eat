package fr;

import java.io.*;
import java.util.List;

public class Client implements Serializable {
    @Serial
    private static final long serialVersionUID = -3822866514056828473L;

    private String nom;
    private int numero_client;
    public Client(String nom) {
        this.nom = nom;
        this.numero_client = this.get_nombres_clients(System.getProperty("user.dir") + "/bdd/client/");
    }

    public boolean existe() {
        File fichier_client = new File(System.getProperty("user.dir") + "/bdd/client/fichier_client.txt");

            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fichier_client));
                List<Client> clients = (List<Client>) objectInputStream.readObject();
                objectInputStream.close();

                for (Client c : clients) {
                    if (this.get_numero_client() == c.get_numero_client()) return false;
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return true;

    }

    public static Client connexion(int numero_client) {
        File fichier_client = new File(System.getProperty("user.dir") + "/bdd/client/fichier_client.txt");
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fichier_client));
            List<Client> clients = (List<Client>) objectInputStream.readObject();
            objectInputStream.close();

            for (Client c: clients) {
                if (numero_client == c.get_numero_client()) return c;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*public void sauvegarder_client() {
        ArrayList<fr.Client> clients = new ArrayList<fr.Client>();
        clients.add(this);
        File fichier_client = new File(System.getProperty("user.dir") + "/bdd/fichier_client.txt");
        System.out.println(fichier_client.getPath());
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fichier_client));
            objectOutputStream.writeObject(clients);
            objectOutputStream.close();
            System.out.println("Votre numéro client est : " + this.getNumero_client());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    public void sauvegarder_client() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream( System.getProperty("user.dir") + "/bdd/client/" + this.numero_client + ".ser");
            ObjectOutput objectOutput = new ObjectOutputStream(fileOutputStream);
            objectOutput.writeObject(this);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Client get_client_by_id(int numero_client) {
        File file = new File(System.getProperty("user.dir") + "/bdd/client/" + numero_client + ".ser");
        System.out.println(file.getPath());
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInput objectInput = new ObjectInputStream(fileInputStream);
            return (Client) objectInput.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean exist(int numero_client) {
        File file = new File(System.getProperty("user.dir") + "/bdd/client/" + numero_client + ".ser");
        //return file.exists() ? true : false;
        if (file.exists()) return true;
        else return false;
    }


    public int get_nombres_clients(String path) {
        File directory = new File(path);
        File[] content_files = directory.listFiles();
        return content_files.length == 0 ? 0 : content_files.length + 1;
    }

    public String get_nom() {
        return this.nom;
    }

    public int get_numero_client() {
        return this.numero_client;
    }
}
