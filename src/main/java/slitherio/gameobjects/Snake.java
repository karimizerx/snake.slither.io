package slitherio.gameobjects;

// Import java packages
import javafx.beans.property.*;
import javafx.collections.*;

public class Snake {
    private ListProperty<Segment> body;

    public Snake(double head_x, double head_y) {
        // Init [body] to an empty list
        body = new SimpleListProperty<Segment>(FXCollections.<Segment>observableArrayList());
        // Snake sould always have a head.
        getBody().add(new Segment(head_x, head_y, 2));
    }

    public void move(double dt, double maxX, double maxY) {
        Segment head_snake = body.getValue().get(0);
        switch (head_snake.getDirection()) {
            case 1 -> {
                if (head_snake.getUp() < 0)
                    return;
            }
            case 2 -> {
                if (maxX < head_snake.getRight())
                    return;
            }
            case 3 -> {
                if (maxY < head_snake.getDown())
                    return;
            }
            case 4 -> {
                if (head_snake.getLeft() < 0)
                    return;
            }
            default -> {
                return;
            }
        }
        moveToDirection(dt, head_snake.getDirection());
    }

    private void moveToDirection(double dt, int d) {
        for (int i = body.size() - 1; i > 0; --i) {
            Segment segment = body.getValue().get(i);
            Segment previous = body.getValue().get(i - 1);
            segment.setMiddleX(previous.getMiddleX());
            segment.setMiddleY(previous.getMiddleY());
            segment.setDirection(previous.getDirection());
        }
        Segment head_snake = body.getValue().get(0);
        switch (d) {
            case 1 -> head_snake.setMiddleY(head_snake.getMiddleY() - head_snake.getDy()); // *dt
            case 2 -> head_snake.setMiddleX(head_snake.getMiddleX() + head_snake.getDx());
            case 3 -> head_snake.setMiddleY(head_snake.getMiddleY() + head_snake.getDy());
            case 4 -> head_snake.setMiddleX(head_snake.getMiddleX() - head_snake.getDx());
            default -> {
                return;
            }
        }
    }

    public void addSegment() {
        // Adding a new [segment] behind the head [head_snake]
        Segment head_snake = getBody().get(0);
        double nx = head_snake.getMiddleX(), ny = head_snake.getMiddleY();
        int nd = head_snake.getDirection();
        switch (nd) {
            case 1 -> ny = head_snake.getMiddleY() + head_snake.getH();
            case 2 -> nx = head_snake.getMiddleX() - head_snake.getW();
            case 3 -> ny = head_snake.getMiddleY() - head_snake.getH();
            case 4 -> nx = head_snake.getMiddleX() + head_snake.getW();
            default -> {
            }
        }
        Segment newseg = new Segment(nx, ny, nd);
        getBody().add(1, newseg);
    }

    /* Getter & Setter */

    public final ObservableList<Segment> getBody() {
        return body.getValue();
    }

    // public final void setBody(ObservableList<Segment> value) {
    // body.set(value);
    // }

    // Access to the property
    public final ListProperty<Segment> getBodyP() {
        return body;
    }
}