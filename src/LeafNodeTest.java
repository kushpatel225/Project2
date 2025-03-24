
import student.TestCase;

/**
 * Test class for the LeafNode class.
 * Ensures full mutation coverage and validates quadtree behavior
 * such as decomposition, region search, point removal, and dumping.
 * 
 * @author Rushil, Kush
 * @version 1.1
 */
public class LeafNodeTest extends TestCase {

    /**
     * Setup before each test.
     */
    public void setUp() {
        // No global setup needed
    }


    /**
     * Cleanup after each test.
     */
    public void tearDown() {
        // No global teardown needed
    }


    /**
     * Test inserting up to 3 points into a LeafNode.
     */
    public void testInsertUnderCapacity() {
        LeafNode node = new LeafNode();
        assertTrue(node.insert(1, 1, "p1", 0, 0, 1024) instanceof LeafNode);
        assertTrue(node.insert(2, 2, "p2", 0, 0, 1024) instanceof LeafNode);
        assertTrue(node.insert(3, 3, "p3", 0, 0, 1024) instanceof LeafNode);
        assertEquals(3, node.getPoints().size());
    }


    /**
     * Test inserting points with the same position (>3) to ensure leaf remains.
     */
    public void testInsertSameLocation() {
        LeafNode node = new LeafNode();
        node.insert(5, 5, "p1", 0, 0, 1024);
        node.insert(5, 5, "p2", 0, 0, 1024);
        node.insert(5, 5, "p3", 0, 0, 1024);
        QuadNode result = node.insert(5, 5, "p4", 0, 0, 1024);
        assertTrue("Node should remain LeafNode", result instanceof LeafNode);
        assertEquals(4, ((LeafNode)result).getPoints().size());
    }


    /**
     * Test inserting >3 points with different coordinates causes split.
     */
    public void testInsertDecomposes() {
        LeafNode node = new LeafNode();
        node.insert(1, 1, "p1", 0, 0, 1024);
        node.insert(2, 2, "p2", 0, 0, 1024);
        node.insert(3, 3, "p3", 0, 0, 1024);
        QuadNode result = node.insert(4, 4, "p4", 0, 0, 1024);
        assertTrue("Node should convert to InternalNode",
            result instanceof InternalNode);
    }


    /**
     * Test removing a point by coordinates.
     */
    public void testRemove() {
        LeafNode node = new LeafNode();
        node.addPoint(new Point("p1", 1, 1));
        RemoveResult result = node.remove(1, 1, 0, 0, 1024);
        assertNotNull(result.getRemovedPoint());
        assertTrue(result.getNode() instanceof EmptyNode);
    }


    /**
     * Test removing a point by name.
     */
    public void testRemoveByName() {
        LeafNode node = new LeafNode();
        node.addPoint(new Point("p2", 2, 2));
        RemoveResult result = node.removeByName("p2", 0, 0, 1024);
        assertNotNull(result.getRemovedPoint());
        assertTrue(result.getNode() instanceof EmptyNode);
    }


    /**
     * Test region search hits node and finds points.
     */
    public void testRegionSearchHit() {
        LeafNode node = new LeafNode();
        node.addPoint(new Point("p1", 10, 10));
        ArrayList results = new ArrayList();
        int visited = node.regionSearch(0, 0, 100, 100, 0, 0, 1024, results);
        assertEquals(1, visited);
        assertEquals(1, results.size());
    }


    /**
     * Test region search does not intersect.
     */
    public void testRegionSearchMiss() {
        LeafNode node = new LeafNode();
        node.addPoint(new Point("p1", 10, 10));
        ArrayList results = new ArrayList();
        int visited = node.regionSearch(500, 500, 10, 10, 0, 0, 1024, results);

        assertEquals("Should still visit 1 node", 1, visited); // <- Updated
        assertTrue("No points should match the region", results.isEmpty());
    }


    /**
     * Test findDuplicates correctly identifies duplicate coordinates.
     */
    public void testFindDuplicates() {
        LeafNode node = new LeafNode();
        node.addPoint(new Point("a", 1, 1));
        node.addPoint(new Point("b", 1, 1));
        java.util.HashMap<String, ArrayList> dups = new java.util.HashMap<>();
        node.findDuplicates(dups, 0, 0, 1024);
        assertEquals(1, dups.size());
    }


    /**
     * Test dump outputs content and updates counter.
     */
    public void testDump() {
        LeafNode node = new LeafNode();
        node.addPoint(new Point("p1", 1, 1));
        StringBuilder sb = new StringBuilder();
        int[] counter = new int[1];
        node.dump(1, sb, 0, 0, 1024, counter);
        assertTrue(sb.toString().contains("p1"));
        assertEquals(1, counter[0]);
    }


    /**
     * Test getPoints returns correct list reference.
     */
    public void testGetPoints() {
        LeafNode node = new LeafNode();
        assertNotNull(node.getPoints());
        assertEquals(0, node.getPoints().size());
    }


    /**
     * Tests the isEmpty method
     */
    public void testIsEmpty() {
        LeafNode node = new LeafNode();
        assertTrue(node.isEmpty());
    }
}
