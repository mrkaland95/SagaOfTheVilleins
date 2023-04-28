package inf112.saga.of.the.villeins.InputProcessors;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;

abstract public class BaseInputProcessor implements InputProcessor {
    private OrthographicCamera camera;
	private Stage stage;
	private final float minimumZoomLevel = 0.5f;
	private final float zoomAmount = 0.10f;
	private final Vector3 current = new Vector3();
	private final Vector3 last = new Vector3(-1, -1, -1);
	private final Vector3 delta = new Vector3();
	private Vector2 rightClickCoordinates;
	private Vector2 leftClickCoordinates;
	public boolean endTurn;




//
//    Vector2 getLeftClickCoordinates();
//    Vector2 getRightClickCoordinates();
//    boolean checkTurn();
//    void endTurn();
}
