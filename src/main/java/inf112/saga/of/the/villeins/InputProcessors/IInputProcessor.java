package inf112.saga.of.the.villeins.InputProcessors;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

public interface IInputProcessor extends InputProcessor {
    public Vector2 getClickCoordinates();
}
