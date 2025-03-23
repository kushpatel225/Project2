import student.TestCase;

/**
 * Test class for the EmptyNode class.
 * 
 * @author Rushil, Kush
 * @version 1.0
 */
public class EmptyNodeTest extends TestCase {
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
     * Test the singleton instance of the EmptyNode class.
     */
    public void testSingletonInstance() {
        EmptyNode node1 = EmptyNode.getInstance();
        EmptyNode node2 = EmptyNode.getInstance();
        assertSame("Both nodes should be the same instance", node1, node2);
    }


    /**
     * Test the insert method of the EmptyNode class.
     * It should create a new LeafNode when insert is called.
     */
    public void testInsert() {
        EmptyNode node = EmptyNode.getInstance();
        QuadNode result = node.insert(1, 1, "point1", 0, 0, 1024);
        assertTrue("Inserted node should be a LeafNode",
            result instanceof LeafNode);
    }


    /**
     * Test the remove method of the EmptyNode class.
     * It should return an EmptyNode as nothing can be removed from it.
     */
    public void testRemove() {
        EmptyNode node = EmptyNode.getInstance();
        RemoveResult result = node.remove(1, 1, 0, 0, 1024);
        assertNull("No point should be removed from an EmptyNode", result
            .getRemovedPoint());
        assertSame("The node should remain an EmptyNode", EmptyNode
            .getInstance(), result.getNode());
    }


    /**
     * Test the removeByName method of the EmptyNode class.
     * It should return an EmptyNode as no point can be removed by name.
     */
    public void testRemoveByName() {
        EmptyNode node = EmptyNode.getInstance();
        RemoveResult result = node.removeByName("point1", 0, 0, 1024);
        assertNull("No point should be removed by name from an EmptyNode",
            result.getRemovedPoint());
        assertSame("The node should remain an EmptyNode", EmptyNode
            .getInstance(), result.getNode());
    }


    /**
     * Test the regionSearch method of the EmptyNode class.
     * It should return an empty result since there are no points in an
     * EmptyNode.
     */
    public void testRegionSearch() {
        EmptyNode node = EmptyNode.getInstance();
        ArrayList results = new ArrayList();
        node.regionSearch(0, 0, 100, 100, 0, 0, 1024, results);
        assertTrue("No points should be found in an EmptyNode", results
            .isEmpty());
    }


    /**
     * Test the findDuplicates method of the EmptyNode class.
     * It should not find any duplicates as there are no points in an EmptyNode.
     */
    public void testFindDuplicates() {
        EmptyNode node = EmptyNode.getInstance();
        java.util.HashMap<String, ArrayList> dups = new java.util.HashMap<>();
        node.findDuplicates(dups, 0, 0, 1024);
        assertTrue("No duplicates should be found in an EmptyNode", dups
            .isEmpty());
    }


    /**
     * Test the dump method of the EmptyNode class.
     * It should include 'Empty' in the dump output.
     */
    public void testDump() {
        EmptyNode node = EmptyNode.getInstance();
        StringBuilder sb = new StringBuilder();
        int[] nodesPrinted = new int[1];
        node.dump(0, sb, 0, 0, 1024, nodesPrinted);
        assertTrue("Dump should contain 'Empty' in the output", sb.toString()
            .contains("Empty"));
    }
}
