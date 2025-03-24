import java.util.Iterator;
import student.TestCase;

/**
 * The test class for skiplist
 * 
 * @author Rushil, Kush
 * @version 1.0
 */
public class SkipListTest extends TestCase {
    private SkipList<Integer, String> skipList;

    /**
     * Sets up the variables for use in this class
     */

    public void setUp() {
        skipList = new SkipList<>();
    }


    /**
     * Tests for null inserts
     */
    public void testInsertNullKVPair() {
        try {
            skipList.insert(null);
        }
        catch (Exception error) {
            assertTrue(error instanceof IllegalArgumentException);
        }
    }


    /**
     * Tests for inserting and searching
     */
    public void testInsertAndSearch() {
        skipList.insert(new KVPair<>(10, "Value10"));
        skipList.insert(new KVPair<>(20, "Value20"));
        skipList.insert(new KVPair<>(30, "Value30"));

        assertEquals("Value10", skipList.search(10).get(0).getValue());
        assertEquals("Value20", skipList.search(20).get(0).getValue());
        assertEquals("Value30", skipList.search(30).get(0).getValue());
    }


    /**
     * Tests for inserting duplicates
     */
    public void testInsertDuplicateKeys() {
        skipList.insert(new KVPair<>(5, "A"));
        skipList.insert(new KVPair<>(5, "B"));
        assertEquals(2, skipList.search(5).size());
    }


    /**
     * Tests for searching for nonexistent values
     */
    public void testSearchNonExistent() {
        assertTrue(skipList.search(99).isEmpty());
    }


    /**
     * Tests for searching for null
     */
    public void testSearchNullKey() {
        skipList.search(null);
    }


    /**
     * Tests the remove method
     */
    public void testRemove() {
        skipList.insert(new KVPair<>(15, "Value15"));
        skipList.insert(new KVPair<>(25, "Value25"));
        assertNotNull(skipList.remove(15));
        assertNull(skipList.remove(15));
    }


    /**
     * Tests for removing nonexistent values
     */
    public void testRemoveNonExistentKey() {
        assertNull(skipList.remove(100));
    }


    /**
     * Tests remove by value
     */
    public void testRemoveByValue() {
        skipList.insert(new KVPair<>(1, "A"));
        skipList.insert(new KVPair<>(2, "B"));
        assertNotNull(skipList.removeByValue("A"));
        assertNull(skipList.removeByValue("A"));
    }


    /**
     * Tests the adjust head method
     */
    public void testAdjustHead() {
        skipList.insert(new KVPair<>(10, "Value10"));
        int oldLevel = skipList.getHead().getNodeLevel();
        skipList.adjustHead(oldLevel + 2);
        assertEquals(oldLevel + 2, skipList.getHead().getNodeLevel());
    }


    /**
     * Tests the node methods
     */
    public void testSkipNodeMethods() {
        KVPair<Integer, String> pair = new KVPair<>(10, "Value10");
        SkipList<Integer, String>.SkipNode node = skipList.new SkipNode(pair,
            3);
        assertEquals(pair, node.element());
        assertEquals(3, node.getNodeLevel());
    }


    /**
     * Tests the random level method
     */
    public void testRandomLevel() {
        skipList = new SkipList<>();
        int level = skipList.randomLevel();
        assertTrue(level >= 1);
    }


    /**
     * Tests the iterator methods
     */
    public void testIterator() {
        skipList.insert(new KVPair<>(1, "One"));
        skipList.insert(new KVPair<>(2, "Two"));

        Iterator<KVPair<Integer, String>> iterator = skipList.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(1, iterator.next().getKey(), 0.01);
        assertEquals(2, iterator.next().getKey(), 0.01);
        assertFalse(iterator.hasNext());
    }


    /**
     * Tests the iterator on an empty list
     */
    public void testIteratorOnEmptyList() {
        Iterator<KVPair<Integer, String>> iterator = skipList.iterator();
        assertFalse(iterator.hasNext());
    }


    /**
     * Tests the size method
     */
    public void testSize() {
        assertEquals(0, skipList.size());
        skipList.insert(new KVPair<>(5, "Five"));
        assertEquals(1, skipList.size());
        skipList.remove(5);
        assertEquals(0, skipList.size());
    }


    /**
     * Tests the dump method
     */
    public void testDump() {
        skipList.dump();
        assertEquals(0, skipList.size());
        skipList.insert(new KVPair<>(5, "Five"));
        skipList.dump();
        assertEquals(1, skipList.size());
    }
}
