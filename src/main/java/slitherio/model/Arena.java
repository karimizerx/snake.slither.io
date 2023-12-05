package slitherio.model;

// Import project packages
import slitherio.gameobjects.*;

// Import java packages
import java.util.*;
import javafx.scene.input.*;
import javafx.animation.*;
import javafx.beans.property.*;
import javafx.collections.*;

public class Arena {
    private ListProperty<Food> foods;
    private Snake snake;
    private double w, h;

    public Arena(double w, double h) {
        this.w = w;
        this.h = h;
        snake = new Snake(100, 300);
        // Init [foods] to an empty list
        foods = new SimpleListProperty<Food>(FXCollections.<Food>observableArrayList());

        // Adding some segments and foods for tests...
        snake.addSegment();
        snake.addSegment();
        snake.addSegment();
        snake.addSegment();
        snake.addSegment();
        snake.addSegment();
        snake.addSegment();
        snake.addSegment();
        snake.addSegment();
        foods.get().add(new Food());
    }

    private void update(double dt) {
        snake.move(dt, w, h);
        // Collides management...
    }

    // Main thread, who manage the game
    public void animate() {
        new AnimationTimer() {
            long last = 0;
            final double dt = 0.1; // update every 0.01s
            double acc = 0.0;

            @Override
            public void handle(long now) {
                if (last == 0) { // ignore the first tick, just compute the first dt
                    last = now;
                    return;
                }

                acc += (now - last) * 1.0e-9; // convert nanoseconds to seconds
                last = now;

                while (acc >= dt) {
                    update(dt);
                    acc -= dt;
                }
            }
        }.start();
    }

    /* Getter & Setter */

    public final ObservableList<Food> getFoodsValue() {
        return foods.get();
    }

    // public final void setFoodsValue(ObservableList<Food> value) {
    // foods.set(value);
    // }

    // Acces to the property
    public final ListProperty<Food> getFoods() {
        return foods;
    }

    public Snake getSnake() {
        return snake;
    }

    public double getW() {
        return w;
    }

    public void setW(double w) {
        this.w = w;
    }

    public double getH() {
        return h;
    }

    public void setH(double h) {
        this.h = h;
    }

    /* Other */
    public void on_key_pressed(KeyCode key) {
        Segment head_snake = snake.getBodyValue().get(0);
        int direction = head_snake.getdirection();
        if (key == KeyCode.UP) {
            if (direction == 2 || direction == 4)
                head_snake.setDirection(1);
        }
        if (key == KeyCode.RIGHT) {
            if (direction == 1 || direction == 3)
                head_snake.setDirection(2);

        }
        if (key == KeyCode.DOWN) {
            if (direction == 2 || direction == 4)
                head_snake.setDirection(3);
        }
        if (key == KeyCode.LEFT) {
            if (direction == 1 || direction == 3)
                head_snake.setDirection(4);
        }
    }
}
