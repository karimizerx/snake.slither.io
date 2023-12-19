package slitherio.view;

import java.util.*;
import slitherio.gameobjects.*;

public final class SnakeView {

    private Snake snake;
    private List<SegmentView> bodyView = new LinkedList<SegmentView>();

    /* ******************** Constructor ******************** */
    public SnakeView(Snake snake) {
        this.snake = snake;
        for (Segment segment : snake.getBody())
            bodyView.add(new SegmentView(segment));
    }

    /* ******************** Functions ******************** */

    /* ******************** Getter & Setter ******************** */

    protected final Snake getSnake() {
        return snake;
    }

    protected final List<SegmentView> getBodyView() {
        return bodyView;
    }

}