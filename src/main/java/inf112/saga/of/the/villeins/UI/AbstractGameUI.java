package inf112.saga.of.the.villeins.UI;

import inf112.saga.of.the.villeins.Characters.ICharacter;
import inf112.saga.of.the.villeins.Characters.IPlayable;

import java.util.List;

public interface AbstractGameUI {
    /** Tegner UIen til skjermen.
     * @param deltaTime
     * @param playerCharacter
     * @param characterList
     */
    void drawUI(float deltaTime, IPlayable playerCharacter, List<ICharacter> characterList);
}
