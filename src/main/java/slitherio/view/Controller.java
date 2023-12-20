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

    /* ******************** Constructor ******************** */
    protected Controller(Pane root, double width, double height) {
        view = new GameView(root, width, height);
        model = new Arena(width, height);
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

                acc += (now - last) * 1.0e-9;
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
        model.onKeyPressed(key);
    }

    private void bindFoods() {
        model.getFoodsProperty().addListener(new ListChangeListener<Food>() {

            @Override
            public void onChanged(Change<? extends Food> change) {
                while (change.next()) {

                    // Manage removed foods
                    if (change.wasRemoved()) {
                        for (Food food : change.getRemoved()) {
                            for (DisplayableObject foodview : view.getFoodsToDisplay()) {
                                if (foodview.getObject().equals(food))
                                    view.removeFood(foodview);
                            }
                        }
                    }

                    // Manage added foods
                    if (change.wasAdded())
                        change.getAddedSubList().forEach((Food food) -> view.addFood(food));
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
                        for (Snake snake : model.getSnakes()) {
                            for (SnakeView snakeView : view.getSnakesToDisplay()) {
                                if (snakeView.getSnake().equals(snake))
                                    view.removeSnake(snakeView);
                            }
                        }
                    }

                    // Manage added snakes
                    if (change.wasAdded())
                        change.getAddedSubList().forEach((Snake snake) -> view.addSnake(snake));
                }
            }

        });
    }

    private void bind() {
        bindFoods();
        bindSnakes();
    }

    protected final void defaultView() {
        model.getSnakes().forEach((Snake snake) -> view.addSnake(snake));
        model.getFoods().forEach((Food food) -> view.addFood(food));
    }

    /* ******************** Getter & Setter ******************** */

    protected final Arena getModel() {
        return model;
    }

    protected final GameView getView() {
        return view;
    }
}