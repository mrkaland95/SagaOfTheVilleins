package inf112.saga.of.the.villeins.Controller;

import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

import inf112.saga.of.the.villeins.Characters.CharacterState;
import inf112.saga.of.the.villeins.Characters.ICharacter;
import inf112.saga.of.the.villeins.Characters.IPlayable;
import inf112.saga.of.the.villeins.Characters.Player;
import inf112.saga.of.the.villeins.InputProcessors.ActivePlayerProcessor;
import inf112.saga.of.the.villeins.InputProcessors.BaseInputProcessor;
import inf112.saga.of.the.villeins.InputProcessors.IInputProcessor;
import inf112.saga.of.the.villeins.InputProcessors.InactivePlayerProcessor;

public class GameController {
    private List<ICharacter> characterList;
    private LinkedList<ICharacter> turnList;
    private ICharacter playerCharacter;
    private ICharacter currentCharacter;
    private GameState gameState;
    private InactivePlayerProcessor inActiveProcessor;
    private ActivePlayerProcessor activeProcessor;
    public BaseInputProcessor currentProcessor;


    public GameController(List<ICharacter> initialCharacterList, OrthographicCamera camera, ICharacter playerChar){
        this.characterList = initialCharacterList;
        this.turnList = new LinkedList<>();
        this.currentProcessor = null;
        this.playerCharacter = playerChar;
        this.currentCharacter = null;
        this.activeProcessor = new ActivePlayerProcessor(camera);
        this.inActiveProcessor = new InactivePlayerProcessor(camera);

        initializeGame(camera);
    }

    private int playerCount(){
        return this.characterList.size();
    }

    public void update(List<ICharacter> currentCharList){
        /*
         * This method should be added to render function in Game.java to keep updating the controller with correct values.
         * Could be used to handle "Action Points" or similar, to handle when to end someones turn.
         * 
         */

        characterList = currentCharList;
        if(playerCount() == 1 && getPlayer()){
            gameState = GameState.MAP_WON;
        }
        else if(!getPlayer()){
            gameState = GameState.GAME_OVER;
        }

        if(currentCharacter instanceof IPlayable) {
            Vector2 movePosition = currentProcessor.getRightClickCoordinates();
            Vector2 attackPosition = currentProcessor.getLeftClickCoordinates();
            if (attackPosition != null) {
                currentCharacter.attack(attackPosition);
            }
            else if (movePosition != null && currentCharacter.getCharacterState() == CharacterState.IDLE) {
                currentCharacter.setEndPosition(movePosition);
            }
            if(this.currentProcessor.checkTurn()){
                this.currentProcessor.endTurn();
                nextTurn(currentCharList);        
            }    
        }
        else if(currentCharacter.getCurrentActionPoints() == 0){
            nextTurn(currentCharList);
        }
    }

    public ICharacter getPlayerCharacter() {
        return this.playerCharacter;
    }

    private void turn(ICharacter currentChar){
        this.currentCharacter = currentChar;

        if(currentChar instanceof IPlayable){
            currentProcessor = activeProcessor;
            Gdx.input.setInputProcessor(currentProcessor);
        }
        else{
            this.currentCharacter.setTargetCharacter(playerCharacter);
            currentProcessor = inActiveProcessor;
            Gdx.input.setInputProcessor(currentProcessor);
            currentCharacter.setEndPosition(playerCharacter.getCurrentPosition());
        }
    }

    private void nextTurn(List<ICharacter> currentCharList){
        /*
         * Sets the turn to the next charcter in turnList
         * Should also change which processor in use based on if its a playerturn or AI-turn
         * Maybe also use turnConter if needed
         */

        ICharacter currentTurnChar = turnList.poll();
        if(currentCharList.contains(currentTurnChar)){
            turn(currentTurnChar);
            currentTurnChar.setCurrentActionPoints(2);
            turnList.add(currentTurnChar);
        }
        else{
            nextTurn(currentCharList);
        }     
    }

        /*
        *   This method initializes everything needed for the game.
        *
        *   Add new processors to this method as they are created.
        */
    private void initializeGame(OrthographicCamera camera){
        gameState = GameState.PLAYING;
        initializeProcessors(camera);
        turnList.addAll(characterList);
        getPlayer();
        nextTurn(characterList);
    }

    private void initializeProcessors(OrthographicCamera camera){
        this.currentProcessor = activeProcessor;
        Gdx.input.setInputProcessor(activeProcessor);
    }

    private boolean getPlayer(){
        for (ICharacter character : characterList) {
            if(character instanceof Player){
                this.playerCharacter = character;
                return true;
            }
        }
        return false;
    }

    public GameState getGameState(){
        return gameState;
    }
}   
