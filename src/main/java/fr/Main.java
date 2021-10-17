package fr;

import util.MenuLayouts;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        MenuLayouts menu = new MenuLayouts();
        menu.logo();
        menu.page_accueil();
        menu.sc.close();
    }
}
