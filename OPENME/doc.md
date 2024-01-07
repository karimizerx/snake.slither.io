## gameobjects/

### GameObject.java

```

```

### Food.java

```t
    Default constructor is "private" because no one can change default values
```

### Segment.java

```t
    Default constructor is "private" because no one can change default values
```

### Snake.java

```java
public Snake(double head_x, double head_y) {
    // Snake sould always have a head.
    getBody().add(new Segment(head_x, head_y));
}
```

```java
// Adding a new [segment] behind the head [headSnake]
public void addSegment() {...}
```

## view/

### DisplayableObject.java

```java
// The Display of all GameObjects is a Rectangle
private Rectangle graphics;
```

```java
public DisplayableObject(Pane root, GameObject go, String filename) {
    (...)
    // Binding [go] values with [graphics] values
    bind(go);
    // Adding [graphics] to the root frame game
    root.getChildren().add(graphics);
}
```

```java
// Binding the differents [go] values with [graphics] values
private void bind(GameObject go) {...}
```

### FoodView.java

```

```

### SegmentView.java

```

```

### GameView.java

```

```

### FrameGame.java

```java
// Pane [root] contains all graphics objects of the game
Pane root = new Pane();
```

```java
// Set [scene] dimensions arbitrary
Scene scene = new Scene(root, 1000, 600);
```

```java
// Init Model, View & Controller
Arena arena = new Arena
(...)
controller.setView(view);
```

```java
// Adding some [scene] listeners
scene.setOnKeyPressed(ev -> controller.onKeyPressed(ev.getCode()));
scene.widthProperty().addListener((obs, oldVal, newVal) -> {
    arena.setWidth((double) newVal);
});
scene.heightProperty().addListener((obs, oldVal, newVal) -> {
    arena.setHeight((double) newVal);
});
```

```java
// Set scene to display
stage.setScene(scene);
(...)
// Run the display
stage.show();
```

```java
// Binding graphics objects and game objects of the game
controller.bind();
// Setting graphics objects default values (and add thme to the root)
controller.defautView();
// Running game
arena.animate();
```

## model/

### Arena.java

```java
// Init [foods] to an empty list
private ListProperty<Food> foods = new SimpleListProperty<Food>(FXCollections.<Food>observableArrayList());
```

```java
// Main thread, who manage the game
public void animate() {
    new AnimationTimer() {
        long last = 0;
        final double dt = 0.1; // update every 0.01s
        double acc = 0.0;
        @Override
        public void handle(long now) {
            if (last == 0) { // ignor€ the first tick, just compute the first dt
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
```

## controller/

### Controller.java

```

```
