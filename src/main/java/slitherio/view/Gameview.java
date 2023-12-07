package slitherio.view;

import java.util.*;
import javafx.scene.layout.*;

public class Gameview {

    private Pane root;
    private List<DisplayableObject> toDisplay = new ArrayList<>();

    public Gameview(Pane root) {
        this.root = root;
    }

    public List<DisplayableObject> getToDisplay() {
        return toDisplay;
    }

    public Pane getRoot() {
        return root;
    }
}