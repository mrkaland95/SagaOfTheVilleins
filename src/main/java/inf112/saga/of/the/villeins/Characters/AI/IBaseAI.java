package inf112.saga.of.the.villeins.Characters.AI;

import inf112.saga.of.the.villeins.Characters.ICharacter;

public interface IBaseAI {
    void setTargetCharacter(ICharacter target);
    ICharacter getTargetCharacter();
    boolean AIPerformAction();
}
