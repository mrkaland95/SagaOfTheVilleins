package inf112.saga.of.the.villeins.Characters.AI;

import inf112.saga.of.the.villeins.Characters.BaseMonster;
import inf112.saga.of.the.villeins.Characters.ICharacter;
import inf112.saga.of.the.villeins.Game.TileInfoMap;
import inf112.saga.of.the.villeins.Utils.TilePosition;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TestAI {


    @Test
    public void testAI() {
        TileInfoMap infoMap = new TileInfoMap(10, 10);
        ICharacter testMonster = new BaseMonster(new TilePosition(2, 1), null,5, 5, 5, 1, "");
        ICharacter testMonster2 = new BaseMonster(new TilePosition(1, 1), null,5, 5, 5, 1, "");
        SimpleAI simpleAI = new SimpleAI(testMonster2, testMonster);
        simpleAI.setTargetCharacter(testMonster2);
        boolean result = simpleAI.AIPerformAction(infoMap);
        assertFalse(result, "Should return false");
    }
}
