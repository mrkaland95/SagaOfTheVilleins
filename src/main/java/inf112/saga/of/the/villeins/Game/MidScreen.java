package inf112.saga.of.the.villeins.Game;

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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.util.List;

public class MidScreen implements Screen {
    private final Skin skin;
    private Stage stage;
    private Table startGameTable;
    private Table statsTable;
    private SagaOfTheVilleinsGame game;
    private List<ICharacter> characterList;
    private LootCollection inventory;
    private IPlayable playerPreUpgrade;
    private IPlayable playerPostUpgrade;
    private int preUpdateHealth;
    private int preUpdateStrength;
    private int preUpdateDefense;
    private int postUpdateDefense;
    private int postUpdateHealth;
    private int postUpdateStrength;
    private TileInfoMap infoMap;


    public MidScreen(SagaOfTheVilleinsGame game, List<ICharacter> characterList, LootCollection inventory, TileInfoMap infoMap) {
        this.stage = new Stage(new ScreenViewport(), game.spriteBatch);
        this.startGameTable = new Table(game.getDefaultSkin());
        this.skin = game.getDefaultSkin();
        this.game = game;
        this.infoMap = infoMap;
        this.inventory = inventory;
        this.characterList = characterList;
        this.playerPreUpgrade = (IPlayable) this.characterList.get(0);

        this.preUpdateDefense = playerPreUpgrade.getDefense();
        this.preUpdateStrength = playerPreUpgrade.getStrength();
        this.preUpdateHealth = playerPreUpgrade.getMaxHealth();

        this.game.updatePlayer(playerPreUpgrade, inventory);
        this.playerPostUpgrade = (IPlayable) this.game.getPlayer();

        this.postUpdateDefense = playerPostUpgrade.getDefense();
        this.postUpdateHealth = playerPostUpgrade.getMaxHealth();
        this.postUpdateStrength = playerPostUpgrade.getStrength();

        Gdx.input.setInputProcessor(stage);

        initStartNextGameButton();
        initPlayerStatTables();
    }

    /**
     * Kjører nextStage()-metoden i sagaOfTheVilleinsGame
     * 
     */
    private void startNextStage() {
        this.game.nextStage(this.infoMap);
    }

    private void initStartNextGameButton() {
        Button startGameButton = new TextButton("Start next map", game.getDefaultSkin(), "default");
        startGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                startNextStage();
            }
        });
        startGameTable.add(startGameButton);
        startGameTable.setFillParent(true);
        startGameTable.bottom();
        stage.addActor(startGameTable);
    }

    private void initPlayerStatTables() {
        this.statsTable = new Table(skin);
        Table preUpgradeTable = new Table(skin);
        preUpgradeTable.add(new Label("Stats Before Upgrade", skin)).colspan(2);
        preUpgradeTable.row();

        // legger til stats
        preUpgradeTable.add(new Label("Max Health: ", skin));
        preUpgradeTable.add(new Label(String.valueOf(preUpdateHealth), skin));
        preUpgradeTable.row();
        preUpgradeTable.add(new Label("Strength: ", skin));
        preUpgradeTable.add(new Label(String.valueOf(preUpdateStrength), skin));
        preUpgradeTable.row();
        preUpgradeTable.add(new Label("Defense: ", skin));
        preUpgradeTable.add(new Label(String.valueOf(preUpdateDefense), skin));

        // Definerer tabellen for stats etter oppgradering.
        Table postUpgradeTable = new Table(skin);
        postUpgradeTable.add(new Label("Stats After Upgrade", skin)).colspan(2);
        postUpgradeTable.row();

        // Legger til stats
        postUpgradeTable.add(new Label("Max Health: ", skin));
        postUpgradeTable.add(new Label(String.valueOf(postUpdateHealth), skin));
        postUpgradeTable.row();
        postUpgradeTable.add(new Label("Strength: ", skin));
        postUpgradeTable.add(new Label(String.valueOf(postUpdateStrength), skin));
        postUpgradeTable.row();
        postUpgradeTable.add(new Label("Defense: ", skin));
        postUpgradeTable.add(new Label(String.valueOf(postUpdateDefense), skin));


        statsTable.center();
        statsTable.setFillParent(true);
        statsTable.add(preUpgradeTable).pad(50);
        statsTable.add(postUpgradeTable).pad(50);
        stage.addActor(statsTable);
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
