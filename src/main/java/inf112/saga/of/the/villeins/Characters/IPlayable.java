package inf112.saga.of.the.villeins.Characters;

/**
 * Interface meant to be implemented by a class that can be played by a human player.
 */
public interface IPlayable extends ICharacter {

    /** Henter skoren til en spillbar karakter.
     * @return
     */
    int getScore();

    /** Setter scoren til en spillbar karakter.
     * @param score
     */
    void setScore(int score);
}
