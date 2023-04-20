package inf112.saga.of.the.villeins.Characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import inf112.saga.of.the.villeins.Animations.CharacterAnimationHandler;
import inf112.saga.of.the.villeins.Characters.AI.SimpleAI;
import inf112.saga.of.the.villeins.Game.GameLoop;
import inf112.saga.of.the.villeins.Game.Main;
import inf112.saga.of.the.villeins.MapUtils.HexGridMapPosition;
import inf112.saga.of.the.villeins.MapUtils.TilePosition;
import inf112.saga.of.the.villeins.Utils.AttackUtils;
import inf112.saga.of.the.villeins.Utils.TileMovement;

import java.util.List;

public class BaseMonster implements ICharacter {
    private String identifier;
    private final int maxHealth;
    private int currentHealth;
    private int strength;
    private int defense;
    private float moveSpeed = Main.globalDefaultMoveSpeed;
    private Vector2 currentPosition;
    private Vector2 endPosition;
    private List<TilePosition> pathToMove;
    private CharacterState characterState;
    private final CharacterAnimationHandler animationController;
    private final TileMovement tileMovement;
    private final AttackUtils attackUtils;
    private int actionPoints = 10;
    private int attackRange;
    private SimpleAI ai;

    public BaseMonster(Vector2 startPosition,
                       CharacterAnimationHandler animationController,
                       int maxHealth,
                       int strength,
                       int defense,
                       int attackRange) {

        this.currentPosition = startPosition;
        this.animationController = animationController;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.strength = strength;
        this.defense = defense;
        this.attackRange = attackRange;
        this.tileMovement = new TileMovement(this);
        this.attackUtils = new AttackUtils(this, attackRange);
        this.characterState = CharacterState.IDLE;
        this.ai = new SimpleAI(null, this);

    }

    public BaseMonster(TilePosition startingTile,
                       CharacterAnimationHandler animationController,
                       int maxHealth,
                       int strength,
                       int defense,
                       int attackRange) {
        this.currentPosition = HexGridMapPosition.calculateVectorCoordinate(startingTile);
        this.animationController = animationController;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.strength = strength;
        this.defense = defense;
        this.attackRange = attackRange;

        this.attackUtils = new AttackUtils(this, attackRange);
        this.tileMovement = new TileMovement(this);
        this.characterState = CharacterState.IDLE;
        this.ai = new SimpleAI(null, this);
    }


    @Override
    public String getIdentifier() {
        return this.identifier;
    }

    @Override
    public void update() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        this.animationController.render(this, deltaTime);
        this.ai.AImakeDecision();
        this.calculatePathToMove();
        this.tileMovement.move(deltaTime);
    }



    void calculatePathToMove() {
        if (this.characterState == CharacterState.MOVING) return;
        if (this.endPosition == null) return;
        if (getActionPoints() == 0) return; 
        while(this.pathToMove.size() > getActionPoints()+1){
            this.pathToMove.remove(pathToMove.size()-1);
        }
        this.tileMovement.setPath(this.pathToMove);
        this.endPosition = null;
    }



    @Override
    public Vector2 getCurrentPosition() {
        return currentPosition;
    }

    @Override
    public void setCurrentPosition(Vector2 position) {
        this.currentPosition = position;
    }

    @Override
    public Boolean setEndPosition(Vector2 destinationPosition) {
        this.endPosition = destinationPosition;
        return true;
    }

    @Override
    public void setTargetCharacter(ICharacter target){
        this.ai.setTargetCharacter(target);
    }

    @Override
    public ICharacter getTargetCharacter() {
        return this.ai.getTargetCharacter();
    }

//    @Override
//    public Boolean setEndPosition(Vector2 endPosition) {
//        if (!this.moving) {
//            this.endPosition = endPosition;
//            return true;
//        } else {
//            return false;
//        }
//    }

    @Override
    public Vector2 getEndPosition() {
        return endPosition;
    }
    @Override
    public CharacterState getCharacterState() {
        return this.characterState;
    }

    @Override
    public void setCharacterState(CharacterState characterState) {
        this.characterState = characterState;
    }

    @Override
    public float getMoveSpeed() {
        return this.moveSpeed;
    }
    @Override
    public void setMoveSpeed(float moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    @Override
    public void setPathToMove(List<TilePosition> pathToMove) {
        this.pathToMove = pathToMove;
    }

    @Override
    public int getCurrentHealth() {
        return this.currentHealth;
    }
    @Override
    public void setStrength(int strength) {
        this.strength = strength;
    }
    @Override
    public int getStrength() {
        return this.strength;
    }

    @Override
    public int getDefense() {
        return this.defense;
    }

    @Override
    public int getMaxHealth() {
        return this.maxHealth;
    }

    @Override
    public void setHealth(int health) {
        if (health > this.maxHealth) {
            this.currentHealth = this.maxHealth;
        } else if(health < 0) {
            this.currentHealth = 0;
        } else {
            this.currentHealth = health;
        }
    }

    @Override
    public void applyDamage(int damage, ICharacter character) {
        int currentHealth = character.getCurrentHealth() - damage;
        character.setHealth(currentHealth);
        setActionPoints(0);
    }

    @Override
    public Boolean attack(Vector2 CoordinateToAttack) {
        return this.attackUtils.attackCharacter(GameLoop.characterList, CoordinateToAttack);
    }

    @Override
    public TilePosition getTilePosition() {
        return HexGridMapPosition.findHexTile(currentPosition);
    }

    @Override
    public void setTilePosition(TilePosition tilePosition) {
        this.currentPosition = HexGridMapPosition.calculateVectorCoordinate(tilePosition);
    }
    @Override
    public int getActionPoints() {
        return this.actionPoints;
    }

    @Override
    public void setActionPoints(int actionPoints) {
        this.actionPoints = actionPoints;
    }
    @Override
    public void setAttackRange(int attackRange) {
        this.attackRange = attackRange;
    }
    @Override
    public int getAttackRange() {
        return this.attackRange;
    }

 
}
