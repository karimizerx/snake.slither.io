package slitherio.model;

import javafx.scene.input.KeyCode;
import slitherio.gameobjects.*;

public final class SnakeGame extends Arena {

    /* ******************** Constructors ******************** */
    public SnakeGame(double width, double height, Player player) {
        super(width, height, width, height);

        // Manage players
        getPlayers().add(player);

        // Manage default [food] list content
        getFoods().add(getValidRandomFood());
    }

    /* ******************** Functions ******************** */

    // Make sure that there is juste one snake
    private boolean assertSize() {
        if (getSnakes().size() != 1) {
            // System.out.println("SnakeGame: assertSize: Invalid Number Of Snakes (" +
            // getSnakes().size() + ")");
            return false;
        }
        return true;
    }

    @Override
    // Update all values of the game. [dt] is the elapsed time
    public final void update(double dt) {
        // Make sure that there is one snake
        if (!assertSize())
            return;

        // Manage collides with food
        for (Food food : getFoods()) {
            if (getSnake().getHead().collides(food)) {
                getFoods().remove(food);
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