package inf112.saga.of.the.villeins.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import inf112.saga.of.the.villeins.Characters.ICharacter;
import inf112.saga.of.the.villeins.Factories.AbstractFactory;
import inf112.saga.of.the.villeins.Utils.TilePosition;

public class GameStage {
    private int stage;
    private AbstractFactory factory;
    private int enemyCount;
    private ICharacter player;
    private TileInfoMap infoMap;

    public GameStage(int stage, AbstractFactory factory, ICharacter player, TileInfoMap infoMap){
        this.stage = stage;
        this.factory = factory;
        this.player = player;
        this.infoMap = infoMap;
        this.enemyCount = generateEnemyCount();
    }

    /**
     * Genererer karakterene og karakterlisten, spilleren er først for å ha lett tilgang til spilleren
     * 
     * @return en generert liste av alle karakterene på kartet
     */
    public List<ICharacter> generateCharacters(){
        ArrayList<ICharacter> generatedCharacters = new ArrayList<>(enemyCount+1);
        ArrayList<TilePosition> spawns = decideSpawns(getPossibleSpawns());

        player.setTilePosition(new TilePosition(1, 1));
        generatedCharacters.add(player);
        if(stage != 4){
            for (int i = 0; i < enemyCount; i++){
                ICharacter enemy = factory.getEnemyCharacter(spawns.get(i));
                generatedCharacters.add(enemy);
            }
        }
        else {
            ICharacter enemy = factory.getBossCharacter(new TilePosition(12, 12));
            generatedCharacters.add(enemy);
        }
        
        return generatedCharacters;
    }


    /**
     * Genererer et random antall fiender i en range basert på hvilken stage/nivå spillet er i
     * 
     * @return returnerer en int av hvor mange fiender som skal spawnes
     */
    private int generateEnemyCount(){
        if(this.stage == 1){
            return ThreadLocalRandom.current().nextInt(2, 3);
        }
        else if(this.stage == 2){
            return ThreadLocalRandom.current().nextInt(3, 5);
        }
        else if(this.stage == 3){  
            return ThreadLocalRandom.current().nextInt(5, 6);
        }
        else if(this.stage == 4){
            return 1;
        }
        else {
            return ThreadLocalRandom.current().nextInt(stage+1, stage + 4);
        }
    }

    /**
     * Velger en tilfeldig spawn fra alle de lovlige spawnsene på kartet og plasserer fiendene der
     * 
     * @param possibleSpawns liste av alle lovlige tiles på kartet
     * @return de tilesene som er valgt for å spawne fiendene
     */
    private ArrayList<TilePosition> decideSpawns(ArrayList<TilePosition> possibleSpawns){
        ArrayList<TilePosition> finalSpawns = new ArrayList<>(enemyCount);
        for(int i = 0; i < enemyCount; i++){
            int randomNum = ThreadLocalRandom.current().nextInt(0, possibleSpawns.size());
            finalSpawns.add(possibleSpawns.get(randomNum));
        }
        return finalSpawns;
    }
    /**
     * Finner alle lovlige tiles på kartet, legger de i en liste
     * 
     * @return returnerer listen av lovlige tiles
     */
    private ArrayList<TilePosition> getPossibleSpawns(){
        ArrayList<TilePosition> spawns = new ArrayList<>(400);
        for (int i = 0; i<20; i++){
            for(int j = 0; j<20; j++){
                TilePosition possibleSpawn = new TilePosition(i, j);
                if(this.infoMap.tileIsMovable(possibleSpawn)){
                    spawns.add(possibleSpawn);
                }
            }
        }
        return spawns;
    }
}
