package slitherio.view;

import slitherio.gameobjects.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;

public class SegmentView extends DisplayableObject {

    public SegmentView(Pane root, GameObject go) {
        super(root, go, "snake_head.png");
    }

}