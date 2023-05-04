package inf112.saga.of.the.villeins.Characters;

public interface ITurnBased {
    /**
     *  Henter gjenværende action points.
     * @return
     */
    int getCurrentActionPoints();

    /**
     * Setter gjenværende action points.
     * @param actionPoints
     */
    void setCurrentActionPoints(int actionPoints);

    /**
     * Reseter action points til en karakter opp til maximum.
     */
    void resetActionPoints();
}
