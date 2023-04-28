package inf112.saga.of.the.villeins.Characters;

public interface IStats {
    int getStrength();
    int getDefense();
    int getMaxHealth();
    int getCurrentHealth();
    void setStrength(int strength);
    void setHealth(int health);
}
