package slitherio.controller;

// Import project packages
import slitherio.model.*;
import slitherio.gameobjects.*;
import slitherio.view.*;

// Import java packages
import javafx.scene.input.*;

public class Controller {
    Gameview view;
    Arena arena;

    public void setArena(Arena a) {
        arena = a;
    }

    public void setView(Gameview g) {
        view = g;
    }

    public void on_key_pressed(KeyCode key) {
        arena.on_key_pressed(key);
    }

    public void addDisp(GameObject go) {
        DisplayableObject newdo;
        if (go instanceof Segment)
            newdo = new SegmentView(view.getRoot(), go);
        else
            newdo = new FoodView(view.getRoot(), go);
        view.getToDisplay().add(newdo);
    }
}