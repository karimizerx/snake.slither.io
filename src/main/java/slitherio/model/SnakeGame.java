package slitherio.model;

import javafx.scene.input.KeyCode;
import slitherio.gameobjects.*;

/**
 * Cette classe s'occupe de gérer un jeu de Snake classique.
 */
public final class SnakeGame extends Arena {

    /* ******************** Constructors ******************** */
    /**
     * Creates a new SNAKE game, with a default [food] object in @attribut
     * [getFoods] list.
     * 
     * @param width  width of the world and the screen
     * @param height height of the world and the screen
     * @param player There is juste one playe.
     */
    public SnakeGame(double width, double height, Player player) {
        super(width, height, width, height);

        // Manage players
        getPlayers().add(player);

        // Manage default [food] list content
        getFoods().add(getValidRandomFood());
    }

    /* ******************** Functions ******************** */

    /**
     * Make sure that there is juste one snake.
     * 
     * @return boolean, who return TRUE if pirate size are note infinity.
     */
    private boolean assertSize() {
        return getSnakes().size() == 1;
    }

    /**
     * 
     * @param player
     */
    private void moveIA(Player player) {

        // Get the nearest food
        Food nearestFood = getFoods().get(0);
        double dist = 0;
        for (Food food : getFoods()) {
            double newDist = Math.abs(player.getSnake().getHead().getCenterX() - food.getCenterX());
            if (dist < newDist)
                nearestFood = food;
        }

        // Direct the snake to this [nearestFood]
        if (nearestFood.getCenterX() < getSnake().getHead().getCenterX()) {
            // TODO: faire bouger le snake
        }
    }

    @Override
    // Update all values of the game. [dt] is the elapsed time
    public final void update(double dt) {
        // Make sure that there is one snake
        if (!assertSize())
            return;

        // Manage IA move
        if (getPlayer().isIa())
            moveIA(getPlayer());

        // Manage collides with food
        for (Food food : getFoods()) {
            if (getSnake().getHead().collides(food)) {
                getFoods().remove(food);
                for (int i = 0; i < 4; ++i)
                    getSnake().addSegment(dt);
                getFoods().add(getValidRandomFood());
                break;
            }
        }

        // Manage auto-collision

        // Manage collides with Window
        if (getSnake().collides(getWidth(), getHeight()))
            getSnake().byPassCollidesWindow(getWidth(), getHeight());

        // Remove all snakes of [snakes] that need to be removed
        getPlayers().removeIf(player -> player.getSnake().getBody().isEmpty());

        // Manage the end of the game
        if (endGame())
            ;

        // Make snake move if he's still alive, i.e if [snakes] isn't empty
        if (!getSnakes().isEmpty())
            getSnake().move(dt);
    }

    @Override
    public final void onKeyPressed(KeyCode key) {
        if (!assertSize())
            return;

        if (!getSnake().getBody().isEmpty()) {
            double angle = getSnake().getHead().getAngle();
            if (angle == 90 || angle == 270) {
                if (key == getPlayer().getKeyUp())
                    getSnake().getHead().setAngle(180);
                else if (key == getPlayer().getKeyDown())
                    getSnake().getHead().setAngle(0);
            } else if (angle == 0 || angle == 360 || angle == 180) {
                if (key == getPlayer().getKeyLeft())
                    getSnake().getHead().setAngle(90);
                else if (key == getPlayer().getKeyRight())
                    getSnake().getHead().setAngle(270);
            }
        }
    }

    @Override
    public final void onMouseMoved(double pointerX, double pointerY) {
    }

    @Override
    public final boolean endGame() {
        return false;
    }
    /* ******************** Getter & Setter ******************** */

    private Player getPlayer() {
        return getPlayers().get(0);
    }

    private Snake getSnake() {
        return getSnakes().get(0);
    }
}