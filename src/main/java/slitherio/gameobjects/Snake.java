package slitherio.gameobjects;

// Import java packages
import java.util.*;

public class Snake {
    List<Segment> body = new ArrayList<>();
    private int direction;

    public Snake(double fstx, double fsty, double fstw, double fsth, double fstdx, double fstdy, int direction) {
        this.direction = direction;
        body.add(new Segment(fstx, fsty, fstw, fsth, fstdx, fstdy, direction));
    }

    public void move(double dt, double maxX, double maxY) {
        Segment head_snake = body.get(0);
        final double epsilon = 0000001;
        switch (direction) {
            case 1 -> {
                if (Math.abs(0 - head_snake.getUp()) < epsilon)
                    return;
            }
            case 2 -> {
                if (Math.abs(maxX - head_snake.getRight()) < epsilon)
                    return;
            }
            case 3 -> {
                if (Math.abs(maxY - head_snake.getDown()) < epsilon)
                    return;
            }
            case 4 -> {
                if (Math.abs(0 - head_snake.getLeft()) < epsilon)
                    return;
            }
            default -> {
                return;
            }
        }
        moveToDirection(dt, direction);
    }

    private void moveToDirection(double dt, int d) {
        for (int i = body.size() - 1; i > 0; --i) {
            Segment segment = body.get(i);
            Segment previous = body.get(i - 1);
            segment.setX(previous.getMiddleX());
            // segment.setX(segment.getMiddleX() + segment.getDx() * 0.01);
            segment.setY(previous.getMiddleY());
            segment.setDirection(previous.getdirection());
        }
        Segment head_snake = body.get(0);
        switch (d) {
            case 1 -> head_snake.setY(head_snake.getMiddleY() - head_snake.getDy()); // *dt
            case 2 -> head_snake.setX(head_snake.getMiddleX() + head_snake.getDx());
            case 3 -> head_snake.setY(head_snake.getMiddleY() + head_snake.getDy());
            case 4 -> head_snake.setX(head_snake.getMiddleX() - head_snake.getDx());
            default -> {
                return;
            }
        }
    }

    public void addSegment() {
        Segment previous = body.get(body.size() - 1);
        double nx = previous.getMiddleX(), ny = previous.getMiddleY();
        switch (previous.getdirection()) {
            case 1 -> ny = previous.getMiddleY() + previous.getH();
            case 2 -> nx = previous.getMiddleX() - previous.getW();
            case 3 -> ny = previous.getMiddleY() - previous.getH();
            case 4 -> nx = previous.getMiddleX() + previous.getW();
            default -> {
            }
        }
        Segment newseg = new Segment(nx, ny, previous.getW(), previous.getH(), previous.getDx(), previous.getDy(),
                direction);
        body.add(newseg);
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int value) {
        for (int i = body.size() - 1; i > 0; --i) {
            Segment segment = body.get(i);
            Segment previous = body.get(i - 1);
            segment.setDirection(previous.getdirection());
        }
        this.direction = value;
        body.get(0).setDirection(value);

    }

    public List<Segment> getBody() {
        return body;
    }
}