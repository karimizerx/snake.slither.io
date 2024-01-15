package slitherio.gameobjects;

/** Represents the game's food. */
public final class Food extends GameObject {

    /** The default value of the food's width is 50.0 */
    private final static double defaultWidth = 50;
    /** The default value of the food's height is 50.0 */
    private final static double defaultHeight = 50;
    /** The default value of the food's speed is 0.0 */
    private final static double defaultDx = 0;
    /** The default value of the food's speed is 0.0 */
    private final static double defaultDy = 0;
    /** The default value of the food's angle is 0.0 */
    private final static double defaultAngle = 0;

    /**
     * @see GameObject#GameObject(double, double, double, double, double, double,
     *      double)
     */
    private Food(double x, double y, double width, double height, double dx, double dy, double angle) {
        super(x, y, width, height, dx, dy, angle);
    }

    /**
     * Create a new instance of {@link Food}, only with the X and Y coordinates.
     * Others attribute are using default values.
     * 
     * @param x X-coordinate of the food
     * @param y Y-coordinate of the food
     */
    private Food(double x, double y) {
        this(x, y, defaultWidth, defaultHeight, defaultDx, defaultDy, defaultAngle);
    }

    /**
     * Like a static constructor. Create a new object of type {@link Food}. The
     * object is in the rectangle delimited by the coordinates (0,0) and
     * (maxX,maxY).
     * 
     * @param maxX limit of X-coordinate
     * @param maxY limit of Y-coordinate
     * @return a new instance of Food, with random coordinates
     */
    public static Food FoodRandom(double maxX, double maxY) {
        double rx = defaultWidth / 2 + Math.random() * (maxX - defaultWidth);
        double ry = defaultHeight / 2 + Math.random() * (maxY - defaultHeight);
        return new Food(rx, ry);
    }
}