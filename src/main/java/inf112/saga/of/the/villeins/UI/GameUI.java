package inf112.saga.of.the.villeins.UI;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer;
import inf112.saga.of.the.villeins.Characters.ICharacter;
import inf112.saga.of.the.villeins.Characters.IPlayable;
import inf112.saga.of.the.villeins.Controller.GameController;
import inf112.saga.of.the.villeins.Controller.PlayerAction;
import inf112.saga.of.the.villeins.Game.SagaOfTheVilleinsGame;
import inf112.saga.of.the.villeins.InputProcessors.BaseInputProcessor;
import inf112.saga.of.the.villeins.Utils.TilePosition;

import java.util.List;


/**
 * Klasse ansvarlig for å holde på det av kode relatert til UI'en som spilleren må interagere med.
 */
public class GameUI implements AbstractGameUI {
    private final ShapeRenderer renderer;
    private final Stage stage;
    private final Skin skin;
    private Button endTurnButton;
    private Label actionPointLabel;
    private Label activeCharacterLabel;
    private Table actionPointTable;
    private Table scoreTable;
    private Label scoreLabel;
    private GameController gameController;
    private ContextMenu contextMenu;
    private Stage gameCameraStage;
    private BaseInputProcessor playerActiveProcessor;
    private Vector2 clickedWorldCoordinate;
    private boolean leftMouseClicked;
    private Table playerStatsTable;


    public GameUI(SagaOfTheVilleinsGame game,
                   Stage uiStage,
                   Stage gameCameraStage,
                   GameController controller,
                   BaseInputProcessor playerActiveProcessor) {

        this.playerActiveProcessor = playerActiveProcessor;
        this.renderer = game.shapeRenderer;
        this.stage = uiStage;
        this.gameCameraStage = gameCameraStage;
        this.skin = game.getDefaultSkin();
        this.gameController = controller;
        this.contextMenu = new ContextMenu(this.skin);
        this.leftMouseClicked = false;

        // Initierer knappene for scoren, "end turn" knappen og gjenværende action points.
        this.initAPAndEndTurnButton();
        this.initScore();
        this.initContextMenu();
        this.initStatsTable();
    }


    public void showMessage(String message, float duration) {
        Label messageLabel = new Label(message, skin);
        messageLabel.setColor(Color.FIREBRICK);

        Table messageTable = new Table(skin);
        messageTable.setFillParent(true);
        messageTable.center();
        messageTable.add(messageLabel);
        stage.addActor(messageTable);

        // Sletter meldingen på skjermen etter den angitte tiden.
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                messageTable.remove();
            }
        }, duration);
    }


    /** Tegner alle elementene som tilhører UI'en. Healthbar, score, endturn knapp og gjenværende AP.
     * @param deltaTime
     * @param playerCharacter
     * @param characterList
     */
    @Override
    public void drawUI(float deltaTime, IPlayable playerCharacter, List<ICharacter> characterList) {
        this.updateAPAndEndTurnButton(gameController.getCurrentCharacter());
        this.updateScore(playerCharacter);

		this.leftMouseClicked = gameController.getCurrentProcessor().isLeftMouseClicked();
		this.clickedWorldCoordinate = playerActiveProcessor.getRightClickCoordinates();

        for (ICharacter character : characterList) {
            this.drawHealthbar(character);
        }

        this.updateContextMenu();
        this.hideContextMenuIfLeftClicked();
        this.stage.act(deltaTime);
        this.stage.draw();
        this.gameCameraStage.act(deltaTime);
        this.gameCameraStage.draw();
    }

    private void updateContextMenu() {
        if (this.clickedWorldCoordinate == null) return;

        contextMenu.setVisible(true);
        contextMenu.setPosition(clickedWorldCoordinate.x, clickedWorldCoordinate.y);

        TilePosition tile = TilePosition.findHexTile(clickedWorldCoordinate);
        String tileInfo = String.format("Clicked Tile: (%sx, %sy)", tile.x(), tile.y());
        contextMenu.setTileInfo(tileInfo);

        ICharacter character = TilePosition.getCharacterOnCoordinate(clickedWorldCoordinate);
        contextMenu.updateCharacterStats(character);

        gameController.setPositionToPerformAction(clickedWorldCoordinate);
        clickedWorldCoordinate = null;
    }

    /** Oppdaterer antall action points som vises utifra den aktive karakteren.
     * @param character
     */
    private void updateAPAndEndTurnButton(ICharacter character) {
        String actionPointText = "Action Points remaining: " + character.getCurrentActionPoints();
        String currentCharacter = "Current Character: " + character;
        this.actionPointLabel.setText(actionPointText);
        this.activeCharacterLabel.setText(currentCharacter);
    }

    /** Oppdaterer scoren til spilleren.
     * @param player
     */
    private void updateScore(IPlayable player) {
        String scoreText = "Score: " + player.getScore();
        this.scoreLabel.setText(scoreText);
    }


    /**
     * Initierer stats menyen.
     */
    private void initStatsTable() {
        IPlayable player = this.gameController.getPlayerCharacter();

        playerStatsTable = new Table(skin);
        playerStatsTable.left();
        playerStatsTable.pad(10);
        playerStatsTable.setFillParent(true);

        playerStatsTable.add(new Label("Player Stats", skin)).colspan(2);
        playerStatsTable.row();

        // Add player stats here
        playerStatsTable.add(new Label("Max Health: ", skin));
        playerStatsTable.add(new Label(String.valueOf(player.getMaxHealth()), skin));
        playerStatsTable.row();

        playerStatsTable.add(new Label("Strength: ", skin));
        playerStatsTable.add(new Label(String.valueOf(player.getStrength()), skin));
        playerStatsTable.row();

        playerStatsTable.add(new Label("Defense: ", skin));
        playerStatsTable.add(new Label(String.valueOf(player.getDefense()), skin));
        playerStatsTable.row();

        stage.addActor(playerStatsTable);
    }

    private void initContextMenu() {
        // Definerer knappene og hendelsene som skal skje.
        contextMenu.getAttackButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Angreps input her.
                if (!gameController.getCurrentCharacter().equals(gameController.getPlayerCharacter())) {
                    return;
                }

                if (gameController.getCurrentCharacter().getCurrentActionPoints() == 0) {
                    showMessage("You do not have enough action points to attack.\n" +
                                        "Press End Turn when ready", 2);
                } else {
                    gameController.setPlayerAction(PlayerAction.ATTACK);
                    contextMenu.setVisible(false);
                }

            }
        });

        contextMenu.getMoveButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (gameController.getCurrentCharacter().getCurrentActionPoints() == 0) {
                    showMessage("You do not have enough action points to move. \n" +
                                        "Press End Turn when ready", 2);
                } else {
                    gameController.setPlayerAction(PlayerAction.MOVE);
                    contextMenu.setVisible(false);
                }
            }
        });

        this.contextMenu.setVisible(false);
        this.gameCameraStage.addActor(contextMenu);
    }

    /**
     * Lager en knapp der spilleren kan gå videre til neste tur. Lager også en stripe med tekst som viser hvor mange
     * Action points den aktive karakteren har igjen.
     */
    private void initAPAndEndTurnButton() {
        actionPointLabel = new Label("", skin);
        endTurnButton = new TextButton("End Turn", skin, "default");
        endTurnButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameController.endTurnFromUI();
            }
        });

        actionPointTable = new Table(skin);
        actionPointTable.setFillParent(true);
        actionPointTable.bottom().right();
        actionPointTable.pad(10);
        actionPointTable.add(actionPointLabel).row();
        actionPointTable.add(endTurnButton).width(150).height(50).pad(5);
        stage.addActor(actionPointTable);
    }
    private void initScore() {
        this.scoreLabel = new Label("", skin);
        this.activeCharacterLabel = new Label("", skin);
        this.scoreTable = new Table(skin);
        this.scoreTable.setFillParent(true);
        this.scoreTable.top();
        this.scoreTable.add(scoreLabel).row();
        this.scoreTable.add(activeCharacterLabel);
        this.stage.addActor(scoreTable);
    }

    /**
     * Tegner en healthbar over hodet til en karakter.
     * Endrer seg dynamisk hver "frame" og flytter seg sammen med karakteren.
     * @param character Karakteren som "healthbaren" skal tegnes over.
     */
    private void drawHealthbar(ICharacter character) {
        float barWidth = 100f;
        float barHeight = 10f;

        float healthBarX = character.getCurrentPosition().x - 50f;
        float healthBarY = character.getCurrentPosition().y + 100f;

        // Henter gjenværende liv som prosent av maks.
        float currentHealthPercentage = (float) character.getMaxHealth() / character.getCurrentHealth();

        // Margin for bakgrunns "healthbaren"
        float paddingX = 2f;
        float paddingY = 2f;

        renderer.begin(ShapeRenderer.ShapeType.Filled);

        // Tegner "Healthbar" som er i bakgrunnen.
        renderer.setColor(1, 0, 0, 1);
        renderer.rect(healthBarX, healthBarY, barWidth, barHeight);

        // Tegner "Healthbar" som viser gjenværende liv.
        renderer.setColor(0, 1, 0, 1);
        renderer.rect(
                healthBarX + paddingX,
                healthBarY + paddingY,
                (barWidth - 2 * paddingX) / currentHealthPercentage,
                barHeight - 2 * paddingY);
        renderer.end();
    }

    /**
     * Hjelpe funksjon som gjemmer "action" menyen hvis venstre knappen har blitt trykt.
     */
    private void hideContextMenuIfLeftClicked() {
        if (this.leftMouseClicked) {
            this.contextMenu.setVisible(false);
            this.leftMouseClicked = false;
        }
    }
}
