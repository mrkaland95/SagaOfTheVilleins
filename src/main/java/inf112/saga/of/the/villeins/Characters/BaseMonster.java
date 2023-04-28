package inf112.saga.of.the.villeins.Characters;

import com.badlogic.gdx.Gdx;
import inf112.saga.of.the.villeins.Animations.CharacterAnimationHandler;
import inf112.saga.of.the.villeins.Characters.AI.IBaseAI;
import inf112.saga.of.the.villeins.Characters.AI.SimpleAI;
import inf112.saga.of.the.villeins.MapUtils.TilePosition;


public class BaseMonster extends BaseCharacter implements ICharacter {

    private IBaseAI ai;
    public BaseMonster(TilePosition startingTile,
                       CharacterAnimationHandler animationController,
                       int maxHealth,
                       int strength,
                       int defense,
                       int attackRange) {
        super(startingTile, animationController, maxHealth, strength, defense, attackRange);
        this.ai = new SimpleAI(null, this);
    }


    @Override
    public void update() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        this.animationController.render(this, deltaTime);
        this.ai.AIPerformAction();
        this.calculatePathToMove();
        this.tileMovement.move(deltaTime);
    }


    void calculatePathToMove() {
        if (this.getCharacterState() == CharacterState.MOVING) return;
        if (this.getEndPosition() == null) return;
        if (getCurrentActionPoints() == 0) return;
        while(this.pathToMove.size() > getCurrentActionPoints()+1){
            this.pathToMove.remove(pathToMove.size()-1);
        }
        this.tileMovement.setPath(this.pathToMove);
        this.setEndPosition(null);
    }


    @Override
    public void setTargetCharacter(ICharacter target){
        this.ai.setTargetCharacter(target);
    }

    @Override
    public ICharacter getTargetCharacter() {
        return this.ai.getTargetCharacter();
    }
}
