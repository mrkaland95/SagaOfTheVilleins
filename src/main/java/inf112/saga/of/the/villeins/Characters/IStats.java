package inf112.saga.of.the.villeins.Characters;

public interface IStats {
    int getHealth();

    int getStrength();

    void setStrength(int strength);

    int getDefense();

    int getMaxHealth();

    void setHealth(int damageTaken, ICharacter character);

}
