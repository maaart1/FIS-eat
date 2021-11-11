package fr;

import util.Load;
import util.MenuLayouts;


public class Main {
    public static void main(String[] args) {
        // Load.charger_fichiers();
        MenuLayouts menu = new MenuLayouts();
        menu.page_accueil();
        menu.sc.close();
        menu.cuisine.shutdown_executor();
    }
}
