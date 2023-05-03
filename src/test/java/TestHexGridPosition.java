import static org.junit.jupiter.api.Assertions.*;

import inf112.saga.of.the.villeins.Utils.TilePosition;
import org.junit.jupiter.api.*;
import com.badlogic.gdx.math.Vector2;


public class TestHexGridPosition {
    // TODO fiks slik at disse testene funker igjen.
//    @Test
//    public void testHexGridToWorldCords() {
//        int targetX = 3;
//        int targetY = 3;
//        Vector2 Expected = new Vector2((float) 800.0, (float) 550.0);
//        assertEquals(Expected, TilePosition.calculateVectorCoordinate(targetX, targetY));
//        targetX = 3;
//        targetY = 4;
//        Expected = new Vector2((float) 700.0, (float) 700.0);
//        assertEquals(Expected, TilePosition.calculateVectorCoordinate(targetX, targetY));
//        targetX = 4;
//        targetY = 3;
//        Expected = new Vector2((float) 1000.0, (float) 550.0);
//        assertEquals(Expected, TilePosition.calculateVectorCoordinate(targetX, targetY));
//    }
    @Test
    public void dynamicHexagonSizeTest(){
        TilePosition position = new TilePosition(0, 1);;

        Vector2 Expected = new Vector2((float) 100.0, (float) 100.0);
        assertEquals(Expected, TilePosition.findVectorCoordinate(position));
        position = new TilePosition(0, 1);
        Expected = new Vector2((float) 200.0, (float) 250.0);
        assertEquals(Expected, TilePosition.findVectorCoordinate(position));
        position = new TilePosition(1, 2);
        Expected = new Vector2((float) 300.0, (float) 400.0);
        assertEquals(Expected, TilePosition.findVectorCoordinate(position));
    }
}