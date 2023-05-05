package inf112.saga.of.the.villeins.Utils;

import com.badlogic.gdx.math.Vector2;
import inf112.saga.of.the.villeins.Game.TileInfoMap;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import inf112.saga.of.the.villeins.Utils.AStarPathfinder;
import inf112.saga.of.the.villeins.Utils.TilePosition;

public class TestAStarPathfinder {

    @Test
    public void testFindPath() {

        TileInfoMap tileInfoMap = new TileInfoMap(5, 5);

        TilePosition start = new TilePosition(0, 0);
        TilePosition end = new TilePosition(4, 4);

        ArrayList<TilePosition> path = AStarPathfinder.findPath(start, end, tileInfoMap);
        assertNotNull(path); // sjekker at ikke er null
        assertFalse(path.isEmpty()); // sjekker at det finnes elementer i listen
        assertEquals(start, path.get(0)); // sjekker at det første elementet i listen svarer til start
        assertEquals(end, path.get(path.size() - 1)); // sjekker at det siste elementet i listen svarer til end
    }

    @Test
    void testGetNeighbors() {
        // Oppretter en TileInfoMap
        TileInfoMap infoMap = new TileInfoMap(10, 10);

        // Definerer en posisjon og finner naboene
        TilePosition position = new TilePosition(4, 4);
        ArrayList<TilePosition> neighbors = AStarPathfinder.getNeighbors(position, infoMap);

        // Liste med forventede naboer
        List<TilePosition> expectedNeighbors = Arrays.asList(
                new TilePosition(3, 5),
                new TilePosition(4, 5),
                new TilePosition(5, 4),
                new TilePosition(4, 3),
                new TilePosition(3, 3),
                new TilePosition(3, 4)
        );

        // Sjekker om antall naboer er korrekt
        assertEquals(expectedNeighbors.size(), neighbors.size());

        // Sjekker om hver forventet nabo er i listen med faktiske naboer
        for (TilePosition expectedNeighbor : expectedNeighbors) {
            boolean contains = neighbors.stream().anyMatch(neighbor -> neighbor.equals(expectedNeighbor));
            assertTrue(contains);
        }
    }
}
