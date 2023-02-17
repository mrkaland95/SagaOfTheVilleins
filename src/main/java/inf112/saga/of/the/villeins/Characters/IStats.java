package inf112.saga.of.the.villeins.Characters;

public interface IStats {
    int getHealth(ICharacter character);

    int getStrength(ICharacter character);

    void setStrength(ICharacter character);

    int getDefense(ICharacter character);

    int getMaxHealth(ICharacter character);

    void setHealth(int damageTaken, ICharacter character);

}
