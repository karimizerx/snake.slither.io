package slitherio.gameobjects;

public class Segment extends GameObject {

    private final static double defaultWidth = 50;
    private final static double defaultHeight = 50;
    private final static double defaultDx = 100;
    private final static double defaultDy = 100;
    private final static double defaultRoation = 0;

    private Segment(double x, double y, double width, double height, double dx, double dy, double rotation) {
        super(x, y, width, height, dx, dy, rotation);
    }

    public Segment(double x, double y, double rotation) {
        this(x, y, defaultWidth, defaultHeight, defaultDx, defaultDy, rotation);
    }

    public Segment(double x, double y) {
        this(x, y, defaultWidth, defaultHeight, defaultDx, defaultDy, defaultRoation);
    }
}