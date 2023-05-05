package inf112.saga.of.the.villeins.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import inf112.saga.of.the.villeins.AssetManager.GameAssetManager;
import inf112.saga.of.the.villeins.Utils.TilePosition;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
    @Test
    public void setIllegalTiles() {
        GameAssetManager gameAssetManager = new GameAssetManager();
        gameAssetManager.manager.finishLoading();
        TiledMap map = gameAssetManager.manager.get(GameAssetManager.testMapPath, TiledMap.class);
        System.out.println(map);
        TileInfoMap infoMap = new TileInfoMap(20, 20);
        infoMap.setIllegalTiles(map);
        TilePosition tile1 = new TilePosition(3,0);
        boolean result = infoMap.tileIsMovable(tile1);
        assertFalse(result);
    }

}

