package slitherio.view;

import java.util.*;
import javafx.scene.layout.*;

public class Gameview {

    private Pane root;
    private List<DisplayableObject> toDisplay = new ArrayList<>();
    private List<DisplayableObject> foodsToDisplay = new ArrayList<>();
    private List<DisplayableObject> snakeToDisplay = new ArrayList<>();
    private List<DisplayableObject> snake2ToDisplay = new ArrayList<>();

    public Gameview(Pane root) {
        this.root = root;
    }

    private List<DisplayableObject> getToDisplay() {
        return toDisplay;
    }

    public Pane getRoot() {
        return root;
    }

    public List<DisplayableObject> getSnakeToDisplay() {
        return snakeToDisplay;
    }

    public List<DisplayableObject> getSnake2ToDisplay() {
        return snake2ToDisplay;
    }

    public List<DisplayableObject> getFoodsToDisplay() {
        return foodsToDisplay;
    }
}