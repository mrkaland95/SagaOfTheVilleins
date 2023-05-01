package inf112.saga.of.the.villeins.Characters;

import com.badlogic.gdx.Gdx;
import inf112.saga.of.the.villeins.Animations.CharacterAnimationHandler;
import inf112.saga.of.the.villeins.Game.GameScreen;
import inf112.saga.of.the.villeins.MovementUtils.TilePosition;
import inf112.saga.of.the.villeins.MovementUtils.AStarPathfinder;

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
        this.calculatePathToMove();
        this.tileMovement.move(deltaTime);
    }


    @Override
    void calculatePathToMove() {
        if (this.getEndPosition() == null) return;
        TilePosition currentTile = TilePosition.findHexTile(this.getCurrentPosition());
        TilePosition finalTile = TilePosition.findHexTile(this.getEndPosition());
        this.pathToMove = AStarPathfinder.findPath(currentTile, finalTile, GameScreen.infoMap);
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

    /**
     * Brukt for debugging.
     */
    @Override
    public String toString() {
        return "Player - " + this.hashCode();
    }
}
