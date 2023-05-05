package inf112.saga.of.the.villeins.Game;

import java.util.HashMap;
import java.util.List;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import inf112.saga.of.the.villeins.Characters.ICharacter;
import inf112.saga.of.the.villeins.Utils.TilePosition;

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
    /**
     * sjekker om en tile er lov å gå på
     * 
     * @param tile tilen som sjekkes
     * @return true om det er lov, false om det ikke er lov
     */
    public Boolean tileIsMovable(TilePosition tile){
        return map.get(tile);
    }
    /**
     * Setter en spesifikk tile til å være lovlig eller ikke
     * 
     * @param tile tilen som endres
     * @param moveable boolean som er true hvis lovlig, false hvis ikke
     */
    public void setMoveable(TilePosition tile, Boolean moveable) {
        map.put(tile, moveable);
    }

    /**
     * Oppdaterer kartet når noen beveger seg
     * 
     * @param start tilen den begynner på
     * @param end tilen den lander på
     */
    public void onMove(TilePosition start, TilePosition end){
        map.put(start, true);
        map.put(end, false);
    }

    /**
     * Resetter tilesene i kartet basert på om det er en karakter der eller ikke
     * Setter false hvis det er en karakter der, true hvis ikke
     * 
     * @param characterList listen av alle karakterene
     */
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

    /**
     * Finner alle ulovlige tilesene i kartet og setter de til det basert på dataen som tiledMap
     * inneholder
     * 
     * @param tileMap kartet laget i Tiled, der tilesene inneholder en property om det er lovlig å gå på den eller ikke.
     */
    public void setIllegalTiles(TiledMap tileMap){
        TiledMapTileLayer tiledLayer = (TiledMapTileLayer)tileMap.getLayers().get(0);
        for (int i = 0; i < mapRows; i++) {
            for (int j = 0; j < mapCols; j++) {
                Boolean isNotWalkable = (tiledLayer.getCell(i, j).getTile().getProperties().get("isNotWalkable", boolean.class));
                if (isNotWalkable == null) {
                    continue;
                }
                else if (isNotWalkable) {
                    map.put(new TilePosition(i, j), false);
                }
            }
        }
    }

    
}
