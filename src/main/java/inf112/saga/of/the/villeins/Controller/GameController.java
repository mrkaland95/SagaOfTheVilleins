package inf112.saga.of.the.villeins.Controller;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;

import inf112.saga.of.the.villeins.Characters.ICharacter;
import inf112.saga.of.the.villeins.InputProcessors.CameraProcessor;
import inf112.saga.of.the.villeins.InputProcessors.IInputProcessor;
import inf112.saga.of.the.villeins.InputProcessors.PlayerProcessor;

public class GameController {

    private ArrayList<ICharacter> playerlist;
    // private int turnCounter;
    private ArrayList<InputProcessor> processorList;
    public IInputProcessor currentProcessor;

    public GameController(ArrayList<ICharacter> playerlist, OrthographicCamera camera){
        this.playerlist = playerlist;
        // this.turnCounter = 0;
        this.processorList = new ArrayList<>();
        this.currentProcessor = null;
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
         * Should also change which processor in use based on if its a playerturn or AI-turn
         * Maybe also use turnConter if needed
         */
    }

        /*
        *   This method initializes the processors needed for the game.
        *
        *   Add new processors to this method as they are created.
        */

    private void initializeProcessors(OrthographicCamera camera){
        IInputProcessor game = new CameraProcessor(camera);
        IInputProcessor player = new PlayerProcessor(camera);

        processorList.add(game);
        processorList.add(player);

        this.currentProcessor = game;
        Gdx.input.setInputProcessor(game);
    }
}   
