package slitherio.gameobjects;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.input.KeyCode;
import slitherio.Utils.Utils;

public class Snake {
    private ListProperty<Segment> body = new SimpleListProperty<Segment>(FXCollections.<Segment>observableArrayList());
    private KeyCode keyUp = KeyCode.UP, keyDown = KeyCode.DOWN, keyLeft = KeyCode.LEFT, keyRight = KeyCode.RIGHT;

    /* ******************** Constructor ******************** */
    public Snake(double headX, double headY) {
        getBody().add(new Segment(headX, headY));
    }

    public Snake(double headX, double headY, int bodyLength) {
        getBody().add(new Segment(headX, headY));
        for (int i = 1; i < bodyLength; ++i) {
            Segment previous = getBody().get(getBody().size() - 1);
            getBody().add(new Segment(previous.getCenterX() + 2.5, previous.getCenterY()));
        }

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
        moveToDirection(dt, getHead().getRotation());
    }

    // Move the snake in the direction of [rotation]. [dt] is the elapsed time
    private void moveToDirection(double dt, double rotation) {
        for (int i = body.size() - 1; i > 0; --i) {
            Segment segment = body.getValue().get(i);
            Segment previous = body.getValue().get(i - 1);
            segment.setCenterX(previous.getCenterX());
            segment.setCenterY(previous.getCenterY());
            segment.setRotation(previous.getRotation());
        }

        // double nx = getHead().getDx() * dt, ny = getHead().getDy() * dt;
        // switch ((int) rotation) {
        // case 180 -> getHead().setCenterY(getHead().getCenterY() - ny);
        // case -90 -> getHead().setCenterX(getHead().getCenterX() + nx);
        // case 0 -> getHead().setCenterY(getHead().getCenterY() + ny);
        // case 90 -> getHead().setCenterX(getHead().getCenterX() - nx);
        // default -> {
        // return;
        // }
        // }

        double hx = getHead().getCenterX(), hy = getHead().getCenterY();
        double dx = getHead().getDx() * dt, dy = getHead().getDy() * dt;
        double nx = hx + Math.sin(rotation) * dx, ny = hy + Math.cos(rotation) * dy;

        getHead().setCenterX(nx);
        getHead().setCenterY(ny);
    }

    // Add a segment to the snake. [dt] is the elapsed time
    public final void addSegment(double dt) {
        double hsx = getHead().getCenterX(), hsy = getHead().getCenterY();
        double hdx = getHead().getDx() * dt, hdy = getHead().getDy() * dt;
        double nx = hsx, ny = hsy;
        double nr = getHead().getRotation();

        switch ((int) nr) {
        case 180 -> ny -= hdy;
        case -90 -> nx += hdx;
        case 0 -> ny += hdy;
        case 90 -> nx -= hdy;
        default -> {
        }
        }

        getBody().add(0, new Segment(nx, ny, nr));
    }

    // Return True if [this] (this.head) collides [snake]
    public final boolean collides(Snake snake) {
        if (this.equals(snake))
            return false;
        for (Segment segment : snake.getBody()) {
            if (getHead().collides(segment))
                return true;
        }
        return false;
    }

    // Return True if [this] (this.head) collides window's edges
    public final boolean collides(double maxWidth, double maxHeight) {
        if (!getHead().collides(maxWidth, maxHeight))
            return false;

        boolean collidesUp = getHead().getUp() < 0 && getHead().getRotation() == 180;
        boolean collidesDown = getHead().getDown() > maxHeight && getHead().getRotation() == 0;
        boolean collidesLeft = getHead().getLeft() < 0 && getHead().getRotation() == 90;
        boolean collidesRight = getHead().getRight() > maxWidth && getHead().getRotation() == -90;
        return collidesUp || collidesDown || collidesLeft || collidesRight;
    }

    // Change [head]'s direction when [this] collides window's edges
    public final void byPassCollidesWindow(double maxWidth, double maxHeight) {
        // Check if snake is collides window's edges
        if (!collides(maxWidth, maxHeight))
            return;

        double newRotation = getHead().getRotation();
        if (getHead().getUp() < 0)
            newRotation = (getHead().getRight() > maxWidth) ? 90 : -90;
        else if (getHead().getDown() > maxHeight)
            newRotation = (getHead().getLeft() < 0) ? -90 : 90;
        else if (getHead().getLeft() < 0)
            newRotation = (getHead().getUp() < 0) ? 0 : 180;
        else if (getHead().getRight() > maxWidth)
            newRotation = (getHead().getDown() > maxHeight) ? 180 : 0;

        getHead().setRotation(newRotation);
    }

    public final void onKeyPressed(KeyCode key) {
        /* Orignal Snake version */
        // if (!getBody().isEmpty()) {
        // double rotation = getHead().getRotation();
        // if (rotation == -90 || rotation == 90) {
        // if (key == keyUp)
        // getHead().setRotation(180);
        // else if (key == keyDown)
        // getHead().setRotation(0);
        // } else if (rotation == 180 || rotation == 0) {
        // if (key == keyRight)
        // getHead().setRotation(-90);
        // else if (key == keyLeft)
        // getHead().setRotation(90);
        // }
        // }

        if (!getBody().isEmpty()) {
            double incrRotate = 0;
            if (key == keyLeft) {
                incrRotate = -10;
            } else if (key == keyRight) {
                incrRotate = 10;
            }
            getHead().setRotation(Utils.getValidAngle(getHead().getRotation() + incrRotate));
        }

    }

    public final void onMouseMoved(double pointerX, double pointerY) {
        getHead().setRotation(Utils.getAngle(getHead().getCenterX(), getHead().getCenterY(), pointerX, pointerY));
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

    // S'il est strictement dans l'hemisphere nord, sud, droite, gauche
    public final boolean isUp() {
        return 180 < getHead().getRotation() && getHead().getRotation() < 360;
    }

    public final boolean isDown() {
        return (0 < getHead().getRotation() && getHead().getRotation() < 180);
    }

    public final boolean isLeft() {
        return 90 < getHead().getRotation() && getHead().getRotation() < 270;
    }

    public final boolean isRight() {
        return (270 < getHead().getRotation() && getHead().getRotation() < 360)
                || (0 < getHead().getRotation() && getHead().getRotation() < 90);
    }

    public final boolean isNorth() {
        return getHead().getRotation() == 180;
    }

    public final boolean isSouth() {
        return getHead().getRotation() == 0 || getHead().getRotation() == 360;
    }

    public final boolean isEast() {
        return getHead().getRotation() == 90;
    }

    public final boolean isWest() {
        return getHead().getRotation() == 270;
    }

}