import student.TestCase;

/**
 * Test class for the LeafNode class.
 * 
 * @author Rushil, Kush
 * @version 1.0
 */
public class LeafNodeTest extends TestCase {
    /**
     * Set up method to initialize resources before each test.
     */

    public void setUp() {

        // Any setup logic needed before each test case (if required).
    }


    /**
     * Tear down method to clean up resources after each test.
     */

    public void tearDown() {

        // Any cleanup logic needed after each test case (if required).
    }


    /**
     * Test the insert method of the LeafNode class.
     * It should insert a new point and return a LeafNode or InternalNode.
     */
    public void testInsert() {
        LeafNode node = new LeafNode();
        QuadNode result = node.insert(1, 1, "point1", 0, 0, 1024);
        assertTrue("Insertion should return a LeafNode or InternalNode",
            result instanceof LeafNode || result instanceof InternalNode);
    }


    /**
     * Test the remove method of the LeafNode class.
     * It should remove a point by its coordinates.
     */
    public void testRemove() {
        LeafNode node = new LeafNode();
        node.addPoint(new Point("point1", 1, 1));
        RemoveResult result = node.remove(1, 1, 0, 0, 1024);
        assertNotNull("Point should be removed from LeafNode", result
            .getRemovedPoint());
    }


    /**
     * Test the removeByName method of the LeafNode class.
     * It should remove a point by its name.
     */
    public void testRemoveByName() {
        LeafNode node = new LeafNode();
        node.addPoint(new Point("point1", 1, 1));
        RemoveResult result = node.removeByName("point1", 0, 0, 1024);
        assertNotNull("Point should be removed by name", result
            .getRemovedPoint());
    }


    /**
     * Test the regionSearch method of the LeafNode class.
     * It should search for points within a region.
     */
    public void testRegionSearch() {
        LeafNode node = new LeafNode();
        node.addPoint(new Point("point1", 1, 1));
        ArrayList results = new ArrayList();
        node.regionSearch(0, 0, 100, 100, 0, 0, 1024, results);
        assertEquals("Region search should return the inserted point", 1,
            results.size());
    }


    /**
     * Test the findDuplicates method of the LeafNode class.
     * It should find and return duplicate points.
     */
    public void testFindDuplicates() {
        LeafNode node = new LeafNode();
        node.addPoint(new Point("point1", 1, 1));
        node.addPoint(new Point("point2", 1, 1));
        java.util.HashMap<String, ArrayList> dups = new java.util.HashMap<>();
        node.findDuplicates(dups, 0, 0, 1024);
        assertEquals("There should be one duplicate", 1, dups.size());
    }


    /**
     * Test the dump method of the LeafNode class.
     * It should include point information in the dump output.
     */
    public void testDump() {
        LeafNode node = new LeafNode();
        node.addPoint(new Point("point1", 1, 1));
        StringBuilder sb = new StringBuilder();
        int[] nodesPrinted = new int[1];
        node.dump(0, sb, 0, 0, 1024, nodesPrinted);
        assertTrue("Dump should contain point information", sb.toString()
            .contains("point1"));
    }
}
