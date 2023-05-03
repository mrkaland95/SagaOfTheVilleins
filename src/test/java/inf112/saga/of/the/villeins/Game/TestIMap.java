package inf112.saga.of.the.villeins.Game;

import inf112.saga.of.the.villeins.Utils.TilePosition;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestIMap {


    @Test
    public void initialSetTest(){
        TileInfoMap map = new TileInfoMap(10, 10);
        TilePosition start = new TilePosition(0, 0);
//        map.InitialSet(start);
//        assertEquals(false, map.isMovable(start));
//        assertEquals(true, map.isOccupied(start));
    }
//
//    @Test
//    public void onMoveTest(){
//        Imap map = new Imap(10, 10);
//        TilePosition start = new TilePosition(0, 0);
//        TilePosition end = new TilePosition(1, 1);
//
//        assertEquals(true, map.isMovable(end));
//        assertEquals(false, map.isOccupied(end));
//
//        map.InitialSet(start);
//        map.onMove(start, end);
//        assertEquals(true, map.isMovable(start));
//        assertEquals(false, map.isOccupied(start));
//        assertEquals(false, map.isMovable(end));
//        assertEquals(true, map.isOccupied(end));
//
//    }
    
}
