package inf112.saga.of.the.villeins.InputProcessors;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;


// Siden inputprossesoren håndterer input for "spillet", can vi kanskje kalle den "game" inputprossor eller noe sånt?

public class PlayerProcessor implements IInputProcessor {
    private OrthographicCamera camera;
	private final Vector3 current = new Vector3();
	private final Vector3 last = new Vector3();
	private final Vector3 delta = new Vector3();
	public Vector2 clickCoordinates = new Vector2();

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
			camera.translate(0, 20, 0);
			return true;
		}
		if(character == 's'){
			camera.translate(0, -20,0);
			return true;
		}
		if(character == 'a'){
			camera.translate(-20, 0 ,0);
			return true;
		}
		if(character == 'd'){
			camera.translate(20, 0 ,0);
			return true;
		}
		if(character == 'z'){
			camera.zoom += 0.10f;
			return true;
		}
		if(character == 'x'){
			camera.zoom -= 0.10f;
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
			camera.unproject(cameraCoordinates);
			clickCoordinates.x = cameraCoordinates.x;
			clickCoordinates.y = cameraCoordinates.y;
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		camera.unproject(current.set(screenX, screenY, 0));
		if (!(last.x == -1 && last.y == -1 && last.z == -1)) {
			camera.unproject(delta.set(last.x, last.y, 0));
			delta.sub(current);
			camera.position.add(delta.x, delta.y, 0);
		}
		last.set(screenX, screenY, 0);
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
