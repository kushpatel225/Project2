/**
 * The PRQuadtree class implements a variant of the PR Quadtree.
 * It organizes points by position for spatial queries and supports
 * operations like insert, remove, region search, and finding duplicates.
 * 
 * The decomposition rules for this PR Quadtree:
 * 1. Any node with 1, 2, or 3 points must always be a leaf node.
 * 2. Any node with more than 3 points that are all at the same (x, y) location
 * must be a leaf node.
 * 3. Any node with more than 3 points must be an internal node (unless #2
 * applies).
 * 
 * @author Rushil, Kush
 * @version 1.0
 */
public class PRQuadtree {
    // The root of the PRQuadtree
    private QuadNode root;
    // The flyweight object representing empty leaf nodes
    private final QuadNode emptyNode;
    // The size of the world
    private final int worldSize = 1024;
    // Counter for nodes visited during region search
    private int nodesVisited;

    /**
     * Constructor to create a new PRQuadtree
     */
    public PRQuadtree() {
        // Create the flyweight empty leaf node
        emptyNode = EmptyNode.getInstance();
        // Initialize the root as an empty leaf node
        root = emptyNode;
        nodesVisited = 0;

    }


    /**
     * Insert a point into the quadtree
     * 
     * @param x
     *            the x-coordinate
     * @param y
     *            the y-coordinate
     * @param name
     *            the name of the point
     * @return true if the point was inserted successfully
     */
    public boolean insert(int x, int y, String name) {
        // Check if point is within the world boundaries
        if (x < 0 || y < 0 || x >= worldSize || y >= worldSize) {
            return false;
        }

        // Insert the point into the tree
        root = root.insert(x, y, name, 0, 0, worldSize);
        return true;
    }


    /**
     * Remove a point from the quadtree by coordinates
     * 
     * @param x
     *            the x-coordinate
     * @param y
     *            the y-coordinate
     * @return the removed point, or null if no point was found
     */
    public Point remove(int x, int y) {
        // Check if point is within the world boundaries
        if (x < 0 || y < 0 || x >= worldSize || y >= worldSize) {
            return null;
        }

        // Remove the point from the tree
        RemoveResult result = root.remove(x, y, 0, 0, worldSize);
        root = result.getNode();

        return result.getRemovedPoint();
    }


    /**
     * Remove a point from the quadtree by name
     * 
     * @param name
     *            the name of the point
     * @return the removed point, or null if no point was found
     */
    public Point removeByName(String name) {
        // Remove the point from the tree
        RemoveResult result = root.removeByName(name, 0, 0, worldSize);
        root = result.getNode();

        return result.getRemovedPoint();
    }


    /**
     * Find all points within a given region
     * 
     * @param x
     *            the x-coordinate of the search region
     * @param y
     *            the y-coordinate of the search region
     * @param w
     *            the width of the search region
     * @param h
     *            the height of the search region
     * @return a list of points within the region
     */

    public ArrayList regionSearch(int x, int y, int w, int h) {
        // Reset nodes visited counter
        nodesVisited = 0;

        // Create a list to store the results
        ArrayList results = new ArrayList();

        // Check for invalid inputs
        if (w <= 0 || h <= 0) {
            System.out.println(
                "Invalid region: width and height must be positive");
            return results;
        }

        // Search the tree
        nodesVisited += root.regionSearch(x, y, w, h, 0, 0, worldSize, results);

        // Print the number of nodes visited
        // System.out.println(nodesVisited + " quadtree nodes visited");

        return results;
    }

// public java.util.ArrayList<Point> regionSearch(
// int x,
// int y,
// int w,
// int h) {
// // Reset nodes visited counter
// nodesVisited = 0;
//
// // Create a list to store the results
// java.util.ArrayList<Point> results = new java.util.ArrayList<>();
//
// // Search the tree
// root.regionSearch(x, y, w, h, 0, 0, WORLD_SIZE, results);
//
// return results;
// }


    /**
     * Get the number of nodes visited during the last region search
     * 
     * @return the number of nodes visited
     */
    public int getNodesVisited() {
        return nodesVisited;
    }


    /**
     * Find all duplicate points in the quadtree
     * 
     * @return a map of positions to lists of points at that position
     */
    public java.util.HashMap<String, ArrayList> findDuplicates() {
        java.util.HashMap<String, ArrayList> dups = new java.util.HashMap<>();

        // Find duplicates in the tree
        root.findDuplicates(dups, 0, 0, worldSize);

        return dups;
    }


    /**
     * Dump the contents of the quadtree for display
     *
     */
    public void dump() {
        StringBuilder sb = new StringBuilder();
        // Start with the header
        sb.append("QuadTree dump:\n");

        // Counter for nodes printed
        int[] nodesPrinted = new int[1]; // Using array to pass by reference
        nodesPrinted[0] = 0;

        // Dump the tree
        root.dump(0, sb, 0, 0, worldSize, nodesPrinted);

        // Add the count of nodes at the end
        sb.append(nodesPrinted[0]).append(" quadtree nodes printed");

        System.out.println(sb.toString());
    }
// public void dump() {
// StringBuilder sb = new StringBuilder();
//
// // Dump the tree
// root.dump(0, sb, 0, 0, WORLD_SIZE);
// System.out.println(sb.toString());
//// return sb.toString();
// }
}
