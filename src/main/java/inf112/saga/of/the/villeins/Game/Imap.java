package inf112.saga.of.the.villeins.Game;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import inf112.saga.of.the.villeins.MapUtils.TilePosition;

public class Imap{
    private final HashMap<TilePosition, Boolean> map = new HashMap<>();
    public Imap(int rows, int cols){
        //creates a simple hashmap so we cna get information from diffrent tiles using Tileposition
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                TilePosition temp = new TilePosition(i, j);
                // they arrylist uses a standardnized setup where index 0 is if tile is movable, 
                // 1 is wheter or not there is a charachter there
                map.put(temp, true);
            }
        }
        System.out.println(map);
    }
    public Boolean isMovable(TilePosition tile){
        return map.get(tile);
    }

    public void setMoveable(TilePosition tile, Boolean moveable) {
        this.map.put(tile, moveable);
    }




    public void onMove(TilePosition start, TilePosition end){
        map.put(start, true);
        map.put(end, false);
    }

//        ArrayList<Boolean> endList = new ArrayList<>();
//        endList.add(false);
//        endList.add(true);
//        map.put(end, endList);
//    }

//    public void setMap(TilePosition tile, ArrayList<Boolean>) {
//        map.put()
//    }


    
}
