package inf112.saga.of.the.villeins.Characters;

import com.badlogic.gdx.math.Vector2;

public interface IMovable2D {
    /**
     * @return a Vector2 of the movable object's position
     */
    Vector2 getPosition();
    void setPosition(Vector2 position);

    /**
     * @param destination Vector2 representing the destination a character should move to.
     */
    void setDestination(Vector2 destination);

    /**
     * @return Vector2 of character's destination.
     */
    Vector2 getDestination();
    /**
     * Method intended to gradually move a movable object to a position, rather than "teleporting" there
     * @param Destination A Vector2 of the x and y position that the movable should move to.
     */
    void moveToPosition(Vector2 Destination, float deltaTime);
    /**
     * @return Whether the object is moving or not.
     */
    boolean isMoving();
}
