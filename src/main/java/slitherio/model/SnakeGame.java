package slitherio.model;

import javafx.scene.input.KeyCode;
import slitherio.gameobjects.*;

/**
 * Gestion du jeu <b>Snake classique</b>. Il n'y a qu'un seul joueur. Les
 * déplacement se font à l'aide des flèches, uniquement vers le Nord, Sud, Est
 * et West. <b>Le snake ne peut pas s'auto collisionner.</b> Les dimensions du
 * WORLD sont celle de la window. Le serpent ne collisionne pas la window, il
 * traverse.
 */
public final class SnakeGame extends Arena {

    /**
     * {@inheritDoc}
     * 
     * This constructor add, by default, a food in {@link #getFoods() foods}.
     * 
     * @param width  the width of window and world
     * @param height the height of windosw and world
     * @param player the player
     */
    public SnakeGame(double width, double height, Player player) {
        super(width, height, width, height);

        // Manage players
        getPlayers().add(player);

        // Manage default [food] list content
        getFoods().add(getValidRandomFood());
    }

    @Override
    public final void update(double dt) {
        // Make sure that there is one snake and food can be created
        if (endGame())
            return;

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

        // TODO: Manage auto-collision

        // Manage collides with Window
        if (getSnake().collides(getWidth(), getHeight()))
            getSnake().byPassCollidesWindow(getWidth(), getHeight());

        // Remove all snakes of [snakes] that need to be removed
        getPlayers().removeIf(player -> player.getSnake().getBody().isEmpty());

        // Manage the end of the game
        if (endGame())
            // TODO: return to menu
            ;

        // Make snake move if he's still alive, i.e if [snakes] isn't empty
        if (!getSnakes().isEmpty())
            getSnake().move(dt);
    }

    @Override
    public final void onKeyPressed(KeyCode key) {
        if (endGame())
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
        return getSnakes().size() != 1 || getValidRandomFood() == null;
    }

    /**
     * Get the player.
     * 
     * @return the player
     */
    private Player getPlayer() {
        return getPlayers().get(0);
    }

    /**
     * Get the snake of the player.
     * 
     * @return the snake of the player
     */
    private Snake getSnake() {
        return getSnakes().get(0);
    }
}