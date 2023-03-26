package inf112.saga.of.the.villeins.Controller;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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

    private int idleFrameCols;
    private int idleframeRows;
    private final SpriteBatch spriteBatch;
    private final ShapeRenderer renderer;

    private final float playbackSpeedMultiplier = 1f;

    public CharacterAnimationController(
                                        String idleAnimationPath,
                                        String walkAnimationPath,
                                        String attackAnimationPath,
                                        SpriteBatch spriteBatch,
                                        ShapeRenderer renderer,
                                        Integer rows,
                                        Integer cols) {
        this.spriteBatch = spriteBatch;
        this.renderer = renderer;


        // very temporary solution until sprites/animations are made.
        if (rows != null) {
            idleframeRows = rows;
        }
        if (rows != null) {
            idleFrameCols = cols;
        }


        if (idleAnimationPath != null) {
            this.idleAnimation = new Animation2D(idleAnimationPath, idleframeRows, idleFrameCols, playbackSpeedMultiplier);
        }
        if (walkAnimationPath != null) {
            this.walkAnimation = new Animation2D(walkAnimationPath, playbackSpeedMultiplier);
        }
        if (attackAnimationPath != null){
            this.attackAnimation = new Animation2D(attackAnimationPath, playbackSpeedMultiplier);


        }
    }

    private void drawHealthbar(TextureRegion sprite, ICharacter character, ShapeRenderer renderer) {

        float characterWidth = sprite.getRegionWidth();
        float characterHeight = sprite.getRegionHeight() / 2f;

        // Gets the size of the bar for "current" health.
        float currentHealthPercentage = (float) character.getMaxHealth() / character.getCurrentHealth();
//        float currentHealthBarWidth = totalBarWidth / currentHealthPercentage;

        float totalBarWidth = 40f;
        float totalBarHeight = 8f;


        float healthBarX = character.getCurrentPosition().x - characterWidth;
        float healthBarY = character.getCurrentPosition().y + characterHeight + 10f;

        // Padding for the background healthbar
        float paddingX = 1.5f;
        float paddingY = 1.5f;

        renderer.begin(ShapeRenderer.ShapeType.Filled);

        // Background bar/total health bar
        renderer.setColor(1, 0, 0, 1);
        renderer.rect(healthBarX, healthBarY, totalBarWidth, totalBarHeight);

        // Current health bar.
        renderer.setColor(0, 1, 0, 1);
        renderer.rect(
                healthBarX + paddingX,
                healthBarY + paddingY,
                (characterWidth - 2 * paddingX) / currentHealthPercentage,
                totalBarHeight - 2 * paddingY);
        renderer.end();



//        renderer.begin(ShapeRenderer.ShapeType.Filled);
//        renderer.setColor(1, 0, 0, 1);
//        renderer.rect(healthBarX, healthBarY, totalBarWidth, totalBarHeight);
//        renderer.setColor(0, 1, 0, 1);
//        renderer.rect(healthBarX + 1, healthBarY + 1, currentHealthBarWidth, totalBarHeight + 5);
//        renderer.end();
    }

    /**
     * Function responsible for getting and rendering a character's sprite. Needs to be called
     * Inside the main game loop, i.e the "render" function of the sagaOfTheVilleinsGame
     */
    public void render(ICharacter character) {

        switch(character.getCharacterState()) {
            case IDLE   -> activeAnimation = idleAnimation;
            case MOVING -> activeAnimation = walkAnimation;
            case ATTACK -> activeAnimation = idleAnimation; // TODO endre dette når attack animasjon er laget.
            default     -> activeAnimation = idleAnimation;
        }

        float deltaTime = Gdx.graphics.getDeltaTime();

        TextureRegion currentSprite = this.activeAnimation.getImageToRender(deltaTime, true);
        Vector2 spriteRenderPosition = calculateRenderPosition(currentSprite, character);
        spriteBatch.begin();
        spriteBatch.draw(currentSprite, spriteRenderPosition.x, spriteRenderPosition.y);
        spriteBatch.end();
        drawHealthbar(currentSprite, character, renderer);
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
