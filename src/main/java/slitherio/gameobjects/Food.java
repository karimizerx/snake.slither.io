package slitherio.gameobjects;

public class Food extends GameObject {

    // No one can change the default values
    private Food(double x, double y, double w, double h, double dx, double dy, int direction) {
        super(x, y, w, h, dx, dy, direction);
    }

    // Food constructor, with random coordinates
    public Food() {
        this(0, 0, 50, 50, 0, 0, 0);
        this.setMiddleX(Math.random() * this.getW() + this.getW());
        this.setMiddleY(Math.random() * this.getH() + this.getH());
    }
}