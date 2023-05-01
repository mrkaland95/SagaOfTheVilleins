package inf112.saga.of.the.villeins.Controller;

import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

import com.badlogic.gdx.scenes.scene2d.Stage;
import inf112.saga.of.the.villeins.Characters.CharacterState;
import inf112.saga.of.the.villeins.Characters.ICharacter;
import inf112.saga.of.the.villeins.Characters.IPlayable;
import inf112.saga.of.the.villeins.InputProcessors.ActivePlayerProcessor;
import inf112.saga.of.the.villeins.InputProcessors.BaseInputProcessor;
import inf112.saga.of.the.villeins.InputProcessors.InactivePlayerProcessor;

public class GameController {
    private List<ICharacter> characterList;
    private LinkedList<ICharacter> turnList;
    private IPlayable playerCharacter;
    private ICharacter currentCharacter;
    private GameState gameState;
    private InactivePlayerProcessor computerActiveProcessor;
    private ActivePlayerProcessor playerActiveProcessor;
    public BaseInputProcessor currentProcessor;

	private InputMultiplexer playerInputMultiplexer;
	private InputMultiplexer computerInputMultiplexer;
    private Stage uiStage;

    public GameController(List<ICharacter> initialCharacterList, OrthographicCamera gameCamera, IPlayable playerChar, Stage stage) {
        this.characterList = initialCharacterList;
        this.turnList = new LinkedList<>();
        this.currentProcessor = null;
        this.playerCharacter = playerChar;
        this.currentCharacter = null;
        this.playerActiveProcessor = new ActivePlayerProcessor(gameCamera);
        this.computerActiveProcessor = new InactivePlayerProcessor(gameCamera);
        this.playerInputMultiplexer = new InputMultiplexer();
        this.computerInputMultiplexer = new InputMultiplexer();
        this.uiStage = stage;

        initializeGame();
    }


    /*
     * This method should be added to render function in Game.java to keep updating the controller with correct values.
     * Could be used to handle "Action Points" or similar, to handle when to end someones turn.
     */
    public void update(List<ICharacter> currentCharList){
        characterList = currentCharList;
        boolean playerIsAlive = isAlive(playerCharacter);
        if(playerCount() == 1 && playerIsAlive) {
            gameState = GameState.MAP_WON;
        }
        else if(!playerIsAlive){
            gameState = GameState.GAME_OVER;
        }

        if(currentCharacter instanceof IPlayable) {
            // Spilleren sin input blir utført her.
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


    public void endTurnFromUI() {
        if (currentCharacter.getCharacterState() == CharacterState.IDLE) {
            nextTurn(characterList);
        }
    }


    /*
     * Sets the turn to the next charcter in turnList
     * Should also change which processor in use based on if its a playerturn or AI-turn
     * Maybe also use turnConter if needed
     */
    private void nextTurn(List<ICharacter> currentCharList) {
        ICharacter currentTurnChar = turnList.poll();
        if (currentCharList.contains(currentTurnChar)) {
            turn(currentTurnChar);
            currentTurnChar.resetActionPoints();
            turnList.add(currentTurnChar);
        } else {
            nextTurn(currentCharList);
        }
    }
    public IPlayable getPlayerCharacter() {
        return this.playerCharacter;
    }
    public GameState getGameState(){
        return gameState;
    }

    public BaseInputProcessor getCurrentProcessor() {
        return currentProcessor;
    }

    public ICharacter getCurrentCharacter() {
        return currentCharacter;
    }

    private void turn(ICharacter currentChar){
        this.currentCharacter = currentChar;

        if(currentChar instanceof IPlayable){
            currentProcessor = playerActiveProcessor;
            Gdx.input.setInputProcessor(this.playerInputMultiplexer);
        }
        else{
            this.currentCharacter.setTargetCharacter(playerCharacter);
            currentProcessor = computerActiveProcessor;
            Gdx.input.setInputProcessor(this.computerInputMultiplexer);
            currentCharacter.setEndPosition(playerCharacter.getCurrentPosition());
        }
    }


    /**
     * Initialiser spillet, inputhandlere og initierer "turn" listen for å håndtere hvilken karakter som er aktiv.
    */
    private void initializeGame() {
        gameState = GameState.PLAYING;
        this.playerInputMultiplexer.addProcessor(uiStage);
        this.playerInputMultiplexer.addProcessor(playerActiveProcessor);

        this.computerInputMultiplexer.addProcessor(uiStage);
        this.computerInputMultiplexer.addProcessor(computerActiveProcessor);

        this.currentProcessor = playerActiveProcessor;
        Gdx.input.setInputProcessor(playerInputMultiplexer);

        turnList.addAll(characterList);
        nextTurn(characterList);
    }

    private int playerCount(){
        return this.characterList.size();
    }

    private boolean isAlive(ICharacter character){
        return character != null;
    }


    public enum ActionToPerform {
        ATTACK,
        MOVE,
        EXAMINE
    }

}
