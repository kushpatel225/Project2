
import java.util.HashMap;
import student.TestCase;

/**
 * Test class for the InternalNode class.
 * 
 * Follows Quadtree decomposition rules:
 * 1. 1–3 points → leaf
 * 2. >3 points at same (x,y) → still a leaf
 * 3. >3 points at different (x,y) → internal
 * 
 * @author Rushil, Kush
 * @version 1.0
 */
public class InternalNodeTest extends TestCase {
    private InternalNode node;

    /**
     * Sets up the variables for use in class
     */
    public void setUp() {
        node = new InternalNode();
    }


    /**
     * Insert points into each of the 4 quadrants and test region search.
     */
    public void testInsertAllQuadrants() {
        node.insert(100, 100, "NW", 0, 0, 1024);
        node.insert(600, 100, "NE", 0, 0, 1024);
        node.insert(100, 600, "SW", 0, 0, 1024);
        node.insert(600, 600, "SE", 0, 0, 1024);

        ArrayList results = new ArrayList();
        node.regionSearch(0, 0, 1024, 1024, 0, 0, 1024, results);
        assertEquals("Should find 4 points in region search", 4, results
            .size());
    }


    /**
     * Removes a point and checks if it is found.
     */
    public void testRemove() {
        node.insert(1, 1, "point1", 0, 0, 1024);
        RemoveResult result = node.remove(1, 1, 0, 0, 1024);
        assertNotNull("Should return removed point", result.getRemovedPoint());
    }


    /**
     * Removes a point by name.
     */
    public void testRemoveByName() {
        node.insert(1, 1, "point1", 0, 0, 1024);
        RemoveResult result = node.removeByName("point1", 0, 0, 1024);
        assertNotNull("Should find and remove point by name", result
            .getRemovedPoint());
    }


    /**
     * Should merge with <=3 points at same location.
     */
    public void testShouldMergeTrueWith3Points() {
        node.insert(1, 1, "p1", 0, 0, 1024);
        node.insert(1, 1, "p2", 0, 0, 1024);
        node.insert(1, 1, "p3", 0, 0, 1024);
        assertTrue("Should merge with 3 points at same location", node
            .shouldMerge());
    }


    /**
     * Should merge even with >3 points at same (x,y)
     */
    public void testShouldMergeTrueWithSameLocationPoints() {
        node.insert(1, 1, "p1", 0, 0, 1024);
        node.insert(1, 1, "p2", 0, 0, 1024);
        node.insert(1, 1, "p3", 0, 0, 1024);
        node.insert(1, 1, "p4", 0, 0, 1024);
        assertTrue("Should merge with >3 points at same location", node
            .shouldMerge());
    }


    /**
     * Should NOT merge if >3 points at different coordinates.
     */
    public void testShouldMergeFalseWithDifferentLocations() {
        node.insert(100, 100, "p1", 0, 0, 1024); // NW
        node.insert(600, 100, "p2", 0, 0, 1024); // NE
        node.insert(100, 600, "p3", 0, 0, 1024); // SW
        node.insert(600, 600, "p4", 0, 0, 1024); // SE
        assertFalse("Should NOT merge with >3 points at different coords", node
            .shouldMerge());
    }


    /**
     * Region search returns correct number of matching points.
     */
    public void testRegionSearch() {
        node.insert(10, 10, "p1", 0, 0, 1024);
        ArrayList results = new ArrayList();
        int visited = node.regionSearch(0, 0, 100, 100, 0, 0, 1024, results);
        assertEquals(1, results.size());
        assertTrue("Should visit at least one node", visited > 0);
    }


    /**
     * Dump output contains the word "Internal".
     */
    public void testDump() {
        StringBuilder sb = new StringBuilder();
        int[] nodesPrinted = new int[1];
        node.dump(0, sb, 0, 0, 1024, nodesPrinted);
        assertTrue("Dump output should contain 'Internal'", sb.toString()
            .contains("Internal"));
    }


    /**
     * Ensure remove triggers a merge and returns the correct node type.
     */
    public void testRemoveTriggersMerge() {
        node.insert(1, 1, "p1", 0, 0, 1024);
        node.insert(1, 1, "p2", 0, 0, 1024);
        node.insert(1, 1, "p3", 0, 0, 1024);
        RemoveResult result = node.remove(1, 1, 0, 0, 1024);
        assertTrue("Merged node should be LeafNode or EmptyNode", result
            .getNode() instanceof LeafNode || result
                .getNode() instanceof EmptyNode);
    }


    /**
     * Tests the isEmpty method
     */
    public void testIsEmpty() {
        assertFalse(node.isEmpty());
    }


    /**
     * Tests the duplicates method
     */
    public void testDuplicates() {
        node.insert(10, 10, "apple", 0, 0, 1024);
        node.insert(10, 10, "banana", 0, 0, 1024);
        HashMap<String, ArrayList> dups = new HashMap<>();
        node.findDuplicates(dups, 0, 0, 1024);
        assertEquals(1, dups.size(), 0.01);

    }
}
