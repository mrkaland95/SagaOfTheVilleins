import static org.junit.jupiter.api.Assertions.*;

import java.time.Year;

import javax.swing.text.Position;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import com.badlogic.gdx.math.Vector2;

import inf112.saga.of.the.villeins.Game.HexGridMapPosition;


public class TestHexGridPosition {
    @Test
    public void testHexGridToWorldCords() {
        double hexaDimensions = 200;
        int targetX = 3;
        int targetY = 3;
        Vector2 Expected = new Vector2((float) 800.0, (float) 550.0);
        HexGridMapPosition map = new HexGridMapPosition(targetX, targetY);
        assertEquals(Expected, map.CalculateHexGridPosition(targetX, targetY, hexaDimensions));
        targetX = 3;
        targetY = 4;
        Expected = new Vector2((float) 700.0, (float) 700.0);
        map = new HexGridMapPosition(targetX, targetY);
        assertEquals(Expected, map.CalculateHexGridPosition(targetX, targetY, hexaDimensions));
        targetX = 4;
        targetY = 3;
        Expected = new Vector2((float) 1000.0, (float) 550.0);
        map = new HexGridMapPosition(targetX, targetY);
        assertEquals(Expected, map.CalculateHexGridPosition(targetX, targetY, hexaDimensions));
    }
    @Test
    public void dynamicHexagonSizeTest(){
        double hexaDimensions = 400;
        int targetX = 0;
        int targetY = 0;
        Vector2 Expected = new Vector2((float) 200.0, (float) 200.0);
        HexGridMapPosition map = new HexGridMapPosition(targetX, targetY);
        assertEquals(Expected, map.CalculateHexGridPosition(targetX, targetY, hexaDimensions));
        targetX = 0;
        targetY = 1;
        Expected = new Vector2((float) 400.0, (float) 500.0);
        map = new HexGridMapPosition(targetX, targetY);
        assertEquals(Expected, map.CalculateHexGridPosition(targetX, targetY, hexaDimensions));
        targetX = 1;
        targetY = 2;
        Expected = new Vector2((float) 600, (float) 800);
        map = new HexGridMapPosition(targetX, targetY);
        assertEquals(Expected, map.CalculateHexGridPosition(targetX, targetY, hexaDimensions));

    }
}