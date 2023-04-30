package inf112.saga.of.the.villeins.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


public class HelpScreen implements Screen {
    private SagaOfTheVilleinsGame game;
    private Stage stage;
    private Texture background;
    private BitmapFont font;
    private GlyphLayout layout;
    private Table buttonTable;
    private Table textTable;


    public HelpScreen(SagaOfTheVilleinsGame game) {
        this.game = game;
        this.stage = new Stage(new ScreenViewport(), game.spriteBatch);
//        this.background = game.getMenuBackground();
        this.background = game.getHelpPageBackground();
        this.font = new BitmapFont();
        this.layout = new GlyphLayout();
        Skin skin = game.getDefaultSkin();
        this.buttonTable = new Table(skin);
        Table helpTextTable = new Table(skin);
        Gdx.input.setInputProcessor(stage);

        background.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);


        String helpString1 = """
                * To move the camera, you can hold the middle mouse button and drag the camera around.
                * To move the character, you can left click on valid tiles. You can not move your character to water tiles or already occupied tiles.
                * To attack another character, you can right click. But do be aware you need to be within range attack, by default this is 1 tile for the player\s
                """;


        Label helpTextLabel = new Label(helpString1, skin);

        float textWidth = Gdx.graphics.getWidth() * 0.8f;
        helpTextLabel.setColor(Color.FIREBRICK);
        helpTextLabel.setWrap(true);
        helpTextLabel.setWidth(textWidth);

        helpTextTable.add(helpTextLabel).width(textWidth).center().row();
        helpTextTable.setFillParent(true);

        Button mainMenuButton = new TextButton("Return to main menu", skin, "small");
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
