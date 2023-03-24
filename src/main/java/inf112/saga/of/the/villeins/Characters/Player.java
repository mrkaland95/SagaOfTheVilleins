package inf112.saga.of.the.villeins.Characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import inf112.saga.of.the.villeins.Controller.CharacterAnimationController;
import inf112.saga.of.the.villeins.Game.GameLoop;
import inf112.saga.of.the.villeins.MapUtils.HexGridMapPosition;
import inf112.saga.of.the.villeins.MapUtils.AStarPathfinder;
import inf112.saga.of.the.villeins.MapUtils.TilePosition;
import inf112.saga.of.the.villeins.Game.Main;
import inf112.saga.of.the.villeins.Movement.TileMovement;

import java.util.List;

public class Player implements ICharacter {
    private String name;
    CharacterAnimationController animationController;
    private int maxHealth;
    private int currentHealth;
    private int strength;
    private int defense;
    private float moveSpeed = Main.globalDefaultMoveSpeed;
    private int score;
    private boolean moving;
    Vector2 currentPosition;
    Vector2 endPosition;
    List<TilePosition> pathToMove;
    TileMovement tileMovement;

    public Player(Vector2 startingPosition,
                  CharacterAnimationController animationController,
                  int maxHealth,
                  int strength,
                  int defense) {
        this.currentPosition = startingPosition;
        this.animationController = animationController;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.strength = strength;
        this.defense = defense;
        this.tileMovement = new TileMovement(this);
    }


    @Override
    public void update() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        this.animationController.render(this);
        calculatePathToMove(endPosition);
        this.tileMovement.move(deltaTime);
    }


    void calculatePathToMove(Vector2 endGoal) {
        if (endGoal == null) return;
        TilePosition currentTile = HexGridMapPosition.findHexTile(currentPosition);
        TilePosition finalTile = HexGridMapPosition.findHexTile(endGoal);
        pathToMove = AStarPathfinder.findPath(currentTile, finalTile, GameLoop.infoMap);
        tileMovement.setPath(pathToMove);
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

    public void setEndPosition(Vector2 destinationPosition){
        this.endPosition = destinationPosition;
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
        int currentHealth = character.getCurrentHealth() - damage;
        character.setHealth(currentHealth);
    }
}
