package com.project.roguelike;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class ProjectRogueLikeMain extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

	Animator2D walkingWarrior;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("./Sprites/Warrior/WalkingWarrior.png");
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 1, 1, 1);
		batch.begin();
		batch.draw(img, 0, 50);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
