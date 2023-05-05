package inf112.saga.of.the.villeins.Utils;

import com.badlogic.gdx.math.Vector2;
import inf112.saga.of.the.villeins.Characters.CharacterDirection;
import inf112.saga.of.the.villeins.Characters.ITurnBasedMovable;
import inf112.saga.of.the.villeins.Characters.CharacterState;

import java.util.List;

public class TileMovement {
    private List<TilePosition> pathToMove;
    private int pathIndex;
    private final ITurnBasedMovable character;

    public TileMovement(ITurnBasedMovable character) {
        this.character = character;
        this.pathToMove = null;
        this.pathIndex = 0;
    }

    public void setPath(List<TilePosition> pathToMove) {
        this.pathToMove = pathToMove;
        this.pathIndex = 0;
    }

    /** Beveger en karakter langs tiles til sin destinasjon.
     * @param deltaTime
     */
    public void move(float deltaTime) {

        // Hvis det ikke fins noen vei å gå, returner ut av metoden tidlig. Hvis dette er tilfelle,
        // Sett karakterens tilstand til "Idle"
        if (pathToMove == null || pathIndex >= pathToMove.size()) {
            if (character.getCharacterState() == CharacterState.MOVING) {
                character.setCharacterState(CharacterState.IDLE);
            }
            return;
        }

        if (!(character.getCharacterState() == CharacterState.MOVING)) {
            // Hvis karakteren's tilsand ikke allerede er sett til "Moving", sett det.
            character.setCharacterState(CharacterState.MOVING);
        }

        TilePosition nextTilePosition = pathToMove.get(pathIndex);
        Vector2 nextTileCoordinate = TilePosition.findVectorCoordinate(nextTilePosition);
        Vector2 newPosition = calculateNewVectorPosition(character.getCurrentPosition(), nextTileCoordinate, deltaTime, character.getMoveSpeed());

        // Setter retningen som animasjonen skal peke mot.
        if (newPosition.x > character.getCurrentPosition().x) {
            character.setCharacterDirection(CharacterDirection.RIGHT);
        } else if (newPosition.x < character.getCurrentPosition().x) {
            character.setCharacterDirection(CharacterDirection.LEFT);
        }

        character.setCurrentPosition(newPosition);

        if (newPosition.equals(nextTileCoordinate)) {
            /*
             * Pathen inneholder den tilen som karakteren står på, så vi må passe på å ikke trekke actionpoints før karakteren har
             * beveget seg
             */
            if(pathIndex > 0){
                character.setCurrentActionPoints(character.getCurrentActionPoints() - 1);
            }
            pathIndex++;
        }
    }


    /**
     * Regner ut hvor langt en karakter har beveget seg utifra start posisjon, destinasjon,
     * fart og hvor lang tid har gått fra forrige tick
     * @return Nytt koordinat.
     */
    public static Vector2 calculateNewVectorPosition(Vector2 currentPosition, Vector2 destination, float deltaTime, float moveSpeed) {
        if (destination == null) return currentPosition;

        Vector2 newPosition = new Vector2(currentPosition);

        float positionMarginOfError = 5.0f;

        if ((Math.abs(newPosition.x - destination.x) > positionMarginOfError) ||
            (Math.abs(newPosition.y - destination.y) > positionMarginOfError)) {
            float pathX = destination.x - newPosition.x;
            float pathY = destination.y - newPosition.y;
            float distanceToMove = (float) Math.sqrt(pathX * pathX + pathY * pathY);
            float directiontoMoveX = pathX / distanceToMove;
            float directiontoMoveY = pathY / distanceToMove;

            newPosition.x += directiontoMoveX * deltaTime * moveSpeed;
            newPosition.y += directiontoMoveY * deltaTime * moveSpeed;
        } else {
            newPosition = destination;
        }
        return newPosition;
    }
}