package slitherio.controller;

import slitherio.model.*;
import slitherio.gameobjects.*;
import slitherio.view.*;
import javafx.collections.*;
import javafx.scene.input.*;
import javafx.scene.paint.ImagePattern;

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
        var root = view.getRoot();
        for (Segment segment : arena.getSnake().getBody()) {
            view.getSnakeToDisplay().add(new SegmentView(root, segment));
        }
        for (Food food : arena.getFoods()) {
            view.getFoodsToDisplay().add(new FoodView(root, food));
        }
    }

    public void bind() {
        Snake snake = arena.getSnake();
        var root = view.getRoot();
        snake.getBodyProperty().addListener(new ListChangeListener<Segment>() {
            @Override
            public void onChanged(Change<? extends Segment> change) {
                while (change.next()) {
                    change.getAddedSubList().forEach(((Segment segment) -> {

                        view.getSnakeToDisplay().add(new SegmentView(root, segment));
                    }));
                }
            }
        });
        arena.getFoodsProperty().addListener(new ListChangeListener<Food>() {
            @Override
            public void onChanged(Change<? extends Food> change) {
                while (change.next()) {
                    change.getAddedSubList().forEach(((Food food) -> {
                        view.getFoodsToDisplay().add(new FoodView(root, food));
                    }));
                    change.getRemoved().forEach(((Food food) -> {
                        DisplayableObject d = view.getFoodsToDisplay().remove(change.getFrom());
                        view.getRoot().getChildren().remove(d.getGraphics());
                    }));
                }
            }
        });
    }
}