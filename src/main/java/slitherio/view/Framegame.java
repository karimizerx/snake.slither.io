package slitherio.view;

import javafx.stage.*;
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.layout.*;

public class Framegame extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Pane [root] contains all graphics objects of the game
        Pane root = new Pane();
        // Set Background color
        root.setStyle("-fx-background-color: green;");
        // Set [scene] dimensions arbitrary
        Scene scene = new Scene(root, 1000, 600);

        // Init Model, View & Controller (by initialisation of Controller)
        Controller controller = new Controller(root, scene.getWidth(), scene.getHeight());

        // Adding [scene] listeners
        scene.setOnKeyPressed(ev -> controller.onKeyPressed(ev.getCode()));
        scene.widthProperty().addListener((obs, oldVal, newVal) -> controller.getView().setWidth((double) newVal));
        scene.heightProperty().addListener((obs, oldVal, newVal) -> controller.getView().setHeight((double) newVal));

        stage.setScene(scene);
        stage.setTitle("Snake - Sliter.io");
        stage.show();

        controller.startGame();
    }
}