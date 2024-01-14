package slitherio.view;

import slitherio.gameobjects.*;
import slitherio.Utils.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;

/**
 * Represents the view of an object. C'est l'équivalent de
 * {@link slitherio.gameobjects.GameObject} pour la vue.
 */
public abstract class DisplayableObject {

    /** Defines the object to display. */
    private GameObject object;
    /** Gameobjects are displayed by rectangles. */
    private Rectangle graphics;

    /**
     * Create a new instance DisplayableObject. This constructor calls
     * {@link #bind()}.
     * 
     * @param go       the object to display
     * @param filename the object's skin. It's a name of a file in the <b>resources
     *                 directory</b>.
     */
    protected DisplayableObject(GameObject go, String filename) {
        object = go;
        graphics = new Rectangle(object.getLeft(), object.getUp(), object.getWidth(), object.getHeight());
        graphics.setFill(new ImagePattern(Utils.getImage(filename)));
        graphics.setRotate(getRotation());
        bind();
    }

    /**
     * Add graphics in root.
     * 
     * @param root the Pane where graphics should be added
     */
    protected final void display(Pane root) {
        root.getChildren().add(graphics);
    }

    /**
     * Return valid angle value of
     * {@link slitherio.gameobjects.GameObject#getAngle() object.angle}. Make sure
     * that the angle is always valid.
     * 
     * @return valid angle value of
     *         {@link slitherio.gameobjects.GameObject#getAngle() object.angle}
     * @see slitherio.Utils.Utils#getValidAngle(double)
     */
    private final double getRotation() {
        return Utils.getValidAngle(object.getAngle());
    }

    /**
     * Bind {@link #object} and {@link #graphics}. Bind the values of x, y, width,
     * height and angle.
     */
    private void bind() {
        object.getXProperty().addListener(e -> graphics.setX(object.getLeft()));
        object.getYProperty().addListener(e -> graphics.setY(object.getUp()));
        object.getWidthProperty().addListener(e -> graphics.setWidth(object.getWidth()));
        object.getHeightProperty().addListener(e -> graphics.setHeight(object.getHeight()));
        object.getAngleProperty().addListener(e -> graphics.setRotate(getRotation()));
    }

    /**
     * Get the value of graphics.
     * 
     * @return graphics
     */
    protected final Rectangle getGraphics() {
        return graphics;
    }

    /**
     * Get the object.
     * 
     * @return the object
     */
    protected final GameObject getObject() {
        return object;
    }
}