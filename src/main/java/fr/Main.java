package fr;

import util.Load;
import util.MenuLayouts;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        //Load.charger_fichiers();
        MenuLayouts menu = new MenuLayouts();
        // System.out.println("\u001B[1m I am bold");
        menu.page_accueil();
        menu.sc.close();


    }
}
