package inf112.saga.of.the.villeins.Game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.HexagonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import inf112.saga.of.the.villeins.Characters.ICharacter;
import inf112.saga.of.the.villeins.Characters.Slime;
import inf112.saga.of.the.villeins.Controller.CharacterAnimationController;
import inf112.saga.of.the.villeins.Characters.Player;
import inf112.saga.of.the.villeins.Controller.GameController;
import inf112.saga.of.the.villeins.MapUtils.HexGridMapPosition;
import inf112.saga.of.the.villeins.MapUtils.AStarPathfinder;
import inf112.saga.of.the.villeins.MapUtils.TilePosition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameLoop implements Screen {
	Game game;
	SpriteBatch spriteBatch;
	ShapeRenderer shapeRenderer;
	Player player;
	Slime slime;
	CharacterAnimationController slimeAnimation;
	CharacterAnimationController playerAnimation;
	private TiledMap map;
	private HexagonalTiledMapRenderer renderer;
	private OrthographicCamera camera;
	private GameController GameController;
	private final List<ICharacter> characterList = new ArrayList<>();
	public static Imap infoMap; // crashes with lower vlaues we should investiagtes
	List<TilePosition> pathToMove;

	// TODO Make an animation loader class responsible for loading in animations for the characters.


	public GameLoop(Game game) {
		this.game = game;
		spriteBatch = new SpriteBatch();
		String idleWarriorPath = "./assets/Sprites/Warrior/IdleWarrior.png";
		String walkingWarriorPath = "./assets/Sprites/Warrior/WalkingWarrior.png";
		String idleSlimePath = "./assets/Sprites/Slime/SlimeIdle.png";
		map = new TmxMapLoader().load("./assets/Maps/TiledRougelikeMap.tmx");

		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.setToOrtho(false);

		shapeRenderer = new ShapeRenderer();

		GameLoop.infoMap = new Imap(map.getProperties().get("width", Integer.class), map.getProperties().get("height", Integer.class));


		TilePosition playerTile = new TilePosition(1, 4);
		TilePosition slimeTile = new TilePosition(1, 6);

		ArrayList<Boolean> temp = new ArrayList<>();
		temp.add(false);
		temp.add(false);
		infoMap.map.put(slimeTile, temp);

		TilePosition playerDestination = new TilePosition(4, 8);

		Vector2 playerPosition = HexGridMapPosition.calculateWorldCoordinateFromHexGrid(playerTile.x(), playerTile.y());
		Vector2 slimePosition = HexGridMapPosition.calculateWorldCoordinateFromHexGrid(slimeTile.x(), slimeTile.y());



		slimeAnimation = new CharacterAnimationController(idleSlimePath, idleSlimePath, null, spriteBatch, shapeRenderer, 1, 4);
		playerAnimation = new CharacterAnimationController(idleWarriorPath, walkingWarriorPath, null, spriteBatch, shapeRenderer,1, 2);

		slime = new Slime(slimePosition, slimeAnimation,30, 10, 4);
		player = new Player(playerPosition, playerAnimation, 20, 10, 10);
		player.setHealth(10);

		characterList.add(player);
		characterList.add(slime);

		GameController = new GameController(characterList, camera);
		renderer = new HexagonalTiledMapRenderer(map);


		// TEMP init characters
		// TODO move the initialization of these into the game controller and/or an object factory.

		// Inits camera and sets it's starting position and zoom.
		camera.translate(playerPosition.x, playerPosition.y, 0f);
		camera.zoom = 1.5f;

		pathToMove = AStarPathfinder.findPath(playerTile, playerDestination);
	}



	/**
	 * This is the render/game loop of the game.
	 * Essentially all the objects or methods that need to be updated every frame should go in here.
	 */
	@Override
	public void render (float deltaTime) {
		ScreenUtils.clear(0.0f, 0.0f, 0.0f, 1f);
//		spriteBatch.begin();
		camera.update();
		spriteBatch.setProjectionMatrix(camera.combined);
		shapeRenderer.setProjectionMatrix(camera.combined);

		renderer.setView(camera);
		renderer.render();
		GameController.update();

		for (ICharacter character : characterList) {
			character.update();
		}
//		spriteBatch.end();

	}
	
	@Override
	public void dispose () {
		spriteBatch.dispose();
		map.dispose();
		renderer.dispose();
	}

	@Override
	public void show() {

	}
	@Override
	public void resize(int width, int height){
		camera.viewportHeight = height;
		camera.viewportWidth = width;
		camera.update();
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
}
