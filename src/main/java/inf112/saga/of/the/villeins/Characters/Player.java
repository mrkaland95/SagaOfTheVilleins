package inf112.saga.of.the.villeins.Characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Player implements ICharacter {
    private float xCurrentPosition;
    private float yCurrentPosition;
    private float moveSpeed = 25.0f;
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

    Vector2 positionToMoveTo;
    private int maxHealth;
    private int health;
    private int strength;
    private int defense;

    // TODO make a new class for loading in all the animations.


    public Player(float startingXPosition, float startingYPosition,
                  Animation2D walkingAnimation,
                  Animation2D idleAnimation,
                  SpriteBatch spriteBatch,
                  int maxHealth,
                  int strength,
                  int defense) {

        this.xCurrentPosition = startingXPosition;
        this.yCurrentPosition = startingYPosition;
//        this.positionToMoveTo = new Vector2(startingXPosition, startingYPosition);

        this.positionToMoveTo = new Vector2(0f, 0f);


        this.spriteBatch = spriteBatch;
        this.walkingPlayer = walkingAnimation;
        this.idleAnimation = idleAnimation;
//        this.currentAnimation = walkingAnimation;
        this.currentAnimation = idleAnimation;

        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.strength = strength;
        this.defense = defense;
    }



    @Override
    public void update() {
        // Method that needs to be called in the render loop, which will draw the correct animation depending on the set current animation state.
        TextureRegion currentImage = currentAnimation.getImageToRender();
        this.spriteBatch.draw(currentImage, this.xCurrentPosition, this.yCurrentPosition);
    }

    @Override
    public void moveToPosition(float xPosition, float yPosition) {
        float deltaTime = Gdx.graphics.getDeltaTime();
        positionToMoveTo.x = xPosition;
        positionToMoveTo.y = yPosition;

        // Temp variable until we can make the character go to the center of a tile.
        float positionMarginOfError = 5.0f;

        if ((Math.abs(positionToMoveTo.x - this.xCurrentPosition) > positionMarginOfError) || (Math.abs(this.yCurrentPosition - positionToMoveTo.y) > positionMarginOfError)) {
//        if ((positionToMoveTo.x != this.xCurrentPosition) || (positionToMoveTo.y != this.yCurrentPosition)) {
            float pathX = positionToMoveTo.x - xCurrentPosition;
            float pathY = positionToMoveTo.y - yCurrentPosition;
            float distanceToMove = (float) Math.sqrt(pathX * pathX + pathY * pathY);
            float directiontoMoveX = pathX / distanceToMove;
            float directiontoMoveY = pathY / distanceToMove;
            this.xCurrentPosition += directiontoMoveX * this.moveSpeed * deltaTime;
            this.yCurrentPosition += directiontoMoveY * this.moveSpeed * deltaTime;
        } else {
            // Is the player within the margin of error?
            // Then snap the player's position to the desired spot.
            xCurrentPosition = positionToMoveTo.x;
            yCurrentPosition = positionToMoveTo.y;
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
    public int getHealth(ICharacter character) {
        return this.health;
    }


    @Override
    public int getStrength(ICharacter character) {
        return this.strength;
    }


    @Override
    public int getDefense(ICharacter character) {
        return this.defense;
    }


    @Override
    public int getMaxHealth(ICharacter character) {
        return this.maxHealth;
    }


    @Override
    public void setHealth(int damageTaken, ICharacter character) {
        this.health = character.getHealth(character)-damageTaken;
        
    }
}
