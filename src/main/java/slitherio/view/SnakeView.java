package slitherio.view;

import java.util.*;

import javafx.collections.ListChangeListener;
import slitherio.gameobjects.*;

/** Represents the view of a {@link slitherio.gameobjects.Snake}. */
public final class SnakeView {

    /** The snake to display. */
    private Snake snake;
    /** The snake's body to display. */
    private List<SegmentView> bodyView = new LinkedList<SegmentView>();

    /**
     * Create a new instance of SnakeView. This constructor calls
     * {@link #bind(GameView, SnakeView)}.
     * 
     * @param view  the game's view
     * @param snake the snake to display
     */
    public SnakeView(GameView view, Snake snake) {
        this.snake = snake;
        for (Segment segment : snake.getBody())
            bodyView.add(new SegmentView(segment, snake.getSkin()));

        bind(view, this);

    }

    /**
     * Bind the properties {@link slitherio.gameobjects.Snake#getBodyProperty()
     * snake.body} and {@link #bodyView}.
     * 
     * @param view      the game's view
     * @param snakeView the snake's view to bind
     */
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
                        change.getAddedSubList().forEach(segment -> view.addSegment(snakeView, segment));
                        bodyView.forEach(segmentView -> view.getRoot().getChildren().remove(segmentView.getGraphics()));
                        bodyView.forEach(segmentView -> segmentView.display(view.getRoot()));

                    }
                }
            }
        });
    }

    /**
     * Get the snake to display.
     * 
     * @return the snake to display
     */
    protected final Snake getSnake() {
        return snake;
    }

    /**
     * Get the property bodyView
     * 
     * @return the property bodyView
     */
    protected final List<SegmentView> getBodyView() {
        return bodyView;
    }

}