package inf112.saga.of.the.villeins.Characters;

import com.badlogic.gdx.math.Vector2;

import inf112.saga.of.the.villeins.Utils.TilePosition;

import java.util.List;



public interface IMovable2D {
    TilePosition getTilePosition();
    void setTilePosition(TilePosition tilePosition);
    /**
     * @return a Vector2 of the movable object's position
     */
    Vector2 getCurrentPosition();
    void setCurrentPosition(Vector2 position);
    /**
     * Returns a boolean
     * @param position Vector2 representing the destination a character should move to.
     */
    Boolean setEndPosition(Vector2 position);

    /**
     * @return Vector2 of character's destination.
     */
    Vector2 getEndPosition();

    /**
     * Henter "tilstanden" til en karakter. F.eks om den ikke gjør noe("Idle"), går("Moving"), angriper("Attacking") osv.
     *
     * @return
     */
    CharacterState getCharacterState();

    /** Setter tilstanden(IDLE,  til en karakter
     * @param characterState
     */
    void setCharacterState(CharacterState characterState);

    /** Henter retningen som en karakter skal peke mot.
     * @return
     */
    CharacterDirection getCharacterDirection();

    /** Setter retningen som en karakter skal peke mot.
     * @param characterDirection
     */
    void setCharacterDirection(CharacterDirection characterDirection);

    /** Henter bevegelses hastigheten til en karakter.
     * @return
     */
    float getMoveSpeed();

    /** Setter bevegelsesnhastigenheten til en karakter.
     * @param moveSpeed
     */
    void setMoveSpeed(float moveSpeed);

    /** Setter stien av tiles som en karakter skal følge til destinasjonen sin.
     * @param pathToMove
     */
    void setPathToMove(List<TilePosition> pathToMove);

}
