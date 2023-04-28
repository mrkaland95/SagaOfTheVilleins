package inf112.saga.of.the.villeins.Characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import inf112.saga.of.the.villeins.Animations.CharacterAnimationHandler;
import inf112.saga.of.the.villeins.Game.GameLoop;
import inf112.saga.of.the.villeins.MapUtils.HexGridMapPosition;
import inf112.saga.of.the.villeins.MapUtils.AStarPathfinder;
import inf112.saga.of.the.villeins.MapUtils.TilePosition;
import inf112.saga.of.the.villeins.Game.Main;
import inf112.saga.of.the.villeins.Utils.AttackUtils;
import inf112.saga.of.the.villeins.Utils.TileMovement;

import java.util.List;

public class Player extends BaseCharacter implements IPlayable {
    private int score;
    public Player(TilePosition startingPosition,
              CharacterAnimationHandler animationController,
              int maxHealth,
              int strength,
              int defense,
              int attackRange) {
        super(startingPosition, animationController, maxHealth, strength, defense, attackRange);
        this.score = 1337;
    }



    @Override
    public void update() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        this.animationController.render(this, deltaTime);
        this.calculatePathToMove(this.getEndPosition());
        this.tileMovement.move(deltaTime);
    }


    void calculatePathToMove(Vector2 endGoal) {
        if (endGoal == null) return;
        TilePosition currentTile = HexGridMapPosition.findHexTile(this.getCurrentPosition());
        TilePosition finalTile = HexGridMapPosition.findHexTile(endGoal);
        this.pathToMove = AStarPathfinder.findPath(currentTile, finalTile, GameLoop.infoMap);
        this.tileMovement.setPath(pathToMove);
        this.setEndPosition(null);
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public void setTargetCharacter(ICharacter target) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setTargetCharacter'");
    }

    @Override
    public ICharacter getTargetCharacter() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTargetCharacter'");
    }

}
