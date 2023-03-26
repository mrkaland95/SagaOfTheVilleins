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
        BaseMonster testMonster = new BaseMonster(startingPosition, null, 40, 2, 5, 1);
        Vector2 expectedPosition = new Vector2(0,0);
        Vector2 result = testMonster.getCurrentPosition();
        assertEquals(expectedPosition, result);
    }
 
    @Test
    public void testSetPosition(){
        Vector2 startingPosition = new Vector2(0f, 0f);
        CharacterAnimationController animationController = null;
        BaseMonster testMonster = new BaseMonster(startingPosition, animationController, 50, 10, 5, 1);
        float expectedPositionX = 10f;
        float expectedPositionY = 10f;
        Vector2 expectedResult = new Vector2(expectedPositionX, expectedPositionY);
        testMonster.setEndPosition(expectedResult);
        Vector2 result = testMonster.getCurrentPosition();
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetDestination(){
        Vector2 startingPosition = new Vector2(0f, 0f);
        CharacterAnimationController animationController = null;
        BaseMonster testMonster = new BaseMonster(startingPosition, animationController, 50, 10, 5, 1);
        Vector2 expectedDestination = new Vector2(10f, 10f);
        testMonster.setEndPosition(expectedDestination);
        Vector2 result = testMonster.getEndPosition();
        assertEquals(expectedDestination, result);
    }

    @Test
    public void testSetDestination(){
        Vector2 startingPosition = new Vector2(0f, 0f);
        CharacterAnimationController animationController = null;
        BaseMonster testMonster = new BaseMonster(startingPosition, animationController, 50, 10, 5, 1);
        Vector2 expectedDestination = new Vector2(10f, 10f);
        testMonster.setEndPosition(expectedDestination);
        Vector2 result = testMonster.getEndPosition();
        assertEquals(expectedDestination, result);
    }

    @Test
    public void testMoveToPosition(){
        Vector2 startingPosition = new Vector2(0f, 0f);
        BaseMonster testMonster = new BaseMonster(startingPosition, null, 0, 0, 0, 1);
        Vector2 destination = new Vector2(10f, 10f);
        float time = 0.06f;
        testMonster.setEndPosition(destination);
        testMonster.setMoveSpeed(time);
       assertFalse(testMonster.getCharacterState());
       assertEquals(destination, testMonster.getEndPosition());
    }

    @Test
    public void testGetCurrentHealth(){
        Vector2 startingPosition = new Vector2(0f, 0f);
        CharacterAnimationController animationController = null;
        BaseMonster testMonster = new BaseMonster(startingPosition, animationController, 50, 10, 5, 1);
        int expectedHealth = 50;
        int result = testMonster.getCurrentHealth();
        assertEquals(expectedHealth, result);
    }

    @Test
    public void testSetStrength(){
        int initialStrength = 5;
        int newStrength = 10;
        Vector2 startingPosition = new Vector2(0f, 0f);
        BaseMonster testMonster = new BaseMonster(startingPosition,null, 40, initialStrength, 5, 1);
        testMonster.setStrength(newStrength);
        int result = testMonster.getStrength();
        assertEquals(newStrength, result);
    }

    @Test
    public void testGetStrength(){
        Vector2 startingPosition = new Vector2(0f, 0f);
        BaseMonster testMonster = new BaseMonster(startingPosition, null, 40, 0, 5, 1);
        int expectedStrength = 0;
        int result = testMonster.getStrength();
        assertEquals(expectedStrength, result);
    }

    @Test
    public void testGetDefence(){
        Vector2 startingPosition = new Vector2(0f, 0f);
        int expectedDefence = 10;
        BaseMonster testMonster = new BaseMonster(startingPosition, null, 40, 2, expectedDefence, 1);
        int result = testMonster.getDefense();
        assertEquals(expectedDefence, result);
    }

    @Test
    public void testGetHealth(){
        Vector2 startingPosition = new Vector2(0f, 0f);
        BaseMonster testMonster = new BaseMonster(startingPosition,null, 40, 2, 5, 1);
        int expectedHealth = 40;
        int result = testMonster.getCurrentHealth();
        assertEquals(expectedHealth, result);
    }

    @Test
    public void testGetMaxHealth(){
        Vector2 startingPosition = new Vector2(0f, 0f);
        BaseMonster testMonster = new BaseMonster(startingPosition,null, 20, 2, 5, 1);
        int expectedMaxHealth = 20;
        int result = testMonster.getMaxHealth();
        assertEquals(expectedMaxHealth, result);
    }

    @Test
    public void testSetHealth(){
        Vector2 startingPosition = new Vector2(0f, 0f);
        BaseMonster testMonster = new BaseMonster(startingPosition, null, 40, 2, 5, 1);
        int expectedHealth = 5;
        testMonster.setHealth(expectedHealth);
        int result = testMonster.getCurrentHealth();
        assertEquals(expectedHealth, result);
    }

    @Test
    public void testApplyDamage() {
    Vector2 initialPosition = new Vector2(0, 0);
    BaseMonster testMonster = new BaseMonster(initialPosition, null, 10, 0, 0, 1);
    Player testPlayer = new Player(initialPosition, null, 10, 0, 0);
    int expectedHealth = 5;
    int damage = 5;
    testMonster.applyDamage(damage, testPlayer);
    int result = testPlayer.getCurrentHealth();
    assertEquals(expectedHealth, result);
    }


}