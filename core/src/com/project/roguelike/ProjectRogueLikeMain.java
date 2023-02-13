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
import com.project.roguelike.Characters.Animation2D;
import com.project.roguelike.Characters.Player;
import com.project.roguelike.InputProcessors.GameProcessor;

import java.awt.*;

public class ProjectRogueLikeMain extends ApplicationAdapter{
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

	// TODO Make an animation loader class responsible for loading in animations for the characters.
	@Override
	public void create () {
		spriteBatch = new SpriteBatch();
		walkingWarrior = new Animation2D("./Sprites/Warrior/WalkingWarrior.png");
		idleWarrior = new Animation2D("./Sprites/Warrior/IdleWarrior.png", 1, 2);
		map = new TmxMapLoader().load("./TiledMap/TiledRougelikeMap.tmx");
		renderer = new HexagonalTiledMapRenderer(map);
		player = new Player(0f, 0f, walkingWarrior, idleWarrior, spriteBatch);
		camera = new OrthographicCamera();
		GameProcessor processor = new GameProcessor(camera);
		Gdx.input.setInputProcessor(processor);

	}

	@Override
	public void render () {
		ScreenUtils.clear(0.0f, 0.0f, 0.0f, 0f);
		spriteBatch.begin();
		renderer.setView(camera);
		renderer.render();
		camera.update();
		player.setPosition(580f, 500f);
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
}
