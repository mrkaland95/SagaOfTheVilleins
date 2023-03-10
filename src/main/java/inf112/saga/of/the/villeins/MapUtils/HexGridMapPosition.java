package inf112.saga.of.the.villeins.MapUtils;

import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;
import java.util.Map;


public class HexGridMapPosition {
    Vector2 worldCoordinate;
    // TODO this should take in the hexagon dimension as a parameter,
    // so it can be dynamically changed if the hexagon size should ever be changed in the future.
    static double hexagonDimension = 200;

    /**
     * Function for calculating the world coordinate of a hexagon tile, given its position in the tilemap.
     * @param gridX x position in the tilemap
     * @param gridY y position in the tilemap
//     * @param hexagonDimension the dimensions of a hexagon tile, i.e. it's width and height.
     * @return Vector2 carrying the world coordinate of a hex tile.
     */
    public static Vector2 calculateWorldCoordinateFromHexGrid(int gridX, int gridY) {
        double doubleX = gridX;
        double doubleY = gridY;
        double x = 0.0;
        double y = 0.0;
      
        if(gridY % 2 == 0){
            x = hexagonDimension/2 + hexagonDimension*doubleX;
            y = hexagonDimension/2 + hexagonDimension/4*3*doubleY;
        }
        else{
            x = hexagonDimension + hexagonDimension*doubleX;
            y = ((hexagonDimension/4) + hexagonDimension) + (hexagonDimension/4)*3*(doubleY-1);
            
        }

        
        float floatX = (float) x;
        float floatY = (float) y;
        return new Vector2(floatX, floatY);

    }


    public static TilePosition findHexTile(Vector2 worldCoordinate) {
        // Adapted from here: https://gamedevelopment.tutsplus.com/tutorials/creating-hexagonal-minesweeper--cms-28655
        double hexTileX = Math.floor(worldCoordinate.x / hexagonDimension);
        double hexTileY = Math.floor(worldCoordinate.y / (hexagonDimension * (3d/4d)));
        double dX = (worldCoordinate.x) % hexagonDimension;
        double dY = (worldCoordinate.y) % (hexagonDimension * (3d/4d));
        double slope = (hexagonDimension / 4d )/ (hexagonDimension / 2d);
        double caldY = dX * slope;
        double delta = (hexagonDimension/4) - caldY;
        if (hexTileY % 2 == 0) {
            if (Math.abs(delta) > dY) {
                if(delta > 0){
                    hexTileX --;
                    hexTileY --;
                }
                else{
                    hexTileY --;
                }
            }
        } else {
            if (dX > (hexagonDimension / 2d)) {
                if (dY < ((hexagonDimension / 2d) - caldY)) {
                    hexTileY--;
                }
            } else {
                if (dY > caldY) {
                    hexTileX--;
                } else {
                    hexTileY--;
                }
            }
        }
        return new TilePosition((int) hexTileX, (int) hexTileY);
    }
}
