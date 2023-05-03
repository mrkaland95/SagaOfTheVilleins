package inf112.saga.of.the.villeins.Characters;

import com.badlogic.gdx.math.Vector2;
import inf112.saga.of.the.villeins.Animations.CharacterAnimationHandler;
import inf112.saga.of.the.villeins.Game.GameScreen;
import inf112.saga.of.the.villeins.Game.Main;
import inf112.saga.of.the.villeins.Utils.TilePosition;
import inf112.saga.of.the.villeins.Utils.AttackUtils;
import inf112.saga.of.the.villeins.Utils.TileMovement;

import java.util.List;

public abstract class BaseCharacter implements ICharacter {
    private String identifier;
    private int maxHealth;
    private int currentHealth;
    private int strength;
    private int defense;
    private float moveSpeed = Main.globalDefaultMoveSpeed;
    private Vector2 currentPosition;
    private Vector2 endPosition;
    private CharacterState characterState;
    private CharacterDirection characterDirection;
    List<TilePosition> pathToMove;
    CharacterAnimationHandler animationController;
    TileMovement tileMovement;
    private AttackUtils attackUtils;
    private int currentActionPoints;
    private int maxActionPoints;
    private int attackRange;

    public BaseCharacter(TilePosition startingTile,
                         CharacterAnimationHandler animationController,
                         int maxHealth,
                         int strength,
                         int defense,
                         int attackRange) {


        this.currentPosition = TilePosition.findVectorCoordinate(startingTile);
        this.animationController = animationController;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.strength = strength;
        this.defense = defense;
        this.attackRange = attackRange;
        this.attackUtils = new AttackUtils(this, attackRange);
        this.tileMovement = new TileMovement(this);
        this.characterState = CharacterState.IDLE;
        this.characterDirection = CharacterDirection.LEFT;

        int tempActionPoints = 10;

        this.currentActionPoints = tempActionPoints;
        this.maxActionPoints = tempActionPoints;
    }


    /**
     * Regner ut "tile" veien som en karakter skal gå.
     */
    abstract void calculatePathToMove();


    public float getMoveSpeed() {
        return this.moveSpeed;
    }
    public void setMoveSpeed(float moveSpeed) {
        this.moveSpeed = moveSpeed;
    }
    public String getIdentifier() {
        return this.identifier;
    }

    /** Setter posisjonen til karakteren i verdenskoordinater.
     * @param position
     */
    public void setCurrentPosition(Vector2 position) {
        this.currentPosition = position;
    }

    /** Henter ende målet til karakterer, i verdenskoordinater.
     * @return
     */
    public Vector2 getEndPosition() {
        return endPosition;
    }

    /** Setter nåværende posisjon til karakteren - I verdenskoordinater.
     * @return
     */
    public Vector2 getCurrentPosition() {
        return currentPosition;
    }

    /** Henter tilen som karakteren står på.
     * @return
     */
    public TilePosition getTilePosition() {
        return TilePosition.findHexTile(currentPosition);
    }

    /** Setter tilen som karakteren skal flyttes til.
     * @param tilePosition
     */
    public void setTilePosition(TilePosition tilePosition) {
        this.currentPosition = TilePosition.findVectorCoordinate(tilePosition);
    }

    /** Setter "tile" stien som karakteren skal bevege seg langs.
     * Definert av en liste med tiles.
     * @param pathToMove
     */
    public void setPathToMove(List<TilePosition> pathToMove) {
        this.pathToMove = pathToMove;
    }
    public int getCurrentHealth() {
        return this.currentHealth;
    }
    public int getStrength() {
        return this.strength;
    }
    public void setStrength(int strength) {
        this.strength = strength;
    }
    public int getDefense() {
        return this.defense;
    }
    public int getMaxHealth() {
        return this.maxHealth;
    }
    public void setHealth(int health) {
        if (health > this.maxHealth) {
            this.currentHealth = this.maxHealth;
        } else if(health < 0) {
            this.currentHealth = 0;
        } else {
            this.currentHealth = health;
        }
    }
    public void setMaxHealth(int maxhealth){
        this.maxHealth = maxhealth;
    }
    public void applyDamage(int damage, ICharacter character) {
        int newHealth;
        if (damage >= character.getCurrentHealth()) {
            newHealth = 0;
        } else if (damage < 0) {
            newHealth = getCurrentHealth();
        } else {
            newHealth = character.getCurrentHealth() - damage;
        }
        character.setHealth(newHealth);
    }
    public Boolean attack(Vector2 CoordinateToAttack) {
        return this.attackUtils.attackCharacter(CoordinateToAttack);
    }
    public void setAttackRange(int attackRange) {
        this.attackRange = attackRange;
    }
    public int getAttackRange() {
        return this.attackRange;
    }
    public void setCurrentActionPoints(int actionPoints) {
        this.currentActionPoints = actionPoints;
    }
    public int getCurrentActionPoints() {
        return this.currentActionPoints;
    }
    public void resetActionPoints() {
        this.currentActionPoints = this.maxActionPoints;
    }
    public CharacterState getCharacterState() {
        return this.characterState;
    }
    public void setCharacterState(CharacterState characterState) {
        this.characterState = characterState;
    }
    @Override
    public Boolean setEndPosition(Vector2 endPosition) {
        this.endPosition = endPosition;
        return true;
    }

    public CharacterDirection getCharacterDirection() {
        return characterDirection;
    }

    public void setCharacterDirection(CharacterDirection characterDirection) {
        this.characterDirection = characterDirection;
    }
}
