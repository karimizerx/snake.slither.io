package slitherio.gameobjects;

public class Food extends GameObject {

    private final static double defaultWidth = 50;
    private final static double defaultHeight = 50;
    private final static double defaultDx = 0;
    private final static double defaultDy = 0;
    private final static int defaultDirection = 0;

    private Food(double x, double y, double width, double height, double dx, double dy, int direction) {
        super(x, y, width, height, dx, dy, direction);
    }

    private Food(double x, double y) {
        this(x, y, defaultWidth, defaultHeight, defaultDx, defaultDy, defaultDirection);
    }

    public static Food FoodRandom(double maxWidth, double maxHeight) {
        double rx = defaultWidth / 2 + Math.random() * (maxWidth - defaultWidth);
        double ry = defaultHeight / 2 + Math.random() * (maxHeight - defaultHeight);
        return new Food(rx, ry);
    }
}