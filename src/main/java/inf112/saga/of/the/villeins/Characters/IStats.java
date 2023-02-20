package inf112.saga.of.the.villeins.Characters;

public interface IStats {
    int getHealth();
    int getStrength();
    int getDefense();
    int getMaxHealth();
    void setStrength(int strength);

    // Maybe change this to "damageCharacter" or something along this lines?
    void setHealth(int damageTaken, ICharacter character);

}
