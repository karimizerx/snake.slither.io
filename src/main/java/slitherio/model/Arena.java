package slitherio.model;

import javafx.beans.property.*;
import javafx.collections.*;
import javafx.scene.input.*;
import slitherio.gamemodes.GameMode;
import slitherio.gameobjects.*;

public abstract class Arena {
    private final ListProperty<Player> players = new SimpleListProperty<Player>(
            FXCollections.<Player>observableArrayList());
    private final ListProperty<Food> foods = new SimpleListProperty<Food>(FXCollections.<Food>observableArrayList());
    private final ListProperty<Snake> snakes = new SimpleListProperty<Snake>(
            FXCollections.<Snake>observableArrayList());
    private double width, height;

    /* ******************** Constructor ******************** */
    public Arena(double width, double height) {
        this.width = width;
        this.height = height;
        bindPlayers();
    }

    /* ******************** Functions ******************** */

    private final void bindPlayers() {
        getPlayers().addListener(new ListChangeListener<Player>() {
            @Override
            public void onChanged(Change<? extends Player> change) {
                while (change.next()) {

                    if (change.wasRemoved())
                        change.getRemoved().forEach(player -> getSnakes().remove(player.getSnake()));

                    if (change.wasAdded())
                        change.getAddedSubList().forEach(player -> getSnakes().add(player.getSnake()));
                }
            }
        });
    }

    protected final boolean isValidFoodPosition(Food food) {
        for (Snake snake : getSnakes()) {
            for (Segment segment : snake.getBody())
                if (food.collides(segment))
                    return false;
        }
        return true;
    }

    protected final Food getValidRandomFood() {
        int cnt = 500;
        Food food = Food.FoodRandom(width, height);
        while (cnt != 0 && !isValidFoodPosition(food)) {
            --cnt;
            food = Food.FoodRandom(width, height);
        }

        // return (cnt == 0) ? (new Food(width / 2, height / 2)) : food;
        return (cnt == 0) ? food : food;
    }

    // Update all values of the game. [dt] is the elapsed time
    public abstract void update(double dt);

    // Run [onKeyPressed] function for each snake of [snakes]
    public abstract void onKeyPressed(KeyCode key);

    // Run [onMouseMoved] function for each snake of [snakes]
    public abstract void onMouseMoved(double pointerX, double pointerY);

    /* ******************** Getter & Setter ******************** */

    public final ListProperty<Player> getPlayersProperty() {
        return players;
    }

    public final ObservableList<Player> getPlayers() {
        return players.get();
    }

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
