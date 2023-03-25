
import inf112.saga.of.the.villeins.Game.Imap;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import inf112.saga.of.the.villeins.MapUtils.AStarPathfinder;
import inf112.saga.of.the.villeins.MapUtils.TilePosition;

public class TestAStarPathfinder {
    
    @Test
    public void testFindPath() {

        Imap imap = new Imap(5,5);

        TilePosition start = new TilePosition(0, 0);
        TilePosition end = new TilePosition(4, 4);

        ArrayList<TilePosition> path = AStarPathfinder.findPath(start, end, imap);
        assertNotNull(path); // sjekker at ikke er null
        assertFalse(path.isEmpty()); // sjekker at det finnes elementer i listen
        assertEquals(start, path.get(0)); // sjekker at det første elementet i listen svarer til start
        assertEquals(end, path.get(path.size() - 1)); // sjekker at det siste elementet i listen svarer til end
      
    }
}

