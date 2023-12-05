package slitherio.controller;

// Import project packages
import slitherio.model.*;
import slitherio.gameobjects.*;
import slitherio.view.*;
import javafx.collections.*;
// Import java packages
import javafx.scene.input.*;

public class Controller {
    Gameview view;
    Arena arena;

    public void setArena(Arena a) {
        arena = a;
    }

    public void setView(Gameview g) {
        view = g;
    }

    public void on_key_pressed(KeyCode key) {
        arena.on_key_pressed(key);
    }

    public void defautView() {
        Snake snake = arena.getSnake();
        for (Segment segment : snake.getBodyValue()) {
            view.getToDisplay().add(new SegmentView(view.getRoot(), segment));
        }
        for (Food food : arena.getFoodsValue()) {
            view.getToDisplay().add(new FoodView(view.getRoot(), food));
        }
    }

    public void bind() {
        Snake snake = arena.getSnake();
        snake.getBody().addListener(new ListChangeListener<Segment>() {
            @Override
            public void onChanged(Change<? extends Segment> change) {
                while (change.next()) {
                    change.getAddedSubList().forEach(((Segment segment) -> {
                        view.getToDisplay().add(new SegmentView(view.getRoot(), segment));
                    }));
                }
            }
        });
        arena.getFoods().addListener(new ListChangeListener<Food>() {
            @Override
            public void onChanged(Change<? extends Food> change) {
                while (change.next()) {
                    change.getAddedSubList().forEach(((Food food) -> {
                        view.getToDisplay().add(new FoodView(view.getRoot(), food));
                    }));
                    change.getRemoved().forEach((Food food) -> {
                        System.out.printf("A food has been removed. Pos: %s,%s", food.getMiddleX(), food.getMiddleY())
                                .println();
                    });
                }
            }
        });
    }
}