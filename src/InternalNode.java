/**
 * Internal node of quadtree
 * 
 * @author Rushil, Kush
 * @version 1.0
 */
public class InternalNode implements QuadNode {
    // The four children of this node (NW, NE, SW, SE)
    private QuadNode[] children;

    /**
     * Constructor to create a new internal node
     */
    public InternalNode() {
        children = new QuadNode[4];
        // Initialize all children as empty nodes
        for (int i = 0; i < 4; i++) {
            children[i] = EmptyNode.getInstance();
        }
    }


    /**
     * Get the quadrant index for a point
     * 
     * @param x
     *            the x-coordinate
     * @param y
     *            the y-coordinate
     * @param xPos
     *            the x-position of the current quadrant
     * @param yPos
     *            the y-position of the current quadrant
     * @param size
     *            the size of the current quadrant
     * @return the quadrant index (0=NW, 1=NE, 2=SW, 3=SE)
     */
    private int getQuadrant(int x, int y, int xPos, int yPos, int size) {
        int halfSize = size / 2;
        int midX = xPos + halfSize;
        int midY = yPos + halfSize;

        if (x < midX) {
            if (y < midY) {
                return 0; // NW
            }
            else {
                return 2; // SW
            }
        }
        else {
            if (y < midY) {
                return 1; // NE
            }
            else {
                return 3; // SE
            }
        }
    }


    /**
     * Get the position of a child quadrant
     * 
     * @param quadrant
     *            the quadrant index
     * @param xPos
     *            the x-position of the current quadrant
     * @param yPos
     *            the y-position of the current quadrant
     * @param size
     *            the size of the current quadrant
     * @return an array with the [x, y, size] of the child quadrant
     */
    private int[] getQuadrantPosition(
        int quadrant,
        int xPos,
        int yPos,
        int size) {
        int halfSize = size / 2;
        int[] result = new int[3];

        switch (quadrant) {
            case 0: // NW
                result[0] = xPos;
                result[1] = yPos;
                break;
            case 1: // NE
                result[0] = xPos + halfSize;
                result[1] = yPos;
                break;
            case 2: // SW
                result[0] = xPos;
                result[1] = yPos + halfSize;
                break;
            case 3: // SE
                result[0] = xPos + halfSize;
                result[1] = yPos + halfSize;
                break;
        }

        result[2] = halfSize;
        return result;
    }


    /**
     * Insert for internal node
     */
    @Override
    public QuadNode insert(
        int x,
        int y,
        String name,
        int xPos,
        int yPos,
        int size) {
        // Find the appropriate quadrant
        int quadrant = getQuadrant(x, y, xPos, yPos, size);
        int[] quadPos = getQuadrantPosition(quadrant, xPos, yPos, size);

        // Insert into the appropriate child
        children[quadrant] = children[quadrant].insert(x, y, name, quadPos[0],
            quadPos[1], quadPos[2]);

        return this;
    }


    /**
     * Removes point based on point values
     */
    @Override
    public RemoveResult remove(int x, int y, int xPos, int yPos, int size) {
        // Find the appropriate quadrant
        int quadrant = getQuadrant(x, y, xPos, yPos, size);
        int[] quadPos = getQuadrantPosition(quadrant, xPos, yPos, size);

        // Remove from the appropriate child
        RemoveResult result = children[quadrant].remove(x, y, quadPos[0],
            quadPos[1], quadPos[2]);

        children[quadrant] = result.getNode();

        // Check if we should merge nodes
        if (shouldMerge()) {
            QuadNode mergedNode = mergeNodes(xPos, yPos, size);
            return new RemoveResult(mergedNode, result.getRemovedPoint());
        }

        return new RemoveResult(this, result.getRemovedPoint());
    }


    /**
     * Removes a point based on name
     */
    @Override
    public RemoveResult removeByName(
        String name,
        int xPos,
        int yPos,
        int size) {
        // Try each quadrant until we find and remove the point
        for (int i = 0; i < 4; i++) {
            int[] quadPos = getQuadrantPosition(i, xPos, yPos, size);
            RemoveResult result = children[i].removeByName(name, quadPos[0],
                quadPos[1], quadPos[2]);

            if (result.getRemovedPoint() != null) {
                // Point was found and removed
                children[i] = result.getNode();

                // Check if we should merge nodes
                if (shouldMerge()) {
                    QuadNode mergedNode = mergeNodes(xPos, yPos, size);
                    return new RemoveResult(mergedNode, result
                        .getRemovedPoint());
                }

                return new RemoveResult(this, result.getRemovedPoint());
            }
        }

        // Point not found in any quadrant
        return new RemoveResult(this, null);
    }


    /**
     * Check if the children nodes should be merged into a leaf
     * 
     * @return true if the nodes should be merged
     */
    boolean shouldMerge() {
        ArrayList allPoints = new ArrayList();

        // Count total non-empty leaf nodes and collect all points
        for (int i = 0; i < 4; i++) {
            if (children[i] instanceof LeafNode) {
                LeafNode leaf = (LeafNode)children[i];
                allPoints.addAll(leaf.getPoints());
            }
            else if (!(children[i] instanceof EmptyNode)) {
                // If any child is an internal node, don't merge
                return false;
            }
        }

        // If we have 3 or fewer points, we should merge
        if (allPoints.size() <= 3) {
            return true;
        }

        // Check if all points have the same coordinates
        if (!allPoints.isEmpty()) {
            int firstX = allPoints.get(0).getX();
            int firstY = allPoints.get(0).getY();

            for (int i = 0; i < allPoints.size(); i++) {
                if (allPoints.get(i).getX() != firstX || allPoints.get(i)
                    .getY() != firstY) {
                    // Not all points have the same coordinates
                    return false;
                }
            }

            // All points have the same coordinates, should merge
            return true;
        }

        return false;
    }


    /**
     * Merge child nodes into a leaf node
     * 
     * @param xPos
     *            the x-position of the current quadrant
     * @param yPos
     *            the y-position of the current quadrant
     * @param size
     *            the size of the current quadrant
     * @return the merged leaf node
     */
    private QuadNode mergeNodes(int xPos, int yPos, int size) {
        LeafNode mergedNode = new LeafNode();

        // Collect all points from child nodes
        for (int i = 0; i < 4; i++) {
            if (children[i] instanceof LeafNode) {
                LeafNode leaf = (LeafNode)children[i];
                for (int j = 0; j < leaf.getPoints().size(); j++) {
                    mergedNode.addPoint(leaf.getPoints().get(j));
                }
            }
        }

        // If no points, return empty node
        if (mergedNode.getPoints().isEmpty()) {
            return EmptyNode.getInstance();
        }

        return mergedNode;
    }


    /**
     * Searches for points within a region
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
        // Only count a node as visited if it intersects the search region
        if (intersects(x, y, w, h, xPos, yPos, size)) {
            // Increment nodes visited count
            nodes++;
            // PRQuadtree.nodesVisited++;

            // Search all child quadrants
            for (int i = 0; i < 4; i++) {
                int[] quadPos = getQuadrantPosition(i, xPos, yPos, size);
                nodes += children[i].regionSearch(x, y, w, h, quadPos[0],
                    quadPos[1], quadPos[2], results);
            }
        }
        return nodes;
    }
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
//
//// Check if this quadrant intersects with the search region
// if (!intersects(x, y, w, h, xPos, yPos, size)) {
// return;
// }
//
//// Search all child quadrants
// for (int i = 0; i < 4; i++) {
// int[] quadPos = getQuadrantPosition(i, xPos, yPos, size);
// children[i].regionSearch(x, y, w, h, quadPos[0], quadPos[1],
// quadPos[2], results);
// }
// }


    /**
     * Check if a quadrant intersects with a search region
     * 
     * @param x
     *            the x-coordinate of the search region
     * @param y
     *            the y-coordinate of the search region
     * @param w
     *            the width of the search region
     * @param h
     *            the height of the search region
     * @param qx
     *            the x-position of the quadrant
     * @param qy
     *            the y-position of the quadrant
     * @param qsize
     *            the size of the quadrant
     * @return true if the quadrant intersects with the search region
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
// private boolean intersects(
// int x,
// int y,
// int w,
// int h,
// int qx,
// int qy,
// int qsize) {
//// Check if the quadrant is completely outside the search region
// return !(qx >= x + w || qx + qsize <= x || qy >= y + h || qy
// + qsize <= y);
// }


    /**
     * Finds the duplicate points in the node
     */
    @Override
    public void findDuplicates(
        java.util.HashMap<String, ArrayList> dups,
        int xPos,
        int yPos,
        int size) {
        // Search all child quadrants
        for (int i = 0; i < 4; i++) {
            int[] quadPos = getQuadrantPosition(i, xPos, yPos, size);
            children[i].findDuplicates(dups, quadPos[0], quadPos[1],
                quadPos[2]);
        }
    }


    /**
     * The dump of internal node
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
        sb.append(", ").append(size).append(": Internal\n");

        // Dump each child
        for (int i = 0; i < 4; i++) {
            // Dump the child
            int[] quadPos = getQuadrantPosition(i, xPos, yPos, size);
            children[i].dump(level + 1, sb, quadPos[0], quadPos[1], quadPos[2],
                nodesPrinted);
        }
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
//
// sb.append("Node at ").append(xPos).append(", ").append(yPos);
// sb.append(", ").append(size).append(": Internal\n");
//
//// Dump each child
// String[] quadrantNames = { "NW", "NE", "SW", "SE" };
// for (int i = 0; i < 4; i++) {
//// Add quadrant name
// for (int j = 0; j < (level + 1) * 2; j++) {
// sb.append(" ");
// }
//// sb.append(quadrantNames[i]).append(":\n");
//
//// Dump the child
// int[] quadPos = getQuadrantPosition(i, xPos, yPos, size);
// children[i].dump(level + 1, sb, quadPos[0], quadPos[1],
// quadPos[2]);
// }
// }


    /**
     * Checks if the node is empty
     */
    @Override
    public boolean isEmpty() {
        // An internal node is never empty
        return false;
    }
}
