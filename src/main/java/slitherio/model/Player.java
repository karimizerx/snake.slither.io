package slitherio.model;

import javafx.scene.input.KeyCode;
import slitherio.gameobjects.*;

public final class Player {

    private int id;
    private String name = "Player";
    private Snake snake;
    private boolean isIa = false, mouseControl = false;
    private KeyCode keyUp = KeyCode.UP, keyDown = KeyCode.DOWN, keyLeft = KeyCode.LEFT, keyRight = KeyCode.RIGHT;

    /* ******************** Constructors ******************** */
    // Complete configuration
    public Player(int id, String name, boolean isIa, double x, double y, KeyCode keyUp, KeyCode keyDown,
            KeyCode keyLeft, KeyCode keyRight) {
        this.id = id;
        this.name = name;
        this.isIa = isIa;
        this.snake = new Snake(x, y);
        this.keyUp = keyUp;
        this.keyDown = keyDown;
        this.keyLeft = keyLeft;
        this.keyRight = keyRight;
    }

    // Human player, control with ARROWS, default configuration
    public Player(int id, String name, double x, double y) {
        this.id = id;
        this.name = name;
        this.snake = new Snake(x, y);
    }

    // Human player, control with ARROWS, complete configuration
    public Player(int id, String name, double x, double y, KeyCode keyUp, KeyCode keyDown, KeyCode keyLeft,
            KeyCode keyRight) {
        this(id, name, x, y);
        this.keyUp = keyUp;
        this.keyDown = keyDown;
        this.keyLeft = keyLeft;
        this.keyRight = keyRight;
    }

    // Human player, control with ARROWS, SlitherIo configuration
    public Player(int id, String name, double x, double y, KeyCode keyLeft, KeyCode keyRight) {
        this(id, name, x, y);
        this.keyLeft = keyLeft;
        this.keyRight = keyRight;
    }

    // Human player, control with MOUSE
    public Player(int id, String name, double x, double y, boolean mouseControl) {
        this(id, name, x, y);
        this.mouseControl = mouseControl;
    }

    // Ia player
    public Player(boolean isIa, int id, String name, double x, double y) {
        this(id, name, x, y);
        this.isIa = isIa;
    }

    /* ******************** Getter & Setter ******************** */

    public final int getId() {
        return id;
    }

    public final void setId(int id) {
        this.id = id;
    }

    public final String getName() {
        return name;
    }

    public final void setName(String name) {
        this.name = name;
    }

    public final Snake getSnake() {
        return snake;
    }

    public final boolean isIa() {
        return isIa;
    }

    public final boolean isMouseControl() {
        return mouseControl;
    }

    public final KeyCode getKeyUp() {
        return keyUp;
    }

    public final KeyCode getKeyDown() {
        return keyDown;
    }

    public final KeyCode getKeyLeft() {
        return keyLeft;
    }

    public final KeyCode getKeyRight() {
        return keyRight;
    }

}