package inf112.saga.of.the.villeins.Characters;

import com.badlogic.gdx.math.Vector2;
import inf112.saga.of.the.villeins.Animations.CharacterAnimationHandler;
import inf112.saga.of.the.villeins.Game.GameLoop;
import inf112.saga.of.the.villeins.Game.Main;
import inf112.saga.of.the.villeins.MapUtils.AStarPathfinder;
import inf112.saga.of.the.villeins.MapUtils.HexGridMapPosition;
import inf112.saga.of.the.villeins.MapUtils.TilePosition;
import inf112.saga.of.the.villeins.Utils.AttackUtils;
import inf112.saga.of.the.villeins.Utils.TileMovement;

import java.util.List;

public abstract class BaseCharacter implements ICharacter {
    private String identifier;
    private int maxHealth;
    private int currentHealth;
    private int strength;
    private int defense;
    private float moveSpeed = Main.globalDefaultMoveSpeed;
    private Vector2 currentPosition;
    private Vector2 endPosition;
    private CharacterState characterState;
    private CharacterDirection characterDirection;
    List<TilePosition> pathToMove;
    CharacterAnimationHandler animationController;
    TileMovement tileMovement;
    private AttackUtils attackUtils;
    private int currentActionPoints;
    private int maxActionPoints;
    private int attackRange;

    public BaseCharacter(TilePosition startingTile,
                         CharacterAnimationHandler animationController,
                         int maxHealth,
                         int strength,
                         int defense,
                         int attackRange) {



        this.currentPosition = HexGridMapPosition.calculateVectorCoordinate(startingTile);
        this.animationController = animationController;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.strength = strength;
        this.defense = defense;
        this.attackRange = attackRange;
        this.attackUtils = new AttackUtils(this, attackRange);
        this.tileMovement = new TileMovement(this);
        this.characterState = CharacterState.IDLE;

        int tempActionPoints = 10;

        this.currentActionPoints = tempActionPoints;
        this.maxActionPoints = tempActionPoints;
    }


    abstract void calculatePathToMove();


    public float getMoveSpeed() {
        return this.moveSpeed;
    }
    public void setMoveSpeed(float moveSpeed) {
        this.moveSpeed = moveSpeed;
    }
    public String getIdentifier() {
        return this.identifier;
    }
    public void setCurrentPosition(Vector2 position) {
        this.currentPosition = position;
    }
    public Vector2 getEndPosition() {
        return endPosition;
    }
    public Vector2 getCurrentPosition() {
        return currentPosition;
    }
    public TilePosition getTilePosition() {
        return HexGridMapPosition.findHexTile(currentPosition);
    }
    public void setTilePosition(TilePosition tilePosition) {
        this.currentPosition = HexGridMapPosition.calculateVectorCoordinate(tilePosition);
    }
    public void setPathToMove(List<TilePosition> pathToMove) {
        this.pathToMove = pathToMove;
    }
    public int getCurrentHealth() {
        return this.currentHealth;
    }
    public int getStrength() {
        return this.strength;
    }
    public void setStrength(int strength) {
        this.strength = strength;
    }
    public int getDefense() {
        return this.defense;
    }
    public int getMaxHealth() {
        return this.maxHealth;
    }
    public void setHealth(int health) {
        if (health > this.maxHealth) {
            this.currentHealth = this.maxHealth;
        } else if(health < 0) {
            this.currentHealth = 0;
        } else {
            this.currentHealth = health;
        }
    }
    public void setMaxHealth(int maxhealth){
        this.maxHealth = maxhealth;
    }
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
    public Boolean attack(Vector2 CoordinateToAttack) {
        return this.attackUtils.attackCharacter(GameLoop.characterList, CoordinateToAttack);
    }
    public void setAttackRange(int attackRange) {
        this.attackRange = attackRange;
    }
    public int getAttackRange() {
        return this.attackRange;
    }
    public void setCurrentActionPoints(int actionPoints) {
        this.currentActionPoints = actionPoints;
    }
    public int getCurrentActionPoints() {
        return this.currentActionPoints;
    }
    public void resetActionPoints() {
        this.currentActionPoints = this.maxActionPoints;
    }
    public CharacterState getCharacterState() {
        return this.characterState;
    }
    public void setCharacterState(CharacterState characterState) {
        this.characterState = characterState;
    }
    @Override
    public Boolean setEndPosition(Vector2 endPosition) {
        this.endPosition = endPosition;
        return true;
    }
}
