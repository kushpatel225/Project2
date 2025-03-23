import student.TestCase;

/**
 * The test class for KVPair
 * 
 * @author Rushil, Kush
 * @version 1.0
 */
public class KVPairTest extends TestCase {

    private KVPair<String, Integer> stringIntPair;
    private KVPair<String, Integer> samePair;
    private KVPair<String, Integer> diffPair;
    private String sameString;
    private String diffString;

    /**
     * Sets up variables for use in class
     */
    public void setUp() {
        stringIntPair = new KVPair<String, Integer>("Key1", 100);
        samePair = new KVPair<String, Integer>("Key1", 100);
        diffPair = new KVPair<String, Integer>("Key2", 100);
        sameString = "Key1";
        diffString = "Key2";
    }


    /**
     * Tests the compareTo method
     */
    public void testCompareTo() {
        assertEquals(0, stringIntPair.compareTo(samePair));
        assertEquals(-1, stringIntPair.compareTo(diffPair));
    }


    /**
     * Tests the compareToKey method
     */
    public void testCompareToKey() {
        assertEquals(0, stringIntPair.compareTo(sameString));
        assertEquals(-1, stringIntPair.compareTo(diffString));
    }


    /**
     * Tests the key method
     */
    public void testKey() {
        assertEquals("Key1", stringIntPair.key());
    }


    /**
     * Tests the value method
     */
    public void testValue() {
        assertEquals(100, stringIntPair.value(), 0.01);
    }


    /**
     * Tests the toString method
     */
    public void testToString() {
        assertEquals("100", stringIntPair.toString());
    }


    /**
     * Tests the getKey method
     */
    public void testGetKey() {
        assertEquals("Key1", stringIntPair.getKey());
    }


    /**
     * Tests the getValue method
     */
    public void testGetValue() {
        assertEquals(100, stringIntPair.getValue(), 0.01);
    }
}
