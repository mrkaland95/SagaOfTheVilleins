package inf112.saga.of.the.villeins.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


/**
 * Dette er en "screen" som definerer hjelpe siden. Inneholder tekst og en knapp for å returnere til spillet.
 */
public class HelpScreen implements Screen {
    private SagaOfTheVilleinsGame game;
    private Stage stage;
    private Texture background;
    private Table buttonTable;


    public HelpScreen(SagaOfTheVilleinsGame game) {
        this.game = game;
        this.stage = new Stage(new ScreenViewport(), game.spriteBatch);
        this.background = game.getHelpPageBackground();
        Skin skin = game.getDefaultSkin();
        this.buttonTable = new Table(skin);
        Table helpTextTable = new Table(skin);
        Gdx.input.setInputProcessor(stage);

        background.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);


        String text = """
                * To move the camera, you can hold the middle mouse button and drag the camera around. You can also use W,A,S,D.
                * You can also zoom in and out using Z and X.
                * To make the character act, you can left click on a valid tile. This opens a menu where you can choose your actions.
                * If you click on an empty tile you can move there, if you click on an enemy, it shows its stats and you can choose to attack if its in range.
                * The default attack range is 1 tile.

                * There is no final victory, survive as long as possible!
                * Everytime you kill all enemies on board, you can go to next stage with more (and more dangerous) enemies!
                * Every enemy killed gives you a stat buff, which are applied after clearing a stage.
                """;


        Label helpTextLabel = new Label(text, skin);

        float textWidth = Gdx.graphics.getWidth() * 0.8f;
        helpTextLabel.setColor(Color.RED);
        helpTextLabel.setWrap(true);
        helpTextLabel.setWidth(textWidth);

        helpTextTable.add(helpTextLabel).width(textWidth).center().row();
        helpTextTable.setFillParent(true);

        Button mainMenuButton = new TextButton("Return to main menu", skin, "default");
        mainMenuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenuScreen(game));
            }
        });

        buttonTable.add(mainMenuButton).expandX().center().row();
        buttonTable.setFillParent(true);
        buttonTable.bottom();

        // Legg til knappen og teksten til "stagen", i.e kameraet.
        stage.addActor(buttonTable);
        stage.addActor(helpTextTable);
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
