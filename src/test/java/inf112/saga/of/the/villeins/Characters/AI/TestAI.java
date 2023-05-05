package inf112.saga.of.the.villeins.Characters.AI;

import inf112.saga.of.the.villeins.Characters.BaseMonster;
import inf112.saga.of.the.villeins.Characters.ICharacter;
import inf112.saga.of.the.villeins.Utils.TilePosition;
import org.junit.jupiter.api.Test;

public class TestAI {

    @Test
    public void testAI() {
        ICharacter testMonster = new BaseMonster(new TilePosition(0, 1), null,5, 5, 5, 1, "");
        ICharacter testMonster2 = new BaseMonster(new TilePosition(0, 0), null,5, 5, 5, 1, "");
        SimpleAI simpleAI = new SimpleAI(testMonster2, testMonster);
        boolean result = simpleAI.AIPerformAction();
        System.out.println(result);
        
    }

}
