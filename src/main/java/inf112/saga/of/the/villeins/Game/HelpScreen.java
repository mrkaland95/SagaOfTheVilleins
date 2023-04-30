package inf112.saga.of.the.villeins.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class HelpScreen implements Screen {

    private SagaOfTheVilleinsGame game;
    private Stage stage;
    private Texture background;
    private BitmapFont font;
    private GlyphLayout layout;
    private Skin skin;
    private Table table;


    public HelpScreen(SagaOfTheVilleinsGame game) {
        this.game = game;
        this.stage = new Stage(new ScreenViewport(), game.spriteBatch);
        this.background = game.getHelpPageBackground();
        this.font = new BitmapFont();
        this.layout = new GlyphLayout();
        this.skin = game.getDefaultSkin();
        this.table = new Table(skin);
        Gdx.input.setInputProcessor(stage);

        background.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        Button mainMenuButton = new TextButton("Return to main menu", skin, "small");
        mainMenuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                    // Change the screen to the game loop screen
                game.setScreen(new MainMenuScreen(game));
            }
        });

        table.add(mainMenuButton).expandX().center().row();
        table.setFillParent(true);
        table.bottom().right();
        stage.addActor(table);

    }


    @Override
    public void render(float deltaTime) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.spriteBatch.begin();
        game.spriteBatch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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
