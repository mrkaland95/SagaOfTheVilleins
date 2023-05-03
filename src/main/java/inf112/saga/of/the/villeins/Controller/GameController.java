package inf112.saga.of.the.villeins.Controller;

import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
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
    private BaseInputProcessor currentProcessor;
	private InputMultiplexer playerInputMultiplexer;
	private InputMultiplexer computerInputMultiplexer;
    private Stage uiStage;
    private Stage gameCameraStage;
    private PlayerAction playerAction;
    private Vector2 positionToPerformAction;

    public GameController(List<ICharacter> initialCharacterList,
                          IPlayable playerChar,
                          ActivePlayerProcessor activePlayerProcessor,
                          InactivePlayerProcessor inactivePlayerProcessor,
                          Stage uiStage,
                          Stage gameCameraStage
        ) {
        this.characterList = initialCharacterList;
        this.turnList = new LinkedList<>();
        this.currentProcessor = null;
        this.playerCharacter = playerChar;
        this.currentCharacter = null;
        this.playerActiveProcessor = activePlayerProcessor;
        this.computerActiveProcessor = inactivePlayerProcessor;
        this.playerInputMultiplexer = new InputMultiplexer();
        this.computerInputMultiplexer = new InputMultiplexer();
        this.uiStage = uiStage;
        this.gameCameraStage = gameCameraStage;
        this.playerAction = PlayerAction.IDLE;
        initializeGame();
    }


    /*
     * This method should be added to render function in Game.java to keep updating the controller with correct values.
     * Could be used to handle "Action Points" or similar, to handle when to end someones turn.
     */
    public void update(List<ICharacter> currentCharList) {

        characterList = currentCharList;
        boolean playerIsAlive = isAlive(playerCharacter);
        if(getPlayerCount() == 1 && playerIsAlive) {
            gameState = GameState.MAP_WON;
        }
        else if(!playerIsAlive){
            gameState = GameState.GAME_OVER;
        }

        if(currentCharacter instanceof IPlayable) {
            if (currentCharacter.getCharacterState() == CharacterState.IDLE) {
                if (playerAction == PlayerAction.MOVE) {
                    currentCharacter.setEndPosition(positionToPerformAction);
                    positionToPerformAction = null;
                    playerAction = PlayerAction.IDLE;

                } else if (playerAction == PlayerAction.ATTACK) {
                    currentCharacter.attack(positionToPerformAction);
                    positionToPerformAction = null;
                    playerAction = PlayerAction.IDLE;
                }
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


    /**
     * Går til neste tur hvis spilleren er aktiv og ikke utfører en hendelse
     */
    public void endTurnFromUI() {
        if (currentCharacter.getCharacterState() == CharacterState.IDLE && currentCharacter instanceof IPlayable) {
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
     * Definerer også hvilket "kamera" og stage som tar i mot input først.
    */
    private void initializeGame() {
        gameState = GameState.PLAYING;
        this.playerInputMultiplexer.addProcessor(gameCameraStage);
        this.playerInputMultiplexer.addProcessor(playerActiveProcessor);
        this.playerInputMultiplexer.addProcessor(uiStage);


        this.playerInputMultiplexer.addProcessor(gameCameraStage);
        this.computerInputMultiplexer.addProcessor(computerActiveProcessor);
        this.computerInputMultiplexer.addProcessor(uiStage);

        this.currentProcessor = playerActiveProcessor;
        Gdx.input.setInputProcessor(playerInputMultiplexer);

        turnList.addAll(characterList);
        nextTurn(characterList);
    }

    private int getPlayerCount() {
        return this.characterList.size();
    }

    private boolean isAlive(ICharacter character){
        return character != null;
    }

    public void setPlayerAction(PlayerAction playerAction) {
        this.playerAction = playerAction;
    }
    public void setPositionToPerformAction(Vector2 coordinate) {
        if (playerCharacter.getCharacterState() == CharacterState.IDLE) {
            this.positionToPerformAction = coordinate;
        }
    }
    public BaseInputProcessor getCurrentProcessor() {
        return currentProcessor;
    }
}
