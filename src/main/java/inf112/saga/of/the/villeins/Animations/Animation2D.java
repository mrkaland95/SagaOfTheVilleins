package inf112.saga.of.the.villeins.Animations;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
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
    float animationStateTime;

    public Animation2D(Texture spriteSheet, float playbackMultiplier) {
        this.animation = loadAnimation(spriteSheet, playbackMultiplier);
        }

    public Animation2D(Texture spriteSheet, int frames, float playbackMultiplier) {
        this.FRAME_COLUMNS = frames;
        this.animation = loadAnimation(spriteSheet, playbackMultiplier);
    }


    private Animation<TextureRegion> loadAnimation(Texture spriteSheet, float playbackMultiplier) {
        this.animationSheet = spriteSheet;
        // The texture region class' split function only returns a 2d array, so despite our sheets being 1d,
        // We need to initialize it as a 2d array.
        TextureRegion[][] tempTextures = TextureRegion.split(
                        animationSheet,
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
        float animationPlaybackSpeed = frameDuration * (1 / playbackMultiplier);
        this.animationStateTime = 0f;
        return new Animation<>(animationPlaybackSpeed, animationFrames);
    }

    public Sprite getImageToRender(float deltaTime, boolean looping) {
        animationStateTime += deltaTime;
        return new Sprite(animation.getKeyFrame(animationStateTime, looping));
    }
}

