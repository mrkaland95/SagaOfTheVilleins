package inf112.saga.of.the.villeins.Game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import inf112.saga.of.the.villeins.Characters.ICharacter;
import inf112.saga.of.the.villeins.Characters.IPlayable;
import inf112.saga.of.the.villeins.Game.LootSystem.LootCollection;

import java.util.List;

public class MidScreen implements Screen {
    private Stage stage;
    private Table startGameTable;
    private SagaOfTheVilleinsGame game;
    private List<ICharacter> characterList;
    private LootCollection inventory;

    public MidScreen(SagaOfTheVilleinsGame game, List<ICharacter> characterList, LootCollection inventory) {
        this.stage = new Stage(new ScreenViewport(), game.spriteBatch);
        this.startGameTable = new Table(game.getDefaultSkin());
        this.game = game;
        this.inventory = inventory;
        this.characterList = characterList;
        Gdx.input.setInputProcessor(stage);

        Button startGameButton = new TextButton("Start next map", game.getDefaultSkin(), "small");
        startGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                tempSetupStage();
            }
        });
        startGameTable.add(startGameButton);
        startGameTable.setFillParent(true);
        startGameTable.bottom();
        stage.addActor(startGameTable);

    }

    private void tempSetupStage() {
        this.game.updatePlayer((IPlayable) this.characterList.get(0), inventory);
        this.game.nextStage();
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float deltaTime) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

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
