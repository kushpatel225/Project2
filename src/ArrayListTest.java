import java.util.Iterator;
import java.util.NoSuchElementException;
import student.TestCase;

public class ArrayListTest extends TestCase {

    private ArrayList list;
    private Point pt;

    public void setUp() {
        list = new ArrayList();
        pt = new Point("Apple", 200, 500);
        list.add(pt);
    }


    public void testAdd() {
        assertEquals(1, list.size());
        Point pt2 = new Point("Apple", 200, 500);
        list.add(pt2);
        Point pt3 = new Point("Apple", 200, 500);
        list.add(pt3);
        Point pt4 = new Point("Apple", 200, 500);
        list.add(pt4);
        Point pt5 = new Point("Apple", 200, 500);
        list.add(pt5);
        Point pt6 = new Point("Apple", 200, 500);
        list.add(pt6);
        Point pt7 = new Point("Apple", 200, 500);
        list.add(pt7);
        Point pt8 = new Point("Apple", 200, 500);
        list.add(pt8);
        Point pt9 = new Point("Apple", 200, 500);
        list.add(pt9);
        Point pt10 = new Point("Apple", 200, 500);
        list.add(pt10);
        Point pt11 = new Point("Apple", 200, 500);
        list.add(pt11);
        assertEquals(11, list.size());

    }


    public void testAddAll() {
        ArrayList nullList = null;
        list.addAll(nullList);
        assertEquals(1, list.size());
        ArrayList otherList = new ArrayList();
        list.addAll(otherList);
        assertEquals(1, list.size());
        Point pt2 = new Point("Apple", 200, 500);
        otherList.add(pt2);
        Point pt3 = new Point("Apple", 200, 500);
        otherList.add(pt3);
        Point pt4 = new Point("Apple", 200, 500);
        otherList.add(pt4);
        Point pt5 = new Point("Apple", 200, 500);
        otherList.add(pt5);
        Point pt6 = new Point("Apple", 200, 500);
        otherList.add(pt6);

        list.addAll(otherList);
        assertEquals(6, list.size());
    }


    public void testRemove() {
        list.remove(pt);
        assertEquals(0, list.size());
        Point pt2 = new Point("Apple", 300, 500);
        list.remove(pt2);
        assertEquals(0, list.size());
        list.add(pt2);
        Point pt3 = new Point("Apple", 400, 500);
        list.add(pt3);
        Point pt4 = new Point("Apple", 500, 500);
        list.add(pt4);
        Point pt5 = new Point("Apple", 600, 500);
        list.add(pt5);
        list.remove(pt3);
        assertEquals(3, list.size());
        Point pt6 = new Point("Apple", 700, 500);
        list.remove(pt6);
        assertEquals(3, list.size());
    }


    public void testGet() {
        assertTrue(list.get(0).equals(pt));
        try {
            list.get(-1);
        }
        catch (Exception error) {
            assertTrue(error instanceof IndexOutOfBoundsException);
        }
        try {
            list.get(100);
        }
        catch (Exception e) {
            assertTrue(e instanceof IndexOutOfBoundsException);
        }
    }


    public void testIsEmpty() {
        assertFalse(list.isEmpty());
        list.remove(pt);
        assertTrue(list.isEmpty());
    }


    public void testToString() {
        String expected = "(Apple, 200, 500)";
        assertEquals(expected, list.toString());
        Point pt2 = new Point("Apple", 300, 500);
        list.add(pt2);
        Point pt3 = new Point("Apple", 400, 500);
        list.add(pt3);
        String expected2 =
            "(Apple, 200, 500), (Apple, 300, 500), (Apple, 400, 500)";
        assertEquals(expected2, list.toString());
    }


    public void testIterator() {
        Iterator iterator = list.iterator();
        assertTrue(iterator.hasNext());
        Point pt2 = new Point("Apple", 200, 500);
        list.add(pt2);
        Point pt3 = new Point("Apple", 200, 500);
        list.add(pt3);
        assertEquals(pt2, iterator.next());
        iterator.next();
        iterator.next();
        try {
            iterator.next();
        }
        catch (Exception error) {
            assertTrue(error instanceof NoSuchElementException);
        }

    }
}
