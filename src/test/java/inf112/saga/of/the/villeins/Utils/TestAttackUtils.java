package inf112.saga.of.the.villeins.Utils;

import com.badlogic.gdx.math.Vector2;
import inf112.saga.of.the.villeins.Characters.BaseMonster;
import inf112.saga.of.the.villeins.Characters.CharacterState;
import inf112.saga.of.the.villeins.Characters.ICharacter;
import inf112.saga.of.the.villeins.Game.GameScreen;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestAttackUtils {
    private ICharacter character;
    private ICharacter opponent;
    private AttackUtils attackUtils;
    private int attackRange;

    @Before
    public void setUp() {
        character = new BaseMonster(new TilePosition(0, 0), null, 20, 4, 4, 1, "");
        opponent = new BaseMonster(new TilePosition(0, 1), null, 20, 4, 4, 1, "");
        attackRange = 1;
        attackUtils = new AttackUtils(character, attackRange);
        GameScreen.characterList.clear();
        GameScreen.characterList.add(character);
        GameScreen.characterList.add(opponent);
    }

    @Test
    public void testAttackCharacterSuccess() {
        character.setCharacterState(CharacterState.IDLE);
        character.setStrength(5);
        character.setCurrentActionPoints(1);
        boolean attackSuccessful = attackUtils.attackCharacter(opponent.getCurrentPosition());
        assertTrue(attackSuccessful);
    }

    @Test
    public void testCharacterCannotAttackSelf() {
        character.setCharacterState(CharacterState.IDLE);
        character.setCurrentActionPoints(1);
        boolean attackSuccessful = attackUtils.attackCharacter(character.getCurrentPosition());
        assertFalse(attackSuccessful);
    }

    @Test
    public void testCharacterCannotAttackOutOfRange() {
        character.setCharacterState(CharacterState.IDLE);
        character.setCurrentActionPoints(1);
        opponent.setCurrentPosition(new Vector2(5, 5));
        boolean attackSuccessful = attackUtils.attackCharacter(opponent.getCurrentPosition());
        assertFalse(attackSuccessful);
    }

    @Test
    public void testCharacterCannotAttackWithoutActionPoints() {
        character.setCharacterState(CharacterState.IDLE);
        character.setCurrentActionPoints(0);
        boolean attackSuccessful = attackUtils.attackCharacter(opponent.getCurrentPosition());
        assertFalse(attackSuccessful);
    }

    @Test
    public void testCharacterCannotAttackInNonIdleState() {
        character.setCharacterState(CharacterState.MOVING);
        boolean attackSuccessful = attackUtils.attackCharacter(opponent.getCurrentPosition());
        assertFalse(attackSuccessful);
    }
}
