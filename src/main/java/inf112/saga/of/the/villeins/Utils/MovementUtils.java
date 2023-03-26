package inf112.saga.of.the.villeins.Utils;

import com.badlogic.gdx.math.Vector2;

public class MovementUtils {

    public static Vector2 calculateNewVectorPosition(Vector2 currentPosition, Vector2 destination, float deltaTime, float moveSpeed) {
        if (destination == null) return currentPosition;

        float positionMarginOfError = 3.0f;

        if ((Math.abs(currentPosition.x - destination.x) > positionMarginOfError) ||
            (Math.abs(currentPosition.y - destination.y) > positionMarginOfError)) {
            float pathX = destination.x - currentPosition.x;
            float pathY = destination.y - currentPosition.y;
            float distanceToMove = (float) Math.sqrt(pathX * pathX + pathY * pathY);
            float directiontoMoveX = pathX / distanceToMove;
            float directiontoMoveY = pathY / distanceToMove;

            currentPosition.x += directiontoMoveX * deltaTime * moveSpeed;
            currentPosition.y += directiontoMoveY * deltaTime * moveSpeed;
        } else {
            currentPosition = destination;
        }

        return currentPosition;
    }
}
