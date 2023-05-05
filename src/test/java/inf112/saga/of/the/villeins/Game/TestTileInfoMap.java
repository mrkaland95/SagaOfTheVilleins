package inf112.saga.of.the.villeins.Game;

import com.badlogic.gdx.maps.*;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import inf112.saga.of.the.villeins.Characters.ICharacter;
import inf112.saga.of.the.villeins.Utils.TilePosition;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestTileInfoMap {



    @Test
    public void testTileIsMovable() {
        // Oppretter en TileInfoMap og setter en bestemt tile som ikke-flyttbar
        TileInfoMap infoMap = new TileInfoMap(20, 20);
        TilePosition nonMovableTile = new TilePosition(2, 2);
        infoMap.setMoveable(nonMovableTile, false);

        // Sjekker om tile er ikke-flyttbar
        assertFalse(infoMap.tileIsMovable(nonMovableTile));
    }

    @Test
    public void testReset() {
        // Oppretter en TileInfoMap
        TileInfoMap infoMap = new TileInfoMap(20, 20);

        // Lager en mock for ICharacter og TilePosition
        ICharacter mockCharacter = Mockito.mock(ICharacter.class);
        Vector2 mockPosition = Mockito.mock(Vector2.class);
        Mockito.when(mockCharacter.getCurrentPosition()).thenReturn(mockPosition);

        // Lager en liste med mockCharacter
        List<ICharacter> characterList = Arrays.asList(mockCharacter);

        // Utfører reset
        try (MockedStatic<TilePosition> mockedTilePosition = Mockito.mockStatic(TilePosition.class)) {
            mockedTilePosition.when(() -> TilePosition.findHexTile(mockPosition)).thenReturn(new TilePosition(5, 5));

            infoMap.reset(characterList);
        }

        // Sjekker om tile med karakter er satt til ikke-flyttbar
        assertFalse(infoMap.tileIsMovable(new TilePosition(5, 5)));
    }


    @Test
    public void testOnMove() {
        // Oppretter en TileInfoMap
        TileInfoMap infoMap = new TileInfoMap(20, 20);

        // Definerer start- og sluttiles
        TilePosition startTile = new TilePosition(2, 2);
        TilePosition endTile = new TilePosition(3, 3);

        // Setter start-tile som ikke-flyttbar
        infoMap.setMoveable(startTile, false);

        // Utfører onMove
        infoMap.onMove(startTile, endTile);

        // Sjekker om start-tile er blitt flyttbar og end-tile er blitt ikke-flyttbar
        assertTrue(infoMap.tileIsMovable(startTile));
        assertFalse(infoMap.tileIsMovable(endTile));
    }



    /**
     * Tester "SetIllegalTiles". Mocker kartet og setter egenskaper på det, og sjekker om en
     * til som skal være blokkert er det.
     */
    @Test
    public void setIllegalTiles() {
        // Oppretter mock for TiledMap og dens lag
        TiledMap mockMap = Mockito.mock(TiledMap.class);
        TiledMapTileLayer mockTileLayer = Mockito.mock(TiledMapTileLayer.class);
        MapLayers mapLayers = new MapLayers();
        mapLayers.add(mockTileLayer);
        Mockito.when(mockMap.getLayers()).thenReturn(mapLayers);

        // Oppretter mock for Cell objekt og definerer oppførselen
        TiledMapTileLayer.Cell mockCell = Mockito.mock(TiledMapTileLayer.Cell.class);
        TiledMapTile mockTile = Mockito.mock(TiledMapTile.class);
        MapProperties mockProperties = Mockito.mock(MapProperties.class);

        Mockito.when(mockTileLayer.getCell(Mockito.anyInt(), Mockito.anyInt())).thenReturn(mockCell);
        Mockito.when(mockCell.getTile()).thenReturn(mockTile);
        Mockito.when(mockTile.getProperties()).thenReturn(mockProperties);
        Mockito.when(mockProperties.get("isNotWalkable", boolean.class)).thenReturn(Boolean.TRUE);

        TileInfoMap infoMap = new TileInfoMap(20, 20);
        infoMap.setIllegalTiles(mockMap);
        TilePosition tile1 = new TilePosition(3, 0);
        boolean result = infoMap.tileIsMovable(tile1);
        assertFalse(result);
    }
}

