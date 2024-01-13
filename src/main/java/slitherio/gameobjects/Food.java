package slitherio.gameobjects;

/**
 * Représente la nourriture du jeu. Possède une vitesse, un angle et des
 * dimensions par défaut.
 */
public final class Food extends GameObject {

    /**
     * ------------------------------------------------------------------
     */
    private final static double defaultWidth = 50;
    private final static double defaultHeight = 50;
    private final static double defaultDx = 0;
    private final static double defaultDy = 0;
    private final static double defaultAngle = 0;

    private Food(double x, double y, double width, double height, double dx, double dy, double angle) {
        super(x, y, width, height, dx, dy, angle);
    }

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
        double ry = defaultHeight / 2 + Math.random() * (maxX - defaultHeight);
        return new Food(rx, ry);
    }
}