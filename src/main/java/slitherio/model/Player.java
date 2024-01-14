package slitherio.model;

import javafx.scene.input.KeyCode;
import slitherio.gameobjects.*;

/**
 * Represents a player.
 */
public final class Player {

    /** Defines the id. */
    private int id;
    /** Defines the name. <b>Default value:</b> Player. */
    private String name = "Player";
    /** Defines the skin's file name. <b>Default value:</b> "snake.head.png". */
    private String skin = "snake.head.png";
    /** Defines the snake. */
    private Snake snake;
    /** Defines the player's type. */
    private boolean isIa = false;
    /** Defines the control's type. Keyboard if false */
    private boolean mouseControl = false;
    /** Defines the keyUp. <b>Default value:</b> Arrow.UP */
    private KeyCode keyUp = KeyCode.UP;
    /** Defines the keyDown. <b>Default value:</b> Arrow.DOWN */
    private KeyCode keyDown = KeyCode.DOWN;
    /** Defines the keyLeft. <b>Default value:</b> Arrow.LEFT */
    private KeyCode keyLeft = KeyCode.LEFT;
    /** Defines the keyRight. <b>Default value:</b> Arrow.RIGHT */
    private KeyCode keyRight = KeyCode.RIGHT;

    /** Create a new instance of Player with <b>complete configuration</b>. */
    public Player(int id, String name, String skin, boolean isIa, double x, double y, KeyCode keyUp, KeyCode keyDown,
            KeyCode keyLeft, KeyCode keyRight) {
        this.id = id;
        this.name = name;
        this.isIa = isIa;
        this.skin = skin;
        this.snake = new Snake(skin, x, y);
        this.keyUp = keyUp;
        this.keyDown = keyDown;
        this.keyLeft = keyLeft;
        this.keyRight = keyRight;
    }

    /** Create a new instance of <b>human Player</b>. */
    public Player(int id, String name, String skin, double x, double y) {
        this.id = id;
        this.name = name;
        this.skin = skin;
        this.snake = new Snake(skin, x, y);
    }

    /**
     * Create a new instance of <b>human Player</b> with <b>keyboard controls</b>.
     */
    public Player(int id, String name, String skin, double x, double y, KeyCode keyUp, KeyCode keyDown, KeyCode keyLeft,
            KeyCode keyRight) {
        this(id, name, skin, x, y);
        this.keyUp = keyUp;
        this.keyDown = keyDown;
        this.keyLeft = keyLeft;
        this.keyRight = keyRight;
    }

    /** Create a new instance of <b>human Player</b> with <b>mouse controls</b>. */
    public Player(int id, String name, String skin, double x, double y, boolean mouseControl) {
        this(id, name, skin, x, y);
        this.mouseControl = mouseControl;
    }

    /** Create a new instance of <b>IA Player</b>. */
    public Player(boolean isIa, int id, String name, String skin, double x, double y) {
        this(id, name, skin, x, y);
        this.isIa = isIa;
    }

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

    public final String getSkin() {
        return skin;
    }

    public final void setSkin(String skin) {
        this.skin = skin;
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