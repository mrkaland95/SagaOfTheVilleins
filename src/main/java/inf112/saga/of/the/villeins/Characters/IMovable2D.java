package inf112.saga.of.the.villeins.Characters;

import com.badlogic.gdx.math.Vector2;

public interface IMovable2D {
    /**
     * @return a 2D movable object's position
     */
    Vector2 getPosition();

    /**
     * @return A movable object's x position
     */
    float getxCurrentPosition();

    /**
     * @return A movable object's y position
     */
    float getyCurrentPosition();

    /**
     * @param distance Moves the movable object in the x direction by the given amount.
     */
    void moveXAxis(float distance);

    /**
     * @param distance Moves the movable object in the y direction by the given amount
     */
    void moveYAxis(float distance);

    /**
     * Sets the position of an object in the world. Used to teleport or initialize its position.
     * @param xPosition The x coordinate to set
     * @param yPosition The y coordinate to set
     */
    public void setPosition(float xPosition, float yPosition);
    /**
     * Method intended to gradually move a movable object to a position, rather than "teleporting" there
     * @param Destination A Vector2 of the x and y position that the movable should move to.
     */
    void moveToPosition(Vector2 Destination);

    boolean isMoving();

}
