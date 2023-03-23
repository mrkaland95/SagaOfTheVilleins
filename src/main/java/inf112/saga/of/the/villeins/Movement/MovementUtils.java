package inf112.saga.of.the.villeins.Movement;

import com.badlogic.gdx.math.Vector2;
import inf112.saga.of.the.villeins.Characters.IMovable2D;

public class MovementUtils {

    public static void moveToPosition(IMovable2D character, Vector2 destination, float deltaTime) {
        Vector2 currentPosition = character.getCurrentPosition();
        float moveSpeed = character.getMoveSpeed();

        if (destination == null) return;

        float positionMarginOfError = 3.0f;

        if ((Math.abs(currentPosition.x - destination.x) > positionMarginOfError) ||
            (Math.abs(currentPosition.y - destination.y) > positionMarginOfError)) {

            character.setMoving(true);
            float pathX = destination.x - currentPosition.x;
            float pathY = destination.y - currentPosition.y;
            float distanceToMove = (float) Math.sqrt(pathX * pathX + pathY * pathY);
            float directiontoMoveX = pathX / distanceToMove;
            float directiontoMoveY = pathY / distanceToMove;

            currentPosition.x += directiontoMoveX * deltaTime * moveSpeed;
            currentPosition.y += directiontoMoveY * deltaTime * moveSpeed;
        } else {
            currentPosition.x = destination.x;
            currentPosition.y = destination.y;
            character.setDestinationPosition(null);
            character.setMoving(false);
        }

        character.setCurrentPosition(currentPosition);
    }
}
