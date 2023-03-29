package inf112.saga.of.the.villeins.Utils;

import com.badlogic.gdx.math.Vector2;
import inf112.saga.of.the.villeins.Characters.CharacterState;
import inf112.saga.of.the.villeins.Characters.IMovable2D;
import inf112.saga.of.the.villeins.MapUtils.HexGridMapPosition;
import inf112.saga.of.the.villeins.MapUtils.TilePosition;

import java.util.List;

public class TileMovement {
    private List<TilePosition> pathToMove;
    private int pathIndex;
    private final IMovable2D character;

    public TileMovement(IMovable2D character) {
        this.character = character;
        this.pathToMove = null;
        this.pathIndex = 0;
    }

    public void setPath(List<TilePosition> pathToMove) {
        this.pathToMove = pathToMove;
        this.pathIndex = 0;
    }

    public void move(float deltaTime) {

        // If there is no path, then return early. In that case, if the character's state is set to moving, set it to idle.
        if (pathToMove == null || pathIndex >= pathToMove.size()) {
            if (character.getCharacterState() == CharacterState.MOVING) {
                character.setCharacterState(CharacterState.IDLE);
            }
            return;
        }

        if (!(character.getCharacterState() == CharacterState.MOVING)) {
            // Set isMoving to true when character starts moving along the array of tiles
            character.setCharacterState(CharacterState.MOVING);
        }

        TilePosition nextTilePosition = pathToMove.get(pathIndex);
        Vector2 nextTileCoordinate = HexGridMapPosition.calculateVectorCoordinate(nextTilePosition);
        Vector2 newPosition = MovementUtils.calculateNewVectorPosition(character.getCurrentPosition(), nextTileCoordinate, deltaTime, character.getMoveSpeed());
        character.setCurrentPosition(newPosition);

        if (newPosition.equals(nextTileCoordinate)) {
            pathIndex++;
        }
    }
}