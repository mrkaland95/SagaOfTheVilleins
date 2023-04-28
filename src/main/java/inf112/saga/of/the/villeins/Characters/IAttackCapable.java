package inf112.saga.of.the.villeins.Characters;

import com.badlogic.gdx.math.Vector2;

public interface IAttackCapable {
    void applyDamage( int damage, ICharacter character);

    /**
     * Prøver å angripe en karakter på de gitte koordinatene, returnerer en bool om angrepet skjedde.
     * @param coordinateToAttack Koordinatene til "Tilen" der en karakter står.
     * @return En bool som beskriver om angrepet skjedde eller ikke.
     */
    Boolean attack(Vector2 coordinateToAttack);
    int getAttackRange();
    void setAttackRange(int attackRange);
    void setTargetCharacter(ICharacter target);
    ICharacter getTargetCharacter();
}
