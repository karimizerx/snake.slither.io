package slitherio.gameobjects;

public class Segment extends GameObject {

    private final static double defaultWidth = 50;
    private final static double defaultHeight = 50;
    private final static double defaultDx = 100;
    private final static double defaultDy = 100;
    private final static int defaultDirection = 2;

    private Segment(double x, double y, double width, double height, double dx, double dy, int direction) {
        super(x, y, width, height, dx, dy, direction);
    }

    public Segment(double x, double y, int direction) {
        this(x, y, defaultWidth, defaultHeight, defaultDx, defaultDy, direction);
    }

    public Segment(double x, double y) {
        this(x, y, defaultWidth, defaultHeight, defaultDx, defaultDy, defaultDirection);
    }
}