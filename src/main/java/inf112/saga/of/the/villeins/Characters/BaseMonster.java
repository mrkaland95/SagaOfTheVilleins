package inf112.saga.of.the.villeins.Characters;

import com.badlogic.gdx.Gdx;
import inf112.saga.of.the.villeins.Animations.CharacterAnimationHandler;
import inf112.saga.of.the.villeins.Characters.AI.IBaseAI;
import inf112.saga.of.the.villeins.Characters.AI.SimpleAI;
import inf112.saga.of.the.villeins.Game.TileInfoMap;
import inf112.saga.of.the.villeins.Utils.TilePosition;

import java.util.concurrent.ThreadLocalRandom;


public class BaseMonster extends BaseCharacter {
    private IBaseAI ai;
    private final String monsterType;
    private final int scoreValue;
    public BaseMonster(TilePosition startingTile,
                       CharacterAnimationHandler animationController,
                       int maxHealth,
                       int strength,
                       int defense,
                       int attackRange,
                       String monsterType) {
        super(startingTile, animationController, maxHealth, strength, defense, attackRange);
        this.ai = new SimpleAI(null, this);
        this.monsterType = monsterType;
        this.scoreValue = ThreadLocalRandom.current().nextInt(30, 100);
    }

    @Override
    public void update(TileInfoMap infoMap, float deltaTime) {
        this.animationController.render(this, deltaTime);
        this.ai.AIPerformAction(infoMap);
        this.calculatePathToMove(infoMap);
        this.tileMovement.move(deltaTime);
    }


    @Override
    void calculatePathToMove(TileInfoMap infoMap) {
        if (this.getCharacterState() == CharacterState.MOVING) return;
        if (this.getEndPosition() == null) return;
        if (getCurrentActionPoints() == 0) return;
        while(this.pathToMove.size() > getCurrentActionPoints() + 1){
            this.pathToMove.remove(pathToMove.size() - 1);
        }
        this.tileMovement.setPath(this.pathToMove);
        if(this.pathToMove.size() == 0){
            this.setCurrentActionPoints(0);
        }
        this.setEndPosition(null);
    }

    public String getMonsterType() {
        return monsterType;
    }

    @Override
    public void setTargetCharacter(ICharacter target){
        this.ai.setTargetCharacter(target);
    }

    @Override
    public ICharacter getTargetCharacter() {
        return this.ai.getTargetCharacter();
    }

    @Override
    public String toString() {
        return monsterType + " - " + this.hashCode();
    }

    public int getScoreValue() {
        return scoreValue;
    }
}
