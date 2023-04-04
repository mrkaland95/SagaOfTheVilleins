package inf112.saga.of.the.villeins.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import inf112.saga.of.the.villeins.Characters.ICharacter;
import inf112.saga.of.the.villeins.Characters.IPlayable;

import java.util.List;


public class GameUI {

    private final ShapeRenderer renderer;
    private final BitmapFont font;
    private final GlyphLayout layout;
    private final SpriteBatch spriteBatch;
    private final OrthographicCamera uiCamera;

    public GameUI(ShapeRenderer renderer, BitmapFont bitmapFont, SpriteBatch spriteBatch, OrthographicCamera uiCamera) {
        this.renderer = renderer;
        this.font = bitmapFont;
        this.spriteBatch = spriteBatch;
        this.uiCamera = uiCamera;
        this.layout = new GlyphLayout();
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

    public void drawScore(IPlayable player) {
        String scoreText = "Score: " + player.getScore();
        layout.setText(font, scoreText);

        float textX = (Gdx.graphics.getWidth() - layout.width) / 2;
        float textY = Gdx.graphics.getHeight() - layout.height - 10;

        spriteBatch.setProjectionMatrix(uiCamera.combined);

        spriteBatch.begin();
        font.draw(spriteBatch, scoreText, textX, textY);
        spriteBatch.end();
    }








    public void drawUI(List<ICharacter> characterList) {

    }




}
