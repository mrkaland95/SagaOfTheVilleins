package inf112.saga.of.the.villeins.Characters;

/**
 * Interface meant to be implemented by a class that can be played by a human player.
 */
public interface IPlayable extends ICharacter {
    int getScore();
    void setScore(int score);
}
