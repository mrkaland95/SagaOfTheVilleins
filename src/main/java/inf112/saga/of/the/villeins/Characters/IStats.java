package inf112.saga.of.the.villeins.Characters;

public interface IStats {
    /** Henter strengthen til en karakter
     * @return
     */
    int getStrength();

    /** Henter defensen(forsvaret) til en karakter
     * @return
     */
    int getDefense();

    /** Henter max healthen(livet) til en karakter.
     * @return
     */
    int getMaxHealth();

    /** Henter gjenværende liv til en karakter.
     * @return
     */
    int getCurrentHealth();

    /** Setter strength(styrke) til en karakter.
     * @param strength
     */
    void setStrength(int strength);

    /** Setter gjenværende liv til en karakter.
     * @param health
     */
    void setHealth(int health);

    /** Setter max liv til en karakter.
     * @param health
     */
    void setMaxHealth(int health);
}
