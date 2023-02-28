package inf112.saga.of.the.villeins.Game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.HexagonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import inf112.saga.of.the.villeins.Characters.Slime;
import inf112.saga.of.the.villeins.Controller.CharacterAnimationController;
import inf112.saga.of.the.villeins.Characters.Player;
import inf112.saga.of.the.villeins.Controller.GameController;

public class sagaOfTheVilleinsGame extends ApplicationAdapter {
	SpriteBatch spriteBatch;
	Player player;
	Slime slime;
	CharacterAnimationController slimeAnimation;
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
		String idleSlimePath = "./assets/Sprites/Slime/SlimeIdle.png";
		map = new TmxMapLoader().load("./assets/Maps/TiledRougelikeMap.tmx");
		camera = new OrthographicCamera();
		GameController = new GameController(null, camera);
		renderer = new HexagonalTiledMapRenderer(map);

		Vector2 slimePosition = new HexGridMapPosition(1, 4).getHexPosition();

		slimeAnimation = new CharacterAnimationController(idleSlimePath, null, null, spriteBatch, 1, 4);
		playerAnimation = new CharacterAnimationController(idleWarriorPath, walkingWarriorPath, null, spriteBatch, 1, 2);

		HexGridMapPosition test = new HexGridMapPosition(1, 5);
		Vector2 testPosition = test.getHexPosition();

		// TEMP init characters
		// TODO move the initialization of these into the game controller and/or an object factory.
		slime = new Slime(slimePosition, slimeAnimation,30, 10, 4);
		player = new Player(testPosition, playerAnimation, 20, 10, 10);
		// Inits camera and sets it's starting position and zoom.
		camera.translate(testPosition.x, testPosition.y, 0f);
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
		slime.update();
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
