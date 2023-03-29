package inf112.saga.of.the.villeins.Animations;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import inf112.saga.of.the.villeins.Characters.ICharacter;

/**
 * Class for handling the animation of characters, separate from the character objects.
 */
public class CharacterAnimationHandler {
    enum AnimationDirections {

    }
    private Animation2D idleAnimation;
    private Animation2D walkAnimation;
    private Animation2D attackAnimation;
    private Animation2D activeAnimation;

    private int idleFrameCols;
    private int idleframeRows;
    private final SpriteBatch spriteBatch;

    private final float playbackSpeedMultiplier = 1f;

    public CharacterAnimationHandler(
            Texture idleAnimationTexture,
            Texture walkAnimationTexture,
            Texture attackAnimationTexture,
            SpriteBatch spriteBatch,
            Integer rows,
            Integer cols) {

        this.spriteBatch = spriteBatch;

        // very temporary solution until sprites/animations are made.
        if (rows != null) {
            idleframeRows = rows;
        }
        if (cols != null) {
            idleFrameCols = cols;
        }


        if (idleAnimationTexture != null) {
            this.idleAnimation = new Animation2D(idleAnimationTexture, idleframeRows, idleFrameCols, playbackSpeedMultiplier);
        }
        if (walkAnimationTexture != null) {
            this.walkAnimation = new Animation2D(walkAnimationTexture, playbackSpeedMultiplier);
        }
        if (attackAnimationTexture != null){
            this.attackAnimation = new Animation2D(attackAnimationTexture, playbackSpeedMultiplier);
        }
    }


    public void render(ICharacter character, float deltaTime) {

        activeAnimation = switch(character.getCharacterState()) {
            case IDLE   -> idleAnimation;
            case MOVING -> walkAnimation;
            case ATTACK -> idleAnimation; // TODO endre denne til "attack" når attack animasjon er laget.
            case DYING  -> idleAnimation;
        };


        TextureRegion currentSprite = this.activeAnimation.getImageToRender(deltaTime, true);
        Vector2 spriteRenderPosition = calculateRenderPosition(currentSprite, character);
        spriteBatch.begin();
        spriteBatch.draw(currentSprite, spriteRenderPosition.x, spriteRenderPosition.y);
        spriteBatch.end();
    }



    /**
     * Since the draw position renders at the bottom left of the sprite/texture, we need to calculate which world
     * coordinate maps to the middle of the sprite.
     * Helper method that calculates this.
     */
    private static Vector2 calculateRenderPosition(TextureRegion currentSprite, ICharacter character) {
        Vector2 characterPosition = character.getCurrentPosition();
        float spriteX = characterPosition.x - (currentSprite.getRegionWidth() / 2.0f);
        float spriteY = characterPosition.y - (currentSprite.getRegionHeight() / 2.0f);
        return new Vector2(spriteX, spriteY);
    }

    public int getSpriteWidth() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        return this.activeAnimation.getImageToRender(deltaTime, true).getRegionWidth();
    }

    public int getSpriteHeight() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        return this.activeAnimation.getImageToRender(deltaTime, true).getRegionHeight();
    }
}
