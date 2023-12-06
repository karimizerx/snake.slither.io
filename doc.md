# Documentation

- ## gameobjects/

  - ### GameObject.java

    ```

    ```

  - ### Food.java

    ```t
    Default constructor is "private" because no one can change default values
    ```

  - ### Segment.java

    ```t
    Default constructor is "private" because no one can change default values
    ```

  - ### Snake.java

    ```java
    public Snake(double head_x, double head_y) {
        getBody().add(new Segment(head_x, head_y)); // Snake sould always have a head.
    }

    public void addSegment(); // Adding a new [segment] behind the head [headSnake]

    ```

- ## view/

  - ### DisplayableObject.java

    ```t
    All GameObjects are displayed with a Rectangle.
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
