import java.time.LocalDate;
import java.util.ArrayList;

public class Commande {
    private int numero_commande;
    private int numero_client;
    private Client client;
    private LocalDate date;
    private int prix;
    private ArrayList<Menu> menus;
    private ArrayList<Produit> produits;
    private boolean en_attente;
    private boolean en_preparation;
    private boolean prete;

}
