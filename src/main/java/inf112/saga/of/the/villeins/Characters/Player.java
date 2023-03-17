package inf112.saga.of.the.villeins.Characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import inf112.saga.of.the.villeins.Controller.CharacterAnimationController;
import inf112.saga.of.the.villeins.MapUtils.HexGridMapPosition;
import inf112.saga.of.the.villeins.MapUtils.AStarPathfinder;
import inf112.saga.of.the.villeins.MapUtils.TilePosition;
import inf112.saga.of.the.villeins.Game.Main;

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
    Vector2 clickedPosition;
    Vector2 destinationPosition;
    List<TilePosition> pathToMove;
    TilePosition currentTile;

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
    }


    @Override
    public void update() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        this.animationController.render(this);
        if (clickedPosition != null) {
            TilePosition currentTile = HexGridMapPosition.findHexTile(currentPosition);
            TilePosition clickedTile = HexGridMapPosition.findHexTile(clickedPosition);
            pathToMove = AStarPathfinder.findPath(currentTile, clickedTile);
        }

        setCurrentDestination(pathToMove);
        moveToPosition(this.destinationPosition, deltaTime);
        
        clickedPosition = null;
    }

    private void setCurrentDestination(List<TilePosition> pathToMove) {
        if (pathToMove == null || pathToMove.size() == 0) return;

        if (destinationPosition == null) {
            currentTile = pathToMove.get(0);
            destinationPosition = HexGridMapPosition.calculateWorldCoordinateFromHexGrid(currentTile.x(), currentTile.y());
            pathToMove.remove(0);

        }
    }


    @Override
    public void moveToPosition(Vector2 destination, float deltaTime) {
        // TODO deltatime should be moved out and be given as a paramater, to make testing this possible.
        this.clickedPosition = destination;

        if (clickedPosition == null) return;

        // Temp variable until we can make the character go to the center of a tile.
        // Used to snap the character's position to the destination once it's within this threshold.
        float positionMarginOfError = 3.0f;

        if ((Math.abs(currentPosition.x - clickedPosition.x) > positionMarginOfError) ||
            (Math.abs(currentPosition.y - clickedPosition.y) > positionMarginOfError)) {

            moving = true;
            float pathX = clickedPosition.x - currentPosition.x;
            float pathY = clickedPosition.y - currentPosition.y;
            float distanceToMove = (float) Math.sqrt(pathX * pathX + pathY * pathY);
            float directiontoMoveX = pathX / distanceToMove;
            float directiontoMoveY = pathY / distanceToMove;

            // TODO implement a "ramping" or "ease in/out" function so the character accelerates and slows down when moving.
            // Something similar to this.
            // https://frc1756-argos.github.io/ArgoBot-Drive-Training/tutorials/8/
            this.currentPosition.x += directiontoMoveX * deltaTime * this.moveSpeed;
            this.currentPosition.y += directiontoMoveY * deltaTime * this.moveSpeed;
        } else {
            // Is the player within the margin of error?
            // Then snap the player's position to the desired spot.
            currentPosition.x = clickedPosition.x;
            currentPosition.y = clickedPosition.y;
            destinationPosition = null;
            moving = false;
        }
    }

    @Override
    public boolean isMoving() {
        return this.moving;
    }

    public void setDestination(Vector2 destinationPosition){
        this.clickedPosition = destinationPosition;
    }

    @Override
    public Vector2 getDestination() {
        return clickedPosition;
    }
    @Override
    public Vector2 getPosition() {
        return currentPosition;
    }
    @Override
    public void setPosition(Vector2 position) {
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
