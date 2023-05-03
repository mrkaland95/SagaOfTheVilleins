package inf112.saga.of.the.villeins.Utils;

import com.badlogic.gdx.math.Vector2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTilePosition {

    @Test
    public void testFindHexTile() {
        TilePosition.setHexagonDimension(200f);
        Vector2 position = new Vector2(50, 50);
        TilePosition expectedPosition = new TilePosition(0, 0);
        TilePosition result = TilePosition.findHexTile(position);
        assertEquals(expectedPosition, result, "Expected tile (0, 0)");

        TilePosition.setHexagonDimension(200f);
        position = new Vector2(300, 300);
        expectedPosition = new TilePosition(1, 2);
        result = TilePosition.findHexTile(position);
        assertEquals(expectedPosition, result, "Expected tile (1, 2)");

        TilePosition.setHexagonDimension(200f);
        position = new Vector2(664, 463);
        expectedPosition = new TilePosition(3, 2);
        result = TilePosition.findHexTile(position);
        assertEquals(expectedPosition, result, "Expected tile (3, 2)");
    }

    /**
     * Tester FindVectorCoordinate, som regner ut koordinatet som tilsvarer midten av en tile.
     */
    @Test
    public void testFindVectorCoordinate() {
        TilePosition.setHexagonDimension(200f);
        TilePosition position = new TilePosition(0, 0);
        Vector2 result = TilePosition.findVectorCoordinate(position);
        Vector2 expectedPosition = new Vector2(100, 100);
        assertEquals(expectedPosition, result);

        position = new TilePosition(2, 2);
        result = TilePosition.findVectorCoordinate(position);
        expectedPosition = new Vector2(500, 400);
        assertEquals(result, expectedPosition);

        position = new TilePosition(5, 5);
        result = TilePosition.findVectorCoordinate(position);
        expectedPosition = new Vector2(1200, 850);
        assertEquals(result, expectedPosition);
    }

    /**
     * Tester at findVectorCoordinate fungerer uansett hvilken dimensjon det er på tilen.
     */
    @Test
    public void dynamicHexagonSizeTest(){

        TilePosition.setHexagonDimension(100f);
        TilePosition position = new TilePosition(0, 1);;

        Vector2 expected = new Vector2(100.0f, 125.0f);
        assertEquals(expected, TilePosition.findVectorCoordinate(position));

        TilePosition.setHexagonDimension(200f);
        position = new TilePosition(0, 1);
        expected = new Vector2(200.0f, 250.0f);
        assertEquals(expected, TilePosition.findVectorCoordinate(position));

        TilePosition.setHexagonDimension(300f);
        position = new TilePosition(1, 2);
        expected = new Vector2(450.0f, 600.0f);
        assertEquals(expected, TilePosition.findVectorCoordinate(position));
    }
}
