package inf112.saga.of.the.villeins.Characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import inf112.saga.of.the.villeins.Controller.CharacterAnimationController;
import inf112.saga.of.the.villeins.Game.GameLoop;
import inf112.saga.of.the.villeins.Game.Main;
import inf112.saga.of.the.villeins.MapUtils.AStarPathfinder;
import inf112.saga.of.the.villeins.MapUtils.HexGridMapPosition;
import inf112.saga.of.the.villeins.MapUtils.TilePosition;
import inf112.saga.of.the.villeins.Utils.AttackUtils;
import inf112.saga.of.the.villeins.Utils.TileMovement;

import java.util.List;

public class BaseMonster implements ICharacter {
    CharacterAnimationController animationController;
    private int currentHealth;
    private int maxHealth;
    private int strength;
    private int defense;
    private float moveSpeed = Main.globalDefaultMoveSpeed;
    private boolean moving;
    Vector2 currentPosition;
    Vector2 endPosition;
    List<TilePosition> pathToMove;
    private final TileMovement tileMovement;
    private final AttackUtils attackUtils;


    public BaseMonster(Vector2 startPosition,
                       CharacterAnimationController animationController,
                       int maxHealth,
                       int strength,
                       int defense) {
        this.currentPosition = startPosition;
        this.animationController = animationController;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.strength = strength;
        this.defense = defense;
        this.tileMovement = new TileMovement(this);
        this.attackUtils = new AttackUtils(this, 1);
    }

    public BaseMonster(TilePosition startingTile,
                       CharacterAnimationController animationController,
                       int maxHealth,
                       int strength,
                       int defense, AttackUtils attackUtils) {
        this.currentPosition = HexGridMapPosition.calculateVectorCoordinate(startingTile);
        this.animationController = animationController;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.strength = strength;
        this.defense = defense;
        this.attackUtils = attackUtils;
        this.tileMovement = new TileMovement(this);
    }



    @Override
    public void update() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        this.animationController.render(this);
        calculatePathToMove(this.endPosition);
        this.tileMovement.move(deltaTime);
    }



    void calculatePathToMove(Vector2 endGoal) {
        if (this.moving) return;
        if (endGoal == null) return;
        TilePosition currentTile = HexGridMapPosition.findHexTile(currentPosition);
        TilePosition finalTile = HexGridMapPosition.findHexTile(endGoal);
        pathToMove = AStarPathfinder.findPath(currentTile, finalTile, GameLoop.infoMap);
        System.out.println(pathToMove);
//        pathToMove.remove(pathToMove.size() - 1); // Life hack
        tileMovement.setPath(pathToMove);
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
    public boolean isMoving() {
        return this.moving;
    }

    @Override
    public void setMoving(boolean moving) {
        this.moving = moving;
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
