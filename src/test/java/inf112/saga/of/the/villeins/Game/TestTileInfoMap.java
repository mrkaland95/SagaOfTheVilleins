package inf112.saga.of.the.villeins.Game;

import inf112.saga.of.the.villeins.Utils.TilePosition;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTileInfoMap {


    @Test
    public void initialSetTest(){
        TileInfoMap map = new TileInfoMap(10, 10);
        TilePosition start = new TilePosition(0, 0);
        map.setMoveable(start, false);
        assertEquals(false, map.tileIsMovable(start));
    }

   @Test
   public void onMoveTest(){
       TileInfoMap map = new TileInfoMap(10, 10);
       TilePosition start = new TilePosition(0, 0);
       TilePosition end = new TilePosition(1, 1);

       map.setMoveable(start, false);
       assertEquals(true, map.tileIsMovable(end));
       assertEquals(false, map.tileIsMovable(start));
    
       map.onMove(start, end);
       assertEquals(false, map.tileIsMovable(end));
       assertEquals(true, map.tileIsMovable(start));

   }
    
}
