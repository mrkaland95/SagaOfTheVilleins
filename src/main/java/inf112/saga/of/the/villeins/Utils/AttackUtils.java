package inf112.saga.of.the.villeins.Utils;

// Midlertidig plan.
// 1. Ta i mot et Vector Koordinat, finn hvilken tile det tilsvarer.
// 2. Sjekk om en karakter ligger på den tilen.
// 3. Hvis en karakter er på "tilen", sjekk om den some prøver å angripe har lov til å angripe(f.eks nok AP, nærmt nok osv.)
// 4.


import com.badlogic.gdx.math.Vector2;
import inf112.saga.of.the.villeins.Characters.CharacterState;
import inf112.saga.of.the.villeins.Characters.ICharacter;
import inf112.saga.of.the.villeins.MapUtils.HexGridMapPosition;
import inf112.saga.of.the.villeins.MapUtils.TilePosition;

import java.util.List;

public class AttackUtils {
    private final int attackRange;
    private final ICharacter character;

    public AttackUtils(ICharacter character, int attackRange) {
        this.character = character;
        this.attackRange = attackRange;
    }

    public boolean attackCharacter(List<ICharacter> characterList, Vector2 clickPosition) {

        ICharacter opponent = getCharacterToAttack(characterList, clickPosition);

        if (opponent == null) return false; // Må ha en karakter å angripe
        if (this.character.equals(opponent)) return false; // En karakter kan ikke angripe seg selv
        if (!(this.character.getCharacterState() == CharacterState.IDLE)) return false; // Kan kun angripe hvis karakteren er idle.
        if (!characterInRange(this.character, opponent, this.attackRange)) return false; // If the current character is not in range, don't attack.

        // TODO En bedre funksjon for å beregne damage burde lages, men denne får fungere mtp. testing
        int damage = this.character.getStrength() / 2;
//        int damage = this.character.getStrength() / 2;


        System.out.println("Dealing damage: " + damage);
        character.applyDamage(damage, opponent);
        character.setCurrentActionPoints(0);
        int opponentHealth = opponent.getCurrentHealth();
        System.out.println("Opponent's current health is: " + opponentHealth);
        return true;
    }

    private ICharacter getCharacterToAttack(List<ICharacter> characterList, Vector2 clickPosition) {
        TilePosition clickedTile = HexGridMapPosition.findHexTile(clickPosition);
        for (ICharacter character : characterList) {
            TilePosition characterTile = HexGridMapPosition.findHexTile(character.getCurrentPosition());
            if (clickedTile.equals(characterTile)) {
                return character;
            }
        }

        return null;
    }


    /** Sjekker om en karakter er innenfor angrepsrekkevidde.
     *
     * @param character
     * @param character2
     * @return
     */
    public static boolean characterInRange(ICharacter character, ICharacter character2, int attackRange) {
        TilePosition character1TilePosition = HexGridMapPosition.findHexTile(character.getCurrentPosition());
        TilePosition character2TilePosition = HexGridMapPosition.findHexTile(character2.getCurrentPosition());
        return attackRange >= hexDistance(character1TilePosition, character2TilePosition);
    }


    /**
     * Bruker noe som heter "Kube distanse" til å regne ut hvor mange tiles er imellom to tiles.
     * Dette er nyttig for å kunne f.eks sjekke om en karakter er nærmt nok til å kunne angripe en annen karakter.
     *
     * @param tile1
     * @param tile2
     * @return Antall tiles imellom tile1 og tile2.
     */

    public static double hexDistance(TilePosition tile1, TilePosition tile2) {
        int a0 = tile1.x();
        int a1 = tile2.x();

        int b0 = tile1.y();
        int b1 = tile2.y();

        double x0 = a0 - Math.floor(b0/2);
        double y0 = b0;
        double x1 = a1 - Math.floor(b1/2);
        double y1 = b1;

        double dx = x1 - x0;
        double dy = y1 - y0;

        double max1 = Math.max(Math.abs(dx), Math.abs(dy));
        return Math.max(max1, Math.abs(dx+dy));

    }
}
