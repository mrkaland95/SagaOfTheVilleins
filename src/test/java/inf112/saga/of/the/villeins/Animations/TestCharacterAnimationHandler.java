package inf112.saga.of.the.villeins.Animations;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import inf112.saga.of.the.villeins.Characters.CharacterDirection;
import inf112.saga.of.the.villeins.Characters.CharacterState;
import inf112.saga.of.the.villeins.Characters.ICharacter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TestCharacterAnimationHandler {


    @Mock
    private ICharacter character;
    @Mock
    private SpriteBatch spriteBatch;
    @Mock
    private Texture idleTexture;
    @Mock
    private Texture walkTexture;
    @Mock
    private Texture attackTexture;

    private CharacterAnimationHandler characterAnimationHandler;

    @Before
    public void setUp() {
        // Set the width and height for mocked Texture objects
        when(idleTexture.getWidth()).thenReturn(100);
        when(idleTexture.getHeight()).thenReturn(100);
        when(walkTexture.getWidth()).thenReturn(100);
        when(walkTexture.getHeight()).thenReturn(100);
        when(attackTexture.getWidth()).thenReturn(100);
        when(attackTexture.getHeight()).thenReturn(100);

        when(character.getCurrentPosition()).thenReturn(new Vector2(0, 0));

        characterAnimationHandler = new CharacterAnimationHandler(
                spriteBatch,
                idleTexture,
                5,
                walkTexture,
                5,
                attackTexture,
                5,
                CharacterDirection.RIGHT
        );
    }

    @Test
    public void testRenderWithIdleCharacter() {
        when(character.getCharacterState()).thenReturn(CharacterState.IDLE);
        when(character.getCharacterDirection()).thenReturn(CharacterDirection.RIGHT);

        characterAnimationHandler.render(character, 0.1f);

        // Verify that the character's state and direction were queried
        verify(character).getCharacterState();
        verify(character).getCharacterDirection();
    }

    @Test
    public void testRenderWithMovingCharacter() {
        when(character.getCharacterState()).thenReturn(CharacterState.MOVING);
        when(character.getCharacterDirection()).thenReturn(CharacterDirection.RIGHT);

        characterAnimationHandler.render(character, 0.1f);

        // Verify that the character's state and direction were queried
        verify(character).getCharacterState();
        verify(character).getCharacterDirection();
    }

    @Test
    public void testRenderWithAttackingCharacter() {
        when(character.getCharacterState()).thenReturn(CharacterState.ATTACK);
        when(character.getCharacterDirection()).thenReturn(CharacterDirection.RIGHT);

        characterAnimationHandler.render(character, 0.1f);

        // Verify that the character's state and direction were queried
        verify(character).getCharacterState();
        verify(character).getCharacterDirection();
    }

    @Test
    public void testRenderWithDyingCharacter() {
        when(character.getCharacterState()).thenReturn(CharacterState.DYING);
        when(character.getCharacterDirection()).thenReturn(CharacterDirection.RIGHT);

        characterAnimationHandler.render(character, 0.1f);

        // Verify that the character's state and direction were queried
        verify(character).getCharacterState();
        verify(character).getCharacterDirection();
    }
}
