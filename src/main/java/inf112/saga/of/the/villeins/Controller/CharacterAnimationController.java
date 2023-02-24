package inf112.saga.of.the.villeins.Controller;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import inf112.saga.of.the.villeins.Characters.Animation2D;
import inf112.saga.of.the.villeins.Characters.ICharacter;

/**
 * Class for handling the animation of characters, separate from the character objects.
 */
public class CharacterAnimationController {
    enum Animations {
        IDLE,
        MOVING,
        ATTACKING
    }
    enum AnimationDirections {

    }
    private Animation2D idleAnimation;
    private Animation2D walkAnimation;
    private Animation2D attackAnimation;
    private Animation2D activeAnimation;
    private final ICharacter character;
    private final SpriteBatch spriteBatch;

    private final float playbackSpeedMultiplier = 1f;

    public CharacterAnimationController(ICharacter character,
                                        String idleAnimationPath,
                                        String walkAnimationPath,
                                        String attackAnimationPath,
                                        SpriteBatch spriteBatch) {
        this.character = character;
        this.spriteBatch = spriteBatch;
        // very temporary solution until sprites/animations are made.
        if (idleAnimationPath != null) {
            this.idleAnimation = new Animation2D(idleAnimationPath, 1, 2, playbackSpeedMultiplier);
        }
        if (walkAnimationPath != null) {
            this.walkAnimation = new Animation2D(walkAnimationPath, playbackSpeedMultiplier);
        }
        if (attackAnimationPath != null){
            this.attackAnimation = new Animation2D(attackAnimationPath, playbackSpeedMultiplier);
        }
    }

    /**
     * Function responsible for getting and rendering a character's sprite. Needs to be called
     * Inside the main game loop, i.e the "render" function of the sagaOfTheVilleinsGame
     */
    public void render() {
        if (character.isMoving()) activeAnimation = walkAnimation;
        else                      activeAnimation = walkAnimation;

        float deltaTime = Gdx.graphics.getDeltaTime();
        TextureRegion currentSprite = this.activeAnimation.getImageToRender(deltaTime, true);
        Vector2 spriteRenderPosition = calculateRenderPosition(currentSprite, this.character);
        spriteBatch.draw(currentSprite, spriteRenderPosition.x, spriteRenderPosition.y);
    }

    /**
     * Since the draw position renders at the bottom left of the sprite/texture, we need to calculate which world
     * coordinate maps to the middle of the sprite.
     * Helper method that calculates this.
     */
    private static Vector2 calculateRenderPosition(TextureRegion currentSprite, ICharacter character) {
        Vector2 characterPosition = character.getPosition();
        float spriteX = characterPosition.x - (currentSprite.getRegionWidth() / 2.0f);
        float spriteY = characterPosition.y - (currentSprite.getRegionHeight() / 3.0f);
//        float spriteY = characterPosition.y;
        return new Vector2(spriteX, spriteY);
    }
}
