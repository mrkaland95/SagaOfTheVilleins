package inf112.saga.of.the.villeins.Factories;

import inf112.saga.of.the.villeins.Animations.CharacterAnimationHandler;
import inf112.saga.of.the.villeins.Characters.BaseMonster;
import inf112.saga.of.the.villeins.Characters.ICharacter;
import inf112.saga.of.the.villeins.Characters.Player;
import inf112.saga.of.the.villeins.Utils.TilePosition;

import java.util.Random;

public class CharacterFactory implements AbstractFactory {
    CharacterAnimationHandler warriorHandler;
    CharacterAnimationHandler slimeHandler;
    CharacterAnimationHandler dragonHandler;
    CharacterAnimationHandler ghostHandler;

    public CharacterFactory(CharacterAnimationHandler warriorHandler,
                            CharacterAnimationHandler slimeHandler,
                            CharacterAnimationHandler dragonHandler,
                            CharacterAnimationHandler ghostHandler) {
        this.warriorHandler = warriorHandler;
        this.slimeHandler   = slimeHandler;
        this.dragonHandler  = dragonHandler;
        this.ghostHandler   = ghostHandler;
    }

    @Override
    public ICharacter getPlayerCharacter(TilePosition spawnPosition) {
        return new Player(spawnPosition, this.warriorHandler, 50, 200, 5, 1);
    }

    @Override
    public ICharacter getEnemyCharacter(TilePosition spawnPosition) {
        ICharacter character;
        Random random = new Random();
        int number = random.nextInt(100);

        if (number < 50) {
            character = getGhostCharacter(spawnPosition);
        } else {
            character = getSlimeCharacter(spawnPosition);
        }

        return character;
    }

    @Override
    public ICharacter getBossCharacter(TilePosition spawnPosition) {
        return getDragonCharacter(spawnPosition);
    }

    private ICharacter getSlimeCharacter(TilePosition spawnPosition) {
        return new BaseMonster(spawnPosition, this.slimeHandler, 25, 5, 5, 1, "Slime");
    }

    private ICharacter getGhostCharacter(TilePosition spawnPosition) {
        return new BaseMonster(spawnPosition, this.ghostHandler, 25, 5, 5, 1, "Ghost");
    }

    private ICharacter getDragonCharacter(TilePosition spawnPosition) {
        return new BaseMonster(spawnPosition, this.dragonHandler,  200, 10, 4, 1, "Dragon");
    }

}
