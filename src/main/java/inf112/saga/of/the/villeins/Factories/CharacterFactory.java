package inf112.saga.of.the.villeins.Factories;

import inf112.saga.of.the.villeins.Animations.CharacterAnimationHandler;
import inf112.saga.of.the.villeins.Characters.ICharacter;
import inf112.saga.of.the.villeins.Characters.Player;
import inf112.saga.of.the.villeins.Characters.Slime;
import inf112.saga.of.the.villeins.MapUtils.TilePosition;

public class CharacterFactory implements ICharacterFactory {
    CharacterAnimationHandler warriorHandler;
    CharacterAnimationHandler slimeHandler;
    CharacterAnimationHandler dragonHandler;
    CharacterAnimationHandler ghostHandler;

    public CharacterFactory(CharacterAnimationHandler warriorHandler,
                            CharacterAnimationHandler slimeHandler) {
        this.warriorHandler = warriorHandler;
        this.slimeHandler   = slimeHandler;
    }

    public ICharacter getWarriorCharacter(TilePosition spawnPosition) {
        return new Player(spawnPosition, this.warriorHandler, 50, 5, 5, 1);
    }
    public ICharacter getSlimeCharacter(TilePosition spawnPosition) {
        return new Slime(spawnPosition, this.slimeHandler, 25, 5, 5, 1);
    }

    public ICharacter getGhostCharacter() {
        throw new IllegalArgumentException("Not implemented yet");
    }

    public ICharacter getDragonCharacter() {
        throw new IllegalArgumentException("Not implemented yet");
    }
}
