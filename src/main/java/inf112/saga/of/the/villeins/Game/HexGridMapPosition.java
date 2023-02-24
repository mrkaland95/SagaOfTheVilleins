package inf112.saga.of.the.villeins.Game;

import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;
import java.util.function.IntToDoubleFunction;


public class HexGridMapPosition {
//    private int x;
//    private int y;

    private final Double hexPixelsDimension = 200.0;


    Vector2 worldCoordinate;

    private HashMap<HexTilePosition, Vector2> hexToWorldCoordinatesMap;


    public HexGridMapPosition(int x, int y) {
        worldCoordinate = CalculateHexGridPosition(x, y);
        System.out.println(worldCoordinate);
    }


    private Vector2 CalculateHexGridPosition(int gridX, int gridY) {
        double doubleX = gridX;
        double doubleY = gridY;
        double x = 0.0;
        double y = 0.0;
      
        if(gridY % 2 == 0){
            x = 100 + 200*doubleX;
            y = 100 + 150*doubleY;
        }
        else{
            x = 200 + 200*doubleX;
            y = 250 + 150*(doubleY-1);
            
        }

        
        float floatX = (float) x;
        float floatY = (float) y;
        return new Vector2(floatX, floatY);

    }

    public Vector2 getHexPosition() {
        return worldCoordinate;
    }
}
