package com.project.roguelike;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;

public class ProjectRogueLikeMain extends ApplicationAdapter {
	SpriteBatch spriteBatch;
	Texture img;

	Animation2D walkingWarrior;
	
	@Override
	public void create () {
		spriteBatch = new SpriteBatch();
		walkingWarrior = new Animation2D("./Sprites/Warrior/WalkingWarrior.png");
	}

	@Override
	public void render () {
		float deltaTime = Gdx.graphics.getDeltaTime();
		ScreenUtils.clear(0.2f, 0.0f, 0.0f, 0f);
		spriteBatch.begin();
		TextureRegion frame = walkingWarrior.getImageToRender(deltaTime);
		spriteBatch.draw(frame, 100, 100);
		spriteBatch.end();
	}
	
	@Override
	public void dispose () {
		spriteBatch.dispose();
		img.dispose();
	}
}
