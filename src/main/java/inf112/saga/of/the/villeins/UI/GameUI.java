package inf112.saga.of.the.villeins.UI;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;

import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import inf112.saga.of.the.villeins.Characters.ICharacter;
import inf112.saga.of.the.villeins.Characters.IPlayable;
import inf112.saga.of.the.villeins.Game.SagaOfTheVilleinsGame;


public class GameUI {
    private final ShapeRenderer renderer;
    private final BitmapFont font;
    private final GlyphLayout layout;
    private final SpriteBatch spriteBatch;
    private final Stage stage;
    private final Skin skin;
    private final Table contextMenu;
    private Label actionPointLabel;
    private Button endTurnButton;
    private TextureAtlas atlas;
    private Table actionPointTable;
    private Table scoreTable;
    private Label scoreLabel;



    public GameUI(SagaOfTheVilleinsGame game, Stage uiStage) {
        this.renderer = game.shapeRenderer;
        this.font = game.bitmapFont;
        this.spriteBatch = game.spriteBatch;
        this.stage = uiStage;
        this.layout = new GlyphLayout();
        this.skin = game.getDefaultSkin();
        this.contextMenu = new Table(skin);

        // Initierer knappene for scoren, "end turn" knappen og gjenværende action points.
        this.initAPAndEndTurnButton();
        this.initScore();
    }

    public void drawUI(float deltaTime, IPlayable playerCharacter) {
        this.updateAPAndEndTurnButton(playerCharacter);
        this.updateScore(playerCharacter);

        this.stage.act(deltaTime);
        this.stage.draw();
    }



    private void initAPAndEndTurnButton() {
        actionPointLabel = new Label("", skin);
        endTurnButton = new TextButton("End Turn", skin, "small");
        endTurnButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                tempEndturn();
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
        String actionPointText = "Action Points: " + character.getCurrentActionPoints();
        actionPointLabel.setText(actionPointText);
    }

    private void initScore() {
        this.scoreLabel = new Label("", skin);
        this.scoreTable = new Table(skin);
        this.scoreTable.setFillParent(true);
        this.scoreTable.top();
        scoreTable.add(scoreLabel).row();
        stage.addActor(scoreTable);
    }

    private void updateScore(IPlayable player) {
        String scoreText = "Score: " + player.getScore();
        scoreLabel.setText(scoreText);
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

        // TODO For å gjøre denne mer dynamisk ved f.eks store sprites, så må hver "frame" ha samme dimensjoner uansett hvilken type det er.
        // TODO altså en frame av "Idle" eller "Moving" må ha samme dimensjoner.
        float healthBarX = character.getCurrentPosition().x - 50f;
        float healthBarY = character.getCurrentPosition().y + 100f;


        // Gets the percentage of current health for the healthbar.
        float currentHealthPercentage = (float) character.getMaxHealth() / character.getCurrentHealth();

        // Padding for the background healthbar
        float paddingX = 2f;
        float paddingY = 2f;

        renderer.begin(ShapeRenderer.ShapeType.Filled);

        // "Healthbar" som er i bakgrunnen.
        renderer.setColor(1, 0, 0, 1);
        renderer.rect(healthBarX, healthBarY, barWidth, barHeight);

        // "Healthbar" som viser gjenværende liv.
        renderer.setColor(0, 1, 0, 1);
        renderer.rect(
                healthBarX + paddingX,
                healthBarY + paddingY,
                (barWidth - 2 * paddingX) / currentHealthPercentage,
                barHeight - 2 * paddingY);
        renderer.end();
    }




    private void tempEndturn() {
        System.out.println("End Turn was clicked");
    }
}
