package com.project.roguelike;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animator2D {
    // Adjusted class from the official libgdx documentation, to implement 2d animations.
    // https://libgdx.com/wiki/graphics/2d/2d-animation

    // Defines the columns of the sprite sheet
    private static final int FRAME_COLUMNS = 12;
    private static final int FRAME_ROWS = 1;
    Animation<TextureRegion> walkAnimation;
    Texture animationSheet;

    // Variable for tracking elapsed time for the animation.
    float elapsedAnimationTime;

    public Animator2D(String spriteSheet) {
        this.animationSheet = new Texture(Gdx.files.internal(spriteSheet));
        // Transforms the 2d texture array back into a 1d array

        TextureRegion[][] tempTextures = TextureRegion.split(animationSheet,
                animationSheet.getWidth() / FRAME_COLUMNS,
                animationSheet.getHeight() / FRAME_ROWS
        );


        TextureRegion[] walkFrames = new TextureRegion[FRAME_COLUMNS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; i < FRAME_COLUMNS; j++) {
                walkFrames[index++] = tempTextures[i][j];
            }
        }
        this.walkAnimation = new Animation<TextureRegion>(0.025f, walkFrames);
        elapsedAnimationTime = 0f;

    }

    public TextureRegion getImageToRender(float deltaTime) {
        elapsedAnimationTime += deltaTime;
        TextureRegion currentFrame = walkAnimation.getKeyFrame(elapsedAnimationTime, true);
        return currentFrame;
    }
}

