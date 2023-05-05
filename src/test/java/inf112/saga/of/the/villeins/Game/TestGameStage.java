package inf112.saga.of.the.villeins.Game;

import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import inf112.saga.of.the.villeins.Animations.CharacterAnimationHandler;
import inf112.saga.of.the.villeins.AssetManager.GameAssetManager;
import inf112.saga.of.the.villeins.Characters.ICharacter;
import inf112.saga.of.the.villeins.Characters.IPlayable;
import inf112.saga.of.the.villeins.Characters.Player;
import inf112.saga.of.the.villeins.Factories.CharacterFactory;
import inf112.saga.of.the.villeins.Utils.TilePosition;

public class TestGameStage {
    // CharacterAnimationHandler slimeAnimation =
    //     new CharacterAnimationHandler(spriteBatch, slimeIdleTexture, 4, slimeIdleTexture, 4, null,1, CharacterDirection.RIGHT);
	// CharacterAnimationHandler playerWarriorAnimation =
    //     new CharacterAnimationHandler(spriteBatch, warriorIdleTexture, 2, warriorWalkingTexture,11,  null, null, CharacterDirection.LEFT);
	// CharacterAnimationHandler dragonAnimation =
    //     new CharacterAnimationHandler(spriteBatch, dragonAttackTexture, 4,  dragonAttackTexture,4,  null, null, CharacterDirection.LEFT);
	// CharacterAnimationHandler ghostAnimation =
    //     new CharacterAnimationHandler(spriteBatch, ghostIdleTexture, 4, ghostIdleTexture, 4, null,1, CharacterDirection.LEFT);


    @Test
    public void GameStageTest(){
        TileInfoMap tileInfoMap = new TileInfoMap(20,20);
        IPlayable player = new Player(new TilePosition(0, 0), null, 0, 0, 0, 0);
        CharacterFactory factory = new CharacterFactory(null, null, null, null);

        GameStage gameStage1 = new GameStage(1, 
        factory, 
        player,
        tileInfoMap);

        List<ICharacter> list = gameStage1.generateCharacters();
        int testGenerateEnemyCount = list.size()-1;
        assertTrue(2 <= testGenerateEnemyCount && testGenerateEnemyCount <= 3);

        GameStage gameStage2 = new GameStage(2, 
        factory, 
        player,
        tileInfoMap);

        List<ICharacter> list2 = gameStage2.generateCharacters();
        int testGenerateEnemyCount2 = list2.size()-1;
        assertTrue(3 <= testGenerateEnemyCount2 && testGenerateEnemyCount2 <= 5);
    }
    
}
