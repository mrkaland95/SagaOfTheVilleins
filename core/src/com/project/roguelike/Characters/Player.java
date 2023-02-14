package com.project.roguelike.Characters;

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

    // TODO make a new class for loading in all the animations.


    public Player(float startingXPosition, float startingYPosition,
                  Animation2D walkingAnimation,
                  Animation2D idleAnimation,
                  SpriteBatch spriteBatch) {

        this.xPosition = startingXPosition;
        this.yPosition = startingYPosition;
        this.positionToMoveTo = new Vector2(startingXPosition, startingYPosition);
        this.spriteBatch = spriteBatch;
        this.walkingPlayer = walkingAnimation;
        this.idleAnimation = idleAnimation;
//        this.currentAnimation = walkingAnimation;
        this.currentAnimation = idleAnimation;
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


}
