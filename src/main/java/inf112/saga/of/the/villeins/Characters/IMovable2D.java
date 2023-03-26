package inf112.saga.of.the.villeins.Characters;

import com.badlogic.gdx.math.Vector2;

public interface IMovable2D {
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
     * Henter "tilstanden" til en karakter. F.eks om den ikke gjør noe("Idle")
     * Beveger seg osv.
     * @return
     */
    CharacterState getCharacterState();
    void setCharacterState(CharacterState characterState);
    float getMoveSpeed();
    void setMoveSpeed(float moveSpeed);

}
