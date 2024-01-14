package slitherio.view;

import java.util.*;
import javafx.beans.property.*;
import javafx.scene.layout.*;
import slitherio.gameobjects.*;

/**
 * Represents the game's view. Gère l'affichage et les éléments à afficher à
 * l'écran.
 */
public class GameView {

    /** Defines the pane where the graphics objects are displayed */
    private Pane root;
    /** Defines the window's width. */
    private DoubleProperty width;
    /** Defines the window's height. */
    private DoubleProperty height;
    /** Defines the list of foods to display. */
    private List<DisplayableObject> foodsToDisplay = new ArrayList<>();
    /** Defines the list of snakes to display. */
    private List<SnakeView> snakesToDisplay = new LinkedList<>();
    /** Defines the X-coordinate of window's center in the world */
    private Double ox;
    /** Defines the X-coordinate of window's center in the world */
    private Double oy;

    protected GameView(Pane root, double width, double height, double originX, double originY) {
        this.root = root;
        this.width = new SimpleDoubleProperty(width);
        this.height = new SimpleDoubleProperty(height);
        this.ox = originX;
        this.oy = originY;
    }

    /**
     * Add a food in to the view and displays it.
     * 
     * @param food the food to add
     */
    protected void addFood(Food food) {
        DisplayableObject foodView = new FoodView(food);
        foodsToDisplay.add(foodView);
        foodView.display(root);
    }

    /**
     * Remove a food's foodView of the view.
     * 
     * @param foodview the food's foodView to remove
     */
    protected void removeFood(DisplayableObject foodview) {
        root.getChildren().remove(foodview.getGraphics());
        foodsToDisplay.remove(foodview);
    }

    /**
     * Add a Segment in the view and displays it.
     * 
     * @param snakeView the snakeView of the segment's snake
     * @param segment   the segment to add
     */
    protected void addSegment(SnakeView snakeView, Segment segment) {
        SegmentView segmentView = new SegmentView(segment, snakeView.getSnake().getSkin());
        snakeView.getBodyView().add(segmentView);
        segmentView.display(root);
    }

    /**
     * Remove a segment's segmentView of the view.
     * 
     * @param snakeView   the snakeView of the segment's snake
     * @param segmentView the segmentView to remove
     */
    protected void removeSegment(SnakeView snakeView, DisplayableObject segmentView) {
        root.getChildren().remove(segmentView.getGraphics());
        snakeView.getBodyView().remove(segmentView);
        if (snakeView.getBodyView().isEmpty())
            removeSnake(snakeView);
    }

    /**
     * Add a snake in the view and displays it.
     * 
     * @param snake the snake to display
     */
    protected void addSnake(Snake snake) {
        SnakeView snakeView = new SnakeView(this, snake);
        snakesToDisplay.add(snakeView);
        snakeView.getBodyView().forEach(segmentView -> segmentView.display(root));
    }

    /**
     * Remove a snake's snakeView of the view.
     * 
     * @param snakeView the snake's snakeView to remove
     */
    protected void removeSnake(SnakeView snakeView) {
        while (snakeView.getBodyView().size() > 0)
            removeSegment(snakeView, snakeView.getBodyView().get(0));
        snakeView.getBodyView().clear();
        snakesToDisplay.remove(snakeView);
    }

    protected final DoubleProperty getWidthProperty() {
        return width;
    }

    protected final double getWidth() {
        return width.getValue();
    }

    protected final void setWidth(double value) {
        width.setValue(value);
    }

    protected final DoubleProperty getHeightProperty() {
        return height;
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

    protected final Double getOx() {
        return ox;
    }

    protected final Double getOy() {
        return oy;
    }
}