package fr;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Commande {
    private int numero_commande;
    private Client client;
    private LocalDate date;
    private int prix;
    private List<Menu> menus;
    private List<Plat> plats;
    private List<Accompagnement> accompagnements;
    private List<Boisson> boissons;
    private boolean en_attente;
    private boolean en_preparation;
    private boolean prete;

    public Commande(Client client) {
        this.client = client;
        this.date = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth());
    }

    public int getNumero_commande() {
        return numero_commande;
    }

    public void setNumero_commande(int numero_commande) {
        this.numero_commande = numero_commande;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public void setMenus(ArrayList<Menu> menus) {
        this.menus = menus;
    }

    public boolean isEn_attente() {
        return en_attente;
    }

    public void setEn_attente(boolean en_attente) {
        this.en_attente = en_attente;
    }

    public boolean isEn_preparation() {
        return en_preparation;
    }

    public void setEn_preparation(boolean en_preparation) {
        this.en_preparation = en_preparation;
    }

    public boolean isPrete() {
        return prete;
    }

    public void setPrete(boolean prete) {
        this.prete = prete;
    }

    // TODO
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Commande n°" + numero_commande + " : \n" + "\n Nom du client : " + client.get_nom() + "\n Date : " + date + "\n Prix : " + prix);
        return stringBuilder.toString();
    }
}
