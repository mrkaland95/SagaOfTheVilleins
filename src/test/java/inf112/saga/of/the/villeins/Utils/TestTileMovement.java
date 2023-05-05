package inf112.saga.of.the.villeins.Utils;
import com.badlogic.gdx.math.Vector2;
import inf112.saga.of.the.villeins.Characters.CharacterDirection;
import inf112.saga.of.the.villeins.Characters.CharacterState;
import inf112.saga.of.the.villeins.Characters.ITurnBasedMovable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TestTileMovement {
    private ITurnBasedMovable mockCharacter;
    private TileMovement tileMovement;

    @BeforeEach
    void setUp() {
        mockCharacter = mock(ITurnBasedMovable.class);
        tileMovement = new TileMovement(mockCharacter);
    }

    @Test
    void testMoveWithNullPath() {
        tileMovement.move(0.1f);
        verify(mockCharacter, never()).setCurrentPosition(any());
        verify(mockCharacter, never()).setCharacterDirection(any());
    }

    @Test
    void testMoveWithEmptyPath() {
        tileMovement.setPath(new ArrayList<>());
        tileMovement.move(0.1f);
        verify(mockCharacter, never()).setCurrentPosition(any());
        verify(mockCharacter, never()).setCharacterDirection(any());
    }

    @Test
    void testMoveWithValidPath() {
        List<TilePosition> path = new ArrayList<>();
        path.add(new TilePosition(0, 0));
        path.add(new TilePosition(1, 0));
        tileMovement.setPath(path);

        when(mockCharacter.getCurrentPosition()).thenReturn(new Vector2(0, 0));
        when(mockCharacter.getMoveSpeed()).thenReturn(1f);
        when(mockCharacter.getCurrentActionPoints()).thenReturn(5);

        tileMovement.move(0.1f);

        verify(mockCharacter).setCurrentPosition(any());
        verify(mockCharacter).setCharacterDirection(eq(CharacterDirection.RIGHT));
    }

    @Test
    void testCalculateNewVectorPosition() {
        Vector2 currentPosition = new Vector2(0, 0);
        Vector2 destination = new Vector2(100, 100);
        float deltaTime = 1f;
        float moveSpeed = 50f;

        Vector2 newPosition = TileMovement.calculateNewVectorPosition(currentPosition, destination, deltaTime, moveSpeed);
        System.out.println(newPosition);

        assertNotEquals(currentPosition.x, newPosition.x);
        assertNotEquals(currentPosition.y, newPosition.y);
    }
}
