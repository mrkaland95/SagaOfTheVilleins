package inf112.saga.of.the.villeins.InputProcessors;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;


// Siden inputprossesoren håndterer input for "spillet", can vi kanskje kalle den "game" inputprossor eller noe sånt?

public class PlayerProcessor implements InputProcessor {

    private OrthographicCamera camera;

	private Vector2 clickCoordinates = new Vector2(0f, 0f);

    public PlayerProcessor(OrthographicCamera camera){
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
		if(character == 'z'){
			camera.zoom += 0.05;
			return true;
		}
		if(character == 'x'){
			camera.zoom -= 0.05;
			return true;
		}

		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
//		Vector3 worldCoordinate = camera.project(new Vector3(screenX, screenY, pointer));
//		System.out.println(worldCoordinate);


		if (button == Input.Buttons.RIGHT) {
			Vector3 cameraCoordinates = new Vector3(screenX, screenY, 0);
			// TODO fjern dette senere
			// Her trengte vi bare å kalle på "unproject" for å få riktige 2d koordinater.
			// Unproject gjør setter de riktige verdiene på cameracoordinates objektet.
			camera.unproject(cameraCoordinates);
			clickCoordinates = new Vector2(cameraCoordinates.x, cameraCoordinates.y);
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		float zoomMultiplier = 0.20f;
		float zoomMinimumLevel = 0.5f;
		if (camera.zoom + amountY * zoomMultiplier < zoomMinimumLevel) {
			camera.zoom = zoomMinimumLevel;
		} else {
			camera.zoom += amountY * 0.25f;
		}
		return false;
	}


	public Vector2 getClickCoordinates() {
		return this.clickCoordinates;
	}
    
}
