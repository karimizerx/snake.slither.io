package slitherio.view;

import javafx.animation.*;
import javafx.collections.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import slitherio.model.*;
import slitherio.gameobjects.*;

/**
 * Fait le lien entre la vue et le model. Cette classe contient la boucle
 * principale du jeu ({@link #animate()}). Elle se charge de lier les éléments
 * du model et leur vue ({@link #bind()}).
 */
public final class Controller {
    /** The game's view. */
    private GameView view;
    /** The game. */
    private Arena model;
    /** Defines the statut of the game. True if game is running. */
    private boolean pause = false;

    /**
     * Create a new instance of Controller. This constructor also create player(s)
     * and inisialize the game and it view.
     * 
     * @param root     the pane of {@link #view}
     * @param width    the window's width
     * @param height   the window's height
     * @param nameGame the game's name
     */
    protected Controller(Pane root, double width, double height, String nameGame) {
        switch (nameGame) {
        case "Snake" -> {
            Player player = new Player(92, "KMZX", "snake.head.png", width / 2, height / 2);
            model = new SnakeGame(width, height, player);
        }
        case "Local SlitherIo" -> {
            Player player1 = new Player(1, "REAL", "snake.head.png", width * 2 / 3, 0);
            Player player2 = new Player(2, "PSG", "snake.head2.png", width / 3, 0, KeyCode.Z, KeyCode.S, KeyCode.Q,
                    KeyCode.D);
            model = new SlitherIoGame(width, height, player1, player2);
        }
        case "The Snake Slither" -> {
            Player player1 = new Player(1, "REAL", "snake.head.png", width * 2 / 3, 0);
            Player player2 = new Player(2, "PSG", "snake.head2.png", width / 3, 0, KeyCode.Z, KeyCode.S, KeyCode.Q,
                    KeyCode.D);
            model = new SnakeSlitherGame(width, height, width, height, player1, player2);
        }
        default -> {
        }
        }
        view = new GameView(root, width, height, model.getWorldtWidth() / 2, model.getWorldHeight() / 2);

    }

    /** Main function that initialize all values of game and launch it */
    protected final void startGame() {
        bind();
        defaultView();
        animate();
    }

    /**
     * Main loop, using the <b>"ups constant"</b> method. The game is update every
     * <b>0.01 seconds</b>.
     */
    protected final void animate() {
        new AnimationTimer() {
            long last = 0;
            final double dt = 0.01;
            double acc = 0.0;

            @Override
            public void handle(long now) {
                if (last == 0) {
                    last = now;
                    return;
                }

                if (pause)
                    last = now;

                acc += (now - last) * 1.0e-9; // Convert nanoseconds to seconds
                last = now;

                while (acc >= dt) {
                    model.update(dt);
                    acc -= dt;
                }

            }
        }.start();
    }

    /**
     * Manage pressed key
     * 
     * @param key the key that has been pressed
     */
    protected final void onKeyPressed(KeyCode key) {
        if (key == KeyCode.P)
            pause = !pause;
        else if (!pause)
            model.onKeyPressed(key);
    }

    /**
     * Manage action when the mouse is moved.
     * 
     * @param mouseX X-coordinate of the mouse
     * @param mouseY Y-coordinate of the mouse
     */
    protected final void onMouseMoved(double mouseX, double mouseY) {
        model.onMouseMoved(mouseX, mouseY);
    }

    /**
     * Bind the properties {@link GameView#getWidthProperty() view.width} and
     * {@link GameView#getHeightProperty() view.height} with
     * {@link slitherio.model.Arena#getWidth() model.width} and
     * {@link slitherio.model.Arena#getHeight() model.height}.
     */
    private void bindSize() {
        view.getWidthProperty().addListener((obs, oldVal, newVal) -> model.setWidth((double) newVal));
        view.getHeightProperty().addListener((obs, oldVal, newVal) -> model.setHeight((double) newVal));
    }

    /**
     * Bind the properties {@link slitherio.model.Arena#getFoods() model.foods} and
     * {@link GameView#getFoodsToDisplay() view.foodsToDisplay}.
     */
    private void bindFoods() {
        model.getFoodsProperty().addListener(new ListChangeListener<Food>() {

            @Override
            public void onChanged(Change<? extends Food> change) {
                while (change.next()) {

                    // Manage removed foods
                    if (change.wasRemoved()) {
                        for (Food food : change.getRemoved()) {
                            int cnt = view.getFoodsToDisplay().size() - 1;
                            while (cnt >= 0) {
                                DisplayableObject foodView = view.getFoodsToDisplay().get(cnt);
                                if (foodView.getObject().equals(food)) {
                                    view.removeFood(foodView);
                                    break;
                                }
                                --cnt;
                            }
                        }
                    }

                    // Manage added foods
                    if (change.wasAdded())
                        change.getAddedSubList().forEach(food -> view.addFood(food));
                }
            }

        });
    }

    /**
     * Bind the properties {@link slitherio.model.Arena#getSnakes() model.snakes}
     * and {@link GameView#getSnakesToDisplay() view.snakesToDisplay}
     */
    private void bindSnakes() {
        model.getSnakesProperty().addListener(new ListChangeListener<Snake>() {

            @Override
            public void onChanged(Change<? extends Snake> change) {
                while (change.next()) {

                    // Manage removed snakes
                    if (change.wasRemoved()) {
                        for (Snake snake : change.getRemoved()) {
                            for (SnakeView snakeView : view.getSnakesToDisplay()) {
                                if (snakeView.getSnake().equals(snake))
                                    view.removeSnake(snakeView);
                            }
                        }
                    }

                    // Manage added snakes
                    if (change.wasAdded())
                        change.getAddedSubList().forEach(snake -> view.addSnake(snake));
                }
            }

        });
    }

    /**
     * Bind all objects of the model with the view.
     * 
     * @see #bindSize()
     * @see #bindFoods()
     * @see #bindSnakes()
     */
    private void bind() {
        bindSize();
        bindFoods();
        bindSnakes();
    }

    /** Initialize the defaut view. */
    protected final void defaultView() {
        model.getSnakes().forEach(snake -> view.addSnake(snake));
        model.getFoods().forEach(food -> view.addFood(food));
    }

    /**
     * Get the model.
     * 
     * @return the model
     */
    protected final Arena getModel() {
        return model;
    }

    /**
     * Get the view.
     * 
     * @return the view
     */
    protected final GameView getView() {
        return view;
    }

}