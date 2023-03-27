package inf112.saga.of.the.villeins.Game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.HexagonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import inf112.saga.of.the.villeins.AssetManager.GameAssetManager;
import inf112.saga.of.the.villeins.Characters.ICharacter;
import inf112.saga.of.the.villeins.Characters.Slime;
import inf112.saga.of.the.villeins.Controller.CharacterAnimationController;
import inf112.saga.of.the.villeins.Characters.Player;
import inf112.saga.of.the.villeins.Controller.GameController;
import inf112.saga.of.the.villeins.Controller.GameUI;
import inf112.saga.of.the.villeins.MapUtils.HexGridMapPosition;
import inf112.saga.of.the.villeins.MapUtils.TilePosition;

import java.util.ArrayList;
import java.util.List;

public class GameLoop implements Screen {
	Game game;
	SpriteBatch spriteBatch;
	ShapeRenderer shapeRenderer;
	CharacterAnimationController slimeAnimation;
	CharacterAnimationController playerWarriorAnimation;
	private final TiledMap map;
	private final HexagonalTiledMapRenderer mapRenderer;
	private final OrthographicCamera camera;
	private final GameController GameController;
	private final GameUI gameUI;
	public static final List<ICharacter> characterList = new ArrayList<>();
	public static Imap infoMap;


	public GameLoop(SagaOfTheVilleinsGame game) {
		this.game = game;
		map = new TmxMapLoader().load("./assets/Maps/TiledRougelikeMap.tmx");

		infoMap = new Imap(20, 20);

		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		camera.setToOrtho(false);

		spriteBatch =   new SpriteBatch();
		shapeRenderer = new ShapeRenderer();

		gameUI = new GameUI(shapeRenderer);

		TilePosition playerTile = new TilePosition(1, 4);
		TilePosition slimeTile =  new TilePosition(1, 6);

		Vector2 playerPosition = HexGridMapPosition.calculateVectorCoordinate(playerTile);
		Vector2 slimePosition =  HexGridMapPosition.calculateVectorCoordinate(slimeTile);

		Texture warriorIdleTexture =    game.assets.manager.get(GameAssetManager.idleWarriorPath, Texture.class);
		Texture warriorWalkingTexture = game.assets.manager.get(GameAssetManager.walkingWarriorPath, Texture.class);
		Texture slimeIdleTexture =      game.assets.manager.get(GameAssetManager.idleSlimePath, Texture.class);

		// Disse burde antageligvis initialiseres og hentes fra et annet sted.
		slimeAnimation =         new CharacterAnimationController(slimeIdleTexture, slimeIdleTexture, null, spriteBatch,1, 4);
		playerWarriorAnimation = new CharacterAnimationController(warriorIdleTexture, warriorWalkingTexture , null, spriteBatch,1, 2);

		// TODO move the initialization of these into the game controller and/or an object factory.
		ICharacter slime =  new Slime(slimePosition, slimeAnimation,30, 10, 4, 1);
		ICharacter player = new Player(playerPosition, playerWarriorAnimation, 20, 10, 10);

		characterList.add(player);
		characterList.add(slime);

		GameController =    new GameController(characterList, camera);
		mapRenderer =       new HexagonalTiledMapRenderer(map);

		// Inits camera and sets it's starting position and zoom.
		camera.translate(playerPosition.x, playerPosition.y, 0f);
		camera.zoom = 1.5f;
	}



	/**
	 * This is the render/game loop of the game.
	 * Essentially all the objects or methods that need to be updated every frame should go in here.
	 */
	@Override
	public void render (float deltaTime) {
		ScreenUtils.clear(0.0f, 0.0f, 0.0f, 1f);
		camera.update();
		spriteBatch.setProjectionMatrix(camera.combined);
		shapeRenderer.setProjectionMatrix(camera.combined);


		mapRenderer.setView(camera);
		mapRenderer.render();
		GameController.update();

		// Denne burde antageligvis flyttes inn til GameController klassen når en karakter dør
		// Men dette vil fungere for øyeblikket.
		characterList.removeIf(character -> character.getCurrentHealth() == 0);

		for (ICharacter character : characterList) {
			character.update();
			gameUI.drawHealthbar(character);
		}

	}
	
	@Override
	public void dispose () {
		spriteBatch.dispose();
		map.dispose();
		mapRenderer.dispose();
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
