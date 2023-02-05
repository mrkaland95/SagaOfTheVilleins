package com.project.roguelike;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animation2D {
    // Adjusted class from the official libgdx documentation, to implement 2d animations.
    // https://libgdx.com/wiki/graphics/2d/2d-animation

    // Defines the columns of the sprite sheet
    private static final int FRAME_COLUMNS = 11;
    private static final int FRAME_ROWS = 1;
    Animation<TextureRegion> walkAnimation;
    Texture animationSheet;

    // Variable for tracking elapsed time for the animation.
    float elapsedAnimationTime;

    public Animation2D(String pathToSpriteSheet) {
        this.animationSheet = new Texture(Gdx.files.internal(pathToSpriteSheet));
        TextureRegion[][] tempTextures = TextureRegion.split(animationSheet,
                animationSheet.getWidth() / FRAME_COLUMNS,
                animationSheet.getHeight() / FRAME_ROWS);

        TextureRegion[] walkFrames = new TextureRegion[FRAME_COLUMNS * FRAME_ROWS];

//        // Transforms the 2d texture array back into a 1d array
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLUMNS; j++) {
                walkFrames[index] = tempTextures[i][j];
                index++;
            }
        }
        walkAnimation = new Animation<TextureRegion>(0.09f, walkFrames);
        elapsedAnimationTime = 0f;
    }


    public TextureRegion getImageToRender(float deltaTime) {
        elapsedAnimationTime += deltaTime;
        return walkAnimation.getKeyFrame(elapsedAnimationTime, true);
    }
}
