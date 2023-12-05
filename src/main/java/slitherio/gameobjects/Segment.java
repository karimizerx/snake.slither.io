package slitherio.gameobjects;

public class Segment extends GameObject {

    public Segment(double x, double y, double w, double h, double dx, double dy, int direction) {
        super(x, y, w, h, dx, dy, direction);
    }

    public Segment(double x, double y, int direction) {
        super(x, y, 50, 50, 25, 25, direction);
    }
}