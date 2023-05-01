package inf112.saga.of.the.villeins.Game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.HexagonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import inf112.saga.of.the.villeins.Characters.ICharacter;
import inf112.saga.of.the.villeins.Characters.IPlayable;
import inf112.saga.of.the.villeins.Controller.GameController;
import inf112.saga.of.the.villeins.Controller.GameState;
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
	private final GameUI gameUI;
	private final GameStage gameStage;
	public static final List<ICharacter> characterList = new ArrayList<>();
	public static TileInfoMap infoMap;
	private LootCollection inventory;
	private final Stage uiStage;

	public GameScreen(SagaOfTheVilleinsGame game, TiledMap map, GameStage stage) {
		this.map = map;
		this.game = game;
		this.gameStage = stage;

		// Hent dimensjonen på kartet og sett det inn i mappet som holder gyldige tiles.
		int width = map.getProperties().get("width", Integer.class);
		int height = map.getProperties().get("height", Integer.class);

		infoMap = new TileInfoMap(height, width);
		infoMap.addIllegalTiles(map);

		gameCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		uiCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		uiStage = new Stage(new ScreenViewport(uiCamera));

		gameCamera.setToOrtho(false);


		spriteBatch   = game.spriteBatch;
		shapeRenderer = game.shapeRenderer;
		bitmapFont    = game.bitmapFont;



		characterList.addAll(gameStage.generateCharacters());
		IPlayable player = (IPlayable) characterList.get(0);
		inventory = new LootCollection();
		System.out.println(player.getMaxHealth());
		System.out.println(player.getStrength());

		gameController = new GameController(characterList, gameCamera, player, uiStage);
		mapRenderer    = new HexagonalTiledMapRenderer(map);

		gameUI = new GameUI(game, uiStage, gameController);

		// Inits camera and sets its starting position and zoom.
//		camera.lookAt(player.getCurrentPosition().x, player.getCurrentPosition().y, 0f);
		gameCamera.translate(player.getCurrentPosition().x, player.getCurrentPosition().y, 0f);
		gameCamera.zoom = 1.5f;
	}



	/**
	 * This is the render/game loop of the game.
	 * Essentially all the objects or methods that need to be updated every frame should go in here.
	 */
	@Override
	public void render (float deltaTime) {
		if(gameController.getGameState() == GameState.GAME_OVER){
			this.game.resetGame();
		}
		if(gameController.getGameState() == GameState.MAP_WON){
			this.game.updatePlayer((IPlayable) characterList.get(0), inventory);
			this.game.nextStage();
		}
		ScreenUtils.clear(0.0f, 0.0f, 0.0f, 1f);
		gameCamera.update();
		uiCamera.update();

		spriteBatch.setProjectionMatrix(gameCamera.combined);
		shapeRenderer.setProjectionMatrix(gameCamera.combined);

		mapRenderer.setView(gameCamera);
		mapRenderer.render();
		
		// Denne burde antageligvis flyttes inn til GameController klassen når en karakter dør
		// Men dette vil fungere for øyeblikket.
		
		//characterList.removeIf(character -> character.getCurrentHealth() == 0);
		List<ICharacter> removeList = new ArrayList<>();
		for (ICharacter iCharacter : characterList) {
			if(iCharacter.getCurrentHealth() == 0){
				removeList.add(iCharacter);
				inventory.generateUpgrade();
			}
		}
		characterList.removeAll(removeList);

		gameController.update(characterList);
		
		for (ICharacter character : characterList) {
			character.update();
		}

		gameUI.drawUI(deltaTime, gameController.getPlayerCharacter(), characterList);

		infoMap.reset(characterList);
		infoMap.addIllegalTiles(map);
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

		gameCamera.viewportWidth = width;
		gameCamera.viewportHeight = height;
		gameCamera.update();
//    	uiCamera.setToOrtho(false, width, height);
       	uiStage.getViewport().update(width, height, true);
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
