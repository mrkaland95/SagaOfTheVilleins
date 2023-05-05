package inf112.saga.of.the.villeins.Game.LootSystem;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TestLootCollection {

    @Test
    public void LootColletionTest(){
        LootCollection loot = new LootCollection();
        for (int i = 0; i < 10; i++){
            loot.generateUpgrade();
        }
        assertEquals(10, loot.getUpgrades().size());
    }
    
    
}
