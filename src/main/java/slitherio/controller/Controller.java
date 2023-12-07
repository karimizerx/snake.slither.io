package slitherio.controller;

import slitherio.model.*;
import slitherio.gameobjects.*;
import slitherio.view.*;
import javafx.collections.*;
import javafx.scene.input.*;

public class Controller {
    private Gameview view;
    private Arena arena;

    public void setArena(Arena arena) {
        this.arena = arena;
    }

    public void setView(Gameview view) {
        this.view = view;
    }

    public void onKeyPressed(KeyCode key) {
        arena.onKeyPressed(key);
    }

    public void defautView() {
        for (Segment segment : arena.getSnake().getBody()) {
            view.getToDisplay().add(new SegmentView(view.getRoot(), segment));
        }
        for (Food food : arena.getFoods()) {
            view.getToDisplay().add(new FoodView(view.getRoot(), food));
        }
    }

    public void bind() {
        Snake snake = arena.getSnake();
        snake.getBodyProperty().addListener(new ListChangeListener<Segment>() {
            @Override
            public void onChanged(Change<? extends Segment> change) {
                while (change.next()) {
                    change.getAddedSubList().forEach(((Segment segment) -> {
                        view.getToDisplay().add(new SegmentView(view.getRoot(), segment));
                    }));
                }
            }
        });
        arena.getFoodsProperty().addListener(new ListChangeListener<Food>() {
            @Override
            public void onChanged(Change<? extends Food> change) {
                while (change.next()) {
                    change.getAddedSubList().forEach(((Food food) -> {
                        view.getToDisplay().add(new FoodView(view.getRoot(), food));
                    }));
                }
            }
        });
    }
}