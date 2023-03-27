package inf112.saga.of.the.villeins.Controller;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import inf112.saga.of.the.villeins.Characters.ICharacter;


public class GameUI {

    private final ShapeRenderer renderer;

    public GameUI(ShapeRenderer renderer) {
        this.renderer = renderer;
    }


    public void drawHealthbar(ICharacter character) {
        // TODO Dette burde antageligvis flyttes ut til "GameUI" klassen eller noe sånt.

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

        // Background bar/total health bar
        renderer.setColor(1, 0, 0, 1);
        renderer.rect(healthBarX, healthBarY, barWidth, barHeight);

        // Current health bar.
        renderer.setColor(0, 1, 0, 1);
        renderer.rect(
                healthBarX + paddingX,
                healthBarY + paddingY,
                (barWidth - 2 * paddingX) / currentHealthPercentage,
                barHeight - 2 * paddingY);
        renderer.end();
    }
}
