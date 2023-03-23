package inf112.saga.of.the.villeins.Movement;

import com.badlogic.gdx.math.Vector2;
import inf112.saga.of.the.villeins.Characters.IMovable2D;
import inf112.saga.of.the.villeins.MapUtils.HexGridMapPosition;
import inf112.saga.of.the.villeins.MapUtils.TilePosition;

import java.util.List;

public class TileMovement {
    private List<TilePosition> pathToMove;
    private int pathIndex;
    private IMovable2D character;

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
        // If there is no path set yet, or if the character has already walked the length of the path.
        if (pathToMove == null || pathIndex >= pathToMove.size()) {
            return;
        }

        TilePosition currentTile = pathToMove.get(pathIndex);
        Vector2 destinationPosition = HexGridMapPosition.calculateWorldCoordinateFromHexGrid(currentTile.x(), currentTile.y());

        if (character.getDestinationPosition() == null) {
            character.setDestinationPosition(destinationPosition);
        }

        MovementUtils.moveToPosition(character, character.getDestinationPosition(), deltaTime);

        if (!character.isMoving()) {
            pathIndex++;
            character.setDestinationPosition(null);
        }
    }
}