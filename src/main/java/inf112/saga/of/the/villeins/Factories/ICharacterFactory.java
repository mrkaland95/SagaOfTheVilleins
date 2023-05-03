package inf112.saga.of.the.villeins.Factories;

import inf112.saga.of.the.villeins.Characters.ICharacter;
import inf112.saga.of.the.villeins.Utils.TilePosition;

public interface ICharacterFactory {
    ICharacter getWarriorCharacter(TilePosition spawnPosition);

    ICharacter getSlimeCharacter(TilePosition spawnPosition);

    ICharacter getGhostCharacter(TilePosition spawnPosition);

    ICharacter getDragonCharacter(TilePosition spawnPosition);
    ICharacter getRandomCharacter(TilePosition spawnPosition);
}
