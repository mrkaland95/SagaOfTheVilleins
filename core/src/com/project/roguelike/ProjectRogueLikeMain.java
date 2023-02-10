package com.project.roguelike;

import com.badlogic.gdx.*;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.assets.loaders.MusicLoader;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.HexagonalTiledMapRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

import java.awt.*;

public class ProjectRogueLikeMain extends ApplicationAdapter implements InputProcessor {
	SpriteBatch spriteBatch;
	Animation2D walkingWarrior;
	Animation2D idleWarrior;
	Player player;
	float warriorXPosition = 0f;
	float screenWidth = 560f;

	Sound sound;
	private TiledMap map;
	private HexagonalTiledMapRenderer renderer;
	private OrthographicCamera camera;

	
	@Override
	public void create () {
		spriteBatch = new SpriteBatch();
		walkingWarrior = new Animation2D("./Sprites/Warrior/WalkingWarrior.png");
		idleWarrior = new Animation2D("./Sprites/Warrior/IdleWarrior.png");
		map = new TmxMapLoader().load("./TiledMap/TiledRougelikeMap.tmx");
		renderer = new HexagonalTiledMapRenderer(map);
		player = new Player(0f, 0f, walkingWarrior, idleWarrior, spriteBatch);
		camera = new OrthographicCamera();
		Gdx.input.setInputProcessor(this);

	}

	@Override
	public void render () {
		ScreenUtils.clear(0.2f, 0.0f, 0.0f, 0f);
		spriteBatch.begin();
		renderer.setView(camera);
		renderer.render();
		camera.update();
		player.setPosition(500f, 500f);
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

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		if(character == 'w'){
			camera.translate(0, 10, 0);
			return true;
		}
		if(character == 's'){
			camera.translate(0, -10,0);
			return true;
		}
		if(character == 'a'){
			camera.translate(-10, 0 ,0);
			return true;
		}
		if(character == 'd'){
			camera.translate(10, 0 ,0);
			return true;
		}
		if(character == 'z'){
			camera.zoom += 0.05;
			return true;
		}
		if(character == 'x'){
			camera.zoom -= 0.05;
			return true;
		}

		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		return false;
	}
}
