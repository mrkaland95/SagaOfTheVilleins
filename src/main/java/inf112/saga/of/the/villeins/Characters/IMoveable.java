package inf112.saga.of.the.villeins.Characters;

import com.badlogic.gdx.math.Vector2;

public interface IMoveable {
    Vector2 getPosition();

    float getxCurrentPosition();

    float getyCurrentPosition();

    void moveXAxis(float distance);

    void moveYAxis(float distance);

    void moveToPosition(float xPosition, float yPosition);
}
