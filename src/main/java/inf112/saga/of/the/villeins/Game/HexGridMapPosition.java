package inf112.saga.of.the.villeins.Game;

import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;


public class HexGridMapPosition {
    Vector2 worldCoordinate;
    static double hexagonDimension = 200;
    private HashMap<HexTilePosition, Vector2> hexToWorldCoordinatesMap;


    public HexGridMapPosition() {
    }


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


    public static HexTilePosition findHexTile(Vector2 worldCoordinate) {
        // Adapted from here: https://gamedevelopment.tutsplus.com/tutorials/creating-hexagonal-minesweeper--cms-28655
        double xHexTile = Math.floor(worldCoordinate.x / hexagonDimension);
        double yHexTile = Math.floor(worldCoordinate.y / (hexagonDimension * (3d/4d)));
        double dX = (worldCoordinate.x) % hexagonDimension;
        double dY = (worldCoordinate.y) % (hexagonDimension * (3d/4d));
        double slope = (hexagonDimension / 4d )/ (hexagonDimension / 2d);
        double caldY = dX * slope;
        double delta = (hexagonDimension/4) - caldY;
        if (yHexTile % 2 == 0) {
            if (Math.abs(delta) > dY) {
                xHexTile--;
            }
        } else {
            if (dX > (hexagonDimension / 2d)) {
                if (dY < ((hexagonDimension / 2d) - caldY)) {
                    yHexTile--;
                }
            } else {
                if (dY > caldY) {
                    xHexTile--;
                } else {
                    yHexTile--;
                }
            }
        }
        return new HexTilePosition((int) xHexTile, (int) yHexTile);
    }


    public Vector2 getHexPosition() {
        return worldCoordinate;
    }
}
