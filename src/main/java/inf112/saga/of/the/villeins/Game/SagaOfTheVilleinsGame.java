package inf112.saga.of.the.villeins.Game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import inf112.saga.of.the.villeins.AssetManager.GameAssetManager;

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

//         Uncomment this to when testing the main menu
//         setScreen(new MainMenuScreen(this));

        // Uncomment/Comment this when testing other screens.
        setScreen(new GameLoop(this, getCurrentMap()));
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

}
