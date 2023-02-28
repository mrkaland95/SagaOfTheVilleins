import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import com.badlogic.gdx.math.Vector2;
import inf112.saga.of.the.villeins.Characters.Player;


public class TestForPlayer {
    @Test
    public void testGetPosition(){
        Vector2 startingPosition = new Vector2(0f, 0f);
        Player testPlayer123 = new Player(startingPosition, null, 40, 2, 5);
        Vector2 expectedPosition = new Vector2(0,0);
        Vector2 result = testPlayer123.getPosition();
        assertEquals(expectedPosition, result);
    }
    @Test
    public void testSetPosition(){
        Vector2 startingPosition = new Vector2(0f, 0f);
        Player testPlayer123 = new Player(startingPosition, null, 40, 2, 5);
        float expectedPositionX = 10f;
        float expectedPositionY = 10f;
        Vector2 expectedResult = new Vector2(expectedPositionX, expectedPositionY);
        testPlayer123.setPosition(expectedResult);
        Vector2 result = testPlayer123.getPosition(); 
        assertEquals(expectedResult, result);
    }


    @Test
    public void testGetZPosition(){
        Vector2 startingPosition = new Vector2(0f, 0f);
        Player testPlayer123 = new Player(startingPosition, null,40, 2, 5);
        Vector2 expectedZPosition = new Vector2(10f,10f);
        testPlayer123.setPosition(expectedZPosition);
        Vector2 result = testPlayer123.getPosition();
        assertEquals(expectedZPosition, result);

    }

    @Test
    public void testGetHealth(){
        Vector2 startingPosition = new Vector2(0f, 0f);
        Player testPlayer123 = new Player(startingPosition,null, 40, 2, 5);
        int expectedHealth = 40;
        int result = testPlayer123.getCurrentHealth();
        assertEquals(expectedHealth, result);
    }

    @Test
    public void testGetStrength(){
        Vector2 startingPosition = new Vector2(0f, 0f);
        Player testPlayer123 = new Player(startingPosition, null, 40, 0, 5);
        int expectedStrength = 0;
        int result = testPlayer123.getStrength();
        assertEquals(expectedStrength, result);
    }

    @Test
    public void testSetStrength(){
        int initialStrength = 5;
        int newStrength = 10;
        Vector2 startingPosition = new Vector2(0f, 0f);
        Player testPlayer123 = new Player(startingPosition,null, 40, initialStrength, 5);
        testPlayer123.setStrength(newStrength);
        int result = testPlayer123.getStrength();
        assertEquals(newStrength, result);
    }


    @Test
    public void testGetDefence(){
        Vector2 startingPosition = new Vector2(0f, 0f);
        int expectedDefence = 10;
        Player testPlayer = new Player(startingPosition, null, 40, 2, expectedDefence);
        int result = testPlayer.getDefense();
        assertEquals(expectedDefence, result);
    }

    @Test
    public void testGetMaxHealth(){
        Vector2 startingPosition = new Vector2(0f, 0f);
        Player testPlayer123 = new Player(startingPosition,null, 20, 2, 5);
        int expectedMaxHealth = 20;
        int result = testPlayer123.getMaxHealth();
        assertEquals(expectedMaxHealth, result);
    }


    @Test
    public void testSetHealth(){
        Vector2 startingPosition = new Vector2(0f, 0f);
        Player testplayer123 = new Player(startingPosition, null, 40, 2, 5);
        int expectedHealth = 5;
        testplayer123.setHealth(expectedHealth);
        int result = testplayer123.getCurrentHealth();
        assertEquals(expectedHealth, result);
    }


    @Test
    public void testApplyDamage(){
        Vector2 initialPosition = new Vector2(0, 0);
        Player testplayer1 = new Player(initialPosition, null,  10, 0, 0);
        Player testplayer2 = new Player(initialPosition, null,  10, 0, 0);
        int expectedHealth = 5;
        int damage = 5;
        testplayer1.applyDamage(damage, testplayer2);
        int result = testplayer2.getCurrentHealth();
        assertEquals(expectedHealth, result);
    }
    

}
