package slitherio.view;

// Import project packages
import slitherio.gameobjects.*;

// Import java packages
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;

public abstract class DisplayableObject {

    // All GameObjects are displayed with a Rectangle.
    private Rectangle graphics;

    public DisplayableObject(Pane root, GameObject go, String filename) {
        graphics = new Rectangle();
        this.graphics.setFill(new ImagePattern(getImage(filename)));
        // Default [graphics] values
        graphics.setX(go.getLeft());
        graphics.setY(go.getUp());
        graphics.setWidth(go.getW());
        graphics.setHeight(go.getH());
        graphics.setRotate(getRotateOfDirection(go.getDirection()));
        // Binding [go] values with [graphics] values
        bind(go);
        // Adding [graphics] to the root frame game
        root.getChildren().add(graphics);
    }

    public Rectangle getGraphics() {
        return graphics;
    }

    private void bind(GameObject go) {
        // Binding the differents [go] values with [graphics] values
        go.getX().addListener(e -> graphics.setX(go.getMiddleX()));
        go.getY().addListener(e -> graphics.setY(go.getMiddleY()));
        go.getWidth().addListener(e -> graphics.setWidth(go.getW()));
        go.getHeight().addListener(e -> graphics.setHeight(go.getH()));
        go.getDirectionP().addListener(e -> graphics.setRotate(getRotateOfDirection(go.getDirection())));
    }

    // Get Rectangle
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

    protected static Image getImage(String file) {
        System.out.println("file = " + file);
        Image img = new Image("file:src/main/resources/" + file + ".png");
        if (!img.isError())
            return img;
        else
            return null;
    }
}