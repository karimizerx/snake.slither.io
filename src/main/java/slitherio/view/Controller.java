package slitherio.view;

import javafx.animation.*;
import javafx.beans.property.*;
import javafx.collections.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import slitherio.model.*;
import slitherio.gameobjects.*;

public final class Controller {
    private GameView view;
    private Arena model;
    private boolean pause = false;
    private final ListProperty<Player> players = new SimpleListProperty<Player>(
            FXCollections.<Player>observableArrayList());

    /* ******************** Constructor ******************** */
    protected Controller(Pane root, double width, double height) {
        view = new GameView(root, width, height);
        model = new Arena(width, height);
    }

    /* ******************** Functions ******************** */

    // Main function that initialize all values of game and launch it
    protected final void startGame() {
        bind();
        // En fonction du gamemodes....
        Player player = new Player(92, "KMZX", false, model.getWidth() / 2, model.getHeight() / 2);
        getPlayers().add(player);
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
                        for (Snake snake : change.getRemoved()) {
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

    private void bindPlayers() {
        players.addListener(new ListChangeListener<Player>() {
            @Override
            public void onChanged(Change<? extends Player> change) {
                while (change.next()) {

                    if (change.wasRemoved())
                        change.getRemoved().forEach((Player player) -> model.getSnakes().remove(player.getSnake()));

                    if (change.wasAdded())
                        change.getAddedSubList().forEach((Player player) -> model.getSnakes().add(player.getSnake()));
                }
            }
        });
    }

    private void bind() {
        bindPlayers();
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

    protected final ListProperty<Player> getPlayersProperty() {
        return players;
    }

    protected final ObservableList<Player> getPlayers() {
        return players.get();
    }
}