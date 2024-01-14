package slitherio.gameobjects;

import javafx.beans.property.*;
import javafx.collections.*;

/**
 * Represents a snake.
 */
public final class Snake {

    /**
     * Defines the snake's body. It's a list of {@link Segment}.
     * 
     * @see javafx.beans.property.ListProperty
     * @see javafx.beans.property.SimpleListProperty
     */
    private final ListProperty<Segment> body = new SimpleListProperty<Segment>(
            FXCollections.<Segment>observableArrayList());
    /**
     * The snake's skin. It's a name of a file in the <b>resources directory</b>.
     */
    private String skin;

    /**
     * Create a new instance of {@link Snake}. The snake has, by default, a head.
     * The constructor initializes the snake's head with the coordinates (headX,
     * headY).
     * 
     * @param skin  the snake's skin
     * @param headX X-coordinate of the snake's head
     * @param headY Y-coordinate of the snake's head
     */
    public Snake(String skin, double headX, double headY) {
        this.skin = skin;
        getBody().add(new Segment(headX, headY));
    }

    /**
     * Make the snake move in the head's direction.
     * 
     * @param dt the elapsed time
     * @see #moveToDirection(double, double)
     */
    public final void move(double dt) {
        moveToDirection(dt, getHead().getAngle());
    }

    /**
     * Make the snake move in the specified direction. The direction is calculated
     * using an angle.
     * 
     * @implSpec Moves each segment of snake's body to the position of the previous
     *           one, starting with the last one. Then, moves the snake's head to
     *           the new position. The new position of snake's head is the snake's
     *           speed multiplied by the elapsed time:
     * 
     *           <b>newX = head.dx * dt</b> and <b>newY = head.dy * dt</b>
     * 
     * @param dt    the elapsed time
     * @param angle angle, measured in degrees.
     * @see GameObject#getAngleProperty
     * @see GameObject#getDx
     * @see GameObject#getDy
     */
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
    /**
     * Add a {@link Segment} to the snake.
     * 
     * @implSpec Add a new segment at the end of the snake's body is like add a new
     *           head.
     * 
     * @param dt the elapsed time
     */
    public final void addSegment(double dt) {
        double hx = getHead().getCenterX(), hy = getHead().getCenterY();
        double hdx = getHead().getDx() * dt, hdy = getHead().getDy() * dt;
        double nr = getHead().getAngle();
        double nx = hx - Math.sin(Math.toRadians(nr)) * hdx, ny = hy + Math.cos(Math.toRadians(nr)) * hdy;
        getBody().add(0, new Segment(nx, ny, nr));
    }

    /**
     * Return true if this snake's head collides [snake]. If this snake and [snake]
     * are equals, return false.
     * 
     * @param snake
     * @return true if this snake's head collides [snake].
     */
    public final boolean collides(Snake snake) {
        if (this.equals(snake))
            return false;
        for (Segment segment : snake.getBody()) {
            if (getHead().collides(segment))
                return true;
        }
        return false;
    }

    /**
     * Return true if the snake collides the rectangle delimited by the coordinates
     * (0,0) and (maxX,maxY). In other words, return true if the snake's head is
     * neither strictly in nor strictly out of bounds delimited by coordinates (0,0)
     * and (maxX,maxY).
     * 
     * @param maxX limit of the X-coordinate
     * @param maxY limit of the Y-coordinate
     * @return true if the snake collides the rectangle delimited by the coordinates
     *         (0,0) and (maxX,maxY)
     */
    public final boolean collides(double maxX, double maxY) {
        if (!getHead().collides(maxX, maxY))
            return false;

        boolean collidesUp = getHead().getUp() < 0;
        boolean collidesDown = getHead().getDown() > maxY;
        boolean collidesLeft = getHead().getLeft() < 0;
        boolean collidesRight = getHead().getRight() > maxX;
        return collidesUp || collidesDown || collidesLeft || collidesRight;
    }

    /**
     * If the snake's head collides the rectangle delimited by (0,0) and (maxX,
     * maxY), moves the snake to the opposite edge, to give the illusion that the
     * snake crosses the edge of the rectangle and reappears on the other edge.
     * 
     * The rectangle is supposed
     * 
     * @param maxX limit of X-coordinate
     * @param maxY limit of Y-coordinate
     * @see #the classic snake game rules
     */
    public final void byPassCollidesWindow(double maxX, double maxY) {
        // Check if snake is collides window's edges
        if (!collides(maxX, maxY))
            return;

        for (Segment segment : body) {
            if (segment.getCenterX() < 0)
                segment.setCenterX(maxX);
            else if (segment.getCenterX() > maxX)
                segment.setCenterX(0);
            if (segment.getCenterY() < 0)
                segment.setCenterY(maxY);
            else if (segment.getCenterY() > maxY)
                segment.setCenterY(0);
        }

    }

    /**
     * Put the snake in a valid coordinate in the rectangle delimited by (0,0) and
     * (maxX, maxY) coordinates. The snake's body supposed to has juste a head.
     * 
     * @param maxX limit of the X-coordinate
     * @param maxY limit of the Y-coordinate
     */
    public final void setValidPosition(double maxX, double maxY) {
        double width = getHead().getWidth(), height = getHead().getHeight();

        if (getHead().getLeft() < 0)
            getHead().setCenterX(width / 2);
        else if (getHead().getRight() > maxX)
            getHead().setCenterX(maxX - width / 2);

        if (getHead().getUp() < 0)
            getHead().setCenterY(height / 2);
        else if (getHead().getDown() > maxY)
            getHead().setCenterY((maxY - height / 2));
    }

    /**
     * Get the snake's skin.
     * 
     * @return the name of the snake's skin
     */
    public String getSkin() {
        return skin;
    }

    /**
     * Get the snake's head.
     */
    public final Segment getHead() {
        return getBody().get(0);
    }

    /**
     * Get the property body.
     * 
     * @return the property body
     */
    public final ListProperty<Segment> getBodyProperty() {
        return body;
    }

    /**
     * Get the snake's body.
     * 
     * @return the value of the property body
     */
    public final ObservableList<Segment> getBody() {
        return body.getValue();
    }

}