package slitherio.view;

import slitherio.gameobjects.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;

public abstract class DisplayableObject {

    private Rectangle graphics;

    public DisplayableObject(Pane root, GameObject go, String filename) {
        graphics = new Rectangle(go.getLeft(), go.getUp(), go.getWidth(), go.getHeight());
        this.graphics.setFill(new ImagePattern(getImage(filename)));
        graphics.setRotate(getRotateOfDirection(go.getDirection()));
        // Binding [go] values with [graphics] values
        bind(go);
        // Adding [graphics] to the root frame game
        root.getChildren().add(graphics);
    }

    protected static Image getImage(String file) {
        Image img = new Image("file:src/main/resources/" + file + ".png");
        if (!img.isError())
            return img;
        else
            return null;
    }

    protected static double getRotateOfDirection(int direction) {
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

    private void bind(GameObject go) {
        // Binding the differents [go] values with [graphics] values
        go.getX().addListener(e -> graphics.setX(go.getCenterX()));
        go.getY().addListener(e -> graphics.setY(go.getCenterY()));
        go.getWidth().addListener(e -> graphics.setWidth(go.getW()));
        go.getHeight().addListener(e -> graphics.setHeight(go.getH()));
        go.getDirectionP().addListener(e -> graphics.setRotate(getRotateOfDirection(go.getDirection())));
    }

    public Rectangle getGraphics() {
        return graphics;
    }
}