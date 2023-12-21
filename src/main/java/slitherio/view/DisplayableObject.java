package slitherio.view;

import slitherio.gameobjects.*;
import slitherio.Utils.*;
import javafx.scene.image.*;
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
        graphics.setRotate(getRotatationOfDirection(object.getDirection()));
        bind();
    }

    /* ******************** Functions ******************** */

    protected final void display(Pane root) {
        root.getChildren().add(graphics);
    }

    protected static double getRotatationOfDirection(int direction) {
        int r = 0;
        switch (direction) {
        case 1 -> r = 180;
        case 2 -> r = -90;
        case 3 -> r = 0;
        case 4 -> r = 90;
        default -> {
        }
        }
        return r;
    }

    private void bind() {
        object.getXProperty().addListener(e -> graphics.setX(object.getLeft()));
        object.getYProperty().addListener(e -> graphics.setY(object.getUp()));
        object.getWidthProperty().addListener(e -> graphics.setWidth(object.getWidth()));
        object.getHeightProperty().addListener(e -> graphics.setHeight(object.getHeight()));
        object.getDirectionProperty()
                .addListener(e -> graphics.setRotate(getRotatationOfDirection(object.getDirection())));
    }

    /* ******************** Getter & Setter ******************** */

    protected final Rectangle getGraphics() {
        return graphics;
    }

    protected final GameObject getObject() {
        return object;
    }
}