package slitherio.view;

// Import project packages
import slitherio.gameobjects.*;

// Import java packages
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;

public abstract class DisplayableObject {

    private Rectangle graphics;

    public DisplayableObject(Pane root, GameObject go) {
        graphics = new Rectangle();
        this.graphics.setFill(new ImagePattern(get_image("a")));
        graphics.setX(go.getLeft());
        graphics.setY(go.getUp());
        graphics.setWidth(go.getW());
        graphics.setHeight(go.getH());
        graphics.setRotate(getRotateOfDirection(go.getdirection()));
        bind(go);
        root.getChildren().add(graphics);
    }

    public Rectangle getGraphics() {
        return graphics;
    }

    private void bind(GameObject go) {
        go.getX().addListener(e -> graphics.setX(go.getMiddleX()));
        go.getY().addListener(e -> graphics.setY(go.getMiddleY()));
        go.getWidth().addListener(e -> graphics.setWidth(go.getW()));
        go.getHeight().addListener(e -> graphics.setHeight(go.getH()));
        go.getDirection().addListener(e -> graphics.setRotate(getRotateOfDirection(go.getdirection())));
    }

    private double getRotateOfDirection(int direction) {
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

    public static Image get_image(String file) {
        Image img = new Image("file:src/main/resources/" + file + ".png");
        if (!img.isError()) {
            return img;
        } else {
            System.out.println("static get_image --------> ERROOOOOOOOOOOOOOOOOOOOOR");
            return new Image("file:src/main/java/fr/uparis/informatique/cpoo5/jfxdemos/view/images/a.png");
        }
    }
}