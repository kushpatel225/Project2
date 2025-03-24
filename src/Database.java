import java.util.Iterator;

/**
 * The database class sets up the commands to set to either QuadTree or SkipList
 * 
 * @author Rushil, Kush
 * @version 1.0
 */
public class Database {
    private SkipList<String, Point> skipList;
    private PRQuadtree quadtree;

    /**
     * The constructor for database
     */
    public Database() {
        skipList = new SkipList<>();
        quadtree = new PRQuadtree();
    }


    /**
     * Validates if the name follows the rule: - Must begin with a letter.
     * May contain letters, digits, and underscores.
     * 
     * @param name
     *            the string to validate
     * @return true if the name is valid, false otherwise
     */
    private boolean isValidName(String name) {
        // Regular expression to match the rule
        String regex = "^[a-zA-Z][a-zA-Z0-9_]*$";

        // Check if the name matches the regex
        return name != null && name.matches(regex);
    }


    /**
     * Inserts the point into quadtree and skiplist
     * 
     * @param name
     *            the name of point
     * @param x
     *            the x coordinate of point
     * @param y
     *            the y coordinate of point
     */
    // Insert point into both SkipList and PRQuadtree
    public void insert(String name, int x, int y) {
        if (x < 0 || y < 0 || x >= 1024 || y >= 1024) {
            System.out.println("Point rejected: (" + name + ", " + x + ", " + y
                + ")");
            return;
        }
        if (!isValidName(name)) {
            System.out.println("Point rejected: (" + x + ", " + y + ")");
            return;
        }
        Point point = new Point(name, x, y);
        KVPair<String, Point> pair = new KVPair<String, Point>(name, point);
        // Insert into skipList
        skipList.insert(pair);
        // Insert into quadTree
        quadtree.insert(point.getX(), point.getY(), point.getName());
        System.out.println("Point inserted: " + point.toString());
    }


    /**
     * Removes a point based on name
     * 
     * @param name
     *            the name of point to be removed
     */
    // Remove point by name
    public void remove(String name) {
        if (!isValidName(name)) {
            System.out.println("Point not removed: " + name);
            return;
        }
        KVPair<String, Point> pair = skipList.remove(name);
        if (pair != null) {
            Point p = pair.getValue();
            quadtree.remove(p.getX(), p.getY());
            System.out.println("Point removed: " + pair.toString());
        }
        else {
            System.out.println("Point not removed: " + name);
        }
    }


    /**
     * Removes a point based on coordinates
     * 
     * @param x
     *            the x coordinate of point to be removed
     * @param y
     *            the y coordinate of point to be removed
     */
    // Remove point by coordinates
    public void remove(int x, int y) {
        if (x < 0 || y < 0 || x >= 1024 || y >= 1024) {
            System.out.println("Point rejected: (" + x + ", " + y + ")");
            return;
        }

        Point point = new Point("TEMP_NAME", x, y);
        KVPair<String, Point> pair = new KVPair<String, Point>(point.getName(),
            point);

        KVPair<String, Point> removedPoint = skipList.removeByValue(pair
            .getValue());
        if (removedPoint != null) {
            quadtree.remove(point.getX(), point.getY());
            System.out.println("Point removed: " + removedPoint.toString());
            // System.out.println("Point removed: (" + x + ", " + y + ")");
        }
        else {
            System.out.println("Point not found: (" + x + ", " + y + ")");
        }
    }


    /**
     * Checks for points within a specific region
     * 
     * @param x
     *            the x coordinate to search from
     * @param y
     *            the y coordinate to search from
     * @param w
     *            the width of search area
     * @param h
     *            the height of search area
     */
    // Region search, duplicates, and search methods...
    public void regionsearch(int x, int y, int w, int h) {
        if (w <= 0 || h <= 0) {
            System.out.println("Rectangle rejected: " + "(" + x + ", " + y
                + ", " + w + ", " + h + ")");
            return;
        }

        ArrayList intersectingRegion = quadtree.regionSearch(x, y, w, h);
        System.out.println("Points intersecting region " + "(" + x + ", " + y
            + ", " + w + ", " + h + "):");
        for (Iterator<Object> iterator = intersectingRegion.iterator(); iterator
            .hasNext();) {
            Point point = (Point)iterator.next();
            System.out.println("Point found: " + point.toString());
        }
        System.out.println(quadtree.getNodesVisited()
            + " quadtree nodes visited");
    }


    /**
     * Prints the duplicates within the quadtree
     */
    public void duplicates() {
        java.util.HashMap<String, ArrayList> dups = quadtree.findDuplicates();
        System.out.println("Duplicate points:");
        for (Iterator<String> iterator = dups.keySet().iterator(); iterator
            .hasNext();) {
            String s = (String)iterator.next();
            System.out.println("(" + s + ")");
        }

    }


    /**
     * Prints out all the rectangles with the specified name in the SkipList.
     * This
     * method will delegate the searching to the SkipList class completely.
     * 
     * @param name
     *            name of the Rectangle to be searched for
     */
    public void search(String name) {
        MyArrayList<KVPair<String, Point>> results = skipList.search(name);

        // Check if there are any results and print them
        if (results.size() > 0) {
            for (int i = 0; i < results.size(); i++) {
                System.out.println("Found " + results.get(i).toString());
            }
        }
        else {
            System.out.println("Point not found: " + name);
        }
    }


    /**
     * Prints out the aspects of quadtree and skiplist
     */
    public void dump() {
        skipList.dump(); // Dump the SkipList
        quadtree.dump(); // Dump the PR Quadtree
    }


    /**
     * Gets the quadtree instance
     * 
     * @return quadtree instance
     */
    public PRQuadtree getQuadTree() {
        return quadtree;
    }


    /**
     * Gets the skiplist instance
     * 
     * @return skiplist instance
     */
    public SkipList<String, Point> getSkipList() {
        return skipList;
    }
}
