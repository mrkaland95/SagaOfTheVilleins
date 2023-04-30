package inf112.saga.of.the.villeins.Game;

import java.util.HashMap;
import java.util.List;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import inf112.saga.of.the.villeins.Characters.ICharacter;
import inf112.saga.of.the.villeins.MovementUtils.TilePosition;

/**
 * Class intended for storing information about tiles, whether they are movable etc. This makes it simpler to access
 * Than using the map object directly, because we can use our own TilePosition objects
 * in addition, our own properties that may not be inherently part of the map object.
 */
public class TileInfoMap {
    private final HashMap<TilePosition, Boolean> map = new HashMap<>();
    private final int mapRows;
    private final int mapCols;

    public TileInfoMap(int mapRows, int mapCols){
        this.mapRows = mapRows;
        this.mapCols = mapCols;
        //creates a simple hashmap so we cna get information from diffrent tiles using Tileposition
        for (int i = 0; i < mapRows; i++) {
            for (int j = 0; j < mapCols; j++) {
                TilePosition temp = new TilePosition(i, j);
                map.put(temp, true);
            }
        }
    }
    public Boolean tileIsMovable(TilePosition tile){
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
        for (int i = 0; i < mapRows; i++) {
            for (int j = 0; j < mapCols; j++) {
                TilePosition temp = new TilePosition(i, j);
                map.put(temp, true);
            }
        }

        for (ICharacter character : characterList) {
            TilePosition characterPosition = TilePosition.findHexTile(character.getCurrentPosition());
            map.put(characterPosition, false);
        }
    }

    public void addIllegalTiles(TiledMap tileMap){
        int walkableTileId = 6;
        TiledMapTileLayer tiledLayer = (TiledMapTileLayer)tileMap.getLayers().get(0);
        for (int i = 0; i < mapRows; i++) {
            for (int j = 0; j < mapCols; j++) {
                if(tiledLayer.getCell(i, j).getTile().getId() == walkableTileId){
                    map.put(new TilePosition(i, j), false);
               }
            }
        }
    }

    
}
