package inf112.saga.of.the.villeins.Controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;

import inf112.saga.of.the.villeins.Characters.ICharacter;
import inf112.saga.of.the.villeins.Characters.Player;
import inf112.saga.of.the.villeins.InputProcessors.PlayerProcessor;
import inf112.saga.of.the.villeins.InputProcessors.IInputProcessor;
import inf112.saga.of.the.villeins.InputProcessors.TestProcessor;

public class GameController {

    private List<ICharacter> playerlist;
    // private int turnCounter;
    private HashMap<String, IInputProcessor> processorList;
    public IInputProcessor currentProcessor;
    private LinkedList<ICharacter> turnList;

    public GameController(List<ICharacter> playerlist, OrthographicCamera camera){
        this.playerlist = playerlist;
        this.turnList = new LinkedList<ICharacter>();

        // this.turnCounter = 0;
        this.processorList = new HashMap<String, IInputProcessor>();
        this.currentProcessor = null;

        initializeGame(camera);
    }

    public int playerCount(){
        return this.playerlist.size();
    }

    public void turn(ICharacter currentChar){
        /*
         * TODO: Setup system for who's turn it is
         *
         */
        if(currentChar instanceof Player){
            this.currentProcessor = processorList.get("player");
            Gdx.input.setInputProcessor(currentProcessor);
        }
        else{
            this.currentProcessor = processorList.get("notPlayer");
            Gdx.input.setInputProcessor(currentProcessor);
        }
    }

    public void nextTurn(){
        /*
         * Sets the turn to the next charcter in turnList
         * Should also change which processor in use based on if its a playerturn or AI-turn
         * Maybe also use turnConter if needed
         */
        
        ICharacter currentTurn = turnList.poll();
        turn(currentTurn);
        turnList.add(currentTurn);
         
    }

        /*
        *   This method initializes the processors needed for the game.
        *
        *   Add new processors to this method as they are created.
        */
    private void initializeGame(OrthographicCamera camera){
        initializeProcessors(camera);
        turnList.addAll(playerlist);
        
    }
    
    private void initializeProcessors(OrthographicCamera camera){
        IInputProcessor player = new PlayerProcessor(camera, this);
        IInputProcessor notPlayer = new TestProcessor(camera, this);

        processorList.put("notPlayer", notPlayer);
        processorList.put("player", player);

        this.currentProcessor = player;
        Gdx.input.setInputProcessor(player);
    }
}   
