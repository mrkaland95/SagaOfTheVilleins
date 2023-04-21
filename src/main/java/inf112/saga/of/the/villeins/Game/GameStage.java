package inf112.saga.of.the.villeins.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import inf112.saga.of.the.villeins.Characters.ICharacter;
import inf112.saga.of.the.villeins.Factories.CharacterFactory;
import inf112.saga.of.the.villeins.MapUtils.TilePosition;

public class GameStage {
    private int stage;
    private CharacterFactory factory;
    private int enemyCount;

    public GameStage(int stage, CharacterFactory factory){
        this.stage = stage;
        this.factory = factory;
        this.enemyCount = generateEnemyCount();
    }

    public List<ICharacter> generateCharacters(){
        ArrayList<ICharacter> generatedCharacters = new ArrayList<>(enemyCount+1);
        ArrayList<TilePosition> spawns = decideSpawns(getPossibleSpawns());

        ICharacter player = factory.getWarriorCharacter(new TilePosition(1, 1));
        generatedCharacters.add(player);
        for (int i = 0; i < enemyCount; i++){
            ICharacter tempEnemy = factory.getSlimeCharacter(spawns.get(i));
            generatedCharacters.add(tempEnemy);
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
                if(GameLoop.infoMap.isMovable(possibleSpawn)){
                    spawns.add(possibleSpawn);
                }
            }
        }
        return spawns;
    }
}
