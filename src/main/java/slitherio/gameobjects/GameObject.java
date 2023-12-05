package slitherio.gameobjects;

// Import java packages
import javafx.beans.property.*;

public abstract class GameObject {
    private DoubleProperty x, y, w, h;
    private IntegerProperty direction;
    private double dx, dy;

    public GameObject(double x, double y, double w, double h, double dx, double dy, int d) {
        this.x = new SimpleDoubleProperty();
        this.y = new SimpleDoubleProperty();
        this.w = new SimpleDoubleProperty();
        this.h = new SimpleDoubleProperty();
        this.direction = new SimpleIntegerProperty();
        this.x.setValue(x);
        this.y.setValue(y);
        this.w.setValue(w);
        this.h.setValue(h);
        this.direction.setValue(d);
        this.dx = dx;
        this.dy = dy;
    }

    public final DoubleProperty getX() {
        return x;
    }

    public final DoubleProperty getY() {
        return y;
    }

    public final DoubleProperty getWidth() {
        return w;
    }

    public final DoubleProperty getHeight() {
        return h;
    }

    public final IntegerProperty getDirectionP() {
        return direction;
    }

    public final int getDirection() {
        return direction.getValue();
    }

    public final void setDirection(int value) {
        direction.setValue(value);
    }

    public final double getMiddleX() {
        return x.getValue();
    }

    public final void setMiddleX(double value) {
        x.setValue(value);
    }

    public final double getMiddleY() {
        return y.getValue();
    }

    public final void setMiddleY(double value) {
        y.setValue(value);
    }

    public final double getLeft() {
        return x.getValue() - w.getValue() / 2;
    }

    public final double getRight() {
        return x.getValue() + w.getValue() / 2;
    }

    public final double getUp() {
        return y.getValue() - h.getValue() / 2;
    }

    public final double getDown() {
        return y.getValue() + h.getValue() / 2;
    }

    public final double getW() {
        return w.getValue();
    }

    public final void setW(double value) {
        w.setValue(value);
    }

    public final double getH() {
        return h.getValue();
    }

    public final void setH(double value) {
        h.setValue(value);
    }

    public final double getDx() {
        return dx;
    }

    public final void setDx(double value) {
        dx = value;
    }

    public final double getDy() {
        return dy;
    }

    public final void setDy(double value) {
        dy = value;
    }

    public final boolean collides(GameObject go, double dt) {
        return false;
    }

    /*
     * 
     * // Math for the collisions:
     * // u = up, d = down, l = left, r = right, x = dx, y = dy
     * // collision when: l1 + x1t < r2 + x2t ∧ l2 + x2t < r1 + x1t
     * // ∧ u1 + y1t < d2 + y2t ∧ u2 + y2t < d1 + y1t
     * // <=> l1 - r2 < (x2 - x1)t < r1 - l2
     * // ∧ u1 - d2 < (y2 - y1)t < d1 - u2
     * // on nomme nos variables pour plus de clarté
     * // <=> a < bt < c ∧ d < et < f
     * 
     * public final boolean collides(GameObject o, double dt) {
     * final double epsilon = 0.0000001;
     * final double a = get_left() - o.get_right();
     * final double b = o.get_dx() - get_dx();
     * final double c = get_right() - o.get_left();
     * final double d = get_up() - o.get_down();
     * final double e = o.get_dy() - get_dy();
     * final double f = get_down() - o.get_up();
     * double min_t;
     * double max_t;
     * if (b < -epsilon) {
     * if (a / b < c / b) {
     * return false;
     * }
     * min_t = c / b;
     * max_t = a / b;
     * } else if (epsilon < b) {
     * if (a / b > c / b) {
     * return false;
     * }
     * min_t = a / b;
     * max_t = c / b;
     * } else { // b is around about 0.
     * if (c < a || 0 < a || c < 0) {
     * return false;
     * }
     * min_t = -100000;
     * max_t = 100000;
     * }
     * if (e < -epsilon) {
     * if (d / e < f / e) {
     * return false;
     * }
     * min_t = Math.max(min_t, f / e);
     * max_t = Math.min(max_t, d / e);
     * } else if (epsilon < e) {
     * if (d / e > f / e) {
     * return false;
     * }
     * min_t = Math.max(min_t, d / e);
     * max_t = Math.min(max_t, f / e);
     * } else { // e is around about 0.
     * if (f < d || 0 < d || f < 0) {
     * return false;
     * }
     * }
     * if (max_t < 0 || min_t > dt) {
     * return false;
     * }
     * final double t = Math.max(0, min_t); // Unused for now, will be used later to
     * calculate the collision point.
     * return true;
     * }
     */
}