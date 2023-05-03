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

    public GameStage(int stage, AbstractFactory factory, ICharacter player){
        this.stage = stage;
        this.factory = factory;
        this.player = player;
        this.enemyCount = generateEnemyCount();
    }

    public List<ICharacter> generateCharacters(){
        ArrayList<ICharacter> generatedCharacters = new ArrayList<>(enemyCount+1);
        ArrayList<TilePosition> spawns = decideSpawns(getPossibleSpawns());

        player.setTilePosition(new TilePosition(1, 1));
        generatedCharacters.add(player);
        for (int i = 0; i < enemyCount; i++){
            ICharacter enemy = factory.getEnemyCharacter(spawns.get(i));
            generatedCharacters.add(enemy);
        }
        return generatedCharacters;
    }

    private int generateEnemyCount(){
        if(this.stage == 1){
            return ThreadLocalRandom.current().nextInt(2, 3);
        }
        if(this.stage == 2){
            return ThreadLocalRandom.current().nextInt(3, 5);
        }
        if(this.stage == 3){  
            return ThreadLocalRandom.current().nextInt(5, 6);
        }
        else {
            return 1;
        }
    }

    private ArrayList<TilePosition> decideSpawns(ArrayList<TilePosition> possibleSpawns){
        ArrayList<TilePosition> finalSpawns = new ArrayList<>(enemyCount);
        for(int i = 0; i < enemyCount; i++){
            int randomNum = ThreadLocalRandom.current().nextInt(0, possibleSpawns.size());
            finalSpawns.add(possibleSpawns.get(randomNum));
        }
        return finalSpawns;
    }

    private ArrayList<TilePosition> getPossibleSpawns(){
        ArrayList<TilePosition> spawns = new ArrayList<>(400);
        for (int i = 0; i<20; i++){
            for(int j = 0; j<20; j++){
                TilePosition possibleSpawn = new TilePosition(i, j);
                if(GameScreen.infoMap.tileIsMovable(possibleSpawn)){
                    spawns.add(possibleSpawn);
                }
            }
        }
        return spawns;
    }
}
