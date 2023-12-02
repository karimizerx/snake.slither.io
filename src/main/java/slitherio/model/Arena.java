package slitherio.model;

// Import project packages
import slitherio.gameobjects.*;

// Import java packages
import javafx.scene.input.*;
import javafx.animation.*;

public class Arena {
    private Snake snake;
    private double w, h;

    public Arena(double w, double h) {
        this.w = w;
        this.h = h;
        snake = new Snake(100, 300, 50, 50, 50, 50, 2);
    }

    private void update(double dt) {
        snake.move(dt, w, h);
    }

    public void animate() {
        new AnimationTimer() {
            long last = 0;
            final double dt = 0.4; // update every 0.01s
            double acc = 0.0;

            @Override
            public void handle(long now) {
                if (last == 0) { // ignore the first tick, just compute the first dt
                    last = now;
                    return;
                }

                acc += (now - last) * 1.0e-9; // convert nanoseconds to seconds
                last = now;

                while (acc >= dt) {
                    update(dt);
                    acc -= dt;
                }
            }
        }.start();
    }

    public Snake getSnake() {
        return snake;
    }

    public double getW() {
        return w;
    }

    public double getH() {
        return h;
    }

    public void setW(double w) {
        this.w = w;
    }

    public void setH(double h) {
        this.h = h;
    }

    public void on_key_pressed(KeyCode key) {
        int direction = snake.getDirection();
        if (key == KeyCode.UP) {
            if (direction == 2 || direction == 4)
                snake.setDirection(1);
        }
        if (key == KeyCode.RIGHT) {
            if (direction == 1 || direction == 3)
                snake.setDirection(2);

        }
        if (key == KeyCode.DOWN) {
            if (direction == 2 || direction == 4)
                snake.setDirection(3);
        }
        if (key == KeyCode.LEFT) {
            if (direction == 1 || direction == 3)
                snake.setDirection(4);
        }
    }
}
