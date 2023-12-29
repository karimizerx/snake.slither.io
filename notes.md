# Notes for later

## GameObject.java

## Food.java

:arrow_right: **(Arena.java)** :warning: Pour **ne pas placer de food sur un serpent**, trouver une solution où `new Food(x,y)` n'est pas `public`. :white_check_mark:

```java
private final Food getValidRandomFood() {
    int cnt = 500;
    Food food = Food.FoodRandom(width, height);
    while (cnt != 0 && !isValidFoodPosition(food)) {
        --cnt;
        food = Food.FoodRandom(width, height);
    }
    return (cnt == 0) ? (new Food(width / 2, height / 2)) : food
}
```

## Segment.java

:arrow_right: :warning:**Trouver la bonne vitesse**, 100 < et <= 250 et < 300

## Snake.java

:arrow_right: :warning: **VOIR POURQUOI JE NE PEUX PAS AJOUTER UN SEGMENT A LA FIN DANS `addSegment` et `SnakeView.bind`** :x:

:arrow_right: **Rebondir le serpent sur les bords de la window** :white_check_mark:

```java
// Rebondir le serpent sur les bords de la window
  double newRotation = getHead().getRotation();
  if (getHead().getUp() < 0)
    newRotation = (getHead().getRight() > maxWidth) ? 90 : -90;
  else if (getHead().getDown() > maxHeight)
    newRotation = (getHead().getLeft() < 0) ? -90 : 90;
  else if (getHead().getLeft() < 0)
    newRotation = (getHead().getUp() < 0) ? 0 : 180;
  else if (getHead().getRight() > maxWidth)
    newRotation = (getHead().getDown() > maxHeight) ? 180 : 0;

  getHead().setRotation(newRotation);
```

:arrow_right: **Déplacer seulement la queue** :x: (Pas urgent, optimisation)

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

## DisplayableObject.java

## FoodView.java

## SegmentView.java

## GameView.java

## FrameGame.java

```java
  // Passer les boutons en image
    Button playButton = new Button("", new ImageView(Utils.getImage(imageButtonPlay)));
    playButton.setPadding(Insets.EMPTY);
```

## Arena.java

## Controller.java

```
Manage Arena.Foods & GameView.foodsToDisplay Concurrency

```

## Player.java
