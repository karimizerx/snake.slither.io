package slitherio.model;

import javafx.beans.property.*;
import javafx.collections.*;
import javafx.scene.input.*;
import slitherio.gameobjects.*;

/**
 * Représente un jeu. C'est la classe principale des jeux. Une Arena s'occupe de
 * gérer les différents éléments du jeu, d'appliquer les règles.
 * 
 * Le jeu se déroule sur une arène appelée <b>WORLD</b>, avec ses dimensions
 * {@link #worldWidth} et {@link #worldHeight}.
 * 
 * @see javafx.beans.property.ListProperty
 * @see javafx.beans.property.SimpleListProperty
 */
public abstract class Arena {
    /** Defines the list of {@link slitherio.model.Player players}. */
    private final ListProperty<Player> players = new SimpleListProperty<Player>(
            FXCollections.<Player>observableArrayList());
    /** Defines the list of {@link slitherio.gameobjects.Food foods}. */
    private final ListProperty<Food> foods = new SimpleListProperty<Food>(FXCollections.<Food>observableArrayList());
    /**
     * Defines the list of {@link slitherio.gameobjects.Snake snakes}. They all
     * belong to one player of {@link #players}, and they are bound to it.
     * 
     * @see slitherio.model.Player
     * @see #bindPlayers()
     */
    private final ListProperty<Snake> snakes = new SimpleListProperty<Snake>(
            FXCollections.<Snake>observableArrayList());
    /** Defines the world's width. */
    private double worldWidth;
    /** Defines the world's height. */
    private double worldHeight;
    /** Defines the window's width. The width of the displayed part of the world. */
    private double width;
    /**
     * Defines the window's height. The height of the displayed part of the world.
     */
    private double height;

    /**
     * Constructor for use by subclasses. This constructor calls
     * {@link #bindPlayers()}.
     * 
     * @param worldWidth  see {@link #worldWidth}
     * @param worldHeight see {@link #worldHeight}
     * @param width       see {@link #width}
     * @param height      see {@link #height}
     */
    public Arena(double worldWidth, double worldHeight, double width, double height) {
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
        this.width = width;
        this.height = height;
        bindPlayers();
    }

    /**
     * Bind {@link #players} and {@link #snakes}. When {@link #players} changes, i.e
     * a player is added or removed, then {@link #snakes} changes in the same way.
     */
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

    /**
     * Return true if the food is in a valid position. I.e if the food doesn't
     * collides with any snake.
     * 
     * @return Return true if the food is in a valid position.
     */
    protected final boolean isValidFoodPosition(Food food) {
        for (Snake snake : getSnakes()) {
            for (Segment segment : snake.getBody())
                if (food.collides(segment))
                    return false;
        }
        return true;
    }

    /**
     * Return a food that is at a valid position. This method try to create a food
     * in a valid position. If there is not valid position after 500 attemps, return
     * a food with random position.
     * 
     * @return food with valid position or random position
     */
    protected final Food getValidRandomFood() {
        int cnt = 500;
        Food food = Food.FoodRandom(worldWidth, worldHeight);
        while (cnt != 0 && !isValidFoodPosition(food)) {
            --cnt;
            food = Food.FoodRandom(worldWidth, worldHeight);
        }

        return (cnt == 0) ? null : food;
    }

    /**
     * Update all values of the game.
     * 
     * Manage collides with food, other snakes, window...
     * 
     * @param dt the elapsed time
     */
    public abstract void update(double dt);

    /**
     * Manage action when a key is pressed, for each player.
     * 
     * @param key the key that has been pressed
     */
    public abstract void onKeyPressed(KeyCode key);

    /**
     * Manage action when the mouse is moved, for each player.
     * 
     * @param pointerX X-coordinate of the mouse
     * @param pointerY Y-coordinate of the mouse
     */
    public abstract void onMouseMoved(double pointerX, double pointerY);

    /**
     * Return true if the game is over.
     * 
     * @return true if the game is over
     */
    public abstract boolean endGame();

    /**
     * Get the property players.
     * 
     * @return the property players.
     */
    public final ListProperty<Player> getPlayersProperty() {
        return players;
    }

    /**
     * Get the value of property players.
     * 
     * @return the list of players.
     */
    public final ObservableList<Player> getPlayers() {
        return players.get();
    }

    /**
     * Get the property foods.
     * 
     * @return the property foods.
     */
    public final ListProperty<Food> getFoodsProperty() {
        return foods;
    }

    /**
     * Get the value of property foods.
     * 
     * @return the list of foods
     */
    public final ObservableList<Food> getFoods() {
        return foods.get();
    }

    /**
     * Get the property snakes.
     * 
     * @return the property snakes
     */
    public final ListProperty<Snake> getSnakesProperty() {
        return snakes;
    }

    /**
     * Get the value of property snakes.
     * 
     * @return the list of snakes
     */
    public final ObservableList<Snake> getSnakes() {
        return snakes.get();
    }

    /**
     * Get the window's width.
     * 
     * @return value of window's width
     */
    public final double getWidth() {
        return width;
    }

    /**
     * Set the value of the window's width.
     * 
     * @param value the new value
     * @see #getWidth()
     */
    public final void setWidth(double value) {
        width = value;
    }

    /**
     * Get the window's height.
     * 
     * @return value of window's height
     */
    public final double getHeight() {
        return height;
    }

    /**
     * Set the value of the window's height.
     * 
     * @param value the new value
     * @see #getHeight()
     */
    public final void setHeight(double value) {
        height = value;
    }

    /**
     * Get the world's width.
     * 
     * @return value of world's width
     */
    public final double getWorldtWidth() {
        return width;
    }

    /**
     * Set the value of the world's width.
     * 
     * @param value the new value
     * @see #getWorldtWidth()
     */
    public final void setWorldWidth(double value) {
        width = value;
    }

    /**
     * Get the world's height.
     * 
     * @return value of world's height
     */
    public final double getWorldHeight() {
        return height;
    }

    /**
     * Set the value of the world's height.
     * 
     * @param value the new value
     * @see #getWorldHeight()
     */
    public final void setWorldHeight(double value) {
        height = value;
    }

}
