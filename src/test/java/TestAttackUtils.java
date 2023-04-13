import inf112.saga.of.the.villeins.MapUtils.TilePosition;
import inf112.saga.of.the.villeins.Utils.AttackUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAttackUtils {

    @Test
    public void testCubeDistance() {
        TilePosition position1 = new TilePosition(1, 2);
        TilePosition position2 = new TilePosition(3, 3);



        int expectedResult = 3;
        int result = AttackUtils.cubeDistance(position1, position2);

        assertEquals(expectedResult, result);
    }
}
