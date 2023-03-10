package inf112.saga.of.the.villeins.Game;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.text.StyledEditorKit.BoldAction;

import inf112.saga.of.the.villeins.MapUtils.TilePosition;

public class Imap{
    public HashMap<TilePosition, ArrayList<Boolean>> map = new HashMap<>(); ;
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

    
}
