package slitherio.gameobjects;

public class Food extends GameObject {

    public Food(double x, double y, double w, double h, double dx, double dy, int direction) {
        super(x, y, w, h, dx, dy, direction);
    }

    public Food() {
        this(0, 0, 50, 50, 0, 0, 0);
        this.setX(Math.random() * this.getW() + this.getW());
        this.setY(Math.random() * this.getH() + this.getH());
    }
}