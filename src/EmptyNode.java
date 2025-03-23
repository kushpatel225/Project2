/**
 * The empty node class of the quadtree
 * 
 * @author Rushil, Kush
 * @version 1.0
 */
public class EmptyNode implements QuadNode {

    private static EmptyNode flyweight = null;

    /**
     * Constructor for emptynode
     */
    private EmptyNode() {

    }


    /**
     * Gets an instance of emptyNode
     * 
     * @return the instance of emptyNode
     */
    public static EmptyNode getInstance() {
        if (flyweight == null) {
            flyweight = new EmptyNode();
        }
        return flyweight;
    }


    /**
     * Inserts a point
     */
    @Override
    public QuadNode insert(
        int x,
        int y,
        String name,
        int xPos,
        int yPos,
        int size) {
        // Create a new leaf node with the point
        LeafNode newLeaf = new LeafNode();
        newLeaf.addPoint(new Point(name, x, y));
        return newLeaf;
    }


    /**
     * Removes a point
     */
    @Override
    public RemoveResult remove(int x, int y, int xPos, int yPos, int size) {
        // Empty node, nothing to remove
        return new RemoveResult(EmptyNode.getInstance(), null);
    }


    /**
     * Removes a point by name
     */
    @Override
    public RemoveResult removeByName(
        String name,
        int xPos,
        int yPos,
        int size) {
        // Empty node, nothing to remove
        return new RemoveResult(EmptyNode.getInstance(), null);
    }


    /**
     * Checks if node intersects a region
     */
    @Override
    public int regionSearch(
        int x,
        int y,
        int w,
        int h,
        int xPos,
        int yPos,
        int size,
        ArrayList results) {
        int nodes = 0;
        // Only count if this node intersects with the search region
        if (intersects(x, y, w, h, xPos, yPos, size)) {
            nodes++;
            // PRQuadtree.nodesVisited++; // May not work b/c its static
        }
        // Empty node, nothing to search
        return nodes;
    }


    /**
     * Checks for points that intersect values
     * 
     * @param x
     *            x coordinate of search area
     * @param y
     *            y coordinate of search area
     * @param w
     *            width of search area
     * @param h
     *            height of search area
     * @param qx
     *            nodes x value
     * @param qy
     *            nodes y value
     * @param qsize
     *            nodes size
     * @return if they intersect
     */
    private boolean intersects(
        int x,
        int y,
        int w,
        int h,
        int qx,
        int qy,
        int qsize) {
        // Check if the quadrant is completely outside the search region
        return !(qx >= x + w || qx + qsize <= x || qy >= y + h || qy
            + qsize <= y);
    }
//
// @Override
// public void regionSearch(
// int x,
// int y,
// int w,
// int h,
// int xPos,
// int yPos,
// int size,
// java.util.ArrayList<Point> results) {
//// Increment nodes visited count
// nodesVisited++;
//// Empty node, nothing to search
// }


    /**
     * Finds the duplicates in the node
     */
    @Override
    public void findDuplicates(
        java.util.HashMap<String, ArrayList> dups,
        int xPos,
        int yPos,
        int size) {
        // Empty node, no duplicates
    }


    /**
     * Dump of empty node
     */
    @Override
    public void dump(
        int level,
        StringBuilder sb,
        int xPos,
        int yPos,
        int size,
        int[] nodesPrinted) {
        // Increment nodes count
        nodesPrinted[0]++;

        // Append indentation
        for (int i = 0; i < level * 2; i++) {
            sb.append(" ");
        }
        sb.append("Node at ").append(xPos).append(", ").append(yPos);
        sb.append(", ").append(size).append(": Empty\n");
    }

// @Override
// public void dump(
// int level,
// StringBuilder sb,
// int xPos,
// int yPos,
// int size) {
//// Append indentation
// for (int i = 0; i < level * 2; i++) {
// sb.append(" ");
// }
// sb.append("Node at ").append(xPos).append(", ").append(yPos);
// sb.append(", ").append(size).append(": Empty\n");
// }


    /**
     * Checks if node is empty
     */
    @Override
    public boolean isEmpty() {
        return true;
    }
}
