package slitherio.controller;

import slitherio.model.*;
import slitherio.gameobjects.*;
import slitherio.view.*;
import javafx.animation.AnimationTimer;
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
                    arena.update(dt);
                    acc -= dt;
                }
            }
        }.start();
    }

    public void defautView() {
        var root = view.getRoot();
        for (Segment segment : arena.getSnake().getBody()) {
            view.getSnakeToDisplay().add(new SegmentView(root, segment));
        }
        for (Segment segment : arena.getSnake2().getBody()) {
            view.getSnake2ToDisplay().add(new SegmentView(root, segment));
        }
        for (Food food : arena.getFoods()) {
            view.getFoodsToDisplay().add(new FoodView(root, food));
        }

        System.out.println("SnakeToDisplay.size() " + view.getSnakeToDisplay().size());
    }

    public void bind() {
        var root = view.getRoot();
        arena.getSnake().getBodyProperty().addListener(new ListChangeListener<Segment>() {
            @Override
            public void onChanged(Change<? extends Segment> change) {
                while (change.next()) {
                    if (change.wasAdded()) {
                        change.getAddedSubList().forEach(((Segment segment) -> {
                            view.getSnakeToDisplay().add(new SegmentView(root, segment));
                        }));
                    } else if (arena.getSnake().getBody().isEmpty()) {
                        for (DisplayableObject d : view.getSnakeToDisplay())
                            view.getRoot().getChildren().remove(d.getGraphics());
                        view.getSnakeToDisplay().clear();
                    }
                }
            }
        });

        arena.getSnake2().getBodyProperty().addListener(new ListChangeListener<Segment>() {
            @Override
            public void onChanged(Change<? extends Segment> change) {
                while (change.next()) {
                    if (change.wasAdded()) {
                        change.getAddedSubList().forEach(((Segment segment) -> {
                            view.getSnake2ToDisplay().add(new SegmentView(root, segment));
                        }));
                    } else if (arena.getSnake2().getBody().isEmpty()) {
                        for (DisplayableObject d : view.getSnake2ToDisplay())
                            view.getRoot().getChildren().remove(d.getGraphics());
                        view.getSnake2ToDisplay().clear();
                    }
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