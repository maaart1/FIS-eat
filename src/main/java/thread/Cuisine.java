package thread;

import fr.Commande;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Cuisine {

    public List<Commande> en_attente;
    public ScheduledExecutorService executor;

    public Cuisine(List<Commande> en_attente, int nb_threads) {
        this.en_attente = en_attente;
        executor = Executors.newScheduledThreadPool(nb_threads);
    }

    public void start() {
        Runnable myRunnable = () -> {
            try {
                if (!en_attente.isEmpty()) {
                    Commande commande = en_attente.get(0);
                    Thread.sleep(commande.get_duree_commande() * 1000L);
                    System.out.println("La commande numéro " + commande.getNumero_commande() + " de " + commande.getClient().get_nom() + " est prête :)");
                    this.en_attente.remove(commande);
                    Thread.sleep(2000);
                }
            } catch (InterruptedException e) { e.printStackTrace(); }
        };

        this.executor.scheduleAtFixedRate(myRunnable, 0, 5, TimeUnit.SECONDS);
    }

    public void setEn_attente(List<Commande> en_attente) {
        this.en_attente = en_attente;
    }

    public void shutdown_executor() {
        this.executor.shutdown();
    }
}
