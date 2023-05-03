package inf112.saga.of.the.villeins.Factories;

import inf112.saga.of.the.villeins.Animations.CharacterAnimationHandler;
import inf112.saga.of.the.villeins.Characters.BaseMonster;
import inf112.saga.of.the.villeins.Characters.ICharacter;
import inf112.saga.of.the.villeins.Characters.Player;
import inf112.saga.of.the.villeins.Utils.TilePosition;

public class CharacterFactory implements ICharacterFactory {
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
    public ICharacter getWarriorCharacter(TilePosition spawnPosition) {
        return new Player(spawnPosition, this.warriorHandler, 50, 200, 5, 1);
    }
    @Override
    public ICharacter getSlimeCharacter(TilePosition spawnPosition) {
        return new BaseMonster(spawnPosition, this.slimeHandler, 25, 5, 5, 1, "Slime");
    }

    @Override
    public ICharacter getGhostCharacter(TilePosition spawnPosition) {
        return new BaseMonster(spawnPosition, this.ghostHandler, 25, 5, 5, 1, "Ghost");
    }

    @Override
    public ICharacter getDragonCharacter(TilePosition spawnPosition) {
        return new BaseMonster(spawnPosition, this.dragonHandler, 25, 5, 4, 1, "Dragon");
    }

    @Override
    public ICharacter getRandomCharacter(TilePosition spawnPosition) {
        return null;
    }
}
