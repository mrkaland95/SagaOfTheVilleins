package inf112.saga.of.the.villeins.Game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.HexagonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import inf112.saga.of.the.villeins.Characters.ICharacter;
import inf112.saga.of.the.villeins.Characters.IPlayable;
import inf112.saga.of.the.villeins.Controller.GameController;
import inf112.saga.of.the.villeins.Controller.GameState;
import inf112.saga.of.the.villeins.InputProcessors.ActivePlayerProcessor;
import inf112.saga.of.the.villeins.InputProcessors.InactivePlayerProcessor;
import inf112.saga.of.the.villeins.UI.AbstractGameUI;
import inf112.saga.of.the.villeins.UI.GameUI;
import inf112.saga.of.the.villeins.Game.LootSystem.LootCollection;

import java.util.ArrayList;
import java.util.List;

public class GameScreen implements Screen {
	SagaOfTheVilleinsGame game;
	SpriteBatch spriteBatch;
	ShapeRenderer shapeRenderer;
	BitmapFont bitmapFont;
	private final TiledMap map;
	private final HexagonalTiledMapRenderer mapRenderer;
	private final OrthographicCamera gameCamera;
	private final OrthographicCamera uiCamera;
	private final GameController gameController;
	private final AbstractGameUI gameUI;
	private final GameStage gameStage;
	public static List<ICharacter> characterList = new ArrayList<>();
	public static TileInfoMap infoMap;
	private LootCollection inventory;
	private final Stage uiCameraStage;
	private final Stage gameCameraStage;
	private InactivePlayerProcessor computerActiveProcessor;
    private ActivePlayerProcessor playerActiveProcessor;


	public GameScreen(SagaOfTheVilleinsGame game, TiledMap map, GameStage stage) {
		this.map = map;
		this.game = game;
		this.gameStage = stage;

		// Hent dimensjonen på kartet og sett det inn i mappet som holder gyldige tiles.
		int width = map.getProperties().get("width", Integer.class);
		int height = map.getProperties().get("height", Integer.class);

		infoMap = new TileInfoMap(height, width);
		infoMap.setIllegalTiles(map);

		gameCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		uiCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		uiCameraStage = new Stage(new ScreenViewport(uiCamera));
		gameCameraStage = new Stage(new ScreenViewport(gameCamera));

		playerActiveProcessor = new ActivePlayerProcessor(gameCamera);
        computerActiveProcessor = new InactivePlayerProcessor(gameCamera);

		spriteBatch   = game.spriteBatch;
		shapeRenderer = game.shapeRenderer;
		bitmapFont    = game.bitmapFont;

		// Genererer karakterer.
		characterList = new ArrayList<>();
		characterList.addAll(gameStage.generateCharacters());

		IPlayable player = (IPlayable) characterList.get(0);
		inventory = new LootCollection();

		gameController = new GameController(characterList, player, playerActiveProcessor, computerActiveProcessor, uiCameraStage, gameCameraStage);
		mapRenderer    = new HexagonalTiledMapRenderer(map);

		gameUI = new GameUI(game, uiCameraStage, gameCameraStage, gameController, playerActiveProcessor);

		gameCamera.translate(player.getCurrentPosition().x, player.getCurrentPosition().y, 0f);
		gameCamera.zoom = 1.5f;
	}


	/**
	 * This is the render/game loop of the game.
	 * Essentially all the objects or methods that need to be updated every frame should go in here.
	 */
	@Override
	public void render (float deltaTime) {
		ScreenUtils.clear(0.0f, 0.0f, 0.0f, 1f);

		infoMap.reset(characterList);
		infoMap.setIllegalTiles(map);

		gameCamera.update();
		uiCamera.update();

		spriteBatch.setProjectionMatrix(gameCamera.combined);
		shapeRenderer.setProjectionMatrix(gameCamera.combined);

		// Tegn kartet.
		mapRenderer.setView(gameCamera);
		mapRenderer.render();


		//characterList.removeIf(character -> character.getCurrentHealth() == 0);
		List<ICharacter> removeList = new ArrayList<>();
		for (ICharacter iCharacter : characterList) {
			if(iCharacter.getCurrentHealth() == 0){
				removeList.add(iCharacter);
				inventory.generateUpgrade();
			}
		}
		characterList.removeAll(removeList);


		for (ICharacter character : characterList) {
			character.update();
		}

		gameUI.drawUI(deltaTime, gameController.getPlayerCharacter(), characterList);
		gameController.update(characterList);

		playerActiveProcessor.resetInput();

		if(gameController.getGameState() == GameState.GAME_OVER){
			this.game.resetGame();
		}
		if(gameController.getGameState() == GameState.MAP_WON) {
			game.setScreen(new MidScreen(game, GameScreen.characterList, inventory));
		}
	}
	
	@Override
	public void dispose () {
		bitmapFont.dispose();
		uiCameraStage.dispose();
		gameCameraStage.dispose();
		map.dispose();
		mapRenderer.dispose();
	}

	@Override
	public void show() {

	}
	@Override
	public void resize(int width, int height){

		gameCamera.viewportWidth = width;
		gameCamera.viewportHeight = height;
		gameCamera.update();
       	gameCameraStage.getViewport().update(width, height, true);
       	uiCameraStage.getViewport().update(width, height, true);
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
