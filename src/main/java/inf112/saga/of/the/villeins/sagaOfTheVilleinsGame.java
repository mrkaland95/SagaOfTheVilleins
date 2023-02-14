package inf112.saga.of.the.villeins;

import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.HexagonalTiledMapRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import inf112.saga.of.the.villeins.Characters.Animation2D;
import inf112.saga.of.the.villeins.Characters.Player;
import inf112.saga.of.the.villeins.InputProcessors.CameraProcessor;

public class sagaOfTheVilleinsGame extends ApplicationAdapter {
	SpriteBatch spriteBatch;
	Animation2D walkingWarrior;
	Animation2D idleWarrior;
	Player player;
	private TiledMap map;
	private HexagonalTiledMapRenderer renderer;
	private OrthographicCamera camera;

	// TODO Make an animation loader class responsible for loading in animations for the characters.
	@Override
	public void create () {
		spriteBatch = new SpriteBatch();
		walkingWarrior = new Animation2D("./assets/Sprites/Warrior/WalkingWarrior.png");
		idleWarrior = new Animation2D("./assets/Sprites/Warrior/IdleWarrior.png", 1, 2);
		map = new TmxMapLoader().load("./assets/TiledMap/TiledRougelikeMap.tmx");
		renderer = new HexagonalTiledMapRenderer(map);

		// Inits the player character and sets the position.
		player = new Player(580f, 500f, walkingWarrior, idleWarrior, spriteBatch);


		// Inits camera and sets it's starting position
		camera = new OrthographicCamera();
		CameraProcessor processor = new CameraProcessor(camera);
		Gdx.input.setInputProcessor(processor);
		camera.translate(800f, 500f, 0f);
		camera.zoom = 1.5f;
	}


	@Override
	public void render () {
		ScreenUtils.clear(0.0f, 0.0f, 0.0f, 0f);
		spriteBatch.begin();
		renderer.setView(camera);
		renderer.render();
		camera.update();
//		player.setPosition(580f, 500f);
		player.drawSpriteAnimation();
		spriteBatch.end();
	}
	
	@Override
	public void dispose () {
		spriteBatch.dispose();
		map.dispose();
		renderer.dispose();
	}

	@Override
	public void resize(int width, int height){
		camera.viewportHeight = height;
		camera.viewportWidth = width;
		camera.update();
	}
}
