package inf112.saga.of.the.villeins.InputProcessors;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

// Siden inputprossesoren håndterer input for "spillet", can vi kanskje kalle den "game" inputprossor eller noe sånt?

public class InactivePlayerProcessor extends BaseInputProcessor {
	public InactivePlayerProcessor(OrthographicCamera camera) {
		super(camera);
	}
}
