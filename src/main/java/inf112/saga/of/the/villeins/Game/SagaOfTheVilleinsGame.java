package inf112.saga.of.the.villeins.Game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import inf112.saga.of.the.villeins.Animations.CharacterAnimationHandler;
import inf112.saga.of.the.villeins.AssetManager.GameAssetManager;
import inf112.saga.of.the.villeins.AssetManager.SoundManager;
import inf112.saga.of.the.villeins.Characters.CharacterDirection;
import inf112.saga.of.the.villeins.Characters.ICharacter;
import inf112.saga.of.the.villeins.Characters.IPlayable;
import inf112.saga.of.the.villeins.Factories.CharacterFactory;
import inf112.saga.of.the.villeins.Game.LootSystem.LootCollection;
import inf112.saga.of.the.villeins.Game.LootSystem.UpgradePlayer;
import inf112.saga.of.the.villeins.Utils.TilePosition;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Text;



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
    private SoundManager soundManager;
    private TileInfoMap infoMap;
    
    /**
     * Lager og holder på alle verdiene og klassene i til spillet. Bytter også mellom screens. 
     * 
     * @param stageIndex holder på "nivået" til spillet i form av vanskelighetsgrad
     * @param player holder på spilleren som skal bli tatt vare på om om/når den går videre til neste "nivå".
     * @param gameStage bruker characterFactoryen til å generere fiendene basert på "stageIndex"
     * @param charFactory er klassen som brukes til å generere de forskjellige karakterene i spillet
     * 
     */
    @Override
    public void create() {
        assetManager = new GameAssetManager();
        assetManager.load();
        assetManager.manager.finishLoading();

        maps.add(assetManager.manager.get(GameAssetManager.testMapPath));

        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        bitmapFont = new BitmapFont();

        Texture warriorIdleTexture    = assetManager.manager.get(GameAssetManager.idleWarriorPath, Texture.class);
		Texture warriorWalkingTexture = assetManager.manager.get(GameAssetManager.walkingWarriorPath, Texture.class);
        Texture warriorAttackTexture = assetManager.manager.get(GameAssetManager.attackingWarrior, Texture.class);
		Texture slimeIdleTexture      = assetManager.manager.get(GameAssetManager.idleSlimePath, Texture.class);
		Texture dragonAttackTexture   = assetManager.manager.get(GameAssetManager.dragonAttackPath, Texture.class);
		Texture ghostIdleTexture      = assetManager.manager.get(GameAssetManager.ghostIdlePath, Texture.class);

        this.defaultSkin = assetManager.manager.get(GameAssetManager.uiSkinPath, Skin.class);

        this.menuBackground = assetManager.manager.get(GameAssetManager.menuBackgroundPath, Texture.class);
        this.menuBackground2 = assetManager.manager.get(GameAssetManager.menuBackgroundPath2, Texture.class);

		CharacterAnimationHandler slimeAnimation =
                new CharacterAnimationHandler(spriteBatch, slimeIdleTexture, 4, slimeIdleTexture, 4, null,1, CharacterDirection.RIGHT);
		CharacterAnimationHandler playerWarriorAnimation =
                new CharacterAnimationHandler(spriteBatch, warriorIdleTexture, 2, warriorWalkingTexture,11, warriorAttackTexture, 5, CharacterDirection.LEFT);
		CharacterAnimationHandler dragonAnimation =
                new CharacterAnimationHandler(spriteBatch, dragonAttackTexture, 4,  dragonAttackTexture,4,  null, null, CharacterDirection.LEFT);
		CharacterAnimationHandler ghostAnimation =
                new CharacterAnimationHandler(spriteBatch, ghostIdleTexture, 4, ghostIdleTexture, 4, null,1, CharacterDirection.LEFT);

		// characterFactory burde endres basert på current stage
		charFactory = new CharacterFactory(playerWarriorAnimation, slimeAnimation, dragonAnimation, ghostAnimation);

        // Lager spiller objektet og setter tilstanden til spillet.
        player = charFactory.getPlayerCharacter(new TilePosition(1, 1));
        initializeMap();
		gameStage = new GameStage(stageIndex, charFactory, player, infoMap);

        Music music = assetManager.manager.get(GameAssetManager.musicPath, Music.class);
        Sound attackSound = assetManager.manager.get(GameAssetManager.swordAttackPath, Sound.class);
        soundManager = new SoundManager(music, attackSound);
        soundManager.playGameMusic();

        setScreen(new MainMenuScreen(this));
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

    
    public void initializeMap(){
        TiledMap currentMap = this.maps.get(mapIndex);

        int width = currentMap.getProperties().get("width", Integer.class);
		int height = currentMap.getProperties().get("height", Integer.class);

		infoMap = new TileInfoMap(height, width);
		infoMap.setIllegalTiles(currentMap);
    }

    public TiledMap getCurrentMap() {
        TiledMap currentMap = this.maps.get(mapIndex);
        return currentMap;
    }

    public GameStage getCurrentStage(){
        return gameStage;
    }
    
    /**
    * Brukes til å resette spillet når spilleren vinner/taper.
    * 
    * @param stageIndex holder på "nivået" til spillet i form av vanskelighetsgrad
    * @param gameStage bruker characterFactoryen til å generere fiendene basert på "stageIndex"
    *
    */

    public void nextStage(TileInfoMap infoMap){
        stageIndex += 1;
        gameStage = new GameStage(stageIndex, charFactory, player, infoMap);
        setScreen(new GameScreen(this, getCurrentMap(), gameStage));
    }

    /**
    * Brukes til å resette spillet når spilleren vinner/taper.
    * 
    * @param stageIndex holder på "nivået" til spillet i form av vanskelighetsgrad
    * @param gameStage bruker characterFactoryen til å generere fiendene basert på "stageIndex"
    *
    */

    public void resetGame(TileInfoMap infoMap){
        stageIndex = 1;
        player = charFactory.getPlayerCharacter(new TilePosition(1, 1));
        gameStage = new GameStage(stageIndex, charFactory, player, infoMap);
        setScreen(new GameScreen(this, getCurrentMap(), gameStage));
    }

    /**
     * Oppdaterer playeren med samlede oppgraderinger
     * 
     * @param updatedPlayer legger til oppgraderingene
     * @param inventory en simpel klasse som inneholder en liste av "upgrades" objekter
     */
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

    public TileInfoMap getInfoMap() {
        return infoMap;
    }

    public SoundManager getSoundManager() {
        return soundManager;
    }
}
