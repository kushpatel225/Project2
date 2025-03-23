import student.TestCase;

/**
 * Test class for the InternalNode class.
 * 
 * @author Rushil, Kush
 * @version 1.0
 */
public class InternalNodeTest extends TestCase {
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
     * Test the insert method of the InternalNode class.
     * It should insert a point into the appropriate quadrant.
     */
    public void testInsert() {
        InternalNode node = new InternalNode();
        QuadNode result = node.insert(1, 1, "point1", 0, 0, 1024);
        assertTrue("Insertion should not change node type",
            result instanceof InternalNode);
    }


    /**
     * Test the remove method of the InternalNode class.
     * It should remove a point from the appropriate quadrant.
     */
    public void testRemove() {
        InternalNode node = new InternalNode();
        node.insert(1, 1, "point1", 0, 0, 1024);
        RemoveResult result = node.remove(1, 1, 0, 0, 1024);
        assertNotNull("Point should be removed from InternalNode", result
            .getRemovedPoint());
    }


    /**
     * Test the removeByName method of the InternalNode class.
     * It should remove a point by its name.
     */
    public void testRemoveByName() {
        InternalNode node = new InternalNode();
        node.insert(1, 1, "point1", 0, 0, 1024);
        RemoveResult result = node.removeByName("point1", 0, 0, 1024);
        assertNotNull("Point should be removed by name", result
            .getRemovedPoint());
    }


    /**
     * Test the shouldMerge method of the InternalNode class.
     * It should return true if the node should merge.
     */
    public void testShouldMerge() {
        InternalNode node = new InternalNode();
        node.insert(1, 1, "point1", 0, 0, 1024);
        node.insert(1, 1, "point2", 0, 0, 1024);
        assertTrue("InternalNode should merge if it has 3 or fewer points", node
            .shouldMerge());
    }


    /**
     * Test the regionSearch method of the InternalNode class.
     * It should perform a region search and return the matching points.
     */
    public void testRegionSearch() {
        InternalNode node = new InternalNode();
        node.insert(1, 1, "point1", 0, 0, 1024);
        ArrayList results = new ArrayList();
        node.regionSearch(0, 0, 100, 100, 0, 0, 1024, results);
        assertEquals("Region search should return the inserted point", 1,
            results.size());
    }


    /**
     * Test the dump method of the InternalNode class.
     * It should include 'Internal' in the dump output.
     */
    public void testDump() {
        InternalNode node = new InternalNode();
        StringBuilder sb = new StringBuilder();
        int[] nodesPrinted = new int[1];
        node.dump(0, sb, 0, 0, 1024, nodesPrinted);
        assertTrue("Dump should contain 'Internal' in the output", sb.toString()
            .contains("Internal"));
    }
}
