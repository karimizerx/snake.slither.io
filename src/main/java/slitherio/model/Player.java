package slitherio.model;

import slitherio.gameobjects.*;

public final class Player {

    private int id;
    private String name;
    private boolean isBot;
    private Snake snake;

    public Player(int id, String name, boolean isBot, double x, double y) {
        this.id = id;
        this.name = name;
        this.isBot = isBot;
        this.snake = new Snake(x, y);
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

    public final boolean isBot() {
        return isBot;
    }

    public final Snake getSnake() {
        return snake;
    }

}