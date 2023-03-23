package inf112.saga.of.the.villeins.Characters;

import com.badlogic.gdx.math.Vector2;

public interface IMovable2D {
    /**
     * @return a Vector2 of the movable object's position
     */
    Vector2 getCurrentPosition();
    void setCurrentPosition(Vector2 position);

    /**
     * @param destination Vector2 representing the destination a character should move to.
     */
    void setDestinationPosition(Vector2 destination);

    /**
     * @return Vector2 of character's destination.
     */
    Vector2 getDestinationPosition();
    /**
     * @return Whether the object is moving or not.
     */
    boolean isMoving();
    void setMoving(boolean moving);
    float getMoveSpeed();
    void setMoveSpeed(float moveSpeed);

}
