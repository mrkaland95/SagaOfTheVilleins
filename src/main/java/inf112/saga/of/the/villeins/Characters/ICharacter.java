package inf112.saga.of.the.villeins.Characters;

import inf112.saga.of.the.villeins.Game.TileInfoMap;

public interface ICharacter extends ITurnBasedMovable, IStats, IAttackCapable{
    /**
     * @return
     */
    String getIdentifier();

    /**
     * Updates the state of the character object. Things like moving the character,
     * rendering its sprite, attack etc. Should be called in this function.
     */
    void update(TileInfoMap infoMap, float deltaTime);
}