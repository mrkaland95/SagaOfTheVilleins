package inf112.saga.of.the.villeins.Controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

import inf112.saga.of.the.villeins.Characters.ICharacter;
import inf112.saga.of.the.villeins.Characters.Player;
import inf112.saga.of.the.villeins.Game.GameLoop;
import inf112.saga.of.the.villeins.InputProcessors.ActivePlayerProcessor;
import inf112.saga.of.the.villeins.InputProcessors.IInputProcessor;
import inf112.saga.of.the.villeins.InputProcessors.InactivePlayerProcessor;
import inf112.saga.of.the.villeins.MapUtils.HexGridMapPosition;
import inf112.saga.of.the.villeins.MapUtils.TilePosition;

public class GameController {

    private List<ICharacter> characterList;
    private HashMap<String, IInputProcessor> processorList;
    public IInputProcessor currentProcessor;
    private LinkedList<ICharacter> turnList;
    private ICharacter playerCharacter;
    private ICharacter currentCharacter;

    public GameController(List<ICharacter> characterList, OrthographicCamera camera){
        this.characterList = characterList;
        this.turnList = new LinkedList<ICharacter>();
        this.processorList = new HashMap<String, IInputProcessor>();
        this.currentProcessor = null;
        this.playerCharacter = null;
        this.currentCharacter = null;

        initializeGame(camera);
    }

    public int playerCount(){
        return this.characterList.size();
    }

    public void update(){
        /*
         * This method should be added to render function in Game.java to keep updating the controller with correct values.
         * Could be used to handle "Action Points" or similar, to handle when to end someones turn.
         * 
         */
        
        if(currentCharacter instanceof Player) {
//            if (!currentCharacter.isMoving()) {
//
//            }
            Vector2 clickPosition = currentProcessor.getClickCoordinates();
            currentCharacter.setEndPosition(clickPosition);


            // Update the IMap with player positions here.

        }
        if(this.currentProcessor.checkTurn()){
            this.currentProcessor.endTurn();
            nextTurn();        
        }

    }

    public void turn(ICharacter currentChar){
        this.currentCharacter = currentChar;

        for (ICharacter character : characterList) {
            TilePosition currentPosition = HexGridMapPosition.findHexTile(character.getCurrentPosition());
            GameLoop.infoMap.setMoveable(currentPosition, false);
        }


        if(currentChar instanceof Player){
            currentProcessor = processorList.get("player");
            Gdx.input.setInputProcessor(currentProcessor);
        }
        else{
            currentProcessor = processorList.get("notPlayer");
            Gdx.input.setInputProcessor(currentProcessor);
            currentCharacter.setEndPosition(playerCharacter.getCurrentPosition());
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
        turnList.addAll(characterList);
        initialGetPlayer();
        nextTurn();
    }

    private void initializeProcessors(OrthographicCamera camera){
        IInputProcessor player = new ActivePlayerProcessor(camera);
        IInputProcessor notPlayer = new InactivePlayerProcessor(camera);

        processorList.put("notPlayer", notPlayer);
        processorList.put("player", player);

        this.currentProcessor = player;
        Gdx.input.setInputProcessor(player);
    }

    private void initialGetPlayer(){
        for (ICharacter character : characterList) {
            if(character instanceof Player){
                this.playerCharacter = character;
            }
        }
    }   
}   
