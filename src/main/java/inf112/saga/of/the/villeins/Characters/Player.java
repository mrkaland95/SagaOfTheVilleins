package inf112.saga.of.the.villeins.Characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import inf112.saga.of.the.villeins.Main;


public class Player implements ICharacter {
    private float xCurrentPosition;
    private float yCurrentPosition;
    // Temporary value from global for testing purposes.
    private float moveSpeed = Main.globalDefaultMoveSpeed;
    private SpriteBatch spriteBatch;
    Animation2D walkingPlayer;

    // Temp variables until those animations are made.
    Animation2D idleAnimation;

    // Currently no attacking animation has been made
    // TODO make an attacking animation for the player character(s)
    Animation2D attackingAnimation;

    // The current animation that should be rendered, depending on which state the character is in. For example, moving, idle, attacking etc.
    Animation2D currentAnimation;

    boolean shouldMove = true;

    Vector2 destinationPosition;
    private int maxHealth;
    private int currentHealth;
    private int strength;
    private int defense;

    // TODO make a new class for loading in all the animations.

    public Player(float startingXPosition,
                  float startingYPosition,
                  Animation2D walkingAnimation,
                  Animation2D idleAnimation,
                  SpriteBatch spriteBatch,
                  int maxHealth,
                  int strength,
                  int defense) {

        this.xCurrentPosition = startingXPosition;
        this.yCurrentPosition = startingYPosition;
//        this.positionToMoveTo = new Vector2(startingXPosition, startingYPosition);

        this.destinationPosition = new Vector2(startingXPosition, startingYPosition);
        this.spriteBatch = spriteBatch;
        this.walkingPlayer = walkingAnimation;
        this.idleAnimation = idleAnimation;
//        this.currentAnimation = walkingAnimation;
        this.currentAnimation = idleAnimation;

        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.strength = strength;
        this.defense = defense;
    }


    @Override
    public void update() {
        // Method that needs to be called in the render loop, which will draw the correct animation depending on the set current animation state.
        TextureRegion currentImage = currentAnimation.getImageToRender();
        this.spriteBatch.draw(currentImage, this.xCurrentPosition, this.yCurrentPosition);
//        this.moveToPosition(this.positionToMoveTo.x, positionToMoveTo.y);
    }

    @Override
    public void moveToPosition(float xPosition, float yPosition) {
        float deltaTime = Gdx.graphics.getDeltaTime();

        destinationPosition.x = xPosition;
        destinationPosition.y = yPosition;

        // Temp variable until we can make the character go to the center of a tile.
        // Used to snap the character's position to the destination once it's within this threshold.
        float positionMarginOfError = 5.0f;

        if ((Math.abs(destinationPosition.x - this.xCurrentPosition) > positionMarginOfError) || (Math.abs(this.yCurrentPosition - destinationPosition.y) > positionMarginOfError)) {
            float pathX = destinationPosition.x - xCurrentPosition;
            float pathY = destinationPosition.y - yCurrentPosition;
            float distanceToMove = (float) Math.sqrt(pathX * pathX + pathY * pathY);
            float directiontoMoveX = pathX / distanceToMove;
            float directiontoMoveY = pathY / distanceToMove;

            // TODO implement a "ramping" function so the character accelerates and slows down when moving.
            // https://frc1756-argos.github.io/ArgoBot-Drive-Training/tutorials/8/
            this.xCurrentPosition += directiontoMoveX * deltaTime * this.moveSpeed;
            this.yCurrentPosition += directiontoMoveY * deltaTime * this.moveSpeed;
        } else {
            // Is the player within the margin of error?
            // Then snap the player's position to the desired spot.
            xCurrentPosition = destinationPosition.x;
            yCurrentPosition = destinationPosition.y;
        }
    }

    @Override
    public void moveToPosition(Vector2 Destination) {
        float deltaTime = Gdx.graphics.getDeltaTime();
        this.destinationPosition = Destination;

        if (destinationPosition == null) {
            return;
        }

    // Temp variable until we can make the character go to the center of a tile.
    // Used to snap the character's position to the destination once it's within this threshold.
        float positionMarginOfError = 5.0f;

        if ((Math.abs(destinationPosition.x - this.xCurrentPosition) > positionMarginOfError) || (Math.abs(this.yCurrentPosition - destinationPosition.y) > positionMarginOfError)) {
            float pathX = destinationPosition.x - xCurrentPosition;
            float pathY = destinationPosition.y - yCurrentPosition;
            float distanceToMove = (float) Math.sqrt(pathX * pathX + pathY * pathY);
            float directiontoMoveX = pathX / distanceToMove;
            float directiontoMoveY = pathY / distanceToMove;

            // TODO implement a "ramping" function so the character accelerates and slows down when moving.
            // https://frc1756-argos.github.io/ArgoBot-Drive-Training/tutorials/8/
            this.xCurrentPosition += directiontoMoveX * deltaTime * this.moveSpeed;
            this.yCurrentPosition += directiontoMoveY * deltaTime * this.moveSpeed;
        } else {
            // Is the player within the margin of error?
            // Then snap the player's position to the desired spot.
            xCurrentPosition = destinationPosition.x;
            yCurrentPosition = destinationPosition.y;
        }
    }



    @Override
    public Vector2 getPosition() {
        return new Vector2(this.xCurrentPosition, this.yCurrentPosition);
    }
    @Override
    public float getxCurrentPosition() {
        return xCurrentPosition;
    }
    @Override
    public float getyCurrentPosition() {
        return yCurrentPosition;
    }

    @Override
    public void moveXAxis(float distance) {
        xCurrentPosition += distance;
    }

    @Override
    public void moveYAxis(float distance) {
        yCurrentPosition += distance;
    }

    public void setPosition(float xPosition, float yPosition) {
        this.xCurrentPosition = xPosition;
        this.yCurrentPosition = yPosition;
    }

    @Override
    public int getHealth() {
        return this.currentHealth;
    }


    @Override
    public int getStrength() {
        return this.strength;
    }

    @Override
    public void setStrength(int strength) {
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
    public void setHealth(int damageTaken, ICharacter character) {
        this.currentHealth = character.getHealth() - damageTaken;
    }
}
