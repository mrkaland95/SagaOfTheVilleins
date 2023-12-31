package inf112.saga.of.the.villeins.Game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class SagaOfTheVilleinsGame extends Game {
    SpriteBatch spriteBatch;
    ShapeRenderer shapeRenderer;
    BitmapFont font;

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        font = new BitmapFont();
        setScreen(new MainMenuScreen(this));
    }

    @Override
    public void dispose () {
        spriteBatch.dispose();
        shapeRenderer.dispose();
        font.dispose();
    }
}
