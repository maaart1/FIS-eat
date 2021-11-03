package thread;

import fr.Commande;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.stream.IntStream;

public class Cuisine {
    private ExecutorService executor;

    public Cuisine(ExecutorService executor) {
        this.executor = executor;
    }

    public void start(List<Commande> en_attente, List<Commande> prete) {
        Runnable myRunnable = () -> {
            try {
                Thread.sleep(5000);
                if (!en_attente.isEmpty()) {
                    Commande commande = en_attente.get(0);
                    Thread.sleep(commande.get_duree_commande() * 1000L);
                    System.out.println("La commande numéro " + commande.getNumero_commande() + " de " + commande.getClient().get_nom() + " est prête :)");
                }
            } catch (InterruptedException e) { e.printStackTrace(); }
        };
        if (!en_attente.isEmpty()) IntStream.range(0, en_attente.size()).forEach((i) -> executor.execute(myRunnable));
        System.out.println("Fin du Main");
    }
}
