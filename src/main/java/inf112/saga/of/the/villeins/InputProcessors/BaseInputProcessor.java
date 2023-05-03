package inf112.saga.of.the.villeins.InputProcessors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Base klasse for inputprossesore. Implementerer håndtering av kamera og slikt.
 */
abstract public class BaseInputProcessor implements InputProcessor {
    private OrthographicCamera camera;
	private final float minimumZoomLevel = 0.5f;
	private final float maxZoomLevel = 50f;
	private final float zoomAmount = 0.10f;
	private final Vector3 current = new Vector3();
	private final Vector3 last = new Vector3(-1, -1, -1);
	private final Vector3 delta = new Vector3();
	private Vector2 rightClickCoordinates;
	private Vector2 leftClickCoordinates;
	private boolean leftMouseClicked;
	public boolean endTurn;

	BaseInputProcessor(OrthographicCamera camera) {
		this.camera = camera;
		this.endTurn = false;
		this.rightClickCoordinates = null;
		this.leftMouseClicked = false;
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
		float cameraTranslationAmount = 10f;
		if(character == 'w'){
			camera.translate(0, cameraTranslationAmount, 0);
			return true;
		}
		if(character == 's'){
			camera.translate(0, -cameraTranslationAmount,0);
			return true;
		}
		if(character == 'a'){
			camera.translate(-cameraTranslationAmount, 0 ,0);
			return true;
		}
		if(character == 'd'){
			camera.translate(cameraTranslationAmount, 0 ,0);
			return true;
		}
		if(character == 'z') {
			camera.zoom += zoomAmount;
			return true;
		}
		if(character == 'x') {
			if (camera.zoom - zoomAmount <= minimumZoomLevel) {
				camera.zoom = minimumZoomLevel;
			} else {
				camera.zoom -= zoomAmount;
			}
			return true;
		}
		if(character == 'n'){
			this.endTurn = true;
			return true;
		}

		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if (button == Input.Buttons.LEFT) {
			this.leftMouseClicked = true;
//
//			Vector3 cameraCoordinates = new Vector3(screenX, screenY, 0);
//			this.camera.unproject(cameraCoordinates);
//			this.leftClickCoordinates = new Vector2(cameraCoordinates.x, cameraCoordinates.y);
		}
		if (button == Input.Buttons.RIGHT) {
			Vector3 cameraCoordinates = new Vector3(screenX, screenY, 0);
			this.camera.unproject(cameraCoordinates);
			this.rightClickCoordinates = new Vector2(cameraCoordinates.x, cameraCoordinates.y);
			return true;
		}

		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (button == Input.Buttons.MIDDLE) {
			// Lagrer posisjonen hvis midtre museknapp ble trykket.
			// Stores the position if the middle mouse button was clicked.
			last.set(-1, -1, -1);
			return true;
		}
		return false;
	}
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		if (Gdx.input.isButtonPressed(Input.Buttons.MIDDLE)) {
			camera.unproject(current.set(screenX, screenY, 0));
			if (!(last.x == -1 && last.y == -1 && last.z == -1)) {
				camera.unproject(delta.set(last.x, last.y, 0));
				delta.sub(current);
				camera.position.add(delta.x, delta.y, 0);
			}
			last.set(screenX, screenY, 0);
			return true;
		}
		return false;
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

	public Vector2 getRightClickCoordinates() {
		return rightClickCoordinates;
	}

//	public boolean getLeftClickCoordinates() {
//		return leftMouseClicked;
//	}

	public void endTurn() {
		this.endTurn = false;
	}

	public boolean checkTurn() {
		return this.endTurn;
	}

	public void resetInput() {
		this.endTurn = false;
		this.rightClickCoordinates = null;
		this.leftClickCoordinates = null;
		this.leftMouseClicked = false;
	}

	public boolean isLeftMouseClicked() {
		return leftMouseClicked;
	}
}
