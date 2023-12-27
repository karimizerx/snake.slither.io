package slitherio.gameobjects;

import javafx.beans.property.*;
import javafx.collections.*;
import javafx.scene.input.KeyCode;
import slitherio.Utils.Utils;

public final class Snake {
    private final ListProperty<Segment> body = new SimpleListProperty<Segment>(
            FXCollections.<Segment>observableArrayList());
    private KeyCode keyUp = KeyCode.UP, keyDown = KeyCode.DOWN, keyLeft = KeyCode.LEFT, keyRight = KeyCode.RIGHT;

    /* ******************** Constructors ******************** */
    public Snake(double headX, double headY) {
        getBody().add(new Segment(headX, headY)); // A snake has, by default, a head.
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

        // Manage snake's head move
        double hx = getHead().getCenterX(), hy = getHead().getCenterY();
        double dx = getHead().getDx() * dt, dy = getHead().getDy() * dt;
        double nx = hx - Math.sin(Math.toRadians(rotation)) * dx, ny = hy + Math.cos(Math.toRadians(rotation)) * dy;
        getHead().setCenterX(nx);
        getHead().setCenterY(ny);
    }

    // Add a segment to the snake. [dt] is the elapsed time
    public final void addSegment(double dt) {
        double hx = getHead().getCenterX(), hy = getHead().getCenterY();
        double hdx = getHead().getDx() * dt, hdy = getHead().getDy() * dt;
        double nr = getHead().getRotation();
        double nx = hx - Math.sin(Math.toRadians(nr)) * hdx, ny = hy + Math.cos(Math.toRadians(nr)) * hdy;
        getBody().add(0, new Segment(nx, ny, nr));
        /* VOIR POURQUOI ON PEUT PAS AJOUTER A LA FINw */
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

        boolean collidesUp = getHead().getUp() < 0;
        boolean collidesDown = getHead().getDown() > maxHeight;
        boolean collidesLeft = getHead().getLeft() < 0;
        boolean collidesRight = getHead().getRight() > maxWidth;
        return collidesUp || collidesDown || collidesLeft || collidesRight;
    }

    // Change [head]'s direction when [this] collides window's edges
    public final void byPassCollidesWindow(double maxWidth, double maxHeight) {
        // Check if snake is collides window's edges
        if (!collides(maxWidth, maxHeight))
            return;

        for (Segment segment : body) {
            if (segment.getCenterX() < 0)
                segment.setCenterX(maxWidth);
            else if (segment.getCenterX() > maxWidth)
                segment.setCenterX(0);
            if (segment.getCenterY() < 0)
                segment.setCenterY(maxHeight);
            else if (segment.getCenterY() > maxHeight)
                segment.setCenterY(0);
        }

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

        /* Slither.io key mode */
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
        if (!getBody().isEmpty()) {
            double angle = Utils.getAngle(getHead().getCenterX(), getHead().getCenterY(), pointerX, pointerY);
            getHead().setRotation(angle);
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