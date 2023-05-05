package inf112.saga.of.the.villeins.Characters;

import com.badlogic.gdx.Gdx;
import inf112.saga.of.the.villeins.Animations.CharacterAnimationHandler;
import inf112.saga.of.the.villeins.Game.GameScreen;
import inf112.saga.of.the.villeins.Game.TileInfoMap;
import inf112.saga.of.the.villeins.Utils.TilePosition;
import inf112.saga.of.the.villeins.Utils.AStarPathfinder;

public class Player extends BaseCharacter implements IPlayable {
    private int score;
    public Player(TilePosition startingPosition,
              CharacterAnimationHandler animationController,
              int maxHealth,
              int strength,
              int defense,
              int attackRange) {
        super(startingPosition, animationController, maxHealth, strength, defense, attackRange);
        this.score = 0;
    }

    @Override
    public void update(TileInfoMap infoMap, float deltaTime) {
        this.animationController.render(this, deltaTime);
        this.calculatePathToMove(infoMap);
        this.tileMovement.move(deltaTime);
    }

    @Override
    void calculatePathToMove(TileInfoMap infoMap) {
        if (this.getEndPosition() == null) return;
        if (getCurrentActionPoints() == 0) return;
        TilePosition currentTile = TilePosition.findHexTile(this.getCurrentPosition());
        TilePosition finalTile = TilePosition.findHexTile(this.getEndPosition());
        this.pathToMove = AStarPathfinder.findPath(currentTile, finalTile, GameScreen.infoMap);

        while(this.pathToMove.size() > getCurrentActionPoints() + 1){
            this.pathToMove.remove(pathToMove.size() - 1);
        }
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
