package inf112.saga.of.the.villeins.Characters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import inf112.saga.of.the.villeins.Game.GameScreen;
import inf112.saga.of.the.villeins.Utils.TilePosition;
import org.junit.jupiter.api.Test;

public class TestPlayer {

    Player player = new Player(new TilePosition(1, 0), null, 10, 10, 10, 1);

    @Test
    void testAttack() {
        // Set the monster at the specified coordinate
        ICharacter monster = new BaseMonster(new TilePosition(0, 0), null, 2, 3, 4, 1, "");

        GameScreen.characterList.clear();
        GameScreen.characterList.add(monster);

        // Perform the attack
        Boolean result = player.attack(monster.getCurrentPosition());

        // Check if the attack was successful
        assertTrue(result);
    }

    @Test
    void testGetScore() {
        // Set the initial score
        int initialScore = 100;
        player.setScore(initialScore);

        // Check if the score is correct
        assertEquals(initialScore, player.getScore());
    }

    @Test
    void testSetScore() {
        // Set a new score
        int newScore = 200;
        player.setScore(newScore);

        // Check if the score is updated
        assertEquals(newScore, player.getScore());
    }
}
