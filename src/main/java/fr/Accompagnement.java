package fr;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class Accompagnement extends Produit implements Serializable {
    @Serial
    private static final long serialVersionUID = 7072625591871715646L;

    public Accompagnement(String nom, List<Ingredient> ingredients) {
        super(nom, ingredients);
    }

    @Override
    public void sauvegarder_produit() {

    }
}
