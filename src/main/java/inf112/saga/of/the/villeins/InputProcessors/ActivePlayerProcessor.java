package inf112.saga.of.the.villeins.InputProcessors;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;

// Siden inputprossesoren håndterer input for "spillet", can vi kanskje kalle den "game" inputprossor eller noe sånt?

public class ActivePlayerProcessor extends BaseInputProcessor {

    public ActivePlayerProcessor(OrthographicCamera camera) {
        super(camera);
    }
}
