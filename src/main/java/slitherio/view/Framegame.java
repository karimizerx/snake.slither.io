package slitherio.view;

import javafx.stage.*;
import javafx.application.*;
import javafx.beans.property.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import slitherio.Utils.*;

public class Framegame extends Application {

    private final String backgroundMenu = "background.menu.png";
    private final String backgroundGame = "grass.jpg";
    private final String imageButtonPlay = "button.play.png";
    private final String imageButtonSettings = "button.settings.png";
    private final StringProperty gameName = new SimpleStringProperty("The Snake Slither");
    private final StringProperty controlName = new SimpleStringProperty("ARROWS");

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

        // Manage Buttons
        Font font = new Font(20);
        String game1 = "Snake", game2 = "Local SlitherIo", game3 = "The Snake Slither";

        // Button Retour
        Button retour = new Button("Retour");
        retour.setFont(font);
        retour.setContentDisplay(ContentDisplay.CENTER);
        retour.setOnAction(ev -> startMenu(stage));

        // Button Game
        Button gameButton = new Button(gameName.get());
        retour.setFont(font);
        retour.setContentDisplay(ContentDisplay.CENTER);
        gameName.addListener((obs, oldName, newName) -> gameButton.setText(newName));
        gameButton.setOnAction(ev -> {
            if (gameName.get().equals(game1))
                gameName.set(game2);
            else if (gameName.get().equals(game2))
                gameName.set(game3);
            else if (gameName.get().equals(game3))
                gameName.set(game1);
        });

        // Button Control
        Button controlButton = new Button(controlName.get());
        controlButton.setFont(font);
        controlButton.setContentDisplay(ContentDisplay.CENTER);
        controlName.addListener((obs, oldName, newName) -> controlButton.setText(newName));
        controlButton.setOnAction(ev -> {
            String control1 = "ARROWS", control2 = "ZSQD", control3 = "MOUSE";
            if (gameName.get().equals(game2)) {
                if (controlName.get().equals(control1))
                    controlName.set(control2);
                else if (controlName.get().equals(control2))
                    controlName.set(control1);
            } else if (gameName.get().equals(game3)) {
                if (controlName.get().equals(control3))
                    controlName.set(control1);
                else if (controlName.get().equals(control1))
                    controlName.set(control3);
            }

        });

        box.getChildren().addAll(retour, gameButton, controlButton);
        box.setAlignment(Pos.CENTER);

        root.setCenter(box);

        Scene menuScene = new Scene(root, 1000, 600);
        // Adding [scene] listeners
        menuScene.setOnKeyPressed(ev -> {
            if (ev.getCode() == KeyCode.ESCAPE)
                startMenu(stage);
        });
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
        Controller controller = new Controller(root, scene.getWidth(), scene.getHeight(), gameName.get());

        // Adding [scene] listeners
        scene.setOnKeyPressed(ev -> {
            if (ev.getCode() == KeyCode.ESCAPE)
                startMenu(stage);
            else
                controller.onKeyPressed(ev.getCode());
        });
        scene.setOnMouseMoved(ev -> controller.onMouseMoved(ev.getX(), ev.getY()));
        scene.widthProperty().addListener((obs, oldVal, newVal) -> controller.getView().setWidth((double) newVal));
        scene.heightProperty().addListener((obs, oldVal, newVal) -> controller.getView().setHeight((double) newVal));

        stage.setScene(scene);
        stage.setTitle(gameName.get() + " - In game ...");
        stage.show();

        controller.startGame();
    }

    private void gameMenu(Stage stage, Pane gameRoot) {
        // TODO: possiblité de dire ce qu'on veut faire lorsqu'on met pause (continuer
        // la partie, revenir au menu, quitter, sauvegarder....)
    }
}