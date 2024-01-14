package slitherio.view;

import slitherio.gameobjects.*;

/** Représente la vue d'un objet {@link slitherio.gameobjects.Food food}. */
public class FoodView extends DisplayableObject {

    protected FoodView(GameObject go) {
        super(go, "food.png");
    }

}