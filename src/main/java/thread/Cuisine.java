package thread;

import fr.Client;
import fr.Commande;
import util.MenuLayouts;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Cuisine {

    public MenuLayouts menuLayouts;
    public ScheduledExecutorService executor;

    public Runnable myRunnable = () -> {
        try {
            if (!menuLayouts.en_attente.isEmpty()) {
                Commande commande = menuLayouts.en_attente.get(0);
                Thread.sleep(commande.get_duree_commande() * 1000L);
                MenuLayouts.clear_screen();
                System.out.println("La commande numéro " + commande.getNumero_commande() + " de " + commande.getClient().get_nom() + " est prête :)");
                System.out.println("Retour dans 5 secondes...");
                menuLayouts.en_attente.remove(commande);
                Thread.sleep(5000);
                Client client = commande.getClient();
                menuLayouts.menu(client, new Commande(client));
            }
        } catch (InterruptedException e) { e.printStackTrace(); }
    };

    public Cuisine(int nb_threads, MenuLayouts menuLayouts) {
        this.menuLayouts = menuLayouts;
        executor = Executors.newScheduledThreadPool(nb_threads);
    }

    public void start() {
        this.executor.execute(myRunnable);
        //this.executor.scheduleWithFixedDelay(myRunnable, 0, 5000, TimeUnit.MILLISECONDS);
    }

    public void shutdown_executor() {
        this.executor.shutdown();
    }
}
