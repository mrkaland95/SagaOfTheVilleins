package inf112.saga.of.the.villeins.Characters;

public interface ICharacter extends IMoveable {

    void update();

    int getHealth(ICharacter character);

    int getStrength(ICharacter character);

    int getDefense(ICharacter character);

    int getMaxHealth(ICharacter character);
    
    void setHealth(int damageTaken, ICharacter character);

}
