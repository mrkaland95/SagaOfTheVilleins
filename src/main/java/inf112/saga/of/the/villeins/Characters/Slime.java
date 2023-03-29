package inf112.saga.of.the.villeins.Characters;

import com.badlogic.gdx.math.Vector2;
import inf112.saga.of.the.villeins.Animations.CharacterAnimationHandler;
import inf112.saga.of.the.villeins.MapUtils.TilePosition;

public class Slime extends BaseMonster {
    public Slime(Vector2 startPosition, CharacterAnimationHandler animationController, int maxHealth, int strength, int defense, int attackRange) {
        super(startPosition, animationController, maxHealth, strength, defense, attackRange);
    }
    public Slime(TilePosition startPosition, CharacterAnimationHandler animationController, int maxHealth, int strength, int defense, int attackRange) {
        super(startPosition, animationController, maxHealth, strength, defense, attackRange);
    }
}
