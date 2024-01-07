# Notes for later

## Snake.java

:arrow_right: :warning: **VOIR POURQUOI JE NE PEUX PAS AJOUTER UN SEGMENT A LA FIN DANS `addSegment` et `SnakeView.bind`** :x:

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
