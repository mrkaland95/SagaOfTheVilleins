package inf112.saga.of.the.villeins.Rules;

import java.util.ArrayList;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;

import inf112.saga.of.the.villeins.Characters.ICharacter;
import inf112.saga.of.the.villeins.InputProcessors.CameraProcessor;
import inf112.saga.of.the.villeins.InputProcessors.PlayerProcessor;

public class GameController {

    private ArrayList<ICharacter> playerlist;
    private int turnCounter;
    private InputMultiplexer multiplexer;

    public GameController(ArrayList<ICharacter> playerlist, OrthographicCamera camera){
        this.playerlist = playerlist;
        this.turnCounter = 0;
        this.multiplexer = new InputMultiplexer();

        initializeProcessors(camera);
    }

    public int playerCount(){
        return this.playerlist.size();
    }

    public void turn(ICharacter currentChar){
        /*
         * TODO: Setup system for who's turn it is
         *
         */
    }

    public void nextTurn(){
        /*
         * Sets the turn to the next charcter in turnList
         */
    }

        /*
        *   This method initializes the processors needed for the game.
        *
        *   Add new processors to this method as they are created.
        */

    private void initializeProcessors(OrthographicCamera camera){
        multiplexer.addProcessor(new CameraProcessor(camera));
        multiplexer.addProcessor(new PlayerProcessor(camera));
    }
}   
