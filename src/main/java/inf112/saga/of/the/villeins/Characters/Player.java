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

public class Player implements ICharacter, IPlayable {
    private String identifier;
    private int score;
    private final int maxHealth;
    private int currentHealth;
    private int strength;
    private int defense;
    private float moveSpeed = Main.globalDefaultMoveSpeed;
    private int actionPoints;
    Vector2 currentPosition;
    Vector2 endPosition;
    List<TilePosition> pathToMove;
    TileMovement tileMovement;
    CharacterAnimationHandler animationController;
    AttackUtils attackUtils;
    CharacterState characterState;
    private int attackRange;

    public Player(Vector2 startingPosition,
                  CharacterAnimationHandler animationController,
                  int maxHealth,
                  int strength,
                  int defense,
                  int attackRange) {

        // TODO legg til "AttackRange" som en parameter.
        this.currentPosition = startingPosition;
        this.animationController = animationController;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.strength = strength;
        this.defense = defense;
        this.attackRange = attackRange;
        this.score = 0;
        this.tileMovement = new TileMovement(this);
        this.attackUtils = new AttackUtils(this, attackRange);
        this.characterState = CharacterState.IDLE;
    }

    public Player(TilePosition startingPosition,
              CharacterAnimationHandler animationController,
              int maxHealth,
              int strength,
              int defense,
              int attackRange) {

        // TODO legg til "AttackRange" som en parameter.
        this.currentPosition = HexGridMapPosition.calculateVectorCoordinate(startingPosition);
        this.animationController = animationController;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.strength = strength;
        this.defense = defense;
        this.score = 1250;
        this.tileMovement = new TileMovement(this);
        this.attackUtils = new AttackUtils(this, attackRange);
        this.characterState = CharacterState.IDLE;
    }






    @Override
    public void update() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        this.animationController.render(this, deltaTime);
        this.calculatePathToMove(endPosition);
        this.tileMovement.move(deltaTime);
    }


    void calculatePathToMove(Vector2 endGoal) {
        if (endGoal == null) return;
        TilePosition currentTile = HexGridMapPosition.findHexTile(currentPosition);
        TilePosition finalTile = HexGridMapPosition.findHexTile(endGoal);
        this.pathToMove = AStarPathfinder.findPath(currentTile, finalTile, GameLoop.infoMap);
        this.tileMovement.setPath(pathToMove);
        this.endPosition = null;
    }


    @Override
    public String getIdentifier() {
        return this.identifier;
    }
    @Override
    public int getScore() {
        return this.score;
    }

    @Override
    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public CharacterState getCharacterState() {
        return this.characterState;
    }

    @Override
    public void setCharacterState(CharacterState characterState) {
        this.characterState = characterState;
    }

    @Override
    public float getMoveSpeed() {
        return this.moveSpeed;
    }

    @Override
    public void setMoveSpeed(float moveSpeed) {
        this.moveSpeed = moveSpeed;
    }


//    @Override
//    public Boolean setEndPosition(Vector2 destinationPosition){
//        if (!this.moving) {
//            this.endPosition = destinationPosition;
//            return true;
//        } else {
//            return false;
//        }
//    }

    @Override
    public Boolean setEndPosition(Vector2 endPosition) {
        this.endPosition = endPosition;
        return true;
    }

    @Override
    public Vector2 getEndPosition() {
        return endPosition;
    }
    @Override
    public Vector2 getCurrentPosition() {
        return currentPosition;
    }
    @Override
    public void setCurrentPosition(Vector2 position) {
        this.currentPosition = position;
    }

    @Override
    public TilePosition getTilePosition() {
        return HexGridMapPosition.findHexTile(currentPosition);
    }

    @Override
    public void setTilePosition(TilePosition tilePosition) {
        this.currentPosition = HexGridMapPosition.calculateVectorCoordinate(tilePosition);
    }

    @Override
    public void setPathToMove(List<TilePosition> pathToMove) {
        this.pathToMove = pathToMove;
    }

    @Override
    public int getActionPoints() {
        return this.actionPoints;
    }

    @Override
    public void setActionPoints(int actionPoints) {
        this.actionPoints = actionPoints;
    }

    @Override
    public int getCurrentHealth() {
        return this.currentHealth;
    }

    @Override
    public int getStrength() {
        return this.strength;
    }

    @Override
    public void setStrength(int strength) {
        this.strength = strength;
    }

    @Override
    public int getDefense() {
        return this.defense;
    }

    @Override
    public int getMaxHealth() {
        return this.maxHealth;
    }


    @Override
    public void setHealth(int health) {
        if (health > this.maxHealth) {
            this.currentHealth = this.maxHealth;
        } else if(health < 0) {
            this.currentHealth = 0;
        } else {
            this.currentHealth = health;
        }
    }
    @Override
    public void applyDamage(int damage, ICharacter character) {
        int newHealth;
        if (damage >= character.getCurrentHealth()) {
            newHealth = 0;
        } else if (damage < 0) {
            newHealth = getCurrentHealth();
        } else {
            newHealth = character.getCurrentHealth() - damage;
        }
        character.setHealth(newHealth);
    }

    @Override
    public Boolean attack(Vector2 CoordinateToAttack) {
        return this.attackUtils.attackCharacter(GameLoop.characterList, CoordinateToAttack);
    }
    @Override
    public void setAttackRange(int attackRange) {
        this.attackRange = attackRange;
    }
    @Override
    public int getAttackRange() {
        return this.attackRange;
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
