package inf112.saga.of.the.villeins.Game;

import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;
import java.util.function.IntToDoubleFunction;


public class HexGridMapPosition {


    Vector2 worldCoordinate;

    private HashMap<HexTilePosition, Vector2> hexToWorldCoordinatesMap;


    public HexGridMapPosition(int x, int y) {
        double hexagonDimension = 200;
        worldCoordinate = CalculateHexGridPosition(x, y, hexagonDimension);
        System.out.println(worldCoordinate);
    }


    /**
     * Function for calculating the world coordinate of a hexagon tile, given its position in the tilemap.
     * @param gridX x position in the tilemap
     * @param gridY y position in the tilemap
     * @param hexagonDimension the dimensions of a hexagon tile, i.e. it's width and height.
     * @return Vector2 carrying the world coordinate of a hex tile.
     */
    public Vector2 CalculateHexGridPosition(int gridX, int gridY, double hexagonDimension) {
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

    public Vector2 getHexPosition() {
        return worldCoordinate;
    }
}
