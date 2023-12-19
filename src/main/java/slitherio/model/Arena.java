package slitherio.model;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.input.KeyCode;
import slitherio.gameobjects.*;

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

    /* aloo */
    private void updateOneSnake(Snake snake, double dt) {

        getFoods().get(0);
        // Collides with Foods
        for (Food food : getFoods()) {
            if (snake.getHead().collides(food)) {
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

        for (Segment seg : snake2.getBody()) {
            if (snake.getHead().collides(seg))
                snake.getBody().clear();
        }
        if (!snake.getBody().isEmpty()) {
            for (Segment seg : snake.getBody()) {
                if (snake2.getHead().collides(seg))
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
        snake.onKeyPressed(key);
        snake2.onKeyPressed(key);
    }
}
