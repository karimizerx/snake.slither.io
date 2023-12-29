package slitherio.gamemodes;

import javafx.scene.input.KeyCode;
import slitherio.model.*;

public interface GameMode {

    public void onKeyPressed(Arena arena, KeyCode key);

    public void onMouseMoved(Arena arena, double pointerX, double pointerY);

    public void update(Arena arena);
}