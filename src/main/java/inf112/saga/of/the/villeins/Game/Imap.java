package inf112.saga.of.the.villeins.Game;

import java.util.ArrayList;
import java.util.HashMap;

import inf112.saga.of.the.villeins.MapUtils.TilePosition;

public class Imap{
    public static HashMap<TilePosition, ArrayList<Boolean>> map = new HashMap<>();
    public Imap(int col, int row){
        //creates a simple hashmap so we cna get information from diffrent tiles using Tileposition
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                TilePosition temp = new TilePosition(i, j);
                // they arrylist uses a standardnized setup where index 0 is if tile is movable, 
                // 1 is wheter or not there is a charachter there
                ArrayList<Boolean> templist = new ArrayList<>();
                templist.add(true);
                templist.add(false);
                map.put(temp, templist);
            }
        }
    }

    public boolean movable(TilePosition tile){
        return map.get(tile).get(0);
    }

    public boolean isOccupied(TilePosition tile){
        return map.get(tile).get(1);
    }

    public void onMove(TilePosition start, TilePosition end){
        ArrayList<Boolean> startList = new ArrayList<>();
        startList.add(true);
        startList.add(false);
        map.put(start, startList);

        ArrayList<Boolean> endList = new ArrayList<>();
        endList.add(false);
        endList.add(true);
        map.put(end, endList);
    }
    public void InitialSet(TilePosition tile){
        ArrayList<Boolean> initial = new ArrayList<>();
        initial.add(false);
        initial.add(true);
        map.put(tile, initial);
    }


    
}
