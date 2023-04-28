package inf112.saga.of.the.villeins.Characters;

public interface ITurnBased {
    int getCurrentActionPoints();
    void setCurrentActionPoints(int actionPoints);
    void resetActionPoints();
}
