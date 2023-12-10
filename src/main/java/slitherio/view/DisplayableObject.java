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
        graphics.setRotate(getRotatationOfDirection(go.getDirection()));
        bind(go);
        root.getChildren().add(graphics);
    }

    public static Image getImage(String file) {
        Image img = new Image("file:src/main/resources/" + file);
        return !img.isError() ? img : null;
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

    private void bind(GameObject go) {
        go.getXProperty().addListener(e -> graphics.setX(go.getLeft()));
        go.getYProperty().addListener(e -> graphics.setY(go.getUp()));
        go.getWidthProperty().addListener(e -> graphics.setWidth(go.getWidth()));
        go.getHeightProperty().addListener(e -> graphics.setHeight(go.getHeight()));
        go.getDirectionProperty().addListener(e -> graphics.setRotate(getRotatationOfDirection(go.getDirection())));
    }

    public Rectangle getGraphics() {
        return graphics;
    }
}