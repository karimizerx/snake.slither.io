package slitherio.Utils;

import javafx.scene.image.Image;

/** Some utils methods. */
public final class Utils {

    /**
     * Create and return an Image object of the file name image.
     * 
     * @param file name of a file in <b>resources</b>
     * @return an Image object of this file
     */
    public final static Image getImage(String file) {
        Image img = new Image("file:src/main/resources/" + file);
        return !img.isError() ? img : null;
    }

    /**
     * Return a valid angle, mesure in degrees. I.e 0 <= angle <= 360.
     * 
     * @param angle an angle mesure in degrees
     * @return Return a valid angle, mesure in degrees
     */
    public final static double getValidAngle(double angle) {
        double newAngle = angle;
        while (newAngle < 0)
            newAngle += 360;

        while (newAngle > 360)
            newAngle /= 360;

        return newAngle;
    }

    /**
     * Return angle formed by pivot Origin, yAxis and (x,y).
     * 
     * @param xOrigin X-coordinate of the pivot
     * @param yOrigin Y-coordinate of the pivot
     * @param x       X-coordinate of the pointer
     * @param y       X-coordinate of the pointer
     * @return Return angle formed by pivot Origin, yAxis and (x,y)
     */
    public final static double getAngle(double xOrigin, double yOrigin, double x, double y) {
        double deltaX = x - xOrigin, deltaY = y - yOrigin;
        double rad = Math.atan2(deltaY, deltaX);
        return getValidAngle(Math.round(rad * (180 / Math.PI)) - 90);
    }
}