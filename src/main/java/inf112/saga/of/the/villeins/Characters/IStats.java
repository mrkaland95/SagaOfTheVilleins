package inf112.saga.of.the.villeins.Characters;

public interface IStats {
    int getHealth();
    int getStrength();
    int getDefense();
    int getMaxHealth();
    void setStrength(int strength);

    // Perhaps change this to "damageCharacter" or something along those lines?
    void setHealth(int damageTaken, ICharacter character);

}
