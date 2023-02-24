package inf112.saga.of.the.villeins.Game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.HexagonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import inf112.saga.of.the.villeins.Controller.CharacterAnimationController;
import inf112.saga.of.the.villeins.Characters.Player;
import inf112.saga.of.the.villeins.Controller.GameController;

public class sagaOfTheVilleinsGame extends ApplicationAdapter {
	SpriteBatch spriteBatch;
	Player player;

	CharacterAnimationController playerAnimation;
	private TiledMap map;
	private HexagonalTiledMapRenderer renderer;
	private OrthographicCamera camera;
	private GameController GameController;


	// TODO Make an animation loader class responsible for loading in animations for the characters.
	@Override
	public void create () {
		spriteBatch = new SpriteBatch();

		String idleWarriorPath = "./assets/Sprites/Warrior/IdleWarrior.png";
		String walkingWarriorPath = "./assets/Sprites/Warrior/WalkingWarrior.png";
		map = new TmxMapLoader().load("./assets/TiledMap/TiledRougelikeMap.tmx");
		camera = new OrthographicCamera();
		GameController = new GameController(null, camera);
		renderer = new HexagonalTiledMapRenderer(map);
		HexGridMapPosition test = new HexGridMapPosition(1, 5);
		Vector2 testPosition = test.getHexPosition();
		float playerXStartPosition = testPosition.x;
		float playerYStartPosition = testPosition.y;
		// Inits the player character and sets the position.
		player = new Player(playerXStartPosition, playerYStartPosition, 20, 10, 10);
		playerAnimation = new CharacterAnimationController(player, idleWarriorPath, walkingWarriorPath, null, spriteBatch);
		// Inits camera and sets it's starting position and zoom.
		camera.translate(playerXStartPosition, playerYStartPosition, 0f);
		camera.zoom = 1.5f;
	}


	/**
	 * This is the render/game loop of the game.
	 * Essentially all the objects or methods that need to be updated every frame should go in here.
	 */
	@Override
	public void render () {
		ScreenUtils.clear(0.0f, 0.0f, 0.0f, 0f);
		spriteBatch.begin();
		renderer.setView(camera);
		Vector2 clickPosition = GameController.currentProcessor.getClickCoordinates();
		renderer.render();
		camera.update();
		player.setDestination(clickPosition);

//		camera.translate(player.getxCurrentPosition(), player.getyCurrentPosition(), 0f);


		player.update();
		playerAnimation.render();
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
