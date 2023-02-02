package com.project.roguelike;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animator2D implements ApplicationListener {
    // Adjusted class from the official libgdx documentation, to implement 2d animations.
    // https://libgdx.com/wiki/graphics/2d/2d-animation

    // Defines the columns of the sprite sheet
    private static final int FRAME_COLUMNS = 12;

    Animation<TextureRegion> walkAnimation;
    Texture warriorWalkSheet;
    SpriteBatch spriteBatch;

    // Variable for tracking elapsed time for the animation.
    float elapsedAnimationTime;

    @Override
    public void create() {
        warriorWalkSheet = new Texture(Gdx.files.internal("./Sprites/Warrior/WalkingWarrior.png"));

        // Has to be done this way because the TextureRegion class expects a 2d array.
        TextureRegion[][] tempTextures = TextureRegion.split(warriorWalkSheet, warriorWalkSheet.getWidth() / FRAME_COLUMNS, warriorWalkSheet.getHeight());


        // Places the textures back into a 1d array in the correct order.
        // Doing this beacuse the "Animation" constructor requires a 1D array
        TextureRegion[] walkFrames = new TextureRegion[FRAME_COLUMNS];
        int index = 0;
        for (int i = 0; i < 1; i++) {
            for (int j = 0; i < FRAME_COLUMNS; j++) {
                walkFrames[index++] = tempTextures[i][j];
            }
        }

        walkAnimation = new Animation<TextureRegion>(0.025f, walkFrames);
        spriteBatch = new SpriteBatch();
        elapsedAnimationTime = 0f;
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Clear screen
        elapsedAnimationTime += Gdx.graphics.getDeltaTime();

        TextureRegion currentFrame = walkAnimation.getKeyFrame(elapsedAnimationTime, true);
        spriteBatch.begin();
        spriteBatch.draw(currentFrame, 50, 50);
        spriteBatch.end();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        warriorWalkSheet.dispose();
    }
}
