package slitherio.model;

import javafx.scene.input.KeyCode;
import slitherio.Utils.Utils;
import slitherio.gameobjects.*;

public final class SnakeSlitherGame extends Arena {

    /* ******************** Constructors ******************** */

    public SnakeSlitherGame(double wWidth, double wHeight, double width, double height, Player player1,
            Player player2) {
        super(wWidth, wHeight, width, height);

        // Manage players
        getPlayers().addAll(player1, player2);
        getPlayers().forEach(player -> player.getSnake().setValidPosition(width, height));

        // Manage default [food] list content
        getFoods().add(getValidRandomFood());
    }

    /* ******************** Functions ******************** */

    // Update values of [snake]. [dt] is the elapsed time
    private void updateOneSnake(Snake snake, double dt) {
        // Make sure that the [snake]'s body isn't empty
        if (snake.getBody().isEmpty())
            return;

        // Manage collides with others snakes
        for (Snake snake2 : getSnakes()) {

            if (!snake2.getBody().isEmpty()) {

                // Mutual collides
                if (snake2.collides(snake) && snake.getHead().collides(snake2.getHead())) {
                    snake2.getBody().clear();
                    snake.getBody().clear();
                    return;
                } else if (snake2.collides(snake))
                    snake2.getBody().clear();
            }
        }

        // Manage collides with food
        for (Food food : getFoods()) {
            if (snake.getHead().collides(food)) {
                getFoods().remove(food);
                snake.addSegment(dt);
                getFoods().add(getValidRandomFood());
                break;
            }
        }

        // Manage collides with Window
        if (snake.collides(getWidth(), getHeight()))
            snake.byPassCollidesWindow(getWidth(), getHeight());
    }

    @Override
    // Update all values of the game. [dt] is the elapsed time
    public final void update(double dt) {
        // Make sure that there is one snake
        if (endGame())
            return;

        // Update all snakes of [snakes]
        for (Snake snake : getSnakes())
            updateOneSnake(snake, dt);

        // Remove all snakes of [snakes] that need to be removed
        getPlayers().removeIf(player -> player.getSnake().getBody().isEmpty());

        // Make all snakes of [snakes] move
        for (Snake snake : getSnakes())
            snake.move(dt);
    }

    @Override
    public final void onKeyPressed(KeyCode key) {
    }

    @Override
    public final void onMouseMoved(double pointerX, double pointerY) {
        for (Snake snake : getSnakes()) {
            if (!snake.getBody().isEmpty()) {
                double angle = Utils.getAngle(snake.getHead().getCenterX(), snake.getHead().getCenterY(), pointerX,
                        pointerY);
                snake.getHead().setAngle(angle);
            }
        }
    }

    @Override
    public final boolean endGame() {
        return getSnakes().isEmpty();
    }
    /* ******************** Getter & Setter ******************** */

}