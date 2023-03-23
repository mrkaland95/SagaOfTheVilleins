import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;
import com.badlogic.gdx.math.Vector2;
import inf112.saga.of.the.villeins.Characters.BaseMonster;
import inf112.saga.of.the.villeins.Characters.Player;
import inf112.saga.of.the.villeins.Controller.CharacterAnimationController;

public class TestForBasemonster {
   
    @Test
    public void testGetPosition(){
        Vector2 startingPosition = new Vector2(0f, 0f);
        BaseMonster testMonster = new BaseMonster(startingPosition, null, 40, 2, 5);
        Vector2 expectedPosition = new Vector2(0,0);
        Vector2 result = testMonster.getPosition();
        assertEquals(expectedPosition, result);
    }
 
    @Test
    public void testSetPosition(){
        Vector2 startingPosition = new Vector2(0f, 0f);
        CharacterAnimationController animationController = null;
        BaseMonster testMonster = new BaseMonster(startingPosition, animationController, 50, 10, 5);
        float expectedPositionX = 10f;
        float expectedPositionY = 10f;
        Vector2 expectedResult = new Vector2(expectedPositionX, expectedPositionY);
        testMonster.setPosition(expectedResult);
        Vector2 result = testMonster.getPosition(); 
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetDestination(){
        Vector2 startingPosition = new Vector2(0f, 0f);
        CharacterAnimationController animationController = null;
        BaseMonster testMonster = new BaseMonster(startingPosition, animationController, 50, 10, 5);
        Vector2 expectedDestination = new Vector2(10f, 10f);
        testMonster.setDestination(expectedDestination);
        Vector2 result = testMonster.getDestination();
        assertEquals(expectedDestination, result);
    }

    @Test
    public void testSetDestination(){
        Vector2 startingPosition = new Vector2(0f, 0f);
        CharacterAnimationController animationController = null;
        BaseMonster testMonster = new BaseMonster(startingPosition, animationController, 50, 10, 5);
        Vector2 expectedDestination = new Vector2(10f, 10f);
        testMonster.setDestination(expectedDestination);
        Vector2 result = testMonster.getDestination();
        assertEquals(expectedDestination, result);
    }

    @Test
    public void testMoveToPosition(){
        Vector2 startingPosition = new Vector2(0f, 0f);
        BaseMonster testMonster = new BaseMonster(startingPosition, null, 0, 0, 0);
        Vector2 destination = new Vector2(10f, 10f);
        testMonster.setDestination(destination);

        float time = 0.06f;
        long timeElapsed = 0;
        while (timeElapsed < 1000) { 
        testMonster.moveToPosition(destination, time); 
        timeElapsed += 100; 
        }

        assertFalse(testMonster.isMoving());
        while (timeElapsed < 2000) {
        testMonster.moveToPosition(destination, time);
        timeElapsed += 100;

        assertFalse(testMonster.isMoving());
        assertEquals(destination, testMonster.getPosition());
        }
    }

    @Test
    public void testGetCurrentHealth(){
        Vector2 startingPosition = new Vector2(0f, 0f);
        CharacterAnimationController animationController = null;
        BaseMonster testMonster = new BaseMonster(startingPosition, animationController, 50, 10, 5);
        int expectedHealth = 50;
        int result = testMonster.getCurrentHealth();
        assertEquals(expectedHealth, result);
    }

    @Test
    public void testSetStrength(){
        int initialStrength = 5;
        int newStrength = 10;
        Vector2 startingPosition = new Vector2(0f, 0f);
        BaseMonster testMonster = new BaseMonster(startingPosition,null, 40, initialStrength, 5);
        testMonster.setStrength(newStrength);
        int result = testMonster.getStrength();
        assertEquals(newStrength, result);
    }

    @Test
    public void testGetStrength(){
        Vector2 startingPosition = new Vector2(0f, 0f);
        BaseMonster testMonster = new BaseMonster(startingPosition, null, 40, 0, 5);
        int expectedStrength = 0;
        int result = testMonster.getStrength();
        assertEquals(expectedStrength, result);
    }

    @Test
    public void testGetDefence(){
        Vector2 startingPosition = new Vector2(0f, 0f);
        int expectedDefence = 10;
        BaseMonster testMonster = new BaseMonster(startingPosition, null, 40, 2, expectedDefence);
        int result = testMonster.getDefense();
        assertEquals(expectedDefence, result);
    }

    @Test
    public void testGetHealth(){
        Vector2 startingPosition = new Vector2(0f, 0f);
        BaseMonster testMonster = new BaseMonster(startingPosition,null, 40, 2, 5);
        int expectedHealth = 40;
        int result = testMonster.getCurrentHealth();
        assertEquals(expectedHealth, result);
    }

    @Test
    public void testGetMaxHealth(){
        Vector2 startingPosition = new Vector2(0f, 0f);
        BaseMonster testMonster = new BaseMonster(startingPosition,null, 20, 2, 5);
        int expectedMaxHealth = 20;
        int result = testMonster.getMaxHealth();
        assertEquals(expectedMaxHealth, result);
    }

    @Test
    public void testSetHealth(){
        Vector2 startingPosition = new Vector2(0f, 0f);
        BaseMonster testMonster = new BaseMonster(startingPosition, null, 40, 2, 5);
        int expectedHealth = 5;
        testMonster.setHealth(expectedHealth);
        int result = testMonster.getCurrentHealth();
        assertEquals(expectedHealth, result);
    }

    @Test
    public void testApplyDamage() {
    Vector2 initialPosition = new Vector2(0, 0);
    BaseMonster testMonster = new BaseMonster(initialPosition, null, 10, 0, 0);
    Player testPlayer = new Player(initialPosition, null, 10, 0, 0);
    int expectedHealth = 5;
    int damage = 5;
    testMonster.applyDamage(damage, testPlayer);
    int result = testPlayer.getCurrentHealth();
    assertEquals(expectedHealth, result);
    }


}