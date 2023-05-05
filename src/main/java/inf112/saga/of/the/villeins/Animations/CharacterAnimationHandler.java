package inf112.saga.of.the.villeins.Animations;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.saga.of.the.villeins.Characters.CharacterDirection;
import inf112.saga.of.the.villeins.Characters.ICharacter;
import inf112.saga.of.the.villeins.Characters.Player;
import inf112.saga.of.the.villeins.Controller.PlayerAction;

/**
 * Klasse som er ansvarling for å holde på alle animasjonene til en type karakter,
 * tegne animasjon som peker i riktig retning osv.
 */
public class CharacterAnimationHandler {
    // Disse er package-private for å testes.
    CharacterDirection animationStartingDirection;
    Animation2D idleAnimation;
    Animation2D walkAnimation;
    Animation2D attackAnimation;
    Animation2D activeAnimation;
    final SpriteBatch spriteBatch;
    final float playbackSpeedMultiplier = 1f;


    public CharacterAnimationHandler(
            SpriteBatch spriteBatch,
            Texture idleAnimationTexture,
            Integer idleFrames,
            Texture walkAnimationTexture,
            Integer walkFrames,
            Texture attackAnimationTexture,
            Integer attackFrames,
            CharacterDirection animationStartingDirection) {

        this.spriteBatch = spriteBatch;
        this.animationStartingDirection = animationStartingDirection;


        if (idleAnimationTexture != null) {
            this.idleAnimation = new Animation2D(idleAnimationTexture, idleFrames, playbackSpeedMultiplier);
        }
        if (walkAnimationTexture != null) {
            this.walkAnimation = new Animation2D(walkAnimationTexture, walkFrames, playbackSpeedMultiplier);
        }
        if (attackAnimationTexture != null){
            this.attackAnimation = new Animation2D(attackAnimationTexture, attackFrames, playbackSpeedMultiplier);
        }
    }


    public void render(ICharacter character, float deltaTime) {

        activeAnimation = switch(character.getCharacterState()) {
            case IDLE   -> idleAnimation;
            case MOVING -> walkAnimation;
            case ATTACK -> attackAnimation; // Fikk ikke laget nok attack animasjoner, men funskjonaliteten er det for det.
            case DYING  -> idleAnimation;
        };

        Sprite currentSprite = this.activeAnimation.getImageToRender(deltaTime, true);
        // flipper spriten mot venstre slik at alle sprites starter i samme retning.
        if (animationStartingDirection == CharacterDirection.RIGHT) {
            currentSprite.flip(true, false);
        }

        if (character.getCharacterDirection() == CharacterDirection.RIGHT) {
            currentSprite.flip(true, false);
        }

        currentSprite.setCenter(character.getCurrentPosition().x, character.getCurrentPosition().y);

        spriteBatch.begin();
        currentSprite.draw(spriteBatch);
        spriteBatch.end();
    }
}
