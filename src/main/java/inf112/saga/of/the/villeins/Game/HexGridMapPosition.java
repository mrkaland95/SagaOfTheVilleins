package inf112.saga.of.the.villeins.Game;

import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;


public class HexGridMapPosition {
//    private int x;
//    private int y;

    private final float hexPixelsWidth = 200f;
    private final float hexPixelsHeight = 200f;

    Vector2 worldCoordinate;

    private HashMap<HexTilePosition, Vector2> hexToWorldCoordinatesMap;


    public HexGridMapPosition(int x, int y) {
        worldCoordinate = CalculateHexGridPosition(x, y);
        System.out.println(worldCoordinate);
    }


    private Vector2 CalculateHexGridPosition(int gridX, int gridY) {
        // Define base case.
//        if (x == 0) {
//            wor
//        }
//
        float worldX = gridX * ((3f / 2f) * hexPixelsWidth);
        float worldY = gridY * ((2f * hexPixelsHeight) - ((hexPixelsHeight / 2f * (gridX % 2)) + (hexPixelsHeight * (gridX % 2))));
        return new Vector2(worldX, worldY);
    }

    public Vector2 getHexPosition() {
        return worldCoordinate;
    }
}
