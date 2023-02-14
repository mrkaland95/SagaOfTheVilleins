package inf112.saga.of.the.villeins.InputProcessors;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

public class CameraProcessor implements InputProcessor{

    private OrthographicCamera camera;

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
		Vector3 worldCoordinate = camera.project(new Vector3(screenX, screenY, pointer));
		System.out.println(worldCoordinate);
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
		return false;
	}
    
}
