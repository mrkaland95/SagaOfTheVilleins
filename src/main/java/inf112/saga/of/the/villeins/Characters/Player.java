package inf112.saga.of.the.villeins.Characters;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Player implements ICharacter {
    private float xPosition;
    private float yPosition;
    private float moveSpeed = 5.0f;
    private SpriteBatch spriteBatch;
    Animation2D walkingPlayer;

    // Temp variables until those animations are made.
    Animation2D idleAnimation;

    Animation2D attackingAnimation;

    // The current animation that should be rendered, depending on which state the character is in. For example, moving, idle, attacking etc.
    Animation2D currentAnimation;


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

        this.xPosition = startingXPosition;
        this.yPosition = startingYPosition;
        this.positionToMoveTo = new Vector2(startingXPosition, startingYPosition);
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


    /**
     * Method that draws the current
     *
     *
     */
    @Override
    public void drawSpriteAnimation() {

        // Method that needs to be called in the render loop, which will draw the correct animation depending on the set current animation state.
        TextureRegion currentImage = currentAnimation.getImageToRender();
        this.spriteBatch.draw(currentImage, this.xPosition, this.yPosition);
    }

    @Override
    public void moveToPosition(float xPosition, float yPosition) {


    }

    @Override
    public Vector2 getPosition() {
        return new Vector2(this.xPosition, this.yPosition);
    }
    @Override
    public float getxPosition() {
        return xPosition;
    }
    @Override
    public float getyPosition() {
        return yPosition;
    }

    @Override
    public void moveXAxis(float distance) {
        xPosition += distance;
    }

    @Override
    public void moveYAxis(float distance) {
        yPosition += distance;
    }

    public void setPosition(float xPosition, float yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
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
