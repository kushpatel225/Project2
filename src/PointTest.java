import student.TestCase;

public class PointTest extends TestCase {
    private Point pt;
    private Point pt2;
    private Point nullPt;
    private Point diffPt;
    private Point diffX;
    private Point diffY;
    private Point samePt;

    public void setUp() {
        pt = new Point("Apple", 200, 300);
        pt2 = new Point("Apple", 200, 300);
        nullPt = null;
        diffPt = new Point("Banana", 300, 400);
        diffX = new Point("Apple", 300, 300);
        diffY = new Point("Apple", 200, 400);
        samePt = pt;
    }


    public void testGetName() {
        assertEquals("Apple", pt.getName());
    }


    public void testGetX() {
        assertEquals(200, pt.getX());
    }


    public void testGetY() {
        assertEquals(300, pt.getY());
    }


    public void testToString() {
        String expected = "(Apple, 200, 300)";
        assertEquals(expected, pt.toString());
    }


    public void testEquals() {
        assertTrue(pt.equals(pt2));
        assertFalse(pt.equals(nullPt));
        assertFalse(pt.equals(diffPt));
        assertFalse(pt.equals(diffX));
        assertFalse(pt.equals(diffY));
        assertTrue(pt.equals(samePt));
    }
}
