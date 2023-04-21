package inf112.saga.of.the.villeins.Game;

import java.util.HashMap;
import java.util.List;

import javax.imageio.plugins.tiff.TIFFDirectory;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.badlogic.gdx.utils.SortedIntList.Iterator;

import inf112.saga.of.the.villeins.Characters.ICharacter;
import inf112.saga.of.the.villeins.MapUtils.HexGridMapPosition;
import inf112.saga.of.the.villeins.MapUtils.TilePosition;

/**
 * Class intended for storing information about tiles, whether they are movable etc. This makes it simpler to access
 * Than using the map object directly, because we can use our own TilePosition objects
 * in addition, our own properties that may not be inherently part of the map object.
 */
public class Imap {
    private final HashMap<TilePosition, Boolean> map = new HashMap<>();
    private final int rows;
    private final int cols;

    public Imap(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        //creates a simple hashmap so we cna get information from diffrent tiles using Tileposition
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                TilePosition temp = new TilePosition(i, j);
                map.put(temp, true);
            }
        }
    }
    public Boolean isMovable(TilePosition tile){
        return map.get(tile);
    }

    public void setMoveable(TilePosition tile, Boolean moveable) {
        map.put(tile, moveable);
    }

    public void onMove(TilePosition start, TilePosition end){
        map.put(start, true);
        map.put(end, false);
    }

    public void reset(List<ICharacter> characterList) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                TilePosition temp = new TilePosition(i, j);
                map.put(temp, true);
            }
        }

        for (ICharacter character : characterList) {
            TilePosition characterPosition = HexGridMapPosition.findHexTile(character.getCurrentPosition());
            map.put(characterPosition, false);
        }
    }

    public void findIllegalTiles(TiledMap tileMap){
        TiledMapTileLayer tiledLayer = (TiledMapTileLayer)tileMap.getLayers().get(0);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if(tiledLayer.getCell(i, j).getTile().getId() == 6){
                    map.put(new TilePosition(i, j), false);
               }
            }
        }
    }

    
}
