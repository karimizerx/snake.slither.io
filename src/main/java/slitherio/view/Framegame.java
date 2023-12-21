package slitherio.view;

import slitherio.Utils.*;
import javafx.stage.*;
import javafx.application.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Framegame extends Application {

    private final String backgroundMenu = "background.menu.png";
    private final String backgroundGame = "grass.jpg";
    private final String imageButtonPlay = "button.play.png";
    private final String imageButtonSettings = "button.settings.png";

    @Override
    public void start(Stage stage) throws Exception {
        startMenu(stage);
    }

    private void startMenu(Stage stage) {
        // Si on passe par le menu, on commence toujours une nouvelle partie
        Pane gameRoot = new Pane();

        // Menu's pane
        BorderPane root = new BorderPane();

        // Setting Background Image
        BackgroundImage bimg = new BackgroundImage(Utils.getImage(backgroundMenu), null, null,
                BackgroundPosition.CENTER, null);
        root.setBackground(new Background(bimg));

        /* ******************** Manage root's top - Title ******************** */

        // App's logo
        ImageView logo = new ImageView(Utils.getImage("slither.logo.png"));
        logo.setFitWidth(75);
        logo.setFitHeight(75);

        // Title
        TilePane tile = new TilePane();
        tile.setPadding(new Insets(20, 20, 20, 20));
        tile.setAlignment(Pos.CENTER_LEFT);
        tile.getChildren().addAll(logo);

        /* ******************** Manage root's center - Menu ******************** */

        /* ********** [WARNING: Check notes.md] ************ */
        // Play button
        Button playButton = new Button("Play");
        playButton.setFont(new Font(20));
        playButton.setContentDisplay(ContentDisplay.CENTER);
        playButton.setOnAction(ev -> startGame(stage, gameRoot));

        // Settings Button
        Button settingsButton = new Button("Settings");
        settingsButton.setFont(new Font(20));
        settingsButton.setContentDisplay(ContentDisplay.CENTER);
        settingsButton.setOnAction(ev -> startSettings(stage, gameRoot));

        // Menu
        HBox menu = new HBox();
        menu.setSpacing(20);
        menu.setAlignment(Pos.CENTER);
        menu.getChildren().addAll(playButton, settingsButton);

        /* ******************** Manage root ******************** */

        root.setTop(tile);
        root.setCenter(menu);

        /* ******************** Manage scene ******************** */

        Scene menuScene = new Scene(root, 1000, 600);
        menuScene.setOnKeyPressed(ev -> {
            if (ev.getCode() == KeyCode.ESCAPE)
                System.exit(0);
        });

        stage.setScene(menuScene);
        stage.setTitle("The Snake Slither - Start Menu");
        stage.show();
    }

    private void startSettings(Stage stage, Pane gameRoot) {
        BorderPane root = new BorderPane();

        // Setting Background Image
        BackgroundImage bimg = new BackgroundImage(Utils.getImage(backgroundMenu), null, null,
                BackgroundPosition.CENTER, null);
        root.setBackground(new Background(bimg));

        VBox box = new VBox();
        box.setSpacing(20);

        // Button Retour
        Button retour = new Button("Retour");
        retour.setFont(new Font(20));
        retour.setContentDisplay(ContentDisplay.CENTER);
        retour.setOnAction(ev -> startMenu(stage));

        String text1 = "Arrow Control";
        String text2 = "ZQSD Control";
        Button controlMode = new Button(text1);
        controlMode.setFont(new Font(20));
        controlMode.setContentDisplay(ContentDisplay.CENTER);
        controlMode.setOnAction(ev -> {
            if (controlMode.getText() == text1)
                controlMode.setText(text2);
            else
                controlMode.setText(text1);
        });

        box.getChildren().addAll(retour, controlMode);
        box.setAlignment(Pos.CENTER);

        root.setCenter(box);

        var menuScene = new Scene(root, 1000, 600);
        stage.setScene(menuScene);
        stage.setTitle("The Snake Slither - Settings");
        stage.show();
    }

    private void startGame(Stage stage, Pane gameRoot) {
        // Pane [root] contains all graphics objects of the game
        Pane root = gameRoot;

        // Set Background
        BackgroundImage bimg = new BackgroundImage(Utils.getImage(backgroundGame), BackgroundRepeat.REPEAT, null, null,
                null);
        root.setBackground(new Background(bimg));

        // Set [scene] dimensions arbitrary
        Scene scene = new Scene(root, 1000, 600);

        // Init Model, View & Controller (by initialisation of Controller)
        Controller controller = new Controller(root, scene.getWidth(), scene.getHeight());

        // Adding [scene] listeners
        scene.setOnKeyPressed(ev -> {
            if (ev.getCode() == KeyCode.ESCAPE)
                startMenu(stage);
            else
                controller.onKeyPressed(ev.getCode());
        });
        scene.widthProperty().addListener((obs, oldVal, newVal) -> controller.getView().setWidth((double) newVal));
        scene.heightProperty().addListener((obs, oldVal, newVal) -> controller.getView().setHeight((double) newVal));

        stage.setScene(scene);
        stage.setTitle("The Snake Slither - In game ...");
        stage.show();

        controller.startGame();
    }

    private void gameMenu(Stage stage, Pane gameRoot) {
        // TODO: possiblité de dire ce qu'on veut faire lorsqu'on met pause (continuer
        // la partie, revenir au menu, quitter, sauvegarder....)
    }
}