package slitherio.view;

import slitherio.gameobjects.*;

/** Represents the view of an {@link slitherio.gameobjects.Food food} object. */
public class FoodView extends DisplayableObject {

    protected FoodView(GameObject go) {
        super(go, "food.png");
    }

}