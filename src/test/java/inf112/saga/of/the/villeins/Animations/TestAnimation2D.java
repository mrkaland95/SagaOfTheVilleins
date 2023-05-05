package inf112.saga.of.the.villeins.Animations;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(MockitoExtension.class)
public class TestAnimation2D {
    private Texture mockTexture;
    private Animation2D animation2D;
    private int frames = 11;
    private float playbackMultiplier = 1f;

    @BeforeEach
    public void setUp() {
        mockTexture = Mockito.mock(Texture.class);
        Mockito.when(mockTexture.getWidth()).thenReturn(11 * 32);
        Mockito.when(mockTexture.getHeight()).thenReturn(32);
        animation2D = new Animation2D(mockTexture, frames, playbackMultiplier);
    }

    @Test
    public void testAnimation2DInitialization() {
        assertNotNull(animation2D);
    }

    @Test
    public void testGetImageToRender() {
        float deltaTime = 0.1f;
        Sprite image = animation2D.getImageToRender(deltaTime, true);
        assertNotNull(image);

        float expectedStateTime = deltaTime;
        assertEquals(expectedStateTime, animation2D.animationStateTime, 0.0001);
    }
}
