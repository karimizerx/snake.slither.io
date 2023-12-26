package slitherio.view;

import slitherio.gameobjects.*;
import slitherio.Utils.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;

public abstract class DisplayableObject {

    private Rectangle graphics;
    private GameObject object;

    /* ******************** Constructor ******************** */
    protected DisplayableObject(GameObject go, String filename) {
        object = go;
        graphics = new Rectangle(object.getLeft(), object.getUp(), object.getWidth(), object.getHeight());
        graphics.setFill(new ImagePattern(Utils.getImage(filename)));
        graphics.setRotate(getRotation());
        bind();
    }

    /* ******************** Functions ******************** */

    protected final void display(Pane root) {
        root.getChildren().add(graphics);
    }

    // Return valid angle for Rectangle rotation
    private final double getRotation() {
        return Utils.getValidAngle(object.getRotation());
    }

    private void bind() {
        object.getXProperty().addListener(e -> graphics.setX(object.getLeft()));
        object.getYProperty().addListener(e -> graphics.setY(object.getUp()));
        object.getWidthProperty().addListener(e -> graphics.setWidth(object.getWidth()));
        object.getHeightProperty().addListener(e -> graphics.setHeight(object.getHeight()));
        object.getRotationProperty().addListener(e -> graphics.setRotate(getRotation()));
    }

    /* ******************** Getter & Setter ******************** */

    protected final Rectangle getGraphics() {
        return graphics;
    }

    protected final GameObject getObject() {
        return object;
    }
}