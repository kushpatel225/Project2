
import java.util.HashMap;
import student.TestCase;

/**
 * Test class for the EmptyNode class.
 * Ensures correct behavior and full mutation coverage.
 * 
 * Covers all methods including singleton pattern, insert, remove,
 * region search, duplicate finding, dump, and isEmpty.
 * 
 * @author Rushil, Kush
 * @version 1.1
 */
public class EmptyNodeTest extends TestCase {

    /**
     * Set up method to initialize resources before each test.
     */
    public void setUp() {
        // No setup required for stateless EmptyNode
    }


    /**
     * Tear down method to clean up resources after each test.
     */
    public void tearDown() {
        // No teardown required
    }


    /**
     * Test the singleton instance of the EmptyNode class.
     */
    public void testSingletonInstance() {
        EmptyNode node1 = EmptyNode.getInstance();
        EmptyNode node2 = EmptyNode.getInstance();
        assertSame("Both nodes should be the same instance", node1, node2);
        assertNotNull("Singleton instance should not be null", node1);
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
        LeafNode leaf = (LeafNode)result;
        assertEquals("point1", leaf.getPoints().get(0).getName());
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
     * Test the regionSearch method when there is no intersection.
     * It should return 0 and an empty result list.
     */
    public void testRegionSearchNoIntersection() {
        EmptyNode node = EmptyNode.getInstance();
        ArrayList results = new ArrayList();
        int visited = node.regionSearch(2000, 2000, 10, 10, 0, 0, 1024,
            results);
        assertEquals("No nodes visited when region is outside bounds", 0,
            visited);
        assertTrue("Result list should be empty", results.isEmpty());
    }


    /**
     * Test the regionSearch method when there is an intersection.
     * It should return 1 for the visited node.
     */
    public void testRegionSearchIntersects() {
        EmptyNode node = EmptyNode.getInstance();
        ArrayList results = new ArrayList();
        int visited = node.regionSearch(0, 0, 1024, 1024, 0, 0, 1024, results);
        assertEquals("One node should be visited", 1, visited);
    }


    /**
     * Test the findDuplicates method of the EmptyNode class.
     * It should not find any duplicates as there are no points.
     */
    public void testFindDuplicates() {
        EmptyNode node = EmptyNode.getInstance();
        HashMap<String, ArrayList> dups = new HashMap<>();
        node.findDuplicates(dups, 0, 0, 1024);
        assertTrue("No duplicates should be found in an EmptyNode", dups
            .isEmpty());
    }


    /**
     * Test the dump method of the EmptyNode class.
     * It should include 'Empty' in the dump output and increment the count.
     */
    public void testDump() {
        EmptyNode node = EmptyNode.getInstance();
        StringBuilder sb = new StringBuilder();
        int[] nodesPrinted = new int[1];
        node.dump(0, sb, 0, 0, 1024, nodesPrinted);
        assertTrue("Dump should contain 'Empty'", sb.toString().contains(
            "Empty"));
        assertEquals("One node should be printed", 1, nodesPrinted[0]);
    }


    /**
     * Test the isEmpty method.
     * It should always return true for EmptyNode.
     */
    public void testIsEmpty() {
        EmptyNode node = EmptyNode.getInstance();
        assertTrue("EmptyNode should always be empty", node.isEmpty());
    }
}
