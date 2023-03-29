package inf112.saga.of.the.villeins.InputProcessors;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

public interface IInputProcessor extends InputProcessor {

    Vector2 getLeftClickCoordinates();
    Vector2 getRightClickCoordinates();
    boolean checkTurn();
    void endTurn();
}
