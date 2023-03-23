package inf112.saga.of.the.villeins.Controller;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import inf112.saga.of.the.villeins.Characters.ICharacter;


public class GameUI {

    private ShapeRenderer renderer;



    public GameUI(ShapeRenderer renderer) {
        this.renderer = renderer;
    }


    private void drawHealthbar(TextureRegion sprite, ICharacter character) {
    float totalBarWidth = sprite.getRegionWidth();
    float characterHeight = sprite.getRegionHeight();
    float totalBarHeight = 5;
    int currentHealthPercentage = character.getMaxHealth() / character.getCurrentHealth();
    float currentHealthBarWidth = totalBarWidth * currentHealthPercentage;
    float healthBarX = character.getCurrentPosition().x;
    float healthBarY = character.getCurrentPosition().y + characterHeight + 10;
    renderer.begin(ShapeRenderer.ShapeType.Filled);
    renderer.setColor(1, 0, 0, 1);
//        renderer.rect(healthBarX, healthBarY, totalBarWidth, totalBarHeight);
//        renderer.setColor(0, 1, 0, 1);
//        renderer.rect(healthBarX, healthBarY, currentHealthBarWidth, totalBarHeight);
    renderer.end();
    }


}
