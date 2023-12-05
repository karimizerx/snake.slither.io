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
        Pane root = new Pane();

        root.setStyle("-fx-background-color: green;");
        Scene scene = new Scene(root, 1000, 600);

        Arena arena = new Arena(scene.getWidth(), scene.getHeight());
        Gameview view = new Gameview(root);
        Controller controller = new Controller();
        controller.setArena(arena);
        controller.setView(view);

        scene.setOnKeyPressed(ev -> controller.on_key_pressed(ev.getCode()));
        scene.widthProperty().addListener((obs, oldVal, newVal) -> {
            arena.setW((double) newVal);
        });
        scene.heightProperty().addListener((obs, oldVal, newVal) -> {
            arena.setH((double) newVal);
        });

        stage.setScene(scene); // définir la scène à afficher
        stage.setTitle("Snake Frame");
        stage.show(); // lancer l'affichage !
        controller.bind();
        controller.defautView();
        arena.animate();
    }
}