package slitherio.view;

// Import java packages
import java.util.*;
import javafx.scene.layout.*;

public class Gameview {

    private Pane root;
    private List<DisplayableObject> toDisplay = new ArrayList<>();
    private List<DisplayableObject> snakeDisplay = new ArrayList<>(); // Utile?

    public Gameview(Pane r) {
        root = r;
    }

    public List<DisplayableObject> getToDisplay() {
        return toDisplay;
    }

    public Pane getRoot() {
        return root;
    }
}