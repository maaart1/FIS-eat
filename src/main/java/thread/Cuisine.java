package thread;

import fr.Client;
import fr.Commande;
import util.MenuLayouts;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


/**
 * Classe Cuisine : La classe Cuisine prépare les commandes validées par les clients.
 */
public class Cuisine {

    public MenuLayouts menuLayouts;
    public ScheduledExecutorService executor;

    public Cuisine(int nb_threads, MenuLayouts menuLayouts) {
        this.menuLayouts = menuLayouts;
        executor = Executors.newScheduledThreadPool(nb_threads);
    }

    public Runnable myRunnable = () -> {
        try {
            if (!menuLayouts.en_attente.isEmpty()) {
                Commande commande = menuLayouts.en_attente.get(0);
                menuLayouts.en_attente.remove(commande);
                Thread.sleep((commande.get_duree_commande() * 1000L) / 2);
                System.out.println("\n\t --------------------------------------------------------");
                System.out.println("\t La commande numéro " + commande.getNumero_commande() + " de " + commande.getClient().get_nom() + " est à 50% de sa préparation:)");
                System.out.println("\t --------------------------------------------------------");
                Thread.sleep((commande.get_duree_commande() * 1000L) / 2);

                //MenuLayouts.clear_screen();
                System.out.println("\n\t --------------------------------------------------------");
                System.out.println("\t La commande numéro " + commande.getNumero_commande() + " de " + commande.getClient().get_nom() + " est prête :)");
                System.out.println("\t --------------------------------------------------------");
                Client client = commande.getClient();
                //menuLayouts.page_accueil();
            }
        } catch (InterruptedException e) { e.printStackTrace(); }
    };


    public void start() {
        this.executor.execute(myRunnable);
    }

    public void shutdown_executor() {
        this.executor.shutdown();
    }
}
