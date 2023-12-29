package slitherio.view;

import javafx.animation.*;
import javafx.collections.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import slitherio.model.*;
import slitherio.gameobjects.*;

public final class Controller {
    private GameView view;
    private Arena model;
    private boolean pause = false;

    /* ******************** Constructor ******************** */
    protected Controller(Pane root, double width, double height, String nameGame) {
        view = new GameView(root, width, height);
        switch (nameGame) {
        case "Snake" -> model = new SnakeGame(width, height);
        case "Local SlitherIo" -> model = new SlitherIoGame(width, height);
        case "The Snake Slither" -> model = new SnakeSlitherGame(width, height);
        default -> {
        }
        }
    }

    /* ******************** Functions ******************** */

    // Main function that initialize all values of game and launch it
    protected final void startGame() {
        bind();
        defaultView();
        animate();
    }

    // Run en continue...
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

    // Manage pressed key
    protected final void onKeyPressed(KeyCode key) {
        if (key == KeyCode.P)
            pause = !pause;
        else
            model.onKeyPressed(key);
    }

    protected final void onMouseMoved(double mouseX, double mouseY) {
        model.onMouseMoved(mouseX, mouseY);
    }

    private void bindSize() {
        view.getWidthProperty().addListener((obs, oldVal, newVal) -> model.setWidth((double) newVal));
        view.getHeightProperty().addListener((obs, oldVal, newVal) -> model.setHeight((double) newVal));
    }

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

    private void bind() {
        bindSize();
        bindFoods();
        bindSnakes();
    }

    protected final void defaultView() {
        model.getSnakes().forEach(snake -> view.addSnake(snake));
        model.getFoods().forEach(food -> view.addFood(food));
    }

    /* ******************** Getter & Setter ******************** */

    protected final Arena getModel() {
        return model;
    }

    protected final GameView getView() {
        return view;
    }

}