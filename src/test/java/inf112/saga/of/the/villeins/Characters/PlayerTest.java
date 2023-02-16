package inf112.saga.of.the.villeins.Characters;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest extends Player {

    public PlayerTest(float startingXPosition, float startingYPosition, Animation2D walkingAnimation, Animation2D idleAnimation, SpriteBatch spriteBatch, int maxHealth, int strength, int defense) {
        super(startingXPosition, startingYPosition, walkingAnimation, idleAnimation, spriteBatch, maxHealth, strength, defense);
    }

    @Test
    void testGetHealth() {
    }

    @Test
    void testGetStrength() {
    }

    @Test
    void testGetDefense() {
    }

    @Test
    void testGetMaxHealth() {
    }

    @Test
    void testSetHealth() {
    }
}