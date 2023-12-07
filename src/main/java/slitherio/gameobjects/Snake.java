package slitherio.gameobjects;

import javafx.beans.property.*;
import javafx.collections.*;

public class Snake {
    private ListProperty<Segment> body = new SimpleListProperty<Segment>(FXCollections.<Segment>observableArrayList());

    public Snake(double headX, double headY) {
        getBody().add(new Segment(headX, headY));
    }

    public void move(double dt, double maxX, double maxY) {
        Segment headSnake = body.getValue().get(0);
        switch (headSnake.getDirection()) {
            case 1 -> {
                if (headSnake.getUp() < 0)
                    return;
            }
            case 2 -> {
                if (maxX < headSnake.getRight())
                    return;
            }
            case 3 -> {
                if (maxY < headSnake.getDown())
                    return;
            }
            case 4 -> {
                if (headSnake.getLeft() < 0)
                    return;
            }
            default -> {
                return;
            }
        }
        moveToDirection(dt, headSnake.getDirection());
    }

    private void moveToDirection(double dt, int d) {
        for (int i = body.size() - 1; i > 0; --i) {
            Segment segment = body.getValue().get(i);
            Segment previous = body.getValue().get(i - 1);
            segment.setCenterX(previous.getCenterX());
            segment.setCenterY(previous.getCenterY());
            segment.setDirection(previous.getDirection());
        }
        Segment headSnake = body.getValue().get(0);
        switch (d) {
            case 1 -> headSnake.setCenterY(headSnake.getCenterY() - headSnake.getDy());
            case 2 -> headSnake.setCenterX(headSnake.getCenterX() + headSnake.getDx());
            case 3 -> headSnake.setCenterY(headSnake.getCenterY() + headSnake.getDy());
            case 4 -> headSnake.setCenterX(headSnake.getCenterX() - headSnake.getDx());
            default -> {
                return;
            }
        }
    }

    public void addSegment() {
        Segment headSnake = getBody().get(0);
        double hsx = headSnake.getCenterX(), hsy = headSnake.getCenterY(), nx = hsx, ny = hsy;
        double hsw = headSnake.getWidth(), hsh = headSnake.getHeight();
        int nd = headSnake.getDirection();
        switch (nd) {
            case 1 -> ny = hsy + hsh;
            case 2 -> nx = hsx - hsw;
            case 3 -> ny = hsy - hsh;
            case 4 -> nx = hsx + hsw;
            default -> {
            }
        }
        Segment newseg = new Segment(nx, ny, nd);
        getBody().add(1, newseg);
    }

    public final ListProperty<Segment> getBodyProperty() {
        return body;
    }

    public final ObservableList<Segment> getBody() {
        return body.getValue();
    }

}