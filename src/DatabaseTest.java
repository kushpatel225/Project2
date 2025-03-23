import student.TestCase;

/**
 * The test class for database
 * 
 * @author Rushil, Kush
 * @version 1.0
 */
public class DatabaseTest extends TestCase {

    private Database db;
    private PRQuadtree tree;
    private SkipList<String, Point> list;

    /**
     * Sets up variables for the class
     */
    public void setUp() {
        db = new Database();
        tree = db.getQuadTree();
        list = db.getSkipList();

    }


    /**
     * Checks if names are valid
     */
    public void testIsValidName() {
        db.insert("1app", 100, 100);
        assertEquals(0, db.getSkipList().size());
        db.insert(null, 100, 100);
        assertEquals(0, db.getSkipList().size());
        db.insert("apple", 100, 100);
        assertEquals(1, db.getSkipList().size());
    }


    /**
     * Tests the insert method
     */
    public void testInsert() {
        db.insert("apple", 10000, 10000);
        assertEquals(0, db.getSkipList().size());
        db.insert("apple", 0, 10000);
        assertEquals(0, db.getSkipList().size());
        db.insert("apple", 10000, 0);
        assertEquals(0, db.getSkipList().size());
        db.insert("apple", -1, 100);
        assertEquals(0, db.getSkipList().size());
        db.insert("apple", 100, -1);
        assertEquals(0, db.getSkipList().size());
    }


    /**
     * Tests the remove by name method
     */
    public void testRemoveName() {
        db.remove("apple");
        assertEquals(0, db.getSkipList().size());
        db.remove("-1b");
        assertEquals(0, db.getSkipList().size());
        db.insert("apple", 100, 100);
        db.remove("apple");
        assertEquals(0, db.getSkipList().size());
    }


    /**
     * Tests the remove by coordinate method
     */
    public void testRemoveCoor() {
        db.remove(10000, 10000);
        assertEquals(0, db.getSkipList().size());
        db.remove(0, 10000);
        assertEquals(0, db.getSkipList().size());
        db.remove(10000, 0);
        assertEquals(0, db.getSkipList().size());
        db.remove(-1, 100);
        assertEquals(0, db.getSkipList().size());
        db.remove(100, -1);
        assertEquals(0, db.getSkipList().size());
        db.remove(-1, -1);
        assertEquals(0, db.getSkipList().size());
        db.remove(100, 100);
        assertEquals(0, db.getSkipList().size());
        db.insert("apple", 100, 100);
        db.remove(100, 100);
        assertEquals(0, db.getSkipList().size());
    }


    /**
     * Tests the regionSearch method
     */
    public void testRegionSearch() {
        db.regionsearch(0, 0, -1, -1);
        assertEquals(0, db.getSkipList().size());
        db.regionsearch(0, 0, 10, -1);
        assertEquals(0, db.getSkipList().size());
        db.regionsearch(0, 0, -1, 10);
        assertEquals(0, db.getSkipList().size());
        db.regionsearch(0, 0, 10, 10);
        assertEquals(0, db.getSkipList().size());
        db.insert("apple", 100, 100);
        db.insert("banana", 200, 100);
        db.insert("orange", 100, 50);
        db.regionsearch(0, 0, 150, 100);
        assertEquals(3, db.getSkipList().size());
    }


    /**
     * Tests the duplicates method
     */
    public void testDuplicates() {
        db.duplicates();
        db.insert("apple", 100, 100);
        db.insert("banana", 100, 100);
        db.insert("orange", 200, 100);
        db.duplicates();
        assertEquals(3, db.getSkipList().size());
    }


    /**
     * Tests the search method
     */
    public void testSearch() {
        db.search("apple");
        assertEquals(0, db.getSkipList().size());
        db.insert("apple", 100, 100);
        db.insert("banana", 100, 100);
        db.insert("apple", 200, 100);
        db.search("apple");
        assertEquals(3, db.getSkipList().size());
    }


    /**
     * Tests the dump method
     */
    public void testDump() {
        db.dump();
        assertEquals(0, db.getSkipList().size());
        db.insert("apple", 100, 100);
        db.insert("banana", 100, 100);
        db.dump();
        assertEquals(2, db.getSkipList().size());
    }


    /**
     * Tests the getQuadtree method
     */
    public void testGetQuadtree() {
        assertEquals(tree, db.getQuadTree());
    }


    /**
     * Tests the getSkiplist method
     */
    public void testGetSkipList() {
        assertEquals(list, db.getSkipList());
    }
}
