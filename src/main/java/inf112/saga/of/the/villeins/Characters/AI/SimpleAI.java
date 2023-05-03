package inf112.saga.of.the.villeins.Characters.AI;

import java.util.ArrayList;
import java.util.List;

import inf112.saga.of.the.villeins.Characters.ICharacter;
import inf112.saga.of.the.villeins.Game.GameScreen;
import inf112.saga.of.the.villeins.Utils.AStarPathfinder;
import inf112.saga.of.the.villeins.Utils.TilePosition;

// Midlertidig plan
// 1. Sjekke om "target" til monsteret er innenfor angrepsrekkevidde. Hvis den er det, angrip "target"
// Hvis ikke: 
// 2. Sjekke alle tiles, finn de tilesene som er innenfor rekkevidde til spilleren, legg de til en liste.
// 3. Finn den nærmeste "tilen" i forhold til start posisjon til monsteret.
// 4. Regn ut korteste vei med "Pathfinder" funksjonen.


public class SimpleAI implements IBaseAI {
    ICharacter targetCharacter;
    ICharacter currentCharacter;

    public SimpleAI(ICharacter targetCharacter, ICharacter currentCharacter) {
        this.targetCharacter = targetCharacter;
        this.currentCharacter = currentCharacter;
    }

    public boolean AIPerformAction() {
        // Karakteren ikke har noen action points igjen eller ikke har et mål? Gå videre.
        if(currentCharacter.getCurrentActionPoints() == 0 || targetCharacter == null){
            return false;
        }
        else if (currentCharacter.attack(targetCharacter.getCurrentPosition())) {
            return true;
        } else {
            List<TilePosition> tempTiles = new ArrayList<>();
            for (TilePosition tilePosition : AStarPathfinder.getNeighbors(targetCharacter.getTilePosition(), GameScreen.infoMap)) {
                if(GameScreen.infoMap.tileIsMovable(tilePosition)){
                    tempTiles.add(tilePosition);
                }
            }

            // Finner den tilen som er nærmest karakteren, og går der.
            TilePosition smallestHeuristic = tempTiles.get(0);   
            for (TilePosition tilePosition : tempTiles) {
                if (AStarPathfinder.heuristic(tilePosition, currentCharacter.getTilePosition()) < 
                    AStarPathfinder.heuristic(smallestHeuristic, currentCharacter.getTilePosition())) {
                        smallestHeuristic = tilePosition;
                }
            }

            // Finner "pathen" til den nærmeste gyldige "tilen, som er innenfor angrepsrekkevidde til målet.
            List<TilePosition> pathToAttack = AStarPathfinder.findPath(currentCharacter.getTilePosition(), smallestHeuristic, GameScreen.infoMap);
            currentCharacter.setPathToMove(pathToAttack);

            return false;
        }
    }

    public void setTargetCharacter(ICharacter target) {
        this.targetCharacter = target;
    }

    public ICharacter getTargetCharacter() {
        return this.targetCharacter;
    }

}
