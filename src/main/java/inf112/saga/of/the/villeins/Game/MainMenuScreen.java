package inf112.saga.of.the.villeins.Game;

import com.badlogic.gdx.*;
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

public class MainMenuScreen implements Screen {
    private SagaOfTheVilleinsGame game;
    private Stage stage;
    private Texture menuBackground;
    private BitmapFont font;
    private GlyphLayout layout;
    private Skin mySkin;
    private Table menuTable;

    public MainMenuScreen(SagaOfTheVilleinsGame game) {
        this.game = game;
        this.stage = new Stage(new ScreenViewport(), game.spriteBatch);
        this.menuBackground = this.game.getMenuBackground();
        this.font = new BitmapFont();
        this.layout = new GlyphLayout();
        this.mySkin = new Skin(Gdx.files.internal("./assets/Skins/glassy/skin/glassy-ui.json"));
        this.menuTable = new Table(mySkin);
        Gdx.input.setInputProcessor(stage);
        this.menuBackground.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);


        Button startGameButton = new TextButton("Start Game", mySkin, "small");
        startGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                    // Change the screen to the game loop screen
                game.setScreen(new GameScreen(game, game.getCurrentMap(), game.getCurrentStage()));
            }
        });

        Button helpScreenButton = new TextButton("Help Page", mySkin, "small");

        helpScreenButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                    // Change the screen to the game loop screen
                game.setScreen(new HelpScreen(game));
            }
        });

        // Legg knappene til tabellen og deretter tegn tabellen på midten av skjermen.
        menuTable.add(startGameButton).expandX().center().row();
        menuTable.add(helpScreenButton).expandX().center().row();
        menuTable.setFillParent(true);
        menuTable.center();

        stage.addActor(menuTable);
    }

    @Override
    public void show() {
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
    public void hide() {
    }

    @Override
    public void dispose() {
    }
}