package inf112.saga.of.the.villeins.Characters;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animation2D {
    // Adjusted class from the official libgdx documentation, to implement 2d animations.
    // https://libgdx.com/wiki/graphics/2d/2d-animation
    // Defines the dimensions of the sprite sheet that's taken in.
    private int FRAME_COLUMNS = 11;
    private int FRAME_ROWS = 1;
    Texture animationSheet;
    Animation<TextureRegion> animation;

    // Variable for tracking elapsed time for the animation.
    float elapsedAnimationTime;

    public Animation2D(String pathToSpriteSheet, float playbackMultiplier) {
        this.animation = loadAnimation(pathToSpriteSheet, playbackMultiplier);
        }

    public Animation2D(String pathToSpriteSheet, int frameRows, int frameColumns, float playbackMultiplier) {
        this.FRAME_ROWS = frameRows;
        this.FRAME_COLUMNS = frameColumns;
        this.animation = loadAnimation(pathToSpriteSheet, playbackMultiplier);
    }



    private Animation<TextureRegion> loadAnimation(String pathToSpriteSheet, float playerbackMultiplier) {
        int rows = this.FRAME_ROWS;
        int cols = this.FRAME_COLUMNS;

        this.animationSheet = new Texture(Gdx.files.internal(pathToSpriteSheet));
        // The texture region class' split function only takens in a 2d array, so despite our sheets being 1d,
        // We need to initialize it as a 2d array.
        TextureRegion[][] tempTextures = TextureRegion.split(animationSheet,
                animationSheet.getWidth() / FRAME_COLUMNS,
                animationSheet.getHeight() / FRAME_ROWS);

        TextureRegion[] animationFrames = new TextureRegion[FRAME_COLUMNS * FRAME_ROWS];

        // Unpacks the 2d texture array back into a 1d array
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLUMNS; j++) {
                animationFrames[index] = tempTextures[i][j];
                index++;
            }
        }
        float frameDuration = 1f / FRAME_COLUMNS;
        float animationPlaybackSpeed = frameDuration * playerbackMultiplier;
        this.elapsedAnimationTime = 0f;
        return new Animation<TextureRegion>(animationPlaybackSpeed, animationFrames);
    }

    public TextureRegion getImageToRender(float deltaTime, boolean looping) {
//        float deltaTime = Gdx.graphics.getDeltaTime();
        elapsedAnimationTime += deltaTime;
        return animation.getKeyFrame(elapsedAnimationTime, looping);
    }
}

