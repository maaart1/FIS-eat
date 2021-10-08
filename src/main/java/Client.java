import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Client implements Serializable {
    public static int cpt = 1;
    private String nom;
    private int numero_client = cpt;

    public Client(String nom) {
        this.nom = nom;
        cpt++;
    }

    public boolean existe() {
        File fichier_client = new File(System.getProperty("user.dir") + "/bdd/client/fichier_client.txt");

            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fichier_client));
                List<Client> clients = (List<Client>) objectInputStream.readObject();
                objectInputStream.close();

                for (Client c : clients) {
                    if (this.getNumero_client() == c.getNumero_client()) return false;
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
                if (numero_client == c.getNumero_client()) return c;
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
        ArrayList<Client> clients = new ArrayList<Client>();
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

        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream( System.getProperty("user.dir") + "/bdd/client/" + this.numero_client + ".ser");
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
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
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

    public String getNom() {
        return nom;
    }

    public int getNumero_client() {
        return numero_client;
    }
}
