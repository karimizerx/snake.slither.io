package slitherio.model;

import slitherio.gameobjects.*;
import javafx.scene.input.*;
import javafx.beans.property.*;
import javafx.collections.*;

public class Arena {
    private ListProperty<Food> foods = new SimpleListProperty<Food>(FXCollections.<Food>observableArrayList());
    private Snake snake, snake2;
    private double width, height;

    public Arena(double width, double height) {
        this.width = width;
        this.height = height;
        snake = new Snake(600, 300);
        snake2 = new Snake(100, 350);
        getFoods().add(Food.FoodRandom(width, height));
    }

    private void updateOneSnake(Snake snake, double dt) {
        Segment headSnake = snake.getBody().get(0);

        // Collides with Foods
        for (Food food : getFoods()) {
            if (headSnake.collides(food)) {
                getFoods().remove(food);
                for (int i = 0; i < 100; i++)
                    snake.addSegment(dt);
                getFoods().add(Food.FoodRandom(width, height));
                break;
            }
        }

        // // Collides with Frame Border
        // int dir = headSnake.getDirection();
        // double epsilon = 0.00001;
        // double left = headSnake.getLeft() - epsilon, right = headSnake.getRight() +
        // epsilon;
        // double up = headSnake.getUp() - epsilon, down = headSnake.getDown() +
        // epsilon;
        // if ((left < 0 && dir == 4) || (right > width && dir == 2) || (up < 0 && dir
        // == 1)
        // || (down > height && dir == 3))
        // System.exit(0);
    }

    public void update(double dt) {
        if (snake.getBody().isEmpty())
            return;
        if (snake2.getBody().isEmpty())
            return;
        updateOneSnake(snake, dt);
        updateOneSnake(snake2, dt);
        Segment headSnake = snake.getBody().get(0);
        Segment headSnake2 = snake2.getBody().get(0);

        for (Segment seg : snake2.getBody()) {
            if (headSnake.collides(seg))
                snake.getBody().clear();
        }
        if (!snake.getBody().isEmpty()) {
            for (Segment seg : snake.getBody()) {
                if (headSnake2.collides(seg))
                    snake2.getBody().clear();
            }
        }
        if (!snake.getBody().isEmpty())
            snake.move(dt, width, height);
        if (!snake2.getBody().isEmpty())
            snake2.move(dt, width, height);
    }

    public Snake getSnake2() {
        return snake2;
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
        if (!snake.getBody().isEmpty()) {
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

        if (!snake2.getBody().isEmpty()) {
            Segment headSnake2 = snake2.getBody().get(0);
            int d = headSnake2.getDirection();
            if (d == 2 || d == 4) {
                if (key == KeyCode.Z)
                    headSnake2.setDirection(1);
                else if (key == KeyCode.S)
                    headSnake2.setDirection(3);
            } else if (d == 1 || d == 3) {
                if (key == KeyCode.D)
                    headSnake2.setDirection(2);
                else if (key == KeyCode.Q)
                    headSnake2.setDirection(4);
            }
        }
    }
}
