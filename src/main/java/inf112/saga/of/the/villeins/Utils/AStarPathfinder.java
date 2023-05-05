package inf112.saga.of.the.villeins.Utils;

import java.util.*;

import inf112.saga.of.the.villeins.Game.TileInfoMap;


public class AStarPathfinder {

    /**
     * Class responsible for finding the shortest possible path along tiles to a given destination tile.
     * Takes in an "IMap" object, which is responsible for holding information about whether a tile is walkable or not.
     *
     * @param start Starting tile
     * @param end Destination Tile
     * @param infomap Class containing information about blocked tile.
     * @return An Arraylist of TilePosition objects, representing the shortest possible path to a destination tile.
     */
    public static ArrayList<TilePosition> findPath(TilePosition start, TilePosition end, TileInfoMap infomap) {

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
        return new ArrayList<>();
    }
    
    private static ArrayList<TilePosition> getPath(Node node) {
        ArrayList<TilePosition> path = new ArrayList<>();
        while (node != null) {
            path.add(0, node.position);
            node = node.parent;
        }
        return path;
    }

    /**
     * Henter naboene til en tile, må litt spesielt siden det er hexgrid istedet for vanlig grid
     * 
     * @param position tilen vi vil finne naboene til
     * @param infomap TileInforMapen som holder alle lovlige tiles
     * @return listen av tilesene som er naboer.
     */
    public static ArrayList<TilePosition> getNeighbors(TilePosition position, TileInfoMap infomap) {
        ArrayList<TilePosition> neighbors = new ArrayList<>();
        ArrayList<TilePosition> tempneighbors = new ArrayList<>();
        int x = position.x();
        int y = position.y();
        System.out.println(infomap);

        // implementation of getNeighbors method depends on your specific grid structure
        // for a hexagonal grid, this could involve checking the six neighboring tiles
        // around the current position and returning any that are valid for movement


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

        // Checks if a Tile can be walked on.
        for (TilePosition maybeTilePosition : tempneighbors) {

            if (infomap.tileIsMovable(maybeTilePosition) == null) {
                continue;
            }
            if (infomap.tileIsMovable(maybeTilePosition)) {
                neighbors.add(maybeTilePosition);
            }
    }
    return neighbors;
    }

    public static int heuristic(TilePosition current, TilePosition end) {
        return Math.abs(current.x() - end.x()) + Math.abs(current.y() - end.y());
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
            if (obj instanceof Node other) {
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
