package slitherio.model;

import javafx.scene.input.KeyCode;
import slitherio.gameobjects.*;

/**
 * Gestion du jeu <b>Slither.Io modifié</b>. Il n'y a que deux joueurs sur un
 * pc. Les déplacement se font à l'aide des flèches, uniquement vers le Nord,
 * Sud, Est et West. <b>Le snake ne peut pas s'auto collisionner.</b> Les
 * dimensions du WORLD sont celle de la window. Le serpent ne collisionne pas la
 * window, il traverse.
 */
public final class SlitherIoGame extends Arena {

    /**
     * {@inheritDoc}
     * 
     * This constructor add, by default, two foods in {@link #getFoods() foods}.
     * 
     * @param width   the width of window and world
     * @param height  the height of windosw and world
     * @param player1 the first player
     * @param player2 the second player
     */
    public SlitherIoGame(double width, double height, Player player1, Player player2) {
        super(width, height, width, height);

        // Manage players
        getPlayers().addAll(player1, player2);
        getPlayers().forEach(player -> player.getSnake().setValidPosition(width, height));

        // Manage default [food] list content
        getFoods().add(getValidRandomFood());
        getFoods().add(getValidRandomFood());
    }

    /**
     * Update values of one snake. Manage collides with others snakes, food and
     * window.
     * 
     * @param snake the snake to update
     * @param dt    the elapsed time
     */
    private void updateOneSnake(Snake snake, double dt) {
        if (snake.getBody().isEmpty())
            return;

        // Manage collides with others snakes
        for (Snake snake2 : getSnakes()) {
            if (snake2.getBody().isEmpty())
                continue;

            // Mutual collides
            if (snake2.collides(snake) && snake.getHead().collides(snake2.getHead())) {
                if (snake.getBody().size() == snake2.getBody().size()) {
                    snake2.getBody().clear();
                    snake.getBody().clear();
                    return;
                } else if (snake.getBody().size() > snake2.getBody().size())
                    snake2.getBody().clear();
                else {
                    snake.getBody().clear();
                    return;
                }
            } else if (snake2.collides(snake))
                snake2.getBody().clear();
        }

        // Manage collides with food
        for (Food food : getFoods()) {
            if (snake.getHead().collides(food)) {
                getFoods().remove(food);
                for (int i = 0; i < 4; ++i)
                    snake.addSegment(dt);
                getFoods().add(getValidRandomFood());
                if (getFoods().size() < 2)
                    getFoods().add(getValidRandomFood());
                break;
            }
        }

        // Manage collides with Window
        if (snake.collides(getWidth(), getHeight()))
            snake.byPassCollidesWindow(getWidth(), getHeight());
    }

    @Override
    public final void update(double dt) {
        // Make sure that there is two snakes and food can be created
        if (endGame())
            return;

        // Update all snakes of [snakes]
        for (Snake snake : getSnakes())
            updateOneSnake(snake, dt);

        // Remove all snakes of [snakes] that need to be removed
        getPlayers().removeIf(player -> player.getSnake().getBody().isEmpty());

        // Manage the end of the game
        if (endGame())
            // TODO: return to menu
            return;

        // Make all snakes of [snakes] move
        for (Snake snake : getSnakes())
            snake.move(dt);
    }

    @Override
    public final void onKeyPressed(KeyCode key) {
        if (endGame())
            return;

        for (Player player : getPlayers()) {
            Snake snake = player.getSnake();
            if (!snake.getBody().isEmpty()) {
                double angle = snake.getHead().getAngle();
                if (angle == 90 || angle == 270) {
                    if (key == player.getKeyUp())
                        snake.getHead().setAngle(180);
                    else if (key == player.getKeyDown())
                        snake.getHead().setAngle(0);
                } else if (angle == 0 || angle == 360 || angle == 180) {
                    if (key == player.getKeyLeft())
                        snake.getHead().setAngle(90);
                    else if (key == player.getKeyRight())
                        snake.getHead().setAngle(270);
                }
            }
        }

    }

    @Override
    public final void onMouseMoved(double pointerX, double pointerY) {
    }

    @Override
    public final boolean endGame() {
        return getSnakes().size() != 2 || getValidRandomFood() == null;
    }

}