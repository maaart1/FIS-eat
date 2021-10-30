package util;

import fr.*;

import java.io.File;
import java.util.ArrayList;

public class Load {

    public static void charger_fichiers() {
        File client = new File(System.getProperty("user.dir") + "/bdd/client/");
        client.mkdirs();
        File ingredients = new File(System.getProperty("user.dir") + "/bdd/ingredients/");
        ingredients.mkdirs();
        File menus = new File(System.getProperty("user.dir") + "/bdd/menus/");
        menus.mkdirs();
        File accompagnements = new File(System.getProperty("user.dir") + "/bdd/produits/accompagnements");
        accompagnements.mkdirs();
        File plats = new File(System.getProperty("user.dir") + "/bdd/produits/plats");
        plats.mkdirs();
        File boissons = new File(System.getProperty("user.dir") + "/bdd/produits/boissons");
        boissons.mkdirs();
        Boisson b = new Boisson("RedBull", 2);
        b.sauvegarder_boisson();
        Boisson b1 = new Boisson( "Coca-Cola", 1.50);
        b1.sauvegarder_boisson();
        Boisson b3 = new Boisson("Fanta Orange", 1.50);
        b3.sauvegarder_boisson();
        Boisson b4 = new Boisson("Fanta Citron", 1.50);
        b4.sauvegarder_boisson();
        Boisson b5 = new Boisson( "IceTea", 1.50);
        b5.sauvegarder_boisson();
        Boisson b6 = new Boisson( "Sprite", 1.50);
        b6.sauvegarder_boisson();
        Boisson b7 = new Boisson( "Orangina", 1.50);
        b7.sauvegarder_boisson();
        Boisson b8 = new Boisson( "Eau", 1);
        b8.sauvegarder_boisson();
        Boisson b9 = new Boisson( "Eau Plate", 1);
        b9.sauvegarder_boisson();
        Accompagnement a = new Accompagnement ( "Frites", 2.50);
        a.sauvegarder_accompagnement();
        Accompagnement a1 = new Accompagnement("Potatoes", 2.50);
        a1.sauvegarder_accompagnement();
        Ingredient i1 = new Ingredient("Pain à burger", false,"", 0 );
        i1.sauvegarder_ingredient();
        Ingredient i2 = new Ingredient("Steak", true, "Grill", 3);
        i2.sauvegarder_ingredient();
        Ingredient i3 = new Ingredient("Oignons", false, "",1);
        i3.sauvegarder_ingredient();
        Ingredient i4 = new Ingredient( "Fromage à burger ", true, "fondu", 2);
        i4.sauvegarder_ingredient();
        Ingredient i5 = new Ingredient("Salade", false, "", 1);
        i5.sauvegarder_ingredient();
        Ingredient i6 = new Ingredient("Tomate", false, "", 1);
        i6.sauvegarder_ingredient();
        Ingredient i7 = new Ingredient("Ketchup",false, "", 1);
        i7.sauvegarder_ingredient();
        Ingredient i8 = new Ingredient("Nuggets", true, "à l'huile",3);
        i8.sauvegarder_ingredient();
        Ingredient i9 = new Ingredient("Jambon", true,"grill", 2 );
        i9.sauvegarder_ingredient();
        Ingredient i10 = new Ingredient("Pain à Croque Monsieur", true, "grille pain", 2);
        i10.sauvegarder_ingredient();
        Ingredient i11 = new Ingredient("Fromage à Croque Monsieur", true, "fondu", 2);
        i11.sauvegarder_ingredient();
        Ingredient i12 = new Ingredient("Poisson pané", true , "à l'huile", 3);
        i12.sauvegarder_ingredient();
        Ingredient i13 = new Ingredient("Chou rouge", false, "", 1);
        i13.sauvegarder_ingredient();
        Ingredient i14 = new Ingredient("Dés de fromage", false, "", 1);
        i14.sauvegarder_ingredient();
        Ingredient i15 = new Ingredient("Tomates cerises",  false, "", 1);
        i15.sauvegarder_ingredient();
        Ingredient i16 = new Ingredient("Poivron", false, "", 1);
        i16.sauvegarder_ingredient();
        Ingredient i17 = new Ingredient("Galettes de pomme de terre", true, "à l'huile", 3);
        i17.sauvegarder_ingredient();
        Plat p = new Plat("Burger classique", new ArrayList<>(){
            {
                add(i1);
                add(i2);
                add(i3);
                add(i4);
                add(i5);
                add(i6);
                add(i7);
            }
        }, true, 6.00);
        p.sauvegarder_plat();
        Plat p1 = new Plat("Nuggets", new ArrayList<>(){
            {
                add(i8);
            }
        }, true, 6.00);
        p1.sauvegarder_plat();
        Plat p2 = new Plat("Croque Monsieur", new ArrayList<>(){
            {
                add(i10);
                add(i9);
                add(i11);
            }
        }, true, 12.50);
        p2.sauvegarder_plat();
        Plat p3 = new Plat("Big Burger", new ArrayList<>(){
            {
                add(i1);
                add(i2);
                add(i2);
                add(i2);
                add(i4);
                add(i4);
                add(i4);
                add(i4);
                add(i3);
                add(i5);
                add(i6);
                add(i7);
            }
        }, false, 0);
        p3.sauvegarder_plat();
        Plat p4 = new Plat("Fish Burger", new ArrayList<>(){
            {
                add(i1);
                add(i7);
                add(i12);
            }
        }, true, 8.00);
        p4.sauvegarder_plat();
        Plat p5 = new Plat("Chicken Burger", new ArrayList<>(){
            {
                add(i1);
                add(i8);
                add(i5);
                add(i7);
            }
        }, true, 8.00);
        p5.sauvegarder_plat();
        Plat p6 = new Plat("Salade Veggie", new ArrayList<>(){
            {
                add(i5);
                add(i15);
                add(i16);
                add(i13);
                add(i14);
                add(i17);
            }
        }, false, 0);
        p6.sauvegarder_plat();

        Menu m1 = new Menu("Le classique", b1, p, a, 7.00);
        m1.sauvegarder_menu();
        Menu m2 = new Menu("Le veggie", b8, p6, a, 9.00);
        m2.sauvegarder_menu();
        Menu m3 = new Menu("Grande faim", b1, p3, a1, 12.00);
        m3.sauvegarder_menu();
    }
}
