package inf112.saga.of.the.villeins.Game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MainMenuScreen implements Screen {
    private SagaOfTheVilleinsGame game;
    private Stage stage;
    private final Texture menuBackground;
    private Sprite sprite;
    private Camera camera;


    public MainMenuScreen(SagaOfTheVilleinsGame game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
//        camera = new OrthographicCamera(1, screenHeight / screenWidth);

        Gdx.input.setInputProcessor(stage);
        // Temp intill "AssetManageren" er implementert.
        String menuBackgroundPath = "./assets/MenuAssets/MenuBackground.png";


        menuBackground = new Texture(Gdx.files.internal(menuBackgroundPath));
        menuBackground.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
//        sprite = new Sprite(menuBackground);
//        sprite.setOrigin(sprite.getWidth() / 2,
//        sprite.getHeight() / 2);
//        sprite.setPosition(-sprite.getWidth() / 2, -sprite.getHeight());
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                if (keycode == Input.Keys.SPACE) {
                    game.setScreen(new GameLoop(game, game.getCurrentMap()));
                }
                return false;
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.spriteBatch.begin();

        float gameWidth = Gdx.graphics.getWidth();
        float gameHeight = Gdx.graphics.getHeight();

        game.spriteBatch.draw(menuBackground, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // TODO Dette burde gjøres med en tabell, men det får fungere for øyeblikket.
        game.bitmapFont.draw(game.spriteBatch, "Press Spacebar to start the game", gameWidth / 2 - 100, gameHeight - 50);

        game.spriteBatch.end();

    }

    @Override
    public void resize(int i, int i1) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {}
}
