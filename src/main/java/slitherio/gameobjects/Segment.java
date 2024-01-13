package slitherio.gameobjects;

/** Représente les segments d'un serpent dans le jeu. */
public final class Segment extends GameObject {

    /** The default value of the segment's width is 50.0 */
    private final static double defaultWidth = 50;
    /** The default value of the segment's height is 50.0 */
    private final static double defaultHeight = 50;
    /** The default value of the segment's speed is 200.0 */
    private final static double defaultDx = 200;
    /** The default value of the segment's speed is 200.0 */
    private final static double defaultDy = 200;
    /** The default value of the segment's angle is 0.0 */
    private final static double defaultAngle = 0;

    /**
     * @see GameObject#GameObject(double, double, double, double, double, double,
     *      double)
     */
    private Segment(double x, double y, double width, double height, double dx, double dy, double angle) {
        super(x, y, width, height, dx, dy, angle);
    }

    /**
     * Create a new instance of {@link Segment}, only with coordinates and angle.
     * Others attribute are using default values.
     * 
     * @param x     X-coordinate of the segment
     * @param y     Y-coordinate of the segment
     * @param angle angle of the segment
     */
    public Segment(double x, double y, double angle) {
        this(x, y, defaultWidth, defaultHeight, defaultDx, defaultDy, angle);
    }

    /**
     * Create a new instance of {@link Segment}, only with X and Y coordinates.
     * Others attribute are using default values.
     * 
     * @param x X-coordinate of the segment
     * @param y Y-coordinate of the segment
     */
    public Segment(double x, double y) {
        this(x, y, defaultWidth, defaultHeight, defaultDx, defaultDy, defaultAngle);
    }
}