package inf112.saga.of.the.villeins.Game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

import inf112.saga.of.the.villeins.Animations.CharacterAnimationHandler;
import inf112.saga.of.the.villeins.AssetManager.GameAssetManager;
import inf112.saga.of.the.villeins.Factories.CharacterFactory;

import java.util.ArrayList;
import java.util.List;



/*
Dette er den øverste klassen som har med "spillet" i seg selv å gjøre. Her blir grafikken, ressurser, spilleren osv.
 Initiert.
 */




public class SagaOfTheVilleinsGame extends Game {
    SpriteBatch spriteBatch;
    ShapeRenderer shapeRenderer;
    BitmapFont bitmapFont;
    public GameAssetManager assets;
    private List<TiledMap> maps = new ArrayList<>();
    private int mapIndex = 0;
    private int stageIndex = 1;
    private GameStage gameStage;
    private CharacterFactory charFactory;

    @Override
    public void create() {
        // Last inn alle ressursene(bilder, sprites, kart osv).
        assets = new GameAssetManager();
        assets.load();
        assets.manager.finishLoading();

        maps.add(new TmxMapLoader().load("./assets/Maps/TiledRougelikeMapUpdated.tmx"));

        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        bitmapFont = new BitmapFont();

        Texture warriorIdleTexture    = assets.manager.get(GameAssetManager.idleWarriorPath, Texture.class);
		Texture warriorWalkingTexture = assets.manager.get(GameAssetManager.walkingWarriorPath, Texture.class);
		Texture slimeIdleTexture      = assets.manager.get(GameAssetManager.idleSlimePath, Texture.class);
		Texture dragonAttackTexture   = assets.manager.get(GameAssetManager.dragonAttackPath, Texture.class);

		// Disse burde antageligvis initialiseres og hentes fra et annet sted.
		CharacterAnimationHandler slimeAnimation =         new CharacterAnimationHandler(slimeIdleTexture, slimeIdleTexture, null, spriteBatch,1, 4);
		CharacterAnimationHandler playerWarriorAnimation = new CharacterAnimationHandler(warriorIdleTexture, warriorWalkingTexture , null, spriteBatch,1, 2);
		CharacterAnimationHandler dragonAnimation 	   = new CharacterAnimationHandler(dragonAttackTexture, dragonAttackTexture , null, spriteBatch,1, 4);

		// characterFactory burde endres basert på current stage
		charFactory = new CharacterFactory(playerWarriorAnimation, slimeAnimation, dragonAnimation, null);
		gameStage = new GameStage(stageIndex, charFactory);

//         Uncomment this to when testing the main menu
//         setScreen(new MainMenuScreen(this));

        // Uncomment/Comment this when testing other screens.
        setScreen(new GameLoop(this, getCurrentMap(), gameStage));
    }

    @Override
    public void dispose() {
        super.dispose();
        spriteBatch.dispose();
        shapeRenderer.dispose();
        bitmapFont.dispose();
        assets.dispose();
    }

    public TiledMap getCurrentMap() {
        return this.maps.get(mapIndex);
    }

    public GameStage getStage(){
        return gameStage;
    }

    public void nextStage(){
        stageIndex += 1;
        gameStage = new GameStage(stageIndex, charFactory);
        setScreen(new GameLoop(this, getCurrentMap(), gameStage));
    }

    public void resetGame(){
        stageIndex = 1;
        gameStage = new GameStage(stageIndex, charFactory);
        setScreen(new GameLoop(this, getCurrentMap(), gameStage));
    }
}
