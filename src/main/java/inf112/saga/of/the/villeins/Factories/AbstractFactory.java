package inf112.saga.of.the.villeins.Factories;

import inf112.saga.of.the.villeins.Characters.ICharacter;
import inf112.saga.of.the.villeins.Utils.TilePosition;

public interface AbstractFactory {
    ICharacter getPlayerCharacter(TilePosition spawnPosition);
    ICharacter getEnemyCharacter(TilePosition spawnPosition);
    ICharacter getBossCharacter(TilePosition spawnPosition);
}
