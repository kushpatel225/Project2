
public class EmptyNode implements QuadNode {

    private static EmptyNode flyweight = null;

    private EmptyNode() {

    }


    public static EmptyNode getInstance() {
        if (flyweight == null) {
            flyweight = new EmptyNode();
        }
        return flyweight;
    }


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


    @Override
    public RemoveResult remove(int x, int y, int xPos, int yPos, int size) {
        // Empty node, nothing to remove
        return new RemoveResult(EmptyNode.getInstance(), null);
    }


    @Override
    public RemoveResult removeByName(
        String name,
        int xPos,
        int yPos,
        int size) {
        // Empty node, nothing to remove
        return new RemoveResult(EmptyNode.getInstance(), null);
    }


    @Override
    public void regionSearch(
        int x,
        int y,
        int w,
        int h,
        int xPos,
        int yPos,
        int size,
        ArrayList results) {
        // Only count if this node intersects with the search region
        if (intersects(x, y, w, h, xPos, yPos, size)) {
            PRQuadtree.nodesVisited++; // May not work b/c its static
        }
        // Empty node, nothing to search
    }


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


    @Override
    public void findDuplicates(
        java.util.HashMap<String, ArrayList> dups,
        int xPos,
        int yPos,
        int size) {
        // Empty node, no duplicates
    }


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


    @Override
    public boolean isEmpty() {
        return true;
    }
}
