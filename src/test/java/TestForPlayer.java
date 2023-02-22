import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import com.badlogic.gdx.math.Vector2;
import inf112.saga.of.the.villeins.Characters.Player;


public class TestForPlayer {
    @Test
    public void testGetPosition(){
        Player testPlayer123 = new Player(0,0, null, null, null, 40, 2, 5);
        Vector2 expectedPosition = new Vector2(0,0);
        Vector2 result = testPlayer123.getPosition();
        assertEquals(expectedPosition, result);

    }

    @Test
    public void testGetXPosition(){
        Player testPlayer123 = new Player(0,0, null, null, null, 40, 2, 5);
        float expectedXPosition = 0f;
        float result = testPlayer123.getxCurrentPosition();
        assertEquals(expectedXPosition, result);

    }

    @Test
    public void testGetYPosition(){
        Player testPlayer123 = new Player(0,0, null, null, null, 40, 2, 5);
        float expectedYPosition = 0f;
        float result = testPlayer123.getyCurrentPosition();
        assertEquals(expectedYPosition, result);

    }

    @Test
    public void testMoveX(){
        Player testPlayer123 = new Player(0,0, null, null, null, 40, 2, 5);
        float expectedPosition = 10f;
        testPlayer123.moveXAxis(expectedPosition);
        float result = testPlayer123.getxCurrentPosition();
        assertEquals(expectedPosition, result);

    }

    @Test
    public void testMoveY(){
        Player testPlayer123 = new Player(0,0, null, null, null, 40, 2, 5);
        float expectedPosition = 10f;
        testPlayer123.moveYAxis(expectedPosition);
        float result = testPlayer123.getyCurrentPosition();
        assertEquals(expectedPosition, result);
    }

/* 
    @Test
    public void testSetPosition(){
        Player testPlayer123 = new Player(0, 0, null, null, null, 0, 0, 0);
        float expectedPositionX = 10f;
        float expectedPositionY = 10f;
        testPlayer123.setPosition(expectedPositionX, expectedPositionY);
        float resultX = testPlayer123.setPositionX(); // hvordan løse dette? set.position ønsker både x og y argument.
        float resultY = testPlayer123.setPositionY();
        assertEquals(expectedPositionX, resultX);
        assertEquals(expectedPositionY, resultY);
    }
    
   // Bytte til Vector2?
    
    @Test
    public void testSetPosition(){
        Player testPlayer123 = new Player(0, 0, null, null, null, 0, 0, 0);
        Vector2 expectedPosition = new Vector2(10f, 10f);
        testPlayer123.setPosition(expectedPosition);
        Vector2 result = testPlayer123.setPosition(); 
        assertEquals(expectedPosition, result);
    }
    */

    @Test
    public void testGetZPosition(){
        Player testPlayer123 = new Player(0,0, null, null, null, 40, 2, 5);
        Vector2 expectedZPosition = new Vector2(10f,10f);
        testPlayer123.setPosition(10f,10f);
        Vector2 result = testPlayer123.getPosition();
        assertEquals(expectedZPosition, result);

    }

    @Test
    public void testGetHealth(){
        Player testPlayer123 = new Player(0,0, null, null, null, 40, 2, 5);
        int expectedHealth = 40;
        int result = testPlayer123.getHealth(); 
        assertEquals(expectedHealth, result);
    }

    @Test
    public void testGetStrength(){
        Player testPlayer123 = new Player(0, 0, null, null, null, 0, 0, 0);
        int expectedStrength = 0;
        int result = testPlayer123.getStrength();
        assertEquals(expectedStrength, result);
    }
/*
    @Test
    public void testSetStrength(){
        int initialStrength = 5;
        int newStrength = 10;
        Player testPlayer = new Player(0,0,null, null, null, 0, initialStrength, 0);
        testPlayer.setStrength(newStrength);
        int result = testPlayer.getStrength();
        assertEquals(newStrength, result);
    }

    */

    @Test
    public void testGetDefence(){
        Player testPlayer = new Player(0, 0, null, null, null, 0, 0, 10);
        int expectedDefence = 10;
        int result = testPlayer.getDefense();
        assertEquals(expectedDefence, result);
    }

    @Test
    public void testGetMaxHealth(){
        Player testPlayer123 = new Player(0, 0, null, null, null, 20, 0, 0);
        int expectedMaxHealth = 20;
        int result = testPlayer123.getMaxHealth();
        assertEquals(expectedMaxHealth, result);
    }

    /*
    @Test
    public void testSetHealth(){
        
    }
     */

}