package com.project.roguelike;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;

import java.awt.*;

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
		System.out.println(Gdx.graphics.getMonitors());
		spriteBatch.begin();
		TextureRegion frame = walkingWarrior.getImageToRender(deltaTime);
		float warriorXPosition = 300;
//		if (warriorXPosition < 300) {
//			warriorXPosition++;
//		} else {
//			warriorXPosition = 0;
//		}
		warriorXPosition += 4;
		spriteBatch.draw(frame, warriorXPosition, 100f);
		spriteBatch.end();
	}
	
	@Override
	public void dispose () {
		spriteBatch.dispose();
		img.dispose();
	}
}
