import student.TestCase;

/**
 * The test class of point
 * 
 * @author Rushil, Kush
 * @version 1.0
 */
public class PointTest extends TestCase {
    private Point pt;
    private Point pt2;
    private Point nullPt;
    private Point diffPt;
    private Point diffX;
    private Point diffY;
    private Point samePt;

    /**
     * Sets up the variables for use in the class
     */
    public void setUp() {
        pt = new Point("Apple", 200, 300);
        pt2 = new Point("Apple", 200, 300);
        nullPt = null;
        diffPt = new Point("Banana", 300, 400);
        diffX = new Point("Apple", 300, 300);
        diffY = new Point("Apple", 200, 400);
        samePt = pt;
    }


    /**
     * Tests the getName method
     */
    public void testGetName() {
        assertEquals("Apple", pt.getName());
    }


    /**
     * Tests the getX method
     */
    public void testGetX() {
        assertEquals(200, pt.getX());
    }


    /**
     * Tests the getY method
     */
    public void testGetY() {
        assertEquals(300, pt.getY());
    }


    /**
     * Tests the toString method
     */
    public void testToString() {
        String expected = "(Apple, 200, 300)";
        assertEquals(expected, pt.toString());
    }


    /**
     * Tests the equals method
     */
    public void testEquals() {
        assertTrue(pt.equals(pt2));
        assertFalse(pt.equals(nullPt));
        assertFalse(pt.equals(diffPt));
        assertFalse(pt.equals(diffX));
        assertFalse(pt.equals(diffY));
        assertTrue(pt.equals(samePt));
    }
}
