package slitherio.model;

import javafx.beans.property.*;
import javafx.collections.*;
import javafx.scene.input.*;
import slitherio.gameobjects.*;

public final class Arena {
    private ListProperty<Food> foods = new SimpleListProperty<Food>(FXCollections.<Food>observableArrayList());
    private ListProperty<Snake> snakes = new SimpleListProperty<Snake>(FXCollections.<Snake>observableArrayList());
    private double width, height;

    /* ******************** Constructor ******************** */
    public Arena(double width, double height) {
        this.width = width;
        this.height = height;

        getSnakes().add(new Snake(600, 300));
        getSnakes().add(new Snake(100, 350, KeyCode.Z, KeyCode.S, KeyCode.Q, KeyCode.D));

        getFoods().add(Food.FoodRandom(width, height));
    }

    /* ******************** Functions ******************** */

    // Update all values of the make. [dt] is the elapsed time
    public final void update(double dt) {
        if (getSnakes().isEmpty())
            return;

        // Update all snakes of [snakes]
        for (Snake snake : getSnakes())
            updateOneSnake(snake, dt);

        // Remove all snakes of [snakes] that need to be removed
        getSnakes().removeIf(snake -> snake.getBody().isEmpty());

        // Make all snakes of [snakes] move
        for (Snake snake : getSnakes())
            snake.move(dt);

    }

    // Update values of [snake]. [dt] is the elapsed time
    private void updateOneSnake(Snake snake, double dt) {

        // Make sure that the [snake]'s body isn't empty
        if (snake.getBody().isEmpty())
            return;

        // Manage collides with others snakes
        for (Snake snake2 : getSnakes()) {
            if (!snake2.getBody().isEmpty() && snake2.collides(snake))
                snake2.getBody().clear();
        }

        // Manage collides with food
        for (Food food : getFoods()) {
            if (snake.getHead().collides(food)) {
                getFoods().remove(food);
                snake.addSegment(dt);
                getFoods().add(Food.FoodRandom(width, height));
                break;
            }
        }

        // Manage collides with Window
        if (snake.collides(width, height))
            snake.byPassCollidesWindow(width, height);

    }

    // Run [onKeyPressed] function for each snake of [snakes]
    public final void onKeyPressed(KeyCode key) {
        for (Snake snake : getSnakes())
            snake.onKeyPressed(key);
    }

    public final void onMouseMoved(double pointerX, double pointerY) {
        for (Snake snake : getSnakes())
            snake.onMouseMoved(pointerX, pointerY);
    }

    /* ******************** Getter & Setter ******************** */

    public final ListProperty<Food> getFoodsProperty() {
        return foods;
    }

    public final ObservableList<Food> getFoods() {
        return foods.get();
    }

    public final ListProperty<Snake> getSnakesProperty() {
        return snakes;
    }

    public final ObservableList<Snake> getSnakes() {
        return snakes.get();
    }

    public final double getWidth() {
        return width;
    }

    public final void setWidth(double value) {
        width = value;
    }

    public final double getHeight() {
        return height;
    }

    public final void setHeight(double value) {
        height = value;
    }

}
