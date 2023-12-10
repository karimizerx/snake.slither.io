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
        int dir = headSnake.getDirection();
        double epsilon = 0.00001;
        double left = headSnake.getLeft() - epsilon;
        double right = headSnake.getRight() + epsilon;
        double up = headSnake.getUp() - epsilon;
        double down = headSnake.getDown() + epsilon;
        if ((left < 0 && dir == 4) || (right > maxX && dir == 2) || (up < 0 && dir == 1) || (down > maxY && dir == 3))
            return;
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
        double nx = headSnake.getDx() * dt, ny = headSnake.getDy() * dt;
        switch (d) {
            case 1 -> headSnake.setCenterY(headSnake.getCenterY() - ny);
            case 2 -> headSnake.setCenterX(headSnake.getCenterX() + nx);
            case 3 -> headSnake.setCenterY(headSnake.getCenterY() + ny);
            case 4 -> headSnake.setCenterX(headSnake.getCenterX() - nx);
            default -> {
                return;
            }
        }
    }

    public void addSegment(double dt) {
        Segment headSnake = getBody().get(0);
        double hsx = headSnake.getCenterX(), hsy = headSnake.getCenterY();
        double hdx = headSnake.getDx() * dt, hdy = headSnake.getDy() * dt;
        double nx = hsx, ny = hsy;
        int nd = headSnake.getDirection();
        switch (nd) {
            case 1 -> ny -= hdy;
            case 2 -> nx += hdx;
            case 3 -> ny += hdy;
            case 4 -> nx -= hdy;
            default -> {
            }
        }
        Segment newseg = new Segment(nx, ny, nd);
        getBody().add(0, newseg);
    }

    public final ListProperty<Segment> getBodyProperty() {
        return body;
    }

    public final ObservableList<Segment> getBody() {
        return body.getValue();
    }

}