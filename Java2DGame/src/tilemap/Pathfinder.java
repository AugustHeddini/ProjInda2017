package tilemap;

import javafx.collections.transformation.SortedList;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.abs;

/**
 * @Author August Heddini
 * @Date 2017-05-17.
 */
public class Pathfinder {

    private TileMap tileMap;
    private ArrayList<Node> closed;
    private ArrayList<Node> open;
    private int tileSize;
    private int maxSearchDistance;
    private Node[][] nodes;


    public Pathfinder(TileMap tileMap) {
        this.tileMap = tileMap;

        init();
    }

    /**
     * Set up
     */
    private void init() {
        closed = new ArrayList<>();
        open = new ArrayList<>();

        nodes = new Node[tileMap.getNumXTiles()][tileMap.getNumYTiles()];

        maxSearchDistance = 5;

        tileSize = tileMap.getTileSize();
    }

    /**
     * An A* pathfinding algorithm.
     *
     * @param cx Current x. The x position of the beginning tile.
     * @param cy Current y. The y position of the beginning tile.
     * @param tx Target x.
     * @param ty Target y.
     * @return A value from 0-3 representing UP, DOWN, LEFT, RIGHT as the next move, or 4 if there is no path.
     */
    public int findPath(int cx, int cy, int tx, int ty) {

        // Lägger till alla tile-platser i nodes.
        for (int x = 0; x < tileMap.getNumXTiles(); x++) {
            for (int y = 0; y < tileMap.getNumYTiles(); y++) {
                nodes[x][y] = new Node(x ,y);
            }
        }

        // Set up och startvärden för A*
        nodes[cx][cy].cost = 0;
        nodes[cx][cy].depth = 0;

        closed.clear();
        open.clear();
        open.add(nodes[cx][cy]);

        nodes[tx][ty].parent = null;

        // A* loop
        int maxDepth = 0;
        while ( maxDepth < maxSearchDistance && open.size() != 0 ) {
            Node current = open.get(0);

            if(current == nodes[tx][ty]) {
                break;
            }

            open.remove(current);
            closed.add(current);

            //Kollar igenom alla närliggande nodes
            for (int x = -1; x < 2; x++) {
                for (int y = -1; y < 2; y++) {

                    // Struntar i sig själv
                    if ( x == 0 && y == 0 ) {
                        continue;
                    }
                    // Struntar i diagonaler
                    if ( x != 0 && y != 0 ) {
                        continue;
                    }

                    int xp = x + current.x;
                    int yp = y + current.y;

                    if (isValidLocation(cx, cy, xp, yp)) {
                        int nextStepCost = current.cost + 1;

                        Node neighbour = nodes[xp][yp];

                        // Detta är den faktiskt viktiga biten:
                        if(!open.contains(neighbour) && !closed.contains(neighbour)) {
                            neighbour.cost = nextStepCost;
                            neighbour.heurisitic = abs(tx - xp) + abs(ty - yp);
                            maxDepth = Math.max(maxDepth, neighbour.setParent(current));
                            open.add(neighbour);
                        }
                    }
                }
            }
        }
        if (nodes[tx][ty].parent == null) {
            return 4;
        }

        Node target = nodes[tx][ty];
        Node prev = nodes[cx][cy];
        while (target != nodes[cx][cy]) {
            prev = target;
            target = target.parent;
        }

        if (prev.x == cx + 1) {
            return 3;
        } else if (prev.x == cx - 1) {
            return 2;
        } else if (prev.y == cy + 1) {
            return 1;
        } else if (prev. y == cy - 1) {
            return 0;
        } else {
            // Ngt har blivit fel, bara rör sig random, ya?
            System.out.println("PATHFINDING ERROR: INVALID FIRST STEP");
            Random rnd = new Random();
            return rnd.nextInt(4);
        }

    }

    protected boolean isValidLocation(int cx, int cy, int x, int y) {
        // Kollar för out of bounds
        boolean invalid = (x < 0) || (y < 0) || (x > tileMap.getNumXTiles()) || (y > tileMap.getNumYTiles());

        // Kollar om en tile är blocked
        if ((!invalid) && ((cx != x) || (cy != y))) {
            invalid = tileMap.isBlockedTile(x*tileSize, y*tileSize);
        }

        return !invalid;
    }

    /**
     * En subklass som håller koll på parent för alla Tiles.
     */
    private class Node implements Comparable {
        private int x;
        private int y;
        private int cost;
        private Node parent;
        private int heurisitic;
        private int depth;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
            cost = 1;
        }

        public int setParent(Node parent) {
            depth = parent.depth + 1;
            this.parent = parent;

            return depth;
        }

        public int compareTo(Object other) {
            Node o = (Node) other;

            int f = heurisitic + cost;
            int of = o.heurisitic + o.cost;

            if (f < of) {
                return -1;
            } else if (f > of) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}
