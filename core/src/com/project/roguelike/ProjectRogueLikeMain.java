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
	float warriorXPosition = 0f;
	float screenWidth = 560f;

	
	@Override
	public void create () {
		spriteBatch = new SpriteBatch();
		walkingWarrior = new Animation2D("./Sprites/Warrior/WalkingWarrior.png");
	}

	@Override
	public void render () {
		ScreenUtils.clear(0.2f, 0.0f, 0.0f, 0f);
		spriteBatch.begin();
		TextureRegion frame = walkingWarrior.getImageToRender();
		if (warriorXPosition < screenWidth) {
			warriorXPosition++;
		} else {
			warriorXPosition = 0;
		}
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
