package inf112.saga.of.the.villeins.Characters;

import inf112.saga.of.the.villeins.Game.TileInfoMap;

public interface ICharacter extends ITurnBasedMovable, IStats, IAttackCapable{
    /**
     * Oppdaterer tilstanden til karakter objektet.
     *
     */
    void update(TileInfoMap infoMap, float deltaTime);
}