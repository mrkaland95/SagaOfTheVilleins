package inf112.saga.of.the.villeins.Characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import inf112.saga.of.the.villeins.Controller.CharacterAnimationController;
import inf112.saga.of.the.villeins.Game.Main;

public class BaseMonster implements ICharacter {
    private Vector2 currentPosition;
    private Vector2 destinationPosition;

    CharacterAnimationController animationController;
    private int currentHealth;
    private int maxHealth;
    private int strength;
    private int defense;
    private final float moveSpeed = Main.globalDefaultMoveSpeed;
    private boolean moving;

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
    }
    @Override
    public void update() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        // Moves the character if it has a destination.
        moveToPosition(destinationPosition, deltaTime);
        // Renders the character's animation the character's position.
        this.animationController.render(this);
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
    public void setDestination(Vector2 destination) {
        this.destinationPosition = destination;
    }

    @Override
    public Vector2 getDestination() {
        return destinationPosition;
    }

    @Override
    public void moveToPosition(Vector2 destination, float deltaTime) {
        // TODO move this into a shared class, that can be injected into all the various characters?
        this.destinationPosition = destination;

        if (destinationPosition == null) return;

        // Temp variable until we can make the character go to the center of a tile.
        // Used to snap the character's position to the destination once it's within this threshold.
        float positionMarginOfError = 3.0f;

        if ((Math.abs(currentPosition.x - destinationPosition.x) > positionMarginOfError) ||
            (Math.abs(currentPosition.y - destinationPosition.y) > positionMarginOfError)) {
            moving = true;
            float pathX = destinationPosition.x - currentPosition.x;
            float pathY = destinationPosition.y - currentPosition.y;
            float distanceToMove = (float) Math.sqrt(pathX * pathX + pathY * pathY);
            float directiontoMoveX = pathX / distanceToMove;
            float directiontoMoveY = pathY / distanceToMove;

            // TODO implement a "ramping" function so the character accelerates and slows down when moving.
            // Something similar to this.
            // https://frc1756-argos.github.io/ArgoBot-Drive-Training/tutorials/8/
            this.currentPosition.x += directiontoMoveX * deltaTime * this.moveSpeed;
            this.currentPosition.y += directiontoMoveY * deltaTime * this.moveSpeed;
        } else {
            // Is the player within the margin of error?
            // Then snap the player's position to the desired spot.
            currentPosition.x = destinationPosition.x;
            currentPosition.y = destinationPosition.y;
            moving = false;
        }
    }

    @Override
    public boolean isMoving() {
        return this.moving;
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
}
