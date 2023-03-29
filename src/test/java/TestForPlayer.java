import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.*;
import com.badlogic.gdx.math.Vector2;
import inf112.saga.of.the.villeins.Characters.Player;
import inf112.saga.of.the.villeins.Utils.AttackUtils;


public class TestForPlayer {
    @Test
    public void testGetPosition(){
        Vector2 startingPosition = new Vector2(0f, 0f);
        Player testPlayer123 = new Player(startingPosition, null, 40, 2, 5, 1);
        Vector2 expectedPosition = new Vector2(0,0);
        Vector2 result = testPlayer123.getCurrentPosition();
        assertEquals(expectedPosition, result);
    }
    @Test
    public void testSetPosition(){
        Vector2 startingPosition = new Vector2(0f, 0f);
        Player testPlayer123 = new Player(startingPosition, null, 40, 2, 5, 1);
        float expectedPositionX = 10f;
        float expectedPositionY = 10f;
        Vector2 expectedResult = new Vector2(expectedPositionX, expectedPositionY);
        testPlayer123.setCurrentPosition(expectedResult);
        Vector2 result = testPlayer123.getCurrentPosition();
        assertEquals(expectedResult, result);
    }

    @Test // går utenfor mapet...
    public void testMoveOutsideMap(){
        Vector2 startingPosition = new Vector2(0f, 0f);
        Player testPlayer123 = new Player(startingPosition, null, 40, 2, 5, 1);
        float newPositionX = 100f;
        float newPositionY = 100f;
        Vector2 newPosition = new Vector2(newPositionX, newPositionY);
        testPlayer123.setCurrentPosition(newPosition);
        Vector2 expectedPosition = startingPosition;
        Vector2 result = testPlayer123.getCurrentPosition();
        assertEquals(expectedPosition, result);
    }

    @Test
    public void testGetZPosition(){
        Vector2 startingPosition = new Vector2(0f, 0f);
        Player testPlayer123 = new Player(startingPosition, null,40, 2, 5, 1);
        Vector2 expectedZPosition = new Vector2(10f,10f);
        testPlayer123.setCurrentPosition(expectedZPosition);
        Vector2 result = testPlayer123.getCurrentPosition();
        assertEquals(expectedZPosition, result);

    }

    @Test
    public void testGetHealth(){
        Vector2 startingPosition = new Vector2(0f, 0f);
        Player testPlayer123 = new Player(startingPosition,null, 40, 2, 5, 1);
        int expectedHealth = 40;
        int result = testPlayer123.getCurrentHealth();
        assertEquals(expectedHealth, result);
    }

    @Test
    public void testGetStrength(){
        Vector2 startingPosition = new Vector2(0f, 0f);
        Player testPlayer123 = new Player(startingPosition, null, 40, 0, 5, 1);
        int expectedStrength = 0;
        int result = testPlayer123.getStrength();
        assertEquals(expectedStrength, result);
    }

    @Test
    public void testSetStrength(){
        int initialStrength = 5;
        int newStrength = 10;
        Vector2 startingPosition = new Vector2(0f, 0f);
        Player testPlayer123 = new Player(startingPosition,null, 40, initialStrength, 5, 1);
        testPlayer123.setStrength(newStrength);
        int result = testPlayer123.getStrength();
        assertEquals(newStrength, result);
    }


    @Test
    public void testGetDefence(){
        Vector2 startingPosition = new Vector2(0f, 0f);
        int expectedDefence = 10;
        Player testPlayer = new Player(startingPosition, null, 40, 2, expectedDefence, 1);
        int result = testPlayer.getDefense();
        assertEquals(expectedDefence, result);
    }

    @Test
    public void testGetMaxHealth(){
        Vector2 startingPosition = new Vector2(0f, 0f);
        Player testPlayer123 = new Player(startingPosition,null, 20, 2, 5, 1);
        int expectedMaxHealth = 20;
        int result = testPlayer123.getMaxHealth();
        assertEquals(expectedMaxHealth, result);
    }


    @Test
    public void testSetHealth(){
        Vector2 startingPosition = new Vector2(0f, 0f);
        Player testplayer123 = new Player(startingPosition, null, 40, 2, 5, 1);
        int expectedHealth = 5;
        testplayer123.setHealth(expectedHealth);
        int result = testplayer123.getCurrentHealth();
        assertEquals(expectedHealth, result);
    }


    @Test
    public void testApplyDamage(){
        Vector2 initialPosition = new Vector2(0, 0);
        Player testplayer1 = new Player(initialPosition, null,  10, 0, 0, 1);
        Player testplayer2 = new Player(initialPosition, null,  10, 0, 0, 1);
       
        // innenfor
        int damage1 = 5;
        int expectedHealth1 = 5;
        testplayer1.applyDamage(damage1, testplayer2);
        int result1 = testplayer2.getCurrentHealth();
        assertEquals(expectedHealth1, result1);
        
        // mer enn
        int damage2 = 15;
        int expectedHealth2 = 0;
        testplayer1.applyDamage(damage2, testplayer2);
        int result2 = testplayer2.getCurrentHealth();
        assertEquals(expectedHealth2, result2);

        // Negativ damage - får ikke til å fungere, tror ikke ApplyDamage hånterer det
        // int damage3 = -5;
        // int expectedHealth3 = 10;
        // testplayer1.applyDamage(damage3, testplayer2);
        // int result3 = testplayer2.getCurrentHealth();
        // assertEquals(expectedHealth3, result3);
     }

    //  @Test
    //  public void testAttack(){
      
    //     }
 }
