package slitherio.view;

import java.util.*;

import javafx.collections.ListChangeListener;
import slitherio.gameobjects.*;

public final class SnakeView {

    private Snake snake;
    private List<SegmentView> bodyView = new LinkedList<SegmentView>();

    /* ******************** Constructor ******************** */
    public SnakeView(GameView view, Snake snake) {
        this.snake = snake;
        for (Segment segment : snake.getBody())
            bodyView.add(new SegmentView(segment));

        bind(view, this);

    }

    /* ******************** Functions ******************** */

    private final void bind(GameView view, SnakeView snakeView) {
        snake.getBodyProperty().addListener(new ListChangeListener<Segment>() {

            @Override
            public void onChanged(Change<? extends Segment> change) {
                while (change.next()) {

                    // Manage removed segment
                    if (change.wasRemoved()) {
                        for (Segment segment : change.getRemoved()) {
                            for (DisplayableObject segmentView : snakeView.getBodyView()) {
                                if (segmentView.getObject().equals(segment))
                                    view.removeSegment(snakeView, segmentView);
                            }
                        }
                    }

                    // Manage added segment
                    if (change.wasAdded()) {
                        change.getAddedSubList().forEach((Segment segment) -> view.addSegment(snakeView, segment));
                        bodyView.forEach((SegmentView segmentView) -> view.getRoot().getChildren().remove(segmentView.getGraphics()));
                        bodyView.forEach((SegmentView segmentView) -> segmentView.display(view.getRoot()));

                    }
                }
            }
        });
    }

    /* ******************** Getter & Setter ******************** */

    protected final Snake getSnake() {
        return snake;
    }

    protected final List<SegmentView> getBodyView() {
        return bodyView;
    }

}