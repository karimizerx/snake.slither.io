package slitherio.gameobjects;

import javafx.beans.property.*;
import javafx.collections.*;
import javafx.scene.input.*;

public class Snake {
    private ListProperty<Segment> body = new SimpleListProperty<Segment>(FXCollections.<Segment>observableArrayList());
    private KeyCode keyUp = KeyCode.UP, keyDown = KeyCode.DOWN, keyLeft = KeyCode.LEFT, keyRight = KeyCode.RIGHT;

    /* ******************** Constructor ******************** */
    public Snake(double headX, double headY) {
        getBody().add(new Segment(headX, headY));
    }

    public Snake(double headX, double headY, KeyCode keyUp, KeyCode keyDown, KeyCode keyLeft, KeyCode keyRight) {
        getBody().add(new Segment(headX, headY));
        this.keyUp = keyUp;
        this.keyDown = keyDown;
        this.keyLeft = keyLeft;
        this.keyRight = keyRight;
    }

    /* ******************** Functions ******************** */
    // Make the snake move. [dt] is the elapsed time
    public final void move(double dt) {
        moveToDirection(dt, getHead().getDirection());
    }

    // Move the snake to the [direction]. [dt] is the elapsed time
    private void moveToDirection(double dt, int direction) {
        for (int i = body.size() - 1; i > 0; --i) {
            Segment segment = body.getValue().get(i);
            Segment previous = body.getValue().get(i - 1);
            segment.setCenterX(previous.getCenterX());
            segment.setCenterY(previous.getCenterY());
            segment.setDirection(previous.getDirection());
        }

        double nx = getHead().getDx() * dt, ny = getHead().getDy() * dt;
        switch (direction) {
        case 1 -> getHead().setCenterY(getHead().getCenterY() - ny);
        case 2 -> getHead().setCenterX(getHead().getCenterX() + nx);
        case 3 -> getHead().setCenterY(getHead().getCenterY() + ny);
        case 4 -> getHead().setCenterX(getHead().getCenterX() - nx);
        default -> {
            return;
        }
        }
    }

    // Add a segment to the snake. [dt] is the elapsed time
    public final void addSegment(double dt) {
        double hsx = getHead().getCenterX(), hsy = getHead().getCenterY();
        double hdx = getHead().getDx() * dt, hdy = getHead().getDy() * dt;
        double nx = hsx, ny = hsy;
        int nd = getHead().getDirection();

        switch (nd) {
        case 1 -> ny -= hdy;
        case 2 -> nx += hdx;
        case 3 -> ny += hdy;
        case 4 -> nx -= hdy;
        default -> {
        }
        }

        getBody().add(0, new Segment(nx, ny, nd));
    }

    // Return True if [this] (this.head) collides [snake]
    public final boolean collidesSnake(Snake snake) {
        if (this.equals(snake))
            return false;
        for (Segment segment : snake.getBody()) {
            if (getHead().collides(segment))
                return true;
        }
        return false;
    }

    public final void onKeyPressed(KeyCode key) {
        if (!getBody().isEmpty()) {
            int direction = getHead().getDirection();
            if (direction == 2 || direction == 4) {
                if (key == keyUp)
                    getHead().setDirection(1);
                else if (key == keyDown)
                    getHead().setDirection(3);
            } else if (direction == 1 || direction == 3) {
                if (key == keyRight)
                    getHead().setDirection(2);
                else if (key == keyLeft)
                    getHead().setDirection(4);
            }
        }
    }

    /* ******************** Getter & Setter ******************** */
    // Get the snake's head
    public final Segment getHead() {
        return getBody().get(0);
    }

    // Get the snake's body property
    public final ListProperty<Segment> getBodyProperty() {
        return body;
    }

    // Get the snake's body
    public final ObservableList<Segment> getBody() {
        return body.getValue();
    }

}