package slitherio.view;

import java.util.*;
import javafx.beans.property.*;
import javafx.scene.layout.*;
import slitherio.gameobjects.*;

public class GameView {

    private Pane root;
    private DoubleProperty width, height;
    private List<DisplayableObject> foodsToDisplay = new ArrayList<>();
    private List<SnakeView> snakesToDisplay = new LinkedList<>();

    /* ******************** Constructor ******************** */
    protected GameView(Pane root, double width, double height) {
        this.root = root;
        this.width = new SimpleDoubleProperty(width);
        this.height = new SimpleDoubleProperty(height);
    }

    /* ******************** Functions ******************** */

    protected void addFood(Food food) {
        DisplayableObject foodView = new FoodView(food);
        foodsToDisplay.add(foodView);
        foodView.display(root);
    }

    protected void removeFood(DisplayableObject foodview) {
        root.getChildren().remove(foodview.getGraphics());
        foodsToDisplay.remove(foodview);
    }

    protected void addSegment(SnakeView snakeView, Segment segment) {
        SegmentView segmentView = new SegmentView(segment);
        snakeView.getBodyView().add(segmentView);
        segmentView.display(root);
    }

    protected void removeSegment(SnakeView snakeView, DisplayableObject segmentView) {
        root.getChildren().remove(segmentView.getGraphics());
        snakeView.getBodyView().remove(segmentView);
        if (snakeView.getBodyView().isEmpty())
            removeSnake(snakeView);
    }

    protected void addSnake(Snake snake) {
        SnakeView snakeView = new SnakeView(this, snake);
        snakesToDisplay.add(snakeView);
        snakeView.getBodyView().forEach((SegmentView segmentView) -> segmentView.display(root));
    }

    protected void removeSnake(SnakeView snakeView) {
        while (snakeView.getBodyView().size() > 0)
            removeSegment(snakeView, snakeView.getBodyView().get(0));
        snakeView.getBodyView().clear();
        snakesToDisplay.remove(snakeView);
    }

    /* ******************** Getter & Setter ******************** */

    protected final DoubleProperty getWidthProperty() {
        return width;
    }

    protected final double getWidth() {
        return width.getValue();
    }

    protected final void setWidth(double value) {
        width.setValue(value);
    }

    protected final double getHeight() {
        return height.getValue();
    }

    protected final void setHeight(double value) {
        height.setValue(value);
    }

    protected final Pane getRoot() {
        return root;
    }

    protected final List<DisplayableObject> getFoodsToDisplay() {
        return foodsToDisplay;
    }

    protected final List<SnakeView> getSnakesToDisplay() {
        return snakesToDisplay;
    }
}