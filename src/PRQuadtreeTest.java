
import java.util.HashMap;
import student.TestCase;

/**
 * The test class for PRQuadTree
 * 
 * @author Rushil, Kush
 * @version 1.0
 */
public class PRQuadtreeTest extends TestCase {

    private PRQuadtree tree;

    /**
     * Sets up variables for use in class
     */
    public void setUp() {
        tree = new PRQuadtree();
    }


    /**
     * Tests the insert for a valid point
     */
    public void testInsertValidPoint() {
        assertTrue(tree.insert(100, 200, "point1"));
    }


    /**
     * Tests for inserting invalid points
     */
    public void testInsertOutOfBounds() {
        assertFalse(tree.insert(-1, 0, "negX"));
        assertFalse(tree.insert(0, -1, "negY"));
        assertFalse(tree.insert(1024, 500, "tooFarX"));
        assertFalse(tree.insert(500, 1024, "tooFarY"));
    }


    /**
     * Tests the remove by coordinates
     */
    public void testRemoveByCoordinates() {
        tree.insert(100, 100, "point1");
        Point removed = tree.remove(100, 100);
        assertNotNull(removed);
        assertEquals("point1", removed.getName());
    }


    /**
     * Tests removing coordinates that don't exist
     */
    public void testRemoveByCoordinatesNotFound() {
        Point removed = tree.remove(999, 999);
        assertNull(removed);
        removed = tree.remove(-1, 0);
        assertNull(removed);
        removed = tree.remove(0, -1);
        assertNull(removed);
        removed = tree.remove(-1, -1);
        assertNull(removed);
        removed = tree.remove(1024, 0);
        assertNull(removed);
        removed = tree.remove(0, 1024);
        assertNull(removed);
        removed = tree.remove(1024, 1024);
        assertNull(removed);
    }


    /**
     * Tests removing by name
     */
    public void testRemoveByName() {
        tree.insert(300, 300, "namedPoint");
        Point removed = tree.removeByName("namedPoint");
        assertNotNull(removed);
        assertEquals("namedPoint", removed.getName());
    }


    /**
     * Tests remove when name doesn't exist
     */
    public void testRemoveByNameNotFound() {
        Point removed = tree.removeByName("notThere");
        assertNull(removed);
    }


    /**
     * Tests the region search
     */
    public void testRegionSearchValid() {
        tree.insert(10, 10, "p1");
        tree.insert(20, 20, "p2");
        ArrayList results = tree.regionSearch(0, 0, 30, 30);
        assertEquals(2, results.size());
        assertEquals(1, tree.getNodesVisited());
    }


    /**
     * Tests for regionsearch with invalid parameters
     */
    public void testRegionSearchInvalidRegion() {
        ArrayList results = tree.regionSearch(0, 0, -10, -20);
        assertEquals(0, results.size());
        results = tree.regionSearch(0, 0, -10, 0);
        assertEquals(0, results.size());
        results = tree.regionSearch(0, 0, 0, -10);
        assertEquals(0, results.size());
        results = tree.regionSearch(0, 0, 0, 0);
        assertEquals(0, results.size());
    }


    /**
     * Tests the duplicates method
     */
    public void testFindDuplicates() {
        tree.insert(50, 50, "p1");
        tree.insert(50, 50, "p2");
        tree.insert(50, 50, "p3");
        tree.insert(50, 50, "p4"); // now >3 at same location
        HashMap<String, ArrayList> dups = tree.findDuplicates();
        assertTrue(dups.containsKey("50,50"));
        assertEquals(4, dups.get("50,50").size());
    }


    /**
     * Tests the dump method
     */
    public void testDumpRuns() {
        tree.insert(5, 5, "p");
        tree.dump(); // visually verify via stdout if needed
    }
}
