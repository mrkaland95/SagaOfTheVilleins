package inf112.saga.of.the.villeins.Factories;

import inf112.saga.of.the.villeins.Characters.BaseMonster;
import inf112.saga.of.the.villeins.Characters.ICharacter;
import inf112.saga.of.the.villeins.Characters.IPlayable;
import inf112.saga.of.the.villeins.Characters.Player;
import inf112.saga.of.the.villeins.Utils.TilePosition;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.Objects;

public class TestCharacterFactory {
    AbstractFactory factory = new CharacterFactory(null, null, null, null);

    @Test
    public void testCharacterFactory() {
        BaseMonster character = (BaseMonster) factory.getEnemyCharacter(new TilePosition(0, 0));
        boolean typeResult = (Objects.equals(character.getMonsterType(), "Slime") || Objects.equals(character.getMonsterType(), "Ghost"));
        assertTrue(typeResult);

        IPlayable player = (IPlayable) factory.getPlayerCharacter(new TilePosition(0, 0));
        assertTrue(player instanceof Player);

        ICharacter bossCharacter = factory.getBossCharacter(new TilePosition(0, 0));
        assertTrue(bossCharacter instanceof BaseMonster);
        typeResult = Objects.equals(((BaseMonster) bossCharacter).getMonsterType(), "Dragon");
        assertTrue(typeResult);
    }

}
