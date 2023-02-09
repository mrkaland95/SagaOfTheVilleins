package com.project.roguelike;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animation2D {
    // Adjusted class from the official libgdx documentation, to implement 2d animations.
    // https://libgdx.com/wiki/graphics/2d/2d-animation
    // Defines the dimensions of the sprite sheet that's taken in.
    private static int FRAME_COLUMNS = 11;
    private static int FRAME_ROWS = 1;
    Animation<TextureRegion> walkAnimation;
    Texture animationSheet;

    // Variable for tracking elapsed time for the animation.
    float elapsedAnimationTime;

    public Animation2D(String pathToSpriteSheet) {
        this.animationSheet = new Texture(Gdx.files.internal(pathToSpriteSheet));
        // The texture region class' split function only takens in a 2d array, so despite our sheets being 1d,
        // We need to initialize it as a 2d array.
        TextureRegion[][] tempTextures = TextureRegion.split(animationSheet,
                animationSheet.getWidth() / FRAME_COLUMNS,
                animationSheet.getHeight() / FRAME_ROWS);

        TextureRegion[] walkFrames = new TextureRegion[FRAME_COLUMNS * FRAME_ROWS];

        // Unpacks the 2d texture array back into a 1d array
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLUMNS; j++) {
                walkFrames[index] = tempTextures[i][j];
                index++;
            }
        }

        float frameDuration = 1f / FRAME_COLUMNS;
        // Defines how fast the animation should play. Higher number slows the animation down, while lower makes it faster.
        float animationSpeed = frameDuration * 1.33f;
        walkAnimation = new Animation<TextureRegion>(animationSpeed, walkFrames);
        elapsedAnimationTime = 0f;
        }

    public Animation2D(String pathToSpriteSheet, int frameRows, int frameColumns) {
        this.FRAME_ROWS = frameRows;
        this.FRAME_COLUMNS = frameColumns;
        this.animationSheet = new Texture(Gdx.files.internal(pathToSpriteSheet));
        // The texture region class' split function only takens in a 2d array, so despite our sheets being 1d,
        // We need to initialize it as a 2d array.
        TextureRegion[][] tempTextures = TextureRegion.split(animationSheet,
                animationSheet.getWidth() / FRAME_COLUMNS,
                animationSheet.getHeight() / FRAME_ROWS);

        TextureRegion[] walkFrames = new TextureRegion[FRAME_COLUMNS * FRAME_ROWS];

        // Unpacks the 2d texture array back into a 1d array
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLUMNS; j++) {
                walkFrames[index] = tempTextures[i][j];
                index++;
            }
        }

        float frameDuration = 1f / FRAME_COLUMNS;
        // Defines how fast the animation should play. Higher number slows the animation down, while lower makes it faster.
        float animationSpeed = frameDuration * 1.33f;
        walkAnimation = new Animation<TextureRegion>(animationSpeed, walkFrames);
        elapsedAnimationTime = 0f;
        }



    public TextureRegion getImageToRender() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        elapsedAnimationTime += deltaTime;
        return walkAnimation.getKeyFrame(elapsedAnimationTime, true);
    }
}

