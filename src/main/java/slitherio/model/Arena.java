package slitherio.model;

import slitherio.gameobjects.*;
import javafx.scene.input.*;
import javafx.animation.*;
import javafx.beans.property.*;
import javafx.collections.*;

public class Arena {
    private ListProperty<Food> foods = new SimpleListProperty<Food>(FXCollections.<Food>observableArrayList());
    private Snake snake;
    private double width, height;

    public Arena(double width, double height) {
        this.width = width;
        this.height = height;
        snake = new Snake(100, 300);
        getFoods().add(Food.FoodRandom(width, height));
    }

    private void update(double dt) {
        Segment headSnake = snake.getBody().get(0);
        for (Food food : getFoods()) {
            if (headSnake.collides(food)) {
                getFoods().remove(food);
                snake.addSegment(dt);
                snake.addSegment(dt);
                snake.addSegment(dt);
                getFoods().add(Food.FoodRandom(width, height));
                break;
            }
        }
        snake.move(dt, width, height);
    }

    public void animate() {
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
                    update(dt);
                    acc -= dt;
                }
            }
        }.start();
    }

    public final ListProperty<Food> getFoodsProperty() {
        return foods;
    }

    public final ObservableList<Food> getFoods() {
        return foods.get();
    }

    public Snake getSnake() {
        return snake;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double value) {
        this.width = value;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double value) {
        this.height = value;
    }

    public void onKeyPressed(KeyCode key) {
        Segment headSnake = snake.getBody().get(0);
        int direction = headSnake.getDirection();
        if (direction == 2 || direction == 4) {
            if (key == KeyCode.UP)
                headSnake.setDirection(1);
            else if (key == KeyCode.DOWN)
                headSnake.setDirection(3);
        } else if (direction == 1 || direction == 3) {
            if (key == KeyCode.RIGHT)
                headSnake.setDirection(2);
            else if (key == KeyCode.LEFT)
                headSnake.setDirection(4);
        }
    }
}
