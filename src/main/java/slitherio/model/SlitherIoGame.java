package slitherio.model;

import javafx.scene.input.KeyCode;
import slitherio.gameobjects.*;

public final class SlitherIoGame extends Arena {

    /* ******************** Constructors ******************** */

    public SlitherIoGame(double width, double height) {
        super(width, height);

        // Manage players
        Player player1 = new Player(1, "REAL", 0, 0);
        Player player2 = new Player(2, "PSG", width, 0, KeyCode.Q, KeyCode.D);
        getPlayers().addAll(player1, player2);
        getPlayers().forEach(player -> player.getSnake().setValidPosition(width, height));

        // Manage default [food] list content
        getFoods().add(getValidRandomFood());
    }

    /* ******************** Functions ******************** */

    // Make sure that there is juste one snake
    private boolean assertSize() {
        if (getSnakes().size() != 2) {
            System.out.println("SnakeGame: assertSize: Invalid Number Of Snakes (" + getSnakes().size() + ")");
            return false;
        }
        return true;
    }

    // Update values of [snake]. [dt] is the elapsed time
    private void updateOneSnake(Snake snake, double dt) {
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
        if (!assertSize())
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
        if (!assertSize())
            return;

        for (Player player : getPlayers()) {
            Snake snake = player.getSnake();
            if (!snake.getBody().isEmpty()) {
                double angle = snake.getHead().getRotation();
                if (angle == 90 || angle == 270) {
                    if (key == player.getKeyUp())
                        snake.getHead().setRotation(180);
                    else if (key == player.getKeyDown())
                        snake.getHead().setRotation(0);
                } else if (angle == 0 || angle == 360 || angle == 180) {
                    if (key == player.getKeyLeft())
                        snake.getHead().setRotation(90);
                    else if (key == player.getKeyRight())
                        snake.getHead().setRotation(270);
                }
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

}