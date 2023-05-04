package inf112.saga.of.the.villeins.Game.LootSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class LootCollection {
    private List<Upgrades> collectedUpgrades;


    public LootCollection() {
        collectedUpgrades = new ArrayList<>();
    }
    /**
     * Genererer og legger til en tilfeldig upgrade
     * 
     * @param Upgrades en enum av forskjellige oppraderinger
     * 
     */
    public void generateUpgrade(){
        collectedUpgrades.add(Upgrades.values()[new Random().nextInt(Upgrades.values().length)]); 
    }

    public List<Upgrades> getUpgrades(){
        return this.collectedUpgrades;
    }    
}   
