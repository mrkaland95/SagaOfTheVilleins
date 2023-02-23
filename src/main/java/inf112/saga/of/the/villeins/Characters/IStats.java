package inf112.saga.of.the.villeins.Characters;

public interface IStats {
    int getHealth();
    int getStrength();
    int getDefense();
    int getMaxHealth();
    void setStrength(int strength);

    void setHealth(int health);

    void applyDamage( int damage, ICharacter character);

}
