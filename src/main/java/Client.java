public class Client {
    private String nom;
    private String mot_de_passe;
    private int numero_client;

    public Client(String nom, String mot_de_passe) {
        this.nom = nom;
        this.mot_de_passe = mot_de_passe;
        this.numero_client = (int) Math.round(Math.random() * 1000 + 2);
    }

    public boolean existe() {
        return false;
    }
}
