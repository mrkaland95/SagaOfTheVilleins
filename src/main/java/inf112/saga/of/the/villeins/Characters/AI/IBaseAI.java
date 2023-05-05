package inf112.saga.of.the.villeins.Characters.AI;

import inf112.saga.of.the.villeins.Characters.ICharacter;
import inf112.saga.of.the.villeins.Game.TileInfoMap;

public interface IBaseAI {
    void setTargetCharacter(ICharacter target);
    ICharacter getTargetCharacter();
    boolean AIPerformAction(TileInfoMap infoMap);
}
