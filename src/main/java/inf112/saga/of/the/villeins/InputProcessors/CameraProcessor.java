package inf112.saga.of.the.villeins.InputProcessors;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;


// Siden inputprossesoren håndterer input for "spillet", can vi kanskje kalle den "game" inputprossor eller noe sånt?

public class CameraProcessor implements IInputProcessor {
	private final float minimumZoomLevel = 0.5f;

	private final float zoomAmount = 0.10f;

    private OrthographicCamera camera;

	private Vector2 moveClickCoordinates;

	private Vector2 lastCameraCoordinates = new Vector2();


    public CameraProcessor(OrthographicCamera camera){
        this.camera = camera;
    }

    @Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		if(character == 'w'){
			camera.translate(0, 10, 0);
			return true;
		}
		if(character == 's'){
			camera.translate(0, -10,0);
			return true;
		}
		if(character == 'a'){
			camera.translate(-10, 0 ,0);
			return true;
		}
		if(character == 'd'){
			camera.translate(10, 0 ,0);
			return true;
		}
		if(character == 'z') {
			camera.zoom += 0.05;
			return true;
		}
		if(character == 'x') {
			if (camera.zoom - zoomAmount <= minimumZoomLevel) {
				camera.zoom = minimumZoomLevel;
			} else {
				camera.zoom -= 0.05;
			}
			return true;
		}

		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {

		if (button == Input.Buttons.RIGHT) {
			Vector3 cameraCoordinates = new Vector3(screenX, screenY, 0);
			this.camera.unproject(cameraCoordinates);
			this.moveClickCoordinates = new Vector2(cameraCoordinates.x, cameraCoordinates.y);
			return true;

		} else if (button == Input.Buttons.MIDDLE) {
			// Stores the position if the middle mouse button was clicked.
			this.lastCameraCoordinates.set(screenX, screenY);
			return true;
		}
		return false;
	}


	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		if (Gdx.input.isButtonPressed(Input.Buttons.MIDDLE)) {
			Vector2 NewCameraCoordinates = new Vector2(screenX, screenY);
			Vector2 delta = NewCameraCoordinates.cpy().sub(lastCameraCoordinates);
			camera.translate(-delta.x, delta.y);
			lastCameraCoordinates = NewCameraCoordinates;
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		float zoomMultiplier = 0.20f;
		if (camera.zoom + amountY * zoomMultiplier < this.minimumZoomLevel) {
			camera.zoom = this.minimumZoomLevel;
		} else {
			camera.zoom += amountY * zoomMultiplier;
		}
		return false;
	}


	public Vector2 getClickCoordinates() {
		return this.moveClickCoordinates;
	}
    
}
