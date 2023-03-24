package inf112.saga.of.the.villeins.MapUtils;

import java.util.*;

import inf112.saga.of.the.villeins.Characters.ICharacter;
import inf112.saga.of.the.villeins.Game.Imap;

public class AStarPathfinder {

    // List of tiles that are blocked by a character, tiles where movement is disallowed etc.
    List<TilePosition> blockedTiles = new ArrayList<>();
    public AStarPathfinder(TilePosition a, TilePosition b, TilePosition c) {
        // Used for testing purposes.
        this.blockedTiles.add(a);
        this.blockedTiles.add(b);
        this.blockedTiles.add(c);
    }

//    public void aStarAlgorithm(TilePosition startPosition, TilePosition destPosition) {
//        Queue<TilePosition> frontier = new PriorityQueue<>();
//        frontier.add(startPosition);


    public static ArrayList<TilePosition> findPath(TilePosition start, TilePosition end, Imap infomap) {

        PriorityQueue<Node> open = new PriorityQueue<>(Comparator.comparingInt(Node::getF));
        HashSet<Node> closed = new HashSet<>();
        open.add(new Node(start, null, 0, heuristic(start, end)));

        while (!open.isEmpty()) {
            Node current = open.poll();
            if (current.position.equals(end)) {
                return getPath(current);
            }
            closed.add(current);
            for (TilePosition neighbor : getNeighbors(current.position, infomap)) {
                Node neighborNode = new Node(neighbor, current, current.g + 1, heuristic(neighbor, end));

                if (closed.contains(neighborNode)) {
                    continue;
                }
                if (!open.contains(neighborNode)) {
                    open.add(neighborNode);
                    
                } else {
                    for (Node openNode : open) {
                        if (openNode.equals(neighborNode) && openNode.g > neighborNode.g) {
                            open.remove(openNode);
                            open.add(neighborNode);
                            break;
                        }

                    }
                }
            }
        }
        return null;
    }
    
    private static ArrayList<TilePosition> getPath(Node node) {
        ArrayList<TilePosition> path = new ArrayList<>();
        while (node != null) {
            path.add(0, node.position);
            node = node.parent;
        }
        return path;
    }

    private static ArrayList<TilePosition> getNeighbors(TilePosition position, Imap Imap) {
        // implementation of getNeighbors method depends on your specific grid structure
        // for a hexagonal grid, this could involve checking the six neighboring tiles
        // around the current position and returning any that are valid for movement

        ArrayList<TilePosition> neighbors = new ArrayList<>();
        ArrayList<TilePosition> tempneighbors = new ArrayList<>();
        int x = position.x();
        int y = position.y();
        if(y % 2 != 0) {
            tempneighbors.add(new TilePosition(x+1, y-1));
            tempneighbors.add(new TilePosition(x+1, y));
            tempneighbors.add(new TilePosition(x+1, y+1));
            tempneighbors.add(new TilePosition(x, y+1));
            tempneighbors.add(new TilePosition(x-1, y));
            tempneighbors.add(new TilePosition(x, y-1));
        }
        else {
            tempneighbors.add(new TilePosition(x-1, y+1));
            tempneighbors.add(new TilePosition(x, y+1));
            tempneighbors.add(new TilePosition(x+1, y));
            tempneighbors.add(new TilePosition(x, y-1));
            tempneighbors.add(new TilePosition(x-1, y-1));
            tempneighbors.add(new TilePosition(x-1, y));
        }

        // Checks if a Tile is blocked by it's type
        for (TilePosition maybeTilePosition : tempneighbors) {
            if(Imap.map.get(maybeTilePosition)!= null){
                if(Imap.movable(maybeTilePosition) == true){
                    neighbors.add(maybeTilePosition);
                }
            }
        }
    return neighbors;
    }

    private static int heuristic(TilePosition current, TilePosition end) {



        return Math.abs(current.x() - end.x()) + Math.abs(current.y() - end.y());
        // Uses something called "Chebyshev" distance

//        return Math.max((current.x() - end.x()), (current.y()) - end.y());
    }

    private static class Node {
        TilePosition position;
        Node parent;
        int g; // cost of getting to this node from the starting node
        int h; // estimated cost of getting from this node to the end node

        Node(TilePosition position, Node parent, int g, int h) {
            this.position = position;
            this.parent = parent;
            this.g = g;
            this.h = h;
        }

        int getF() {
            return g + h;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Node) {
                Node other = (Node) obj;
                return position.equals(other.position);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return position.hashCode();
        }
    }





}
