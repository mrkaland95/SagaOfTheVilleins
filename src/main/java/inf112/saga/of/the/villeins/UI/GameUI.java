package inf112.saga.of.the.villeins.UI;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;

import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import inf112.saga.of.the.villeins.Characters.ICharacter;
import inf112.saga.of.the.villeins.Characters.IPlayable;
import inf112.saga.of.the.villeins.Controller.GameController;
import inf112.saga.of.the.villeins.Controller.PlayerAction;
import inf112.saga.of.the.villeins.Game.SagaOfTheVilleinsGame;
import inf112.saga.of.the.villeins.InputProcessors.BaseInputProcessor;
import inf112.saga.of.the.villeins.MovementUtils.TilePosition;

import java.util.List;


public class GameUI {
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
    private Vector2 clickedWorldCoordinate;
    private boolean leftMouseClicked;
    private Stage gameCameraStage;
    private BaseInputProcessor playerActiveProcessor;



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
    }

    /** Tegner alle elementene som tilhører UI'en. Healthbar, score, endturn knapp og gjenværende AP.
     * @param deltaTime
     * @param playerCharacter
     * @param characterList
     */
    public void updateUI(float deltaTime, IPlayable playerCharacter, List<ICharacter> characterList) {
        this.updateAPAndEndTurnButton(gameController.getCurrentCharacter());
        this.updateScore(playerCharacter);

		leftMouseClicked = gameController.getCurrentProcessor().isLeftMouseClicked();
		clickedWorldCoordinate = playerActiveProcessor.getRightClickCoordinates();

        for (ICharacter character : characterList) {
            this.drawHealthbar(character);
        }

        this.updateContextMenu();
        this.hideContextMenu();
        this.stage.act(deltaTime);
        this.stage.draw();
    }

    public void updateTileInfo(String info) {
        contextMenu.setTileInfo(info);
    }

    private void updateContextMenu() {
        if (this.clickedWorldCoordinate == null) return;

        Vector3 projectedCoordinate = this.gameCameraStage.getCamera().project(new Vector3(clickedWorldCoordinate.x, clickedWorldCoordinate.y, 0));

        contextMenu.setVisible(true);
        contextMenu.setPosition(projectedCoordinate.x, projectedCoordinate.y);

        String tileInfo = "" + TilePosition.findHexTile(clickedWorldCoordinate);
        contextMenu.setTileInfo(tileInfo);
        gameController.setPositionToPerformAction(clickedWorldCoordinate);
        clickedWorldCoordinate = null;
    }

    private void initContextMenu() {
        contextMenu.getAttackButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Angreps input her.
                gameController.setPlayerAction(PlayerAction.ATTACK);
                contextMenu.setVisible(false);
            }
        });

        contextMenu.getMoveButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Bevegelses logikk her.
                gameController.setPlayerAction(PlayerAction.MOVE);
                contextMenu.setVisible(false);
            }
        });

        contextMenu.getExamineButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // "Examine" logikk her.
                gameController.setPlayerAction(PlayerAction.EXAMINE);
                contextMenu.setVisible(false);
            }
        });
        this.contextMenu.setVisible(false);
        this.stage.addActor(contextMenu);
    }

    private void hideContextMenu() {
        if (this.leftMouseClicked) {
            this.contextMenu.setVisible(false);
            this.leftMouseClicked = false;
        }
    }


    private void initAPAndEndTurnButton() {
        actionPointLabel = new Label("", skin);
        endTurnButton = new TextButton("End Turn", skin, "small");
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
        /**
     * Vanligvis ville jeg ha splittet disse opp i to metoder, men fordi jeg vil ha knappen og informasjonen i en tabell
     * Må det defineres i samme metode.
     */
    private void updateAPAndEndTurnButton(ICharacter character) {
        String actionPointText = "Action Points remaining: " + character.getCurrentActionPoints();
        String currentCharacter = "Current Character: " + character;
        this.actionPointLabel.setText(actionPointText);
        this.activeCharacterLabel.setText(currentCharacter);
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

    private void updateScore(IPlayable player) {
        String scoreText = "Score: " + player.getScore();
        this.scoreLabel.setText(scoreText);
    }


    /**
     * Tegner en healthbar over hodet til en karakter.
     * Endrer seg dynamisk hver "frame" og flytter seg sammen med karakteren.
     * @param character Karakteren som "healthbaren" skal tegnes over.
     */
    public void drawHealthbar(ICharacter character) {
        // TODO: 01.05.2023 flytt kallet på denne inn til "update" metoden
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

    public void setClickedWorldCoordinate(Vector2 clickedWorldCoordinate) {
        this.clickedWorldCoordinate = clickedWorldCoordinate;
    }
    public void setLeftMouseClicked(boolean leftMouseClicked) {
        this.leftMouseClicked = leftMouseClicked;
    }

}
