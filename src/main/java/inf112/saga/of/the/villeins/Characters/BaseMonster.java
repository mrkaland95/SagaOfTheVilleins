package inf112.saga.of.the.villeins.Characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import inf112.saga.of.the.villeins.Animations.CharacterAnimationHandler;
import inf112.saga.of.the.villeins.Characters.AI.IBaseAI;
import inf112.saga.of.the.villeins.Characters.AI.SimpleAI;
import inf112.saga.of.the.villeins.Game.Main;
import inf112.saga.of.the.villeins.MapUtils.HexGridMapPosition;
import inf112.saga.of.the.villeins.MapUtils.TilePosition;


public class BaseMonster extends BaseCharacter implements ICharacter {

    private SimpleAI ai;
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
        this.ai.AImakeDecision();
        this.calculatePathToMove();
        this.tileMovement.move(deltaTime);
    }



    void calculatePathToMove() {
        if (this.getCharacterState() == CharacterState.MOVING) return;
        if (this.getEndPosition() == null) return;
        if (getActionPoints() == 0) return; 
        while(this.pathToMove.size() > getActionPoints()+1){
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
