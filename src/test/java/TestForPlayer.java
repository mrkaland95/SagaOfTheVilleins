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
        

    }

    
}
