
import org.junit.jupiter.api.*;
import java.util.ArrayList;
import inf112.saga.of.the.villeins.MapUtils.AStarPathfinder;
import inf112.saga.of.the.villeins.MapUtils.TilePosition;

public class TestAStarPathfinder {
    
    @Test
    public void testFindPath(String[] args) {
        TilePosition a = new TilePosition(1, 1);
        TilePosition b = new TilePosition(2, 2);
        TilePosition c = new TilePosition(3, 3);

        TilePosition start = new TilePosition(0, 0);
        TilePosition end = new TilePosition(4, 4);

        ArrayList<TilePosition> path = AStarPathfinder.findPath(start, end);
        if (path == null) {
            System.out.println("No path found!");
        } else {
            System.out.println("Path found: " + path);
        }
    }
}

