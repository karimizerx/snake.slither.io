package slitherio.gameobjects;

import javafx.beans.property.*;

/**
 * Représente les objets "physiques" du jeu. Elle fonctionne avec des
 * DoupleProperty représentation sa position (x,y), ses dimensions (width,
 * height) et son angle de rotation. Chaque objet possède une vitesse en x et en
 * y (dx et dy respectivement).
 * 
 * Cette objet doit être considéré comme un rectangle !
 */
public abstract class GameObject {
    private DoubleProperty x, y, width, height, angle;
    private double dx, dy;

    public GameObject(double x, double y, double width, double height, double dx, double dy, double angle) {
        this.x = new SimpleDoubleProperty(x);
        this.y = new SimpleDoubleProperty(y);
        this.width = new SimpleDoubleProperty(width);
        this.height = new SimpleDoubleProperty(height);
        this.angle = new SimpleDoubleProperty(angle);
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Return true if the object collides go.
     * 
     * Collides working: l = left, r = right, u = up, d = down.
     * 
     * [l1 < r2] ∧ [l2 < r1] ∧ [u1 < d2] ∧ [u2 < d1] ⟺ l1 - r2 < 0 < r1 - l2 ∧ u1 -
     * d2 < 0 < d1 - u2
     * 
     * @param go
     * @return true if the object collides go.
     */
    public final boolean collides(GameObject go) {
        double a = getLeft() - go.getRight();
        double b = getRight() - go.getLeft();
        double c = getUp() - go.getDown();
        double d = getDown() - go.getUp();
        return (a < 0) && (0 < b) && (c < 0) && (0 < d);
    }

    /**
     * Return true if the object collides the rectangle delimited by the coordinates
     * (0,0) and (maxX,maxY). In other words, return true if the object is neither
     * strictly in nor strictly out of bounds delimited by coordinates (0,0) and
     * (maxX,maxY).
     * 
     * @param maxX limit of the X-coordinate.
     * @param maxY limit of the Y-coordinate.
     * @return true if the object collides the rectangle delimited by the
     *         coordinates (0,0) and (maxX,maxY).
     */
    public final boolean collides(double maxX, double maxY) {
        boolean x1 = getLeft() <= 0 && 0 <= getRight();
        boolean x2 = getLeft() <= maxX && maxX <= getRight();
        boolean y1 = getUp() <= 0 && 0 <= getDown();
        boolean y2 = getUp() <= maxY && maxY <= getDown();

        return x1 || x2 || y1 || y2;
    }

    /**
     * Return true if the object is strictly out of bounds delimited by coordinates
     * (0,0) and (maxX,maxY).
     * 
     * @param maxX limit of the X-coordinate.
     * @param maxY limit of the Y-coordinate.
     * @return true if the object is strictly out of bounds delimited by (0,0) and
     *         (maxX, maxY).
     */
    public final boolean isOut(double maxX, double maxY) {
        return (getRight() < 0) || (maxX < getLeft()) || (getDown() < 0) && (maxY < getUp());
    }

    /**
     * Defines the X coordinate of the center of the object.
     *
     */
    public final DoubleProperty getXProperty() {
        return x;
    }

    /**
     * Defines the Y coordinate of the center of the object.
     *
     */
    public final DoubleProperty getYProperty() {
        return y;
    }

    /**
     * Defines the width of the object.
     *
     */
    public final DoubleProperty getWidthProperty() {
        return width;
    }

    /**
     * Defines the height of the object.
     *
     */
    public final DoubleProperty getHeightProperty() {
        return height;
    }

    /**
     * Defines the angle of rotation about the object's center, measured in degrees.
     *
     */
    public final DoubleProperty getAngleProperty() {
        return angle;
    }

    /**
     * Get the value of the property angle.
     * 
     * @return The current value.
     * @see #getAngleProperty()
     */
    public final double getAngle() {
        return angle.getValue();
    }

    /**
     * Sets the value of the property angle.
     * 
     * @param value the new value
     * @see #getAngleProperty()
     * @see #getAngle()
     */
    public final void setAngle(double value) {
        angle.setValue(value);
    }

    /**
     * Get the value of the property x.
     * 
     * @return The current value.
     * @see #getXProperty()
     */
    public final double getCenterX() {
        return x.getValue();
    }

    /**
     * Sets the value of the property x.
     * 
     * @param value the new value
     * @see #getXProperty()
     * @see #getCenterX()
     */
    public final void setCenterX(double value) {
        x.setValue(value);
    }

    /**
     * Get the value of the property y.
     * 
     * @return The current value.
     * @see #getYProperty()
     */
    public final double getCenterY() {
        return y.getValue();
    }

    /**
     * Sets the value of the property y.
     * 
     * @param value the new value
     * @see #getYProperty()
     * @see #getCenterY()
     */
    public final void setCenterY(double value) {
        y.setValue(value);
    }

    /**
     * Defines the X coordinate of the top-left corner of the object.
     *
     */
    public final double getLeft() {
        return x.getValue() - width.getValue() / 2;
    }

    /**
     * Defines the X coordinate of the top-right corner of the object.
     *
     */
    public final double getRight() {
        return x.getValue() + width.getValue() / 2;
    }

    /**
     * Defines the Y coordinate of the top-left corner of the object.
     *
     */
    public final double getUp() {
        return y.getValue() - height.getValue() / 2;
    }

    /**
     * Defines the X coordinate of the bottom-right corner of the object.
     *
     */
    public final double getDown() {
        return y.getValue() + height.getValue() / 2;
    }

    /**
     * Get the value of the property width.
     * 
     * @return The current value.
     * @see #getWidthProperty()
     */
    public final double getWidth() {
        return width.getValue();
    }

    /**
     * Sets the value of the property width.
     * 
     * @param value the new value
     * @see #getWidthProperty()
     * @see #getWidth()
     */
    public final void setWidth(double value) {
        width.setValue(value);
    }

    /**
     * Get the value of the property height.
     * 
     * @return The current value.
     * @see #getHeightProperty()
     */
    public final double getHeight() {
        return height.getValue();
    }

    /**
     * Sets the value of the property height.
     * 
     * @param value the new value
     * @see #getHeightProperty()
     * @see #getHeight()
     */
    public final void setHeight(double value) {
        height.setValue(value);
    }

    /**
     * Defines the speed of the object along the X axis.
     *
     */
    public final double getDx() {
        return dx;
    }

    /**
     * Sets the value of the speed of the object along the X axis.
     * 
     * @param value the new value
     * @see #getDx()
     */
    public final void setDx(double value) {
        dx = value;
    }

    /**
     * Defines the speed of the object along the Y axis.
     *
     */
    public final double getDy() {
        return dy;
    }

    /**
     * Sets the value of the speed of the object along the Y axis.
     * 
     * @param value the new value
     * @see #getDy()
     */
    public final void setDy(double value) {
        dy = value;
    }

}
