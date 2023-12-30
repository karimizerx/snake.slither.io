package slitherio.gameobjects;

import javafx.beans.property.*;
import javafx.collections.*;

public final class Snake {
    private final ListProperty<Segment> body = new SimpleListProperty<Segment>(
            FXCollections.<Segment>observableArrayList());
    private String skin;

    /* ******************** Constructors ******************** */

    public Snake(String skin, double headX, double headY) {
        this.skin = skin;
        getBody().add(new Segment(headX, headY)); // A snake has, by default, a head.
    }

    /* ******************** Functions ******************** */

    // Make the snake move. [dt] is the elapsed time
    public final void move(double dt) {
        moveToDirection(dt, getHead().getAngle());
    }

    // Move the snake in the direction of [angle]. [dt] is the elapsed time
    private void moveToDirection(double dt, double angle) {
        for (int i = getBody().size() - 1; i > 0; --i) {
            Segment segment = getBody().get(i);
            Segment previous = getBody().get(i - 1);
            segment.setCenterX(previous.getCenterX());
            segment.setCenterY(previous.getCenterY());
            segment.setAngle(previous.getAngle());
        }

        // Manage snake's head move
        double hx = getHead().getCenterX(), hy = getHead().getCenterY();
        double dx = getHead().getDx() * dt, dy = getHead().getDy() * dt;
        double nx = hx - Math.sin(Math.toRadians(angle)) * dx, ny = hy + Math.cos(Math.toRadians(angle)) * dy;
        getHead().setCenterX(nx);
        getHead().setCenterY(ny);
    }

    // Add a segment to the snake. [dt] is the elapsed time
    public final void addSegment(double dt) {
        double hx = getHead().getCenterX(), hy = getHead().getCenterY();
        double hdx = getHead().getDx() * dt, hdy = getHead().getDy() * dt;
        double nr = getHead().getAngle();
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

    // Put [snake] in a valid position, respecting x & y.
    public final void setValidPosition(double x, double y) {
        double width = getHead().getWidth(), height = getHead().getHeight();

        if (getHead().getLeft() < 0)
            getHead().setCenterX(width / 2);
        else if (getHead().getRight() > x)
            getHead().setCenterX(x - width / 2);

        if (getHead().getUp() < 0)
            getHead().setCenterY(height / 2);
        else if (getHead().getDown() > y)
            getHead().setCenterY((y - height / 2));
    }

    /* ******************** Getter & Setter ******************** */
    // Get the snake's skin
    public String getSkin() {
        return skin;
    }

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