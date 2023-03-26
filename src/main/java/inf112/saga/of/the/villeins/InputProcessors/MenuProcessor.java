package inf112.saga.of.the.villeins.InputProcessors;

import com.badlogic.gdx.math.Vector2;

public class MenuProcessor implements IInputProcessor{
    @Override
    public Vector2 getLeftClickCoordinates() {
        return null;
    }

    @Override
    public Vector2 getRightClickCoordinates() {
        return null;
    }

    @Override
    public boolean keyDown(int i) {
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(float v, float v1) {
        return false;
    }

    @Override
    public boolean checkTurn() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'checkTurn'");
    }

    @Override
    public void endTurn() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'endTurn'");
    }
}
