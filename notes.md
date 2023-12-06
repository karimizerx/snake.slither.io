# Notes for later

- ## gameobjects/

  - ### GameObject.java

    ```java
    // Math for the collisions:
    // u = up, d = down, l = left, r = right, x = dx, y = dy
    // collision when: l1 + x1t < r2 + x2t ∧ l2 + x2t < r1 + x1t
    // ∧ u1 + y1t < d2 + y2t ∧ u2 + y2t < d1 + y1t
    // <=> l1 - r2 < (x2 - x1)t < r1 - l2
    // ∧ u1 - d2 < (y2 - y1)t < d1 - u2
    // on nomme nos variables pour plus de clarté
    // <=> a < bt < c ∧ d < et < f

    public final boolean collides(GameObject o, double dt) {
        final double epsilon = 0.0000001;
        final double a = getLeft() - o.getRight();
        final double b = o.getDx() - getDx();
        final double c = getRight() - o.getLeft();
        final double d = getUp() - o.getDown();
        final double e = o.getDy() - getDy();
        final double f = getDown() - o.getUp();
        double min_t;
        double max_t;
        if (b < -epsilon) {
            if (a / b < c / b) {
                return false;
            }
            min_t = c / b;
            max_t = a / b;
        } else if (epsilon < b) {
            if (a / b > c / b) {
                return false;
            }
            min_t = a / b;
            max_t = c / b;
        } else { // b is around about 0.
            if (c < a || 0 < a || c < 0) {
                return false;
            }
            min_t = -100000;
            max_t = 100000;
        }
        if (e < -epsilon) {
            if (d / e < f / e) {
                return false;
            }
            min_t = Math.max(min_t, f / e);
            max_t = Math.min(max_t, d / e);
        } else if (epsilon < e) {
            if (d / e > f / e) {
                return false;
            }
            min_t = Math.max(min_t, d / e);
            max_t = Math.min(max_t, f / e);
        } else { // e is around about 0.
            if (f < d || 0 < d || f < 0) {
                return false;
            }
        }
        if (max_t < 0 || min_t > dt) {
            return false;
        }
        final double t = Math.max(0, min_t); // Unused for now, will be used later to calculate the collision point.
        return true;
    }
    ```

  - ### Food.java

    ```

    ```

  - ### Segment.java

    ```

    ```

  - ### Snake.java

    ```java
    // in moveToDirection(double dt, int d) replace
    for (int i = body.size() - 1; i > 0; --i) {
      Segment segment = body.getValue().get(i);
      Segment previous = body.getValue().get(i - 1);
      segment.setCenterX(previous.getCenterX());
      segment.setCenterY(previous.getCenterY());
      segment.setDirection(previous.getDirection());
    }
    // by
    Segment segment = body.getValue().get(body.size()-1);
    segment.setCenterX(headSnake.getCenterX());
    segment.setCenterY(headSnake.getCenterY());
    segment.setDirection(headSnake.getDirection());
    ```

- ## view/

  - ### DisplayableObject.java

    ```

    ```

  - ### FoodView.java

    ```

    ```

  - ### SegmentView.java

    ```

    ```

  - ### GameView.java

    ```

    ```

  - ### FrameGame.java

    ```

    ```

- ## model/

  - ### Arena.java

    ```

    ```

- ## controller/

  - ### Controller.java

    ```

    ```
