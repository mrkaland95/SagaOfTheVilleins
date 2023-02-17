package inf112.saga.of.the.villeins;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.HexagonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import inf112.saga.of.the.villeins.Characters.Animation2D;
import inf112.saga.of.the.villeins.Characters.Player;
import inf112.saga.of.the.villeins.InputProcessors.CameraProcessor;
import inf112.saga.of.the.villeins.Rules.GameController;

public class sagaOfTheVilleinsGame extends ApplicationAdapter {
	SpriteBatch spriteBatch;
	Animation2D walkingWarrior;
	Animation2D idleWarrior;
	Player player;
	private TiledMap map;
	private HexagonalTiledMapRenderer renderer;
	private OrthographicCamera camera;
	CameraProcessor processor;
	Vector2 clickPosition;
	private GameController GameController;


	// TODO Make an animation loader class responsible for loading in animations for the characters.
	@Override
	public void create () {
		spriteBatch = new SpriteBatch();
		walkingWarrior = new Animation2D("./assets/Sprites/Warrior/WalkingWarrior.png");
		idleWarrior = new Animation2D("./assets/Sprites/Warrior/IdleWarrior.png", 1, 2);
		map = new TmxMapLoader().load("./assets/TiledMap/TiledRougelikeMap.tmx");
		camera = new OrthographicCamera();
		GameController = new GameController(null, camera);
		renderer = new HexagonalTiledMapRenderer(map);
		float playerXStartPosition = 580f;
		float playerYStartPosition = 500f;
		this.clickPosition = new Vector2(playerXStartPosition, playerYStartPosition);
		// Inits the player character and sets the position.
		player = new Player(playerXStartPosition, playerYStartPosition, walkingWarrior, idleWarrior, spriteBatch, 20, 10, 10);
		// Inits camera and sets it's starting position and zoom.
		camera.translate(800f, 500f, 0f);
		camera.zoom = 1.5f;
	}


	/**
	 * This is the render loop of the program.
	 * Essentially all the objects or methods that need to be updated every frame should go in here.
	 */
	@Override
	public void render () {
		ScreenUtils.clear(0.0f, 0.0f, 0.0f, 0f);
		spriteBatch.begin();
		renderer.setView(camera);
		Vector2 clickPosition = GameController.currentProcessor.getMoveClickCoordinates();
		renderer.render();
		camera.update();
		player.update();
		player.moveToPosition(clickPosition);
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
