package slitherio.view;

// Import project packages
import slitherio.gameobjects.*;
import slitherio.controller.*;
import slitherio.model.*;

// Import java packages
import java.util.*;
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

        // Init Model, View & Controller
        Arena arena = new Arena(scene.getWidth(), scene.getHeight());
        Gameview view = new Gameview(root);
        Controller controller = new Controller();
        controller.setArena(arena);
        controller.setView(view);

        // Adding [scene] listeners
        scene.setOnKeyPressed(ev -> controller.on_key_pressed(ev.getCode()));
        scene.widthProperty().addListener((obs, oldVal, newVal) -> {
            arena.setW((double) newVal);
        });
        scene.heightProperty().addListener((obs, oldVal, newVal) -> {
            arena.setH((double) newVal);
        });

        // Settings & displaying scene
        stage.setScene(scene); // définir la scène à afficher
        stage.setTitle("Snake Frame");
        stage.show(); // lancer l'affichage !

        // Binding graphics objects and game objects of the game
        controller.bind();
        // Setting graphics objects default values;
        controller.defautView();
        // Running game
        arena.animate();
    }
}