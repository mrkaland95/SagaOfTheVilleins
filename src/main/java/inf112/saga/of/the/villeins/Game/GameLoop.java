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
import inf112.saga.of.the.villeins.Animations.CharacterAnimationHandler;
import inf112.saga.of.the.villeins.Characters.Player;
import inf112.saga.of.the.villeins.Controller.GameController;
import inf112.saga.of.the.villeins.Controller.GameUI;
import inf112.saga.of.the.villeins.Factories.CharacterFactory;
import inf112.saga.of.the.villeins.MapUtils.HexGridMapPosition;
import inf112.saga.of.the.villeins.MapUtils.TilePosition;

import java.util.ArrayList;
import java.util.List;

public class GameLoop implements Screen {
	Game game;
	SpriteBatch spriteBatch;
	ShapeRenderer shapeRenderer;
	CharacterAnimationHandler slimeAnimation;
	CharacterAnimationHandler playerWarriorAnimation;
	private final TiledMap map;
	private final HexagonalTiledMapRenderer mapRenderer;
	private final OrthographicCamera camera;
	private final GameController GameController;
	private final GameUI gameUI;
	private final CharacterFactory characterFactory;
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

		Texture warriorIdleTexture =    game.assets.manager.get(GameAssetManager.idleWarriorPath, Texture.class);
		Texture warriorWalkingTexture = game.assets.manager.get(GameAssetManager.walkingWarriorPath, Texture.class);
		Texture slimeIdleTexture =      game.assets.manager.get(GameAssetManager.idleSlimePath, Texture.class);

		// Disse burde antageligvis initialiseres og hentes fra et annet sted.
		slimeAnimation =         new CharacterAnimationHandler(slimeIdleTexture, slimeIdleTexture, null, spriteBatch,1, 4);
		playerWarriorAnimation = new CharacterAnimationHandler(warriorIdleTexture, warriorWalkingTexture , null, spriteBatch,1, 2);

		characterFactory = new CharacterFactory(playerWarriorAnimation, slimeAnimation);

		ICharacter slime =   characterFactory.getSlimeCharacter(new TilePosition(1, 4));
		ICharacter slime2 =  characterFactory.getSlimeCharacter(new TilePosition(4, 6));
		ICharacter slime3 =  characterFactory.getSlimeCharacter(new TilePosition(5, 7));
		ICharacter slime4 =  characterFactory.getSlimeCharacter(new TilePosition(7, 1));

		ICharacter player =  characterFactory.getWarriorCharacter(new TilePosition(1, 6));

		characterList.add(player);
		characterList.add(slime);
		characterList.add(slime2);
		characterList.add(slime3);
		characterList.add(slime4);

		GameController =    new GameController(characterList, camera);
		mapRenderer =       new HexagonalTiledMapRenderer(map);

		// Inits camera and sets it's starting position and zoom.
		camera.translate(player.getCurrentPosition().x, player.getCurrentPosition().y, 0f);
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

		infoMap.reset(characterList);
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
