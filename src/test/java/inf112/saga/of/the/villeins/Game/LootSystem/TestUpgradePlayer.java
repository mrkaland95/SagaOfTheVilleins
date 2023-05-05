package inf112.saga.of.the.villeins.Game.LootSystem;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import inf112.saga.of.the.villeins.Characters.IPlayable;
import inf112.saga.of.the.villeins.Characters.Player;
import inf112.saga.of.the.villeins.Utils.TilePosition;

public class TestUpgradePlayer {
    
    @Test
    public void UpgradePlayerTest(){
        UpgradePlayer upgrader = new UpgradePlayer();
        IPlayable player = new Player(new TilePosition(0, 0), null, 0, 0, 0, 0);
        IPlayable notUpgradedPlayer = new Player(new TilePosition(0, 0), null, 0, 0, 0, 0);
        LootCollection loot = new LootCollection();
        loot.addUpgrade(Upgrades.DAMAGE_BOOST);
        upgrader.UpgradeStats(player, loot);

        boolean isEqual = player.getStrength() != notUpgradedPlayer.getStrength();
        
        assertTrue(isEqual);
        assertTrue(3 <= player.getStrength() && player.getStrength() <= 9);
    }
}
