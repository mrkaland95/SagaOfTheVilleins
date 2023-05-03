import com.badlogic.gdx.math.Vector2;
import inf112.saga.of.the.villeins.Characters.BaseMonster;
import inf112.saga.of.the.villeins.Utils.TilePosition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestBaseCharacter {
    BaseMonster testMonster;

    @BeforeEach
    public void setup() {
        testMonster = new BaseMonster(new TilePosition(1,1), null, 40, 2, 5, 1, "test");
    }

    @Test
    public void testGetPosition() {
        Vector2 expectedPosition = new Vector2(400.0f,250.0f);
        assertEquals(expectedPosition, testMonster.getCurrentPosition());
    }
}
