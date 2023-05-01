package inf112.saga.of.the.villeins.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import inf112.saga.of.the.villeins.Characters.ICharacter;
import inf112.saga.of.the.villeins.Characters.IPlayable;


public class GameUI {

    private final ShapeRenderer renderer;
    private final BitmapFont font;
    private final GlyphLayout layout;
    private final SpriteBatch spriteBatch;
    private final OrthographicCamera uiCamera;
    private final Stage stage;
    private final Skin skin;
    private final Table contextMenu;
    private Label actionPointsLabel;
    private Button endTurnButton;
    private TextureAtlas atlas;





    public GameUI(ShapeRenderer renderer, BitmapFont bitmapFont, SpriteBatch spriteBatch, OrthographicCamera uiCamera) {
        this.renderer = renderer;
        this.font = bitmapFont;
        this.spriteBatch = spriteBatch;
        this.uiCamera = uiCamera;
        this.stage = new Stage(new ScreenViewport(uiCamera));
        this.layout = new GlyphLayout();
        this.skin = new Skin();
        this.contextMenu = new Table(skin);

    }


    /**
     * Tegner en healthbar over hodet til en karakter.
     * Endrer seg dynamisk hver "frame" og flytter seg sammen med karakteren.
     * @param character Karakteren som "healthbaren" skal tegnes over.
     */
    public void drawHealthbar(ICharacter character) {

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

    /** Tegner "Skoren" til nåværende spiller på toppen av skjermen.
     *
     * @param player
     */
    public void drawScore(IPlayable player) {
        String scoreText = "Score: " + player.getScore();

        // Denne brukes for å finne bredden på teksten slik at den blir sentrert skikkelig, uansett hvordan vinduet
        // får endret dimensjoner.
        layout.setText(font, scoreText);

        // Regner ut posisjon på teksten.
        float textX = (Gdx.graphics.getWidth() - layout.width) / 2;
        float textY = Gdx.graphics.getHeight() - layout.height - 10;

        // Tegner "Scoren"
        spriteBatch.setProjectionMatrix(uiCamera.combined);
        spriteBatch.begin();
        font.draw(spriteBatch, scoreText, textX, textY);
        spriteBatch.end();
    }

    public void drawActionPoints(ICharacter character) {
        // Create a custom large font
        BitmapFont font = new BitmapFont();
        font.getData().setScale(2);

        String actionPointText = "Action Points: " + character.getCurrentActionPoints();
        layout.setText(font, actionPointText);

//        Label.LabelStyle labelStyle = new Label.LabelStyle();
//        labelStyle.font = largeFont;
//        labelStyle.fontColor = Color.WHITE;


//        float yPadding = 10f;
        float yPadding = Gdx.graphics.getHeight() / 20f;

        float xPadding = Gdx.graphics.getWidth() / 30f;

        float textX = (Gdx.graphics.getWidth() - layout.width) - xPadding;
        float textY = layout.height + yPadding;

        spriteBatch.setProjectionMatrix(uiCamera.combined);
        spriteBatch.begin();
        font.draw(spriteBatch, actionPointText, textX, textY);
        spriteBatch.end();
    }

    private void drawText(String text, float xPos, float yPos) {}



    public void drawUI(float deltaTime) {



        this.stage.act(deltaTime);
        this.stage.draw();
    }

    public void drawCenteredText(String text, float xPos, float yPos) {
        layout.setText(font, text);
        float textX = layout.width / 2f;
        float textY = layout.height / 2f;

    }


}
