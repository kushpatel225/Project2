import student.TestCase;

public class DatabaseTest extends TestCase {

    private Database db;
    private PRQuadtree tree;
    private SkipList list;

    public void setUp() {
        db = new Database();
        tree = db.getQuadTree();
        list = db.getSkipList();

    }


    public void testIsValidName() {
        db.insert("1app", 100, 100);
        assertEquals(0, db.getSkipList().size());
        db.insert(null, 100, 100);
        assertEquals(0, db.getSkipList().size());
        db.insert("apple", 100, 100);
        assertEquals(1, db.getSkipList().size());
    }


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


    public void testRemoveName() {
        db.remove("apple");
        assertEquals(0, db.getSkipList().size());
        db.remove("-1b");
        assertEquals(0, db.getSkipList().size());
        db.insert("apple", 100, 100);
        db.remove("apple");
        assertEquals(0, db.getSkipList().size());
    }


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


    public void testDuplicates() {
        db.duplicates();
        db.insert("apple", 100, 100);
        db.insert("banana", 100, 100);
        db.insert("orange", 200, 100);
        db.duplicates();
        assertEquals(3, db.getSkipList().size());
    }


    public void testSearch() {
        db.search("apple");
        assertEquals(0, db.getSkipList().size());
        db.insert("apple", 100, 100);
        db.insert("banana", 100, 100);
        db.insert("apple", 200, 100);
        db.search("apple");
        assertEquals(3, db.getSkipList().size());
    }


    public void testDump() {
        db.dump();
        assertEquals(0, db.getSkipList().size());
        db.insert("apple", 100, 100);
        db.insert("banana", 100, 100);
        db.dump();
        assertEquals(2, db.getSkipList().size());
    }


    public void testGetQuadtree() {
        assertEquals(tree, db.getQuadTree());
    }


    public void testGetSkipList() {
        assertEquals(list, db.getSkipList());
    }
}
