package inf112.saga.of.the.villeins.Characters;

import com.badlogic.gdx.math.Vector2;
import inf112.saga.of.the.villeins.Controller.CharacterAnimationController;

public class Slime extends BaseMonster {
    public Slime(Vector2 startPosition, CharacterAnimationController animationController, int maxHealth, int strength, int defense, int attackRange) {
        super(startPosition, animationController, maxHealth, strength, defense, attackRange);
    }
}
