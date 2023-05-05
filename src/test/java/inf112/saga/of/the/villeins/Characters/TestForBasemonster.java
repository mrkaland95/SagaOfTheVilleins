package inf112.saga.of.the.villeins.Characters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import com.badlogic.gdx.math.Vector2;
import inf112.saga.of.the.villeins.Utils.TilePosition;



public class TestForBasemonster {



   @Test
   public void testGetPosition(){
       TilePosition startingPosition = new TilePosition(0, 0);
       BaseMonster testMonster = new BaseMonster(startingPosition, null, 40, 2, 5, 1, "");
       Vector2 expectedPosition = new Vector2(100, 100);
       Vector2 result = testMonster.getCurrentPosition();
       assertEquals(expectedPosition, result);
   }

   @Test
   public void testSetCurrentPosition(){
       TilePosition startingPosition = new TilePosition(0,0);
       BaseMonster testMonster = new BaseMonster(startingPosition, null, 40, 2, 5, 1, "");
       float expectedPositionX = 300f;
       float expectedPositionY = 100f;
       Vector2 expectedResult = new Vector2(expectedPositionX, expectedPositionY);
       testMonster.setCurrentPosition(expectedResult);
       Vector2 result = testMonster.getCurrentPosition();
       assertEquals(expectedResult, result);
   }

   @Test
   public void testGetDestination(){
       TilePosition startingPosition = new TilePosition(0,0);
       BaseMonster testMonster = new BaseMonster(startingPosition, null, 40, 2, 5, 1, "");
       Vector2 expectedDestination = new Vector2(10f, 10f);
       testMonster.setEndPosition(expectedDestination);
       Vector2 result = testMonster.getEndPosition();
       assertEquals(expectedDestination, result);
   }

   @Test
   public void testSetDestination(){
       TilePosition startingPosition = new TilePosition(0,0);
       BaseMonster testMonster = new BaseMonster(startingPosition, null, 40, 2, 5, 1, "");
       Vector2 expectedDestination = new Vector2(10f, 10f);
       testMonster.setEndPosition(expectedDestination);
       Vector2 result = testMonster.getEndPosition();
       assertEquals(expectedDestination, result);
   }

   @Test
   public void testMoveToPosition(){
      TilePosition startingPosition = new TilePosition(0,0);
      BaseMonster testMonster = new BaseMonster(startingPosition, null, 40, 2, 5, 1, "");
       Vector2 destination = new Vector2(10f, 10f);
       float time = 0.06f;
       testMonster.setEndPosition(destination);
       testMonster.setMoveSpeed(time);
//       assertFalse(testMonster.getCharacterState());
      assertEquals(destination, testMonster.getEndPosition());
   }

   @Test
   public void testGetCurrentHealth(){
       TilePosition startingPosition = new TilePosition(0,0);
       BaseMonster testMonster = new BaseMonster(startingPosition, null, 40, 2, 5, 1, "");
       int expectedHealth = 40;
       int result = testMonster.getCurrentHealth();
       assertEquals(expectedHealth, result);
   }

   @Test
   public void testSetStrength(){
       int initialStrength = 5;
       int newStrength = 10;
       TilePosition startingPosition = new TilePosition(0,0);
       BaseMonster testMonster = new BaseMonster(startingPosition, null, 40, initialStrength, 5, 1, "");
       testMonster.setStrength(newStrength);
       int result = testMonster.getStrength();
       assertEquals(newStrength, result);
   }

   @Test
   public void testGetStrength(){
    TilePosition startingPosition = new TilePosition(0,0);
    BaseMonster testMonster = new BaseMonster(startingPosition, null, 40, 2, 5, 1, "");
       int expectedStrength = 2;
       int result = testMonster.getStrength();
       assertEquals(expectedStrength, result);
   }

   @Test
   public void testGetDefence(){
       int expectedDefence = 10;
       TilePosition startingPosition = new TilePosition(0,0);
       BaseMonster testMonster = new BaseMonster(startingPosition, null, 40, 2, 10, 1, "");
       int result = testMonster.getDefense();
       assertEquals(expectedDefence, result);
   }

   @Test
   public void testGetHealth(){
       TilePosition startingPosition = new TilePosition(0,0);
       BaseMonster testMonster = new BaseMonster(startingPosition, null, 40, 2, 5, 1, "");
       int expectedHealth = 40;
       int result = testMonster.getCurrentHealth();
       assertEquals(expectedHealth, result);
   }

   @Test
   public void testGetMaxHealth(){
       TilePosition startingPosition = new TilePosition(0,0);
       BaseMonster testMonster = new BaseMonster(startingPosition, null, 40, 2, 5, 1, "");
       int expectedMaxHealth = 40;
       int result = testMonster.getMaxHealth();
       assertEquals(expectedMaxHealth, result);
   }

   @Test
   public void testSetHealth(){
       TilePosition startingPosition = new TilePosition(0,0);
       BaseMonster testMonster = new BaseMonster(startingPosition, null, 40, 2, 5, 1, "");
       int expectedHealth = 5;
       testMonster.setHealth(expectedHealth);
       int result = testMonster.getCurrentHealth();
       assertEquals(expectedHealth, result);
   }

   @Test
   public void testApplyDamage() {
        TilePosition startingPosition = new TilePosition(0,0);
        BaseMonster testMonster = new BaseMonster(startingPosition, null, 40, 2, 5, 1, "");
        BaseCharacter testPlayer = new Player(startingPosition, null, 10, 0, 0, 1);
        int expectedHealth = 5;
        int damage = 5;
        testMonster.applyDamage(damage, testPlayer);
        int result = testPlayer.getCurrentHealth();
        assertEquals(expectedHealth, result);
   }

   @Test
   public void testSetAndGetEndPosition(){
       TilePosition startingPosition = new TilePosition(0,0);
       BaseMonster testMonster = new BaseMonster(startingPosition, null, 40, 2, 5, 1, "");
       Vector2 position = new Vector2(5,6);
       assertTrue(testMonster.setEndPosition(position));
       assertEquals(position, testMonster.getEndPosition());
   }

   @Test
   public void getCurrentPosition(){
       TilePosition startingPosition = new TilePosition(0,0);
       BaseMonster testMonster = new BaseMonster(startingPosition, null, 40, 2, 5, 1, "");
       Vector2 currentPosition = testMonster.getCurrentPosition();
       assertEquals(new Vector2(100, 100), currentPosition);
   }

   @Test
   public void testGetCharacterState(){
       TilePosition startingPosition = new TilePosition(0,0);
       BaseMonster testMonster = new BaseMonster(startingPosition, null, 40, 2, 5, 1, "");
       assertEquals(CharacterState.IDLE, testMonster.getCharacterState());
   }

   @Test
   public void testSetCharacterState(){
       TilePosition startingPosition = new TilePosition(0,0);
       BaseMonster testMonster = new BaseMonster(startingPosition, null, 40, 2, 5, 1, "");
       testMonster.setCharacterState(CharacterState.ATTACK);
       assertEquals(CharacterState.ATTACK, testMonster.getCharacterState());
   }

   @Test
   public void testGetMoveSpeed(){
       TilePosition startingPosition = new TilePosition(0,0);
       BaseMonster testMonster = new BaseMonster(startingPosition, null, 40, 2, 5, 1, "");
       assertEquals(250, testMonster.getMoveSpeed());
   }

   @Test
   public void testSetMoveSpeed(){
       TilePosition startingPosition = new TilePosition(0,0);
       BaseMonster testMonster = new BaseMonster(startingPosition, null, 40, 2, 5, 1, "");
       testMonster.setMoveSpeed(70);
       assertEquals(70, testMonster.getMoveSpeed());
   }

   @Test
   public void testAttack() {
       TilePosition startingPosition = new TilePosition(0,0);
       BaseMonster testMonster = new BaseMonster(startingPosition, null, 40, 2, 5, 1, "");
       Vector2 coordinateToAttack = new Vector2(5f, 5f);
       Boolean result = testMonster.attack(coordinateToAttack);
       assertFalse(result);
   }

   @Test
   public void testGetTilePosition() {
       TilePosition startingPosition = new TilePosition(0,0);
       BaseMonster testMonster = new BaseMonster(startingPosition, null, 40, 2, 5, 1, "");
       TilePosition expectedTilePosition = new TilePosition(0, 0);
       TilePosition result = testMonster.getTilePosition();
       assertEquals(expectedTilePosition, result);
   }

   @Test
   public void testSetTilePosition() {
       TilePosition startingPosition = new TilePosition(0,0);
       BaseMonster testMonster = new BaseMonster(startingPosition, null, 40, 2, 5, 1, "");
       TilePosition tilePosition = new TilePosition(1, 1);
       testMonster.setTilePosition(tilePosition);
       TilePosition result = testMonster.getTilePosition();
       assertEquals(tilePosition, result);
   }

    @Test
    public void testGetActionPoints() {
        TilePosition startingPosition = new TilePosition(0,0);
        BaseMonster testMonster = new BaseMonster(startingPosition, null, 40, 2, 5, 1, "");
        int expectedActionPoints = 10;
        int result = testMonster.getCurrentActionPoints();
        assertEquals(expectedActionPoints, result);
    }

   @Test
   public void testSetActionPoints() {
       TilePosition startingPosition = new TilePosition(0,0);
       BaseMonster testMonster = new BaseMonster(startingPosition, null, 40, 2, 5, 1, "");
       int actionPoints = 2;
       testMonster.setCurrentActionPoints(actionPoints);
       int result = testMonster.getCurrentActionPoints();
    assertEquals(actionPoints, result);
   }

   @Test
   public void testSetAttackRange() {
       TilePosition startingPosition = new TilePosition(0,0);
       BaseMonster testMonster = new BaseMonster(startingPosition, null, 40, 2, 5, 1, "");
       int attackRange = 2;
       testMonster.setAttackRange(attackRange);
       int result = testMonster.getAttackRange();
       assertEquals(attackRange, result);
   }

   @Test
   public void testGetAttackRange() {
       int expectedAttackRange = 1;
       TilePosition startingPosition = new TilePosition(0,0);
       BaseMonster testMonster = new BaseMonster(startingPosition, null, 40, 2, 5, 1, "");
       int result = testMonster.getAttackRange();
       assertEquals(expectedAttackRange, result);
   }
}
