package inf112.saga.of.the.villeins.Game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.HexagonalTiledMapRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import inf112.saga.of.the.villeins.AssetManager.GameAssetManager;
import inf112.saga.of.the.villeins.Characters.ICharacter;
import inf112.saga.of.the.villeins.Animations.CharacterAnimationHandler;
import inf112.saga.of.the.villeins.Characters.IPlayable;
import inf112.saga.of.the.villeins.Controller.GameController;
import inf112.saga.of.the.villeins.UI.GameUI;
import inf112.saga.of.the.villeins.Factories.CharacterFactory;
import inf112.saga.of.the.villeins.MapUtils.TilePosition;

import java.util.ArrayList;
import java.util.List;

public class GameLoop implements Screen {
	Game game;
	SpriteBatch spriteBatch;
	ShapeRenderer shapeRenderer;
	BitmapFont bitmapFont;
	CharacterAnimationHandler slimeAnimation;
	CharacterAnimationHandler playerWarriorAnimation;
	private final TiledMap map;
	private final HexagonalTiledMapRenderer mapRenderer;
	private final OrthographicCamera camera;
	private final OrthographicCamera uiCamera;
	private final GameController gameController;
	private final GameUI gameUI;
	private final CharacterFactory characterFactory;
	public static final List<ICharacter> characterList = new ArrayList<>();
	public static Imap infoMap;

	public GameLoop(SagaOfTheVilleinsGame game, TiledMap map) {
		this.map = map;
		this.game = game;
		infoMap = new Imap(20, 20);

		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.setToOrtho(false);
		uiCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		uiCamera.setToOrtho(false);

		spriteBatch   = game.spriteBatch;
		shapeRenderer = game.shapeRenderer;
		bitmapFont    = game.bitmapFont;

		gameUI = new GameUI(shapeRenderer, bitmapFont, spriteBatch, uiCamera);

		Texture warriorIdleTexture    = game.assets.manager.get(GameAssetManager.idleWarriorPath, Texture.class);
		Texture warriorWalkingTexture = game.assets.manager.get(GameAssetManager.walkingWarriorPath, Texture.class);
		Texture slimeIdleTexture      = game.assets.manager.get(GameAssetManager.idleSlimePath, Texture.class);

		// Disse burde antageligvis initialiseres og hentes fra et annet sted.
		slimeAnimation =         new CharacterAnimationHandler(slimeIdleTexture, slimeIdleTexture, null, spriteBatch,1, 4);
		playerWarriorAnimation = new CharacterAnimationHandler(warriorIdleTexture, warriorWalkingTexture , null, spriteBatch,1, 2);

		characterFactory = new CharacterFactory(playerWarriorAnimation, slimeAnimation);

		ICharacter slime  =  characterFactory.getSlimeCharacter(new TilePosition(1, 4));
		ICharacter slime2 =  characterFactory.getSlimeCharacter(new TilePosition(4, 6));
		ICharacter slime3 =  characterFactory.getSlimeCharacter(new TilePosition(5, 7));
		ICharacter slime4 =  characterFactory.getSlimeCharacter(new TilePosition(7, 1));

		ICharacter player =  characterFactory.getWarriorCharacter(new TilePosition(1, 6));

		characterList.add(player);
		characterList.add(slime);
		characterList.add(slime2);
		characterList.add(slime3);
		characterList.add(slime4);

		gameController = new GameController(characterList, camera);
		mapRenderer    = new HexagonalTiledMapRenderer(map);

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
		uiCamera.update();

		spriteBatch.setProjectionMatrix(camera.combined);
		shapeRenderer.setProjectionMatrix(camera.combined);

		mapRenderer.setView(camera);
		mapRenderer.render();
		gameController.update();

		// Denne burde antageligvis flyttes inn til GameController klassen når en karakter dør
		// Men dette vil fungere for øyeblikket.
		characterList.removeIf(character -> character.getCurrentHealth() == 0);

		for (ICharacter character : characterList) {
			character.update();
			gameUI.drawHealthbar(character);
		}

		gameUI.drawScore((IPlayable) gameController.getPlayerCharacter());

		infoMap.reset(characterList);
	}
	
	@Override
	public void dispose () {
		shapeRenderer.dispose();
		spriteBatch.dispose();
		map.dispose();
		mapRenderer.dispose();
	}

	@Override
	public void show() {

	}
	@Override
	public void resize(int width, int height){

//		uiCamera.viewportWidth = width;
//		uiCamera.viewportHeight = height;
    	camera.setToOrtho(false, width, height);
    	uiCamera.setToOrtho(false, width, height);
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
