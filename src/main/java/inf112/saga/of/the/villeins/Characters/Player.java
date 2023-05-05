package inf112.saga.of.the.villeins.Characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import inf112.saga.of.the.villeins.Animations.CharacterAnimationHandler;
import inf112.saga.of.the.villeins.Game.GameScreen;
import inf112.saga.of.the.villeins.Game.TileInfoMap;
import inf112.saga.of.the.villeins.Utils.AttackUtils;
import inf112.saga.of.the.villeins.Utils.TileMovement;
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
    public Boolean attack(Vector2 coordinate) {
        BaseMonster monster = (BaseMonster) TilePosition.getCharacterOnCoordinate(coordinate, GameScreen.characterList);
        Boolean result = super.attack(coordinate);
        if (result) {
            if (monster.getCurrentHealth() == 0) {
                this.score += monster.getScoreValue();
            }
        }
        return result;
    }

    @Override
    void calculatePathToMove(TileInfoMap infoMap) {
        if (this.getEndPosition() == null) return;
        if (getCurrentActionPoints() == 0) return;
        TilePosition currentTile = TilePosition.findHexTile(this.getCurrentPosition());
        TilePosition finalTile = TilePosition.findHexTile(this.getEndPosition());
        this.pathToMove = AStarPathfinder.findPath(currentTile, finalTile, infoMap);

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
        // this method is not used for player
        throw new UnsupportedOperationException("Unimplemented method 'setTargetCharacter'");
    }

    @Override
    public ICharacter getTargetCharacter() {
        // this method is not used for player
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
