/**
 * The LeafNode class of quadtree
 * 
 * @author Rushil, Kush
 * @version 1.0
 */
public class LeafNode implements QuadNode {
    // List to store the points in this leaf
    /**
     * List of points in node
     */
    private ArrayList points;
    private PRQuadtree tree;

    /**
     * Constructor to create a new leaf node
     */
    public LeafNode() {
        points = new ArrayList();
    }


    /**
     * Gets the points in the node
     * 
     * @return points the elements in the node
     */
    public ArrayList getPoints() {
        return points;
    }


    /**
     * Add a point to this leaf
     * 
     * @param point
     *            the point to add
     */
    public void addPoint(Point point) {
        points.add(point);
    }


    /**
     * Inserts a point into the node
     */
    @Override
    public QuadNode insert(
        int x,
        int y,
        String name,
        int xPos,
        int yPos,
        int size) {
        // Add the new point
        Point newPoint = new Point(name, x, y);

        // Check if this would exceed decomposition rules
        if (points.size() < 3) {
            // Rule 1: Nodes with 1-3 points remain leaf nodes
            addPoint(newPoint);
            return this;
        }

        // Check if all existing points have the same coordinates
        boolean allSamePosition = true;
        int firstX = points.get(0).getX();
        int firstY = points.get(0).getY();

        for (int i = 0; i < points.size(); i++) {
            if (points.get(i).getX() != firstX || points.get(i)
                .getY() != firstY) {
                allSamePosition = false;
                break;
            }
        }

        // Check if the new point has the same position
        if (allSamePosition && x == firstX && y == firstY) {
            // Rule 2: If all points (including the new one) have the same
            // position,
            // keep as a leaf node
            addPoint(newPoint);
            return this;
        }

        // Rule 3: If we have > 3 points not all at the same position, split
        // into internal node
        InternalNode internalNode = new InternalNode();

        // Add all existing points to the internal node
        for (int i = 0; i < points.size(); i++) {
            internalNode = (InternalNode)internalNode.insert(points.get(i)
                .getX(), points.get(i).getY(), points.get(i).getName(), xPos,
                yPos, size);
        }

        // Add the new point
        return internalNode.insert(x, y, name, xPos, yPos, size);
    }


    /**
     * Removes a point based on coordinates
     */
    @Override
    public RemoveResult remove(int x, int y, int xPos, int yPos, int size) {
        // Find a point with the given coordinates
        for (int i = 0; i < points.size(); i++) {
            Point p = points.get(i);
            if (p.getX() == x && p.getY() == y) {
                // Remove the point
                Point removedPoint = points.remove(points.get(i));

                // If no points left, return empty node
                if (points.isEmpty()) {
                    return new RemoveResult(EmptyNode.getInstance(),
                        removedPoint);
                }

                // Otherwise, return this node and the removed point
                return new RemoveResult(this, removedPoint);
            }
        }

        // No point found with the given coordinates
        return new RemoveResult(this, null);
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
        // Find a point with the given name
        for (int i = 0; i < points.size(); i++) {
            Point p = points.get(i);
            if (p.getName().equals(name)) {
                // Remove the point
                Point removedPoint = points.remove(points.get(i));

                // If no points left, return empty node
                if (points.isEmpty()) {
                    return new RemoveResult(EmptyNode.getInstance(),
                        removedPoint);
                }

                // Otherwise, return this node and the removed point
                return new RemoveResult(this, removedPoint);
            }
        }

        // No point found with the given name
        return new RemoveResult(this, null);
    }


    /**
     * Checks for points within a region
     */
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
            // Increment nodes visited count
            PRQuadtree.nodesVisited++;

            // Check each point to see if it falls within the search region
            for (int i = 0; i < points.size(); i++) {
                int px = points.get(i).getX();
                int py = points.get(i).getY();

                if (px >= x && px < x + w && py >= y && py < y + h) {
                    results.add(points.get(i));
                }
            }
        }
    }


    /**
     * Checks if a node intersects a region
     * 
     * @param x
     *            the x coordinate of search area
     * @param y
     *            the y coordinate of search area
     * @param w
     *            the width of search area
     * @param h
     *            the height of search area
     * @param qx
     *            the x coordinate of node
     * @param qy
     *            the y coordinate of node
     * @param qsize
     *            the size of node
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
//// Check each point to see if it falls within the search region
// for (Point p : points) {
// int px = p.getX();
// int py = p.getY();
//
// if (px >= x && px < x + w && py >= y && py < y + h) {
// results.add(p);
// }
// }
// }


    /**
     * Checks if there exists duplicates in node
     */
    @Override
    public void findDuplicates(
        java.util.HashMap<String, ArrayList> dups,
        int xPos,
        int yPos,
        int size) {
        // Group points by their coordinates
        java.util.HashMap<String, ArrayList> localDups =
            new java.util.HashMap<>();

        for (int i = 0; i < points.size(); i++) {
            String key = points.get(i).getX() + "," + points.get(i).getY();
            if (!localDups.containsKey(key)) {
                localDups.put(key, new ArrayList());
            }
            localDups.get(key).add(points.get(i));
        }

        // Add duplicates to the result map
        for (java.util.Map.Entry<String, ArrayList> entry : localDups
            .entrySet()) {
            if (entry.getValue().size() > 1) {
                dups.put(entry.getKey(), entry.getValue());
            }
        }
    }


    /**
     * The dump for leafNode
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
        sb.append(", ").append(size).append(":\n");

        // Print each point
        for (int j = 0; j < points.size(); j++) {
            for (int i = 0; i < (level + 1) * 2; i++) {
                sb.append(" ");
            }
            Point pt = points.get(j);
            sb.append("(").append(pt.getName()).append(", ");
            sb.append(pt.getX()).append(", ").append(pt.getY()).append(")\n");
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
// sb.append(", ").append(size).append(":\n");
//
//// Print each point
// for (Point p : points) {
// for (int i = 0; i < (level + 1) * 2; i++) {
// sb.append(" ");
// }
// sb.append("(").append(p.getName()).append(", ");
// sb.append(p.getX()).append(", ").append(p.getY()).append(
// ")\n");
// }
// }


    /**
     * Checks if the node is empty
     */
    @Override
    public boolean isEmpty() {
        return points.isEmpty();
    }
}
