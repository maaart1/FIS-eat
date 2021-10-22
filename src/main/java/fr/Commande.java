package fr;

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

    public Commande() {

    }

    public int getNumero_commande() {
        return numero_commande;
    }

    public void setNumero_commande(int numero_commande) {
        this.numero_commande = numero_commande;
    }

    public int getNumero_client() {
        return numero_client;
    }

    public void setNumero_client(int numero_client) {
        this.numero_client = numero_client;
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

    public ArrayList<Menu> getMenus() {
        return menus;
    }

    public void setMenus(ArrayList<Menu> menus) {
        this.menus = menus;
    }

    public ArrayList<Produit> getProduits() {
        return produits;
    }

    public void setProduits(ArrayList<Produit> produits) {
        this.produits = produits;
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
}
