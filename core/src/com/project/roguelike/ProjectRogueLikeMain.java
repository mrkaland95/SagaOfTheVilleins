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

	Animator2D walkingWarrior;
	
	@Override
	public void create () {
		spriteBatch = new SpriteBatch();
		walkingWarrior = new Animator2D("./Sprites/Warrior/WalkingWarrior.png");
	}

	@Override
	public void render () {
		float deltaTime = Gdx.graphics.getDeltaTime();
		ScreenUtils.clear(1, 1, 1, 1);
		spriteBatch.begin();
		TextureRegion frame = walkingWarrior.getImageToRender(deltaTime);
//		batch.draw(img, 0, 50);
		spriteBatch.draw(frame, 50, 50);
		spriteBatch.end();
	}
	
	@Override
	public void dispose () {
		spriteBatch.dispose();
		img.dispose();
	}
}
