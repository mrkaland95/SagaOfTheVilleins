package inf112.saga.of.the.villeins.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class HelpScreen implements Screen {

    private SagaOfTheVilleinsGame game;
    private Stage stage;
    private Texture menuBackground;
    private BitmapFont font;
    private GlyphLayout layout;


    public HelpScreen(SagaOfTheVilleinsGame game) {
        this.game = game;
        this.stage = new Stage(new ScreenViewport(), game.spriteBatch);
        this.menuBackground = game.menuBackground2;
        this.font = new BitmapFont();
        this.layout = new GlyphLayout();

        Gdx.input.setInputProcessor(stage);
        menuBackground.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }


    @Override
    public void render(float deltaTime) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.spriteBatch.begin();
        game.spriteBatch.draw(menuBackground, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.spriteBatch.end();

        stage.act(deltaTime);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }


    @Override
    public void dispose() {

    }

    @Override
    public void hide() {}

    @Override
    public void show() {}
}
