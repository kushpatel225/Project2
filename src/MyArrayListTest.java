import student.TestCase;

/**
 * This class tests the KVPair class so that the member methods work properly
 * and that the expected behavior occurs.
 * 
 * @author Rushil, Kush
 * @version 1.0
 */
public class MyArrayListTest extends TestCase {

    private MyArrayList<Integer> list;

    /**
     * Sets up test objects before each test runs.
     */
    public void setUp() {
        list = new MyArrayList<Integer>();
    }


    /**
     * Tests the constructor
     */
    public void testMyArrayList() {
        MyArrayList<Integer> newList = new MyArrayList<Integer>(10);
        assertEquals(0, newList.size());
        try {
            @SuppressWarnings("unused")
            MyArrayList<Integer> badList = new MyArrayList<Integer>(-1);
        }
        catch (Exception error) {
            assertTrue(error instanceof IllegalArgumentException);
        }
    }


    /**
     * Tests the add method
     */
    public void testAdd() {
        list.add(2);
        assertEquals(1, list.size());
    }


    /**
     * Tests the get method
     */
    public void testGet() {
        try {
            list.get(0);
        }
        catch (Exception e) {
            assertTrue(e instanceof IndexOutOfBoundsException);
        }
        try {
            list.get(-2);
        }
        catch (Exception e) {
            assertTrue(e instanceof IndexOutOfBoundsException);
        }
        list.add(2);
        assertEquals(2, list.get(0), 0.1);
    }


    /**
     * Tests the set method
     */
    public void testSet() {
        try {
            list.set(0, 3);
        }
        catch (Exception e) {
            assertTrue(e instanceof IndexOutOfBoundsException);
        }
        try {
            list.set(-2, 3);
        }
        catch (Exception e) {
            assertTrue(e instanceof IndexOutOfBoundsException);
        }
        list.add(2);
        list.set(0, 3);
        assertEquals(3, list.get(0), 0.1);

    }


    /**
     * Tests the size method
     */
    public void testSize() {
        assertEquals(0, list.size(), 0.1);
        list.add(2);
        assertEquals(1, list.size(), 0.1);
    }


    /**
     * Tests the isEmpty method
     */
    public void testIsEmpty() {
        assertTrue(list.isEmpty());
        list.add(2);
        assertFalse(list.isEmpty());
    }


    /**
     * Tests the ensureCapacity method
     */
    public void testEnsureCapacity() {
        list.add(2);
        list.add(2);
        list.add(2);
        list.add(2);
        list.add(2);
        list.add(2);
        list.add(2);
        list.add(2);
        list.add(2);
        list.add(2);
        list.add(2);
        assertEquals(11, list.size(), 0.1);
    }


    /**
     * Tests the clear method
     */
    public void testClear() {
        list.add(2);
        list.add(3);
        list.clear();
        assertEquals(0, list.size());
    }


    /**
     * Tests the index method
     */
    public void testIndexOf() {
        list.add(2);
        list.add(3);
        assertEquals(-1, list.indexOf(null));
        list.add(null);
        assertEquals(0, list.indexOf(2));
        assertEquals(-1, list.indexOf(5));
        assertEquals(2, list.indexOf(null));
    }
}
