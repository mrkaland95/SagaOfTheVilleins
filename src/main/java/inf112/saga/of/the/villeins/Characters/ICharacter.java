package inf112.saga.of.the.villeins.Characters;

public interface ICharacter extends IMovable2D, IStats, IAttackCapable {

    String getIdentifier();

    /**
     * Updates the state of the character object. Things like moving the character,
     * rendering its sprite, attack etc. Should be called in this function.
     */
    void update();
}