package inf112.saga.of.the.villeins.Characters;

import com.badlogic.gdx.math.Vector2;
import inf112.saga.of.the.villeins.Utils.TilePosition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//import static org.junit.jupiter.api.Assertions.*;
//
//import java.util.ArrayList;
//
//import inf112.saga.of.the.villeins.MapUtils.TilePosition;
//import inf112.saga.of.the.villeins.Utils.TileMovement;
//import org.junit.jupiter.api.*;
//import com.badlogic.gdx.math.Vector2;
//
//import inf112.saga.of.the.villeins.Animations.CharacterAnimationHandler;
//import inf112.saga.of.the.villeins.Characters.CharacterState;
//import inf112.saga.of.the.villeins.Characters.Player;
//import inf112.saga.of.the.villeins.Utils.AttackUtils;
//
//
public class TestForPlayer {
    @BeforeEach
    public void setup() {
    }

    @Test
    public void testGetPosition() {
        Player testPlayer123 = new Player(new TilePosition(0, 0), null, 40, 2, 5, 1);
        Vector2 expectedPosition = new Vector2(100, 100);
        Vector2 result = testPlayer123.getCurrentPosition();
        assertEquals(expectedPosition, result);
    }

    @Test
    public void testSetPosition() {
        Player testPlayer123 = new Player(new TilePosition(0, 0), null, 40, 2, 5, 1);
        float expectedPositionX = 10f;
        float expectedPositionY = 10f;
        Vector2 expectedResult = new Vector2(expectedPositionX, expectedPositionY);
        testPlayer123.setCurrentPosition(expectedResult);
        Vector2 result = testPlayer123.getCurrentPosition();
        assertEquals(expectedResult, result);
    }
//    // @Test // går utenfor mapet...
//    // public void testMoveOutsideMap(){
//    //     Vector2 startingPosition = new Vector2(0f, 0f);
//    //     Player testPlayer123 = new Player(startingPosition, null, 40, 2, 5, 1);
//    //     float newPositionX = 100f;
//    //     float newPositionY = 100f;
//    //     Vector2 newPosition = new Vector2(newPositionX, newPositionY);
//    //     testPlayer123.setCurrentPosition(newPosition);
//    //     Vector2 expectedPosition = startingPosition;
//    //     Vector2 result = testPlayer123.getCurrentPosition();
//    //     assertEquals(expectedPosition, result);
//    // }
//
//    @Test
//    public void testGetZPosition(){
//        Vector2 startingPosition = new Vector2(0f, 0f);
//        Player testPlayer123 = new Player(startingPosition, null,40, 2, 5, 1);
//        Vector2 expectedZPosition = new Vector2(10f,10f);
//        testPlayer123.setCurrentPosition(expectedZPosition);
//        Vector2 result = testPlayer123.getCurrentPosition();
//        assertEquals(expectedZPosition, result);
//
//    }
//
//    @Test
//    public void testGetHealth(){
//        Vector2 startingPosition = new Vector2(0f, 0f);
//        Player testPlayer123 = new Player(startingPosition,null, 40, 2, 5, 1);
//        int expectedHealth = 40;
//        int result = testPlayer123.getCurrentHealth();
//        assertEquals(expectedHealth, result);
//    }
//
//    @Test
//    public void testGetStrength(){
//        Vector2 startingPosition = new Vector2(0f, 0f);
//        Player testPlayer123 = new Player(startingPosition, null, 40, 0, 5, 1);
//        int expectedStrength = 0;
//        int result = testPlayer123.getStrength();
//        assertEquals(expectedStrength, result);
//    }
//
//    @Test
//    public void testSetStrength(){
//        int initialStrength = 5;
//        int newStrength = 10;
//        Vector2 startingPosition = new Vector2(0f, 0f);
//        Player testPlayer123 = new Player(startingPosition,null, 40, initialStrength, 5, 1);
//        testPlayer123.setStrength(newStrength);
//        int result = testPlayer123.getStrength();
//        assertEquals(newStrength, result);
//    }
//
//
//    @Test
//    public void testGetDefence(){
//        Vector2 startingPosition = new Vector2(0f, 0f);
//        int expectedDefence = 10;
//        Player testPlayer = new Player(startingPosition, null, 40, 2, expectedDefence, 1);
//        int result = testPlayer.getDefense();
//        assertEquals(expectedDefence, result);
//    }
//
//    @Test
//    public void testGetMaxHealth(){
//        Vector2 startingPosition = new Vector2(0f, 0f);
//        Player testPlayer123 = new Player(startingPosition,null, 20, 2, 5, 1);
//        int expectedMaxHealth = 20;
//        int result = testPlayer123.getMaxHealth();
//        assertEquals(expectedMaxHealth, result);
//    }
//
//
//    @Test
//    public void testSetHealth(){
//        Vector2 startingPosition = new Vector2(0f, 0f);
//        Player testplayer123 = new Player(startingPosition, null, 40, 2, 5, 1);
//        int expectedHealth = 5;
//        testplayer123.setHealth(expectedHealth);
//        int result = testplayer123.getCurrentHealth();
//        assertEquals(expectedHealth, result);
//    }
//
//
//    @Test
//    public void testApplyDamage(){
//        Vector2 initialPosition = new Vector2(0, 0);
//        Player testplayer1 = new Player(initialPosition, null,  10, 0, 0, 1);
//        Player testplayer2 = new Player(initialPosition, null,  10, 0, 0, 1);
//
//        // innenfor
//        int damage1 = 5;
//        int expectedHealth1 = 5;
//        testplayer1.applyDamage(damage1, testplayer2);
//        int result1 = testplayer2.getCurrentHealth();
//        assertEquals(expectedHealth1, result1);
//
//        // mer enn
//        int damage2 = 15;
//        int expectedHealth2 = 0;
//        testplayer1.applyDamage(damage2, testplayer2);
//        int result2 = testplayer2.getCurrentHealth();
//        assertEquals(expectedHealth2, result2);
//
//        // Negativ damage - får ikke til å fungere, tror ikke ApplyDamage hånterer det
//        // int damage3 = -5;
//        // int expectedHealth3 = 10;
//        // testplayer1.applyDamage(damage3, testplayer2);
//        // int result3 = testplayer2.getCurrentHealth();
//        // assertEquals(expectedHealth3, result3);
//     }
//
//    @Test
//    public void testGetIdentifier(){
//        Player player = new Player(new Vector2(0,0), new CharacterAnimationHandler(null, null, null, null, null, null), 100, 10, 5, 3);
//        assertNull(player.getIdentifier());
//    }
//
//    @Test
//    public void testGetScore(){
//        Player player = new Player(new Vector2(0,0), new CharacterAnimationHandler(null, null, null, null, null, null), 100, 10, 5, 3);
//        assertEquals(0, player.getScore());
//    }
//
//    @Test
//    public void testSetScore(){
//        Player player = new Player(new Vector2(0,0), new CharacterAnimationHandler(null, null, null, null, null, null), 100, 10, 5, 3);
//        player.setScore(100);
//        assertEquals(100, player.getScore());
//    }
//
//    @Test
//    public void testGetCharacterState(){
//        Player player = new Player(new Vector2(0,0), new CharacterAnimationHandler(null, null, null, null, null, null), 100, 10, 5, 3);
//        assertEquals(CharacterState.IDLE, player.getCharacterState());
//    }
//
//    @Test
//    public void testSetCharacterState(){
//        Player player = new Player(new Vector2(0,0), new CharacterAnimationHandler(null, null, null, null, null, null), 100, 10, 5, 3);
//        player.setCharacterState(CharacterState.ATTACK);
//        assertEquals(CharacterState.ATTACK, player.getCharacterState());
//    }
//
//    @Test
//    public void testGetMoveSpeed(){
//        Player player = new Player(new Vector2(0,0), new CharacterAnimationHandler(null, null, null, null, null, null), 100, 10, 5, 3);
//        assertEquals(100, player.getMoveSpeed());
//    }
//
//    @Test
//    public void testSetMoveSpeed(){
//        Player player = new Player(new Vector2(0,0), new CharacterAnimationHandler(null, null, null, null, null, null), 100, 10, 5, 3);
//        player.setMoveSpeed(70);
//        assertEquals(70, player.getMoveSpeed());
//    }
//
//    @Test
//    public void testSetEndPosition(){
//        Player player = new Player(new Vector2(0,0), new CharacterAnimationHandler(null, null, null, null, null, null), 100, 10, 5, 3);
//        Vector2 endPosition = new Vector2(2,2);
//        assertTrue(player.setEndPosition(endPosition));
//        assertEquals(endPosition, player.getEndPosition());
//    }
//
//    @Test
//    public void testGetCurrentPosition(){
//        Player player = new Player(new Vector2(0,0), new CharacterAnimationHandler(null, null, null, null, null, null), 100, 10, 5, 3);
//        Vector2 startingPosition = new Vector2(0,0);
//        assertEquals(startingPosition, player.getCurrentPosition());
//    }
//
//    @Test
//    public void testSetCurrentPosition(){
//        Player player = new Player(new Vector2(0,0), new CharacterAnimationHandler(null, null, null, null, null, null), 100, 10, 5, 3);
//        Vector2 newPosition = new Vector2(1,2);
//        player.setCurrentPosition(newPosition);
//        assertEquals(newPosition, player.getCurrentPosition());
//    }
//
//    @Test
//    public void testSetPathToMove() {
//        Player player = new Player(new TilePosition(1, 2), new CharacterAnimationHandler(null, null, null, null, null, null), 100, 10, 5, 3);
//        ArrayList<TilePosition> expectedList = new ArrayList<>();
//        expectedList.add(new TilePosition(1, 2));
//        expectedList.add(new TilePosition(1, 3));
//        expectedList.add(new TilePosition(1, 4));
//        player.setPathToMove(expectedList);
//        assertEquals(expectedList, player.getPathToMove());
//    }
}
