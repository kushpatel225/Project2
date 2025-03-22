
public interface QuadNode {
    /**
     * Insert a point into the quadtree
     * 
     * @param x
     *            the x-coordinate
     * @param y
     *            the y-coordinate
     * @param name
     *            the name of the point
     * @param xPos
     *            the x-position of the current quadrant
     * @param yPos
     *            the y-position of the current quadrant
     * @param size
     *            the size of the current quadrant
     * @return the updated node after insertion
     */
    public QuadNode insert(
        int x,
        int y,
        String name,
        int xPos,
        int yPos,
        int size);


    /**
     * Remove a point from the quadtree by coordinates
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
     * @return the updated node after removal and a Point if one was removed
     */
    public RemoveResult remove(int x, int y, int xPos, int yPos, int size);


    /**
     * Remove a point from the quadtree by name
     * 
     * @param name
     *            the name of the point
     * @param xPos
     *            the x-position of the current quadrant
     * @param yPos
     *            the y-position of the current quadrant
     * @param size
     *            the size of the current quadrant
     * @return the updated node after removal and a Point if one was removed
     */
    public RemoveResult removeByName(String name, int xPos, int yPos, int size);


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
     * @param xPos
     *            the x-position of the current quadrant
     * @param yPos
     *            the y-position of the current quadrant
     * @param size
     *            the size of the current quadrant
     * @param results
     *            the list to add found points to
     */
    public void regionSearch(
        int x,
        int y,
        int w,
        int h,
        int xPos,
        int yPos,
        int size,
        ArrayList results);


    /**
     * Find all duplicate points
     * 
     * @param dups
     *            the map to store duplicate positions
     * @param xPos
     *            the x-position of the current quadrant
     * @param yPos
     *            the y-position of the current quadrant
     * @param size
     *            the size of the current quadrant
     */
    public void findDuplicates(
        java.util.HashMap<String, ArrayList> dups,
        int xPos,
        int yPos,
        int size);


    /**
     * Dump the tree nodes for output
     * 
     * @param level
     *            the current level in the tree (for indentation)
     * @param sb
     *            the string builder to append the dump to
     * @param xPos
     *            the x-position of the current quadrant
     * @param yPos
     *            the y-position of the current quadrant
     * @param size
     *            the size of the current quadrant
     */
    public void dump(
        int level,
        StringBuilder sb,
        int xPos,
        int yPos,
        int size,
        int[] nodesPrinted);
// void dump(int level, StringBuilder sb, int xPos, int yPos, int size);


    /**
     * Check if the node is an empty leaf
     * 
     * @return true if the node is an empty leaf
     */
    public boolean isEmpty();
}
