package inf112.saga.of.the.villeins.Characters;

import com.badlogic.gdx.Gdx;
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

public class BaseMonster implements ICharacter {
    private String identifier;
    private final int maxHealth;
    private int currentHealth;
    private int strength;
    private int defense;
    private float moveSpeed = Main.globalDefaultMoveSpeed;
    private Vector2 currentPosition;
    private Vector2 endPosition;
    private List<TilePosition> pathToMove;
    private CharacterState characterState;
    private final CharacterAnimationHandler animationController;
    private final TileMovement tileMovement;
    private final AttackUtils attackUtils;


    public BaseMonster(Vector2 startPosition,
                       CharacterAnimationHandler animationController,
                       int maxHealth,
                       int strength,
                       int defense,
                       int attackRange) {

        this.currentPosition = startPosition;
        this.animationController = animationController;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.strength = strength;
        this.defense = defense;
        this.tileMovement = new TileMovement(this);
        this.attackUtils = new AttackUtils(this, attackRange);
        this.characterState = CharacterState.IDLE;

    }

    public BaseMonster(TilePosition startingTile,
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
        this.attackUtils = new AttackUtils(this, attackRange);
        this.tileMovement = new TileMovement(this);
        this.characterState = CharacterState.IDLE;
    }


    @Override
    public String getIdentifier() {
        return this.identifier;
    }

    @Override
    public void update() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        this.animationController.render(this, deltaTime);
        this.calculatePathToMove();
        this.tileMovement.move(deltaTime);
    }



    void calculatePathToMove() {
        if (this.characterState == CharacterState.MOVING) return;
        if (this.endPosition == null) return;
        TilePosition currentTile = HexGridMapPosition.findHexTile(currentPosition);
        TilePosition finalTile = HexGridMapPosition.findHexTile(endPosition);
        this.pathToMove = AStarPathfinder.findPath(currentTile, finalTile, GameLoop.infoMap);
//        pathToMove.remove(pathToMove.size() - 1); // Life hack
        this.tileMovement.setPath(pathToMove);
        this.endPosition = null;
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
    public Boolean setEndPosition(Vector2 destinationPosition) {
        this.endPosition = destinationPosition;
        return true;
    }


//    @Override
//    public Boolean setEndPosition(Vector2 endPosition) {
//        if (!this.moving) {
//            this.endPosition = endPosition;
//            return true;
//        } else {
//            return false;
//        }
//    }

    @Override
    public Vector2 getEndPosition() {
        return endPosition;
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

    @Override
    public int getCurrentHealth() {
        return this.currentHealth;
    }
    @Override
    public void setStrength(int strength) {
        this.strength = strength;
    }
    @Override
    public int getStrength() {
        return this.strength;
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
        int currentHealth = character.getCurrentHealth() - damage;
        character.setHealth(currentHealth);
    }

    @Override
    public Boolean attack(Vector2 CoordinateToAttack) {
        return this.attackUtils.attackCharacter(GameLoop.characterList, CoordinateToAttack);
    }
}
