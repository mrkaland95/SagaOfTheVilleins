package inf112.saga.of.the.villeins.Game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import inf112.saga.of.the.villeins.Animations.CharacterAnimationHandler;
import inf112.saga.of.the.villeins.AssetManager.GameAssetManager;
import inf112.saga.of.the.villeins.Characters.ICharacter;
import inf112.saga.of.the.villeins.Characters.IPlayable;
import inf112.saga.of.the.villeins.Factories.CharacterFactory;
import inf112.saga.of.the.villeins.Game.LootSystem.LootCollection;
import inf112.saga.of.the.villeins.Game.LootSystem.UpgradePlayer;
import inf112.saga.of.the.villeins.Utils.TilePosition;

import java.util.ArrayList;
import java.util.List;



/*
Dette er den øverste klassen som har med "spillet" i seg selv å gjøre. Her blir grafikken, ressurser, spilleren osv.
Lastet inn og initiert.
 */
public class SagaOfTheVilleinsGame extends Game {
    public SpriteBatch spriteBatch;
    public ShapeRenderer shapeRenderer;
    public BitmapFont bitmapFont;
    public GameAssetManager assetManager;
    private final List<TiledMap> maps = new ArrayList<>();
    private int mapIndex = 0;
    private int stageIndex = 1;
    private GameStage gameStage;
    private CharacterFactory charFactory;
    private ICharacter player;
    private UpgradePlayer upgradeApplier;
    private Texture menuBackground2;
    private Texture menuBackground;
    private Skin defaultSkin;

    @Override
    public void create() {
        // Last inn alle ressursene(bilder, sprites, kart osv).
        assetManager = new GameAssetManager();
        assetManager.load();
        assetManager.manager.finishLoading();

        maps.add(assetManager.manager.get(GameAssetManager.testMapPath));

        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        bitmapFont = new BitmapFont();

        Texture warriorIdleTexture    = assetManager.manager.get(GameAssetManager.idleWarriorPath, Texture.class);
		Texture warriorWalkingTexture = assetManager.manager.get(GameAssetManager.walkingWarriorPath, Texture.class);
		Texture slimeIdleTexture      = assetManager.manager.get(GameAssetManager.idleSlimePath, Texture.class);
		Texture dragonAttackTexture   = assetManager.manager.get(GameAssetManager.dragonAttackPath, Texture.class);
		Texture ghostIdleTexture      = assetManager.manager.get(GameAssetManager.ghostIdlePath, Texture.class);

        this.defaultSkin = assetManager.manager.get(GameAssetManager.uiSkin, Skin.class);

        this.menuBackground = assetManager.manager.get(GameAssetManager.menuBackgroundPath, Texture.class);
        this.menuBackground2 = assetManager.manager.get(GameAssetManager.menuBackgroundPath2, Texture.class);

		CharacterAnimationHandler slimeAnimation =         new CharacterAnimationHandler(slimeIdleTexture, slimeIdleTexture, null, spriteBatch,1, 4);
		CharacterAnimationHandler playerWarriorAnimation = new CharacterAnimationHandler(warriorIdleTexture, warriorWalkingTexture , null, spriteBatch,1, 2);
		CharacterAnimationHandler dragonAnimation 	     = new CharacterAnimationHandler(dragonAttackTexture, dragonAttackTexture , null, spriteBatch,1, 4);
		CharacterAnimationHandler ghostAnimation 	     = new CharacterAnimationHandler(ghostIdleTexture, ghostIdleTexture , null, spriteBatch,1, 4);

		// characterFactory burde endres basert på current stage
		charFactory = new CharacterFactory(playerWarriorAnimation, slimeAnimation, dragonAnimation, ghostAnimation);

        // Lager spiller objektet og setter tilstanden til spillet.
        player = charFactory.getWarriorCharacter(new TilePosition(1, 1));
		gameStage = new GameStage(stageIndex, charFactory, player);


        // TODO: 03.05.2023 slett dette før endelig innlevering, og bruk "setScreen(new MainMenuScreen(this));"
        // Sett denne til den verdien som du skal teste.
        int screenToTest = 0;

        switch (screenToTest) {
            case 0 -> setScreen(new MainMenuScreen(this));
            case 1 -> setScreen(new GameScreen(this, getCurrentMap(), gameStage));
            case 2 -> setScreen(new HelpScreen(this));
            case 3 -> setScreen(new MidScreen(this, null, null));
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        spriteBatch.dispose();
        shapeRenderer.dispose();
        bitmapFont.dispose();
        assetManager.dispose();
        menuBackground.dispose();
        menuBackground2.dispose();
        defaultSkin.dispose();
    }

    public TiledMap getCurrentMap() {
        return this.maps.get(mapIndex);
    }

    public GameStage getCurrentStage(){
        return gameStage;
    }

    public void nextStage(){
        stageIndex += 1;
        gameStage = new GameStage(stageIndex, charFactory, player);
        setScreen(new GameScreen(this, getCurrentMap(), gameStage));
    }

    public void resetGame(){
        stageIndex = 1;
        player = charFactory.getWarriorCharacter(new TilePosition(1, 1));
        gameStage = new GameStage(stageIndex, charFactory, player);
        setScreen(new GameScreen(this, getCurrentMap(), gameStage));
    }

    public void updatePlayer(IPlayable updatedPlayer, LootCollection inventory) {
        upgradeApplier = new UpgradePlayer();
        updatedPlayer = upgradeApplier.UpgradeStats(updatedPlayer, inventory);
        this.player = updatedPlayer;
    }

    public ICharacter getPlayer() {
        return player;
    }

    public Skin getDefaultSkin() {
        return defaultSkin;
    }

    public Texture getMenuBackground() {
        return menuBackground;
    }

    public Texture getHelpPageBackground() {
        return menuBackground2;
    }
}
