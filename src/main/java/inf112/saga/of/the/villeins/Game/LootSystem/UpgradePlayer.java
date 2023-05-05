package inf112.saga.of.the.villeins.Game.LootSystem;

import java.util.concurrent.ThreadLocalRandom;
import inf112.saga.of.the.villeins.Characters.IPlayable;

public class UpgradePlayer {

    /**
     * Legger til alle oppgraderingene i inventory til player
     * 
     * @param player spilleren som skal få oppgraderinger
     * @param inventory en liste av alle oppgraderingen samlet
     */
    public IPlayable UpgradeStats(IPlayable player, LootCollection inventory) {
        for (Upgrades upgrade : inventory.getUpgrades()) {
            applyUpgrade(player, upgrade);
        }
        return player;
    }

    /**
     * Sjekker hvilken oppgradering det er og legger til den oppgraderingen 
     * basert på et random tall i en range
     * 
     * @param player spilleren som oppgraderes
     * @param upgrade enum av oppgraderings-typen
     */
    private void applyUpgrade(IPlayable player, Upgrades upgrade){

        if(upgrade == Upgrades.DAMAGE_BOOST){
            int rand = ThreadLocalRandom.current().nextInt(1,3);
            player.setStrength(player.getStrength()+(rand*3));
        }
        else if(upgrade == Upgrades.HEALTH_BOOST){
            int rand = ThreadLocalRandom.current().nextInt(1,3);
            player.setHealth(player.getCurrentHealth()+(rand*3));
            player.setMaxHealth(player.getMaxHealth()+(rand*3));
        }
    }
}
