import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import inf112.saga.of.the.villeins.Game.Imap;
import inf112.saga.of.the.villeins.MapUtils.TilePosition;



public class TestIMap {


    @Test
    public void initialSetTest(){ 
        Imap map = new Imap(10, 10);     
        TilePosition start = new TilePosition(0, 0);
        map.InitialSet(start); 
        assertEquals(false, map.movable(start));
        assertEquals(true, map.isOccupied(start));
    }

    @Test
    public void onMoveTest(){
        Imap map = new Imap(10, 10);     
        TilePosition start = new TilePosition(0, 0);
        TilePosition end = new TilePosition(1, 1);
        
        assertEquals(true, map.movable(end));
        assertEquals(false, map.isOccupied(end));

        map.InitialSet(start); 
        map.onMove(start, end);
        assertEquals(true, map.movable(start));
        assertEquals(false, map.isOccupied(start));
        assertEquals(false, map.movable(end));
        assertEquals(true, map.isOccupied(end));

    }
    
}
