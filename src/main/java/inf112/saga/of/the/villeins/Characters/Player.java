package inf112.saga.of.the.villeins.Characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import inf112.saga.of.the.villeins.Game.Main;


public class Player implements ICharacter {
    private float xCurrentPosition;
    private float yCurrentPosition;
    Vector2 destinationPosition;
    private String name;
    private int maxHealth;
    private int currentHealth;
    private int strength;
    private int defense;
    private float moveSpeed = Main.globalDefaultMoveSpeed;

    private int score;

    private boolean moving;

    // TODO make a new class for loading in all the animations.

    public Player(float startingXPosition,
                  float startingYPosition,
                  int maxHealth,
                  int strength,
                  int defense) {

        this.xCurrentPosition = startingXPosition;
        this.yCurrentPosition = startingYPosition;
//        this.positionToMoveTo = new Vector2(startingXPosition, startingYPosition);

        this.destinationPosition = new Vector2(startingXPosition, startingYPosition);
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.strength = strength;
        this.defense = defense;
    }


    @Override
    public void update() {
        // Method that needs to be called in the render loop, which will draw the correct animation depending on the set current animation state.
//        TextureRegion currentImage = currentAnimation.getImageToRender();
//        this.spriteBatch.draw(currentImage, this.xCurrentPosition, this.yCurrentPosition);
        if (destinationPosition != null){
            this.moveToPosition(this.destinationPosition.x, destinationPosition.y);
        }
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
            this.moving = true;
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
            this.moving = false;
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
            // Something similar to this.
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
    public boolean isMoving() {
        return this.moving;
    }

    public void setDestination(Vector2 destinationPosition){
        this.destinationPosition = destinationPosition;
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
