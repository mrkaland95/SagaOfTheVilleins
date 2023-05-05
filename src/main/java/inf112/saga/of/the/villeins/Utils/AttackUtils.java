package inf112.saga.of.the.villeins.Utils;

// Midlertidig plan.
// 1. Ta i mot et Vector Koordinat, finn hvilken tile det tilsvarer.
// 2. Sjekk om en karakter ligger på den tilen.
// 3. Hvis en karakter er på "tilen", sjekk om den some prøver å angripe har lov til å angripe(f.eks nok AP, nærmt nok osv.)
// 4.


import com.badlogic.gdx.math.Vector2;
import inf112.saga.of.the.villeins.Characters.CharacterState;
import inf112.saga.of.the.villeins.Characters.ICharacter;
import inf112.saga.of.the.villeins.Game.GameScreen;


public class AttackUtils {
    private final int attackRange;
    private final ICharacter character;

    public AttackUtils(ICharacter character, int attackRange) {
        this.character = character;
        this.attackRange = attackRange;
    }
    /**
     * Angriper en karakter på en posisjon.
     * 
     * @param clickPosition posisjonen til karakteren som skal angripes
     * @return true hvis angrepet gikk gjennom, false hvis det ikke gikk gjennom
     */
    public boolean attackCharacter(Vector2 clickPosition) {

        ICharacter opponent = TilePosition.getCharacterOnCoordinate(clickPosition, GameScreen.characterList);
        
        if (opponent == null) return false; // Må ha en karakter å angripe
        if (this.character.equals(opponent)) return false; // En karakter kan ikke angripe seg selv
        if (!(this.character.getCharacterState() == CharacterState.IDLE)) return false; // Kan kun angripe hvis karakteren er idle.
        if (!characterInRange(this.character, opponent, this.attackRange)) return false; // If the current character is not in range, don't attack.
        if (this.character.getCurrentActionPoints() == 0) return false;

        int damage = this.character.getStrength() - this.character.getDefense() / 2;

        character.applyDamage(damage, opponent);
        character.setCurrentActionPoints(0);

        return true;
    }

    /** Sjekker om en karakter er innenfor angrepsrekkevidde.
     * @param character
     * @param character2
     * @return
     */
    public static boolean characterInRange(ICharacter character, ICharacter character2, int attackRange) {
        TilePosition character1TilePosition = TilePosition.findHexTile(character.getCurrentPosition());
        TilePosition character2TilePosition = TilePosition.findHexTile(character2.getCurrentPosition());
        return attackRange >= TilePosition.hexDistance(character1TilePosition, character2TilePosition);
    }

    /**
     * Blir ikke brukt akkurat nå
     * 
     * @param character1
     * @param character2
     * @return 
     */
    public static int calculateDamage(ICharacter character1, ICharacter character2) {
        return 0;
    }
}
