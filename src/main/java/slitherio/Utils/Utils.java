package slitherio.Utils;

import javafx.scene.image.Image;

public final class Utils {

    // Create and return an Image object of [file] image
    public final static Image getImage(String file) {
        Image img = new Image("file:src/main/resources/" + file);
        return !img.isError() ? img : null;
    }

    // Return a valid angle, i.e 0 <= angle <= 360
    public final static double getValidAngle(double angle) {
        double newAngle = angle;
        while (newAngle < 0)
            newAngle += 360;

        while (newAngle > 360)
            newAngle /= 360;

        return newAngle;
    }

    // Return angle formed by Origin, xAxis, (x,y)
    public final static double getAngle(double xOrigin, double yOrigin, double x, double y) {
        double deltaX = x - xOrigin, deltaY = y - yOrigin;
        double rad = Math.atan2(deltaY, deltaX);
        return getValidAngle(Math.round(rad * (180 / Math.PI)));
    }
}