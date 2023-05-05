package inf112.saga.of.the.villeins.Utils;

import com.badlogic.gdx.math.Vector2;
import inf112.saga.of.the.villeins.Characters.ICharacter;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

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

    @Test
    public void testHexDistance() {
        TilePosition tile1 = new TilePosition(0, 0);
        TilePosition tile2 = new TilePosition(1, 1);
        TilePosition tile3 = new TilePosition(3, 3);

        double distance1 = TilePosition.hexDistance(tile1, tile2);
        double distance2 = TilePosition.hexDistance(tile1, tile3);
        double distance3 = TilePosition.hexDistance(tile2, tile3);

        assertEquals(2, distance1, 0.01);
        assertEquals(5, distance2, 0.01);
        assertEquals(3, distance3, 0.01);
    }

    @Test
    public void testGetCharacterOnCoordinate() {
        // Lager mock for ICharacter
        ICharacter mockCharacter1 = Mockito.mock(ICharacter.class);
        ICharacter mockCharacter2 = Mockito.mock(ICharacter.class);

        // Setter opp koordinater for karakterene
        Vector2 mockPosition1 = new Vector2(100, 100);
        Vector2 mockPosition2 = new Vector2(200, 200);
        when(mockCharacter1.getCurrentPosition()).thenReturn(mockPosition1);
        when(mockCharacter2.getCurrentPosition()).thenReturn(mockPosition2);

        // Liste med karakterer
        List<ICharacter> characterList = Arrays.asList(mockCharacter1, mockCharacter2);

        // Tester om karakterene blir funnet riktig
        ICharacter result1 = TilePosition.getCharacterOnCoordinate(mockPosition1, characterList);
        ICharacter result2 = TilePosition.getCharacterOnCoordinate(mockPosition2, characterList);


        assertEquals(mockCharacter1, result1);
        assertEquals(mockCharacter2, result2);

        // Tester om ingen karakter blir funnet
        Vector2 mockPosition3 = new Vector2(300, 300);
        assertNull(TilePosition.getCharacterOnCoordinate(mockPosition3, characterList));
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
