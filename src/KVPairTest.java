import student.TestCase;

public class KVPairTest extends TestCase {

    private KVPair<String, Integer> stringIntPair;
    private KVPair<String, Integer> samePair;
    private KVPair<String, Integer> diffPair;
    private String sameString;
    private String diffString;

    public void setUp() {
        stringIntPair = new KVPair<String, Integer>("Key1", 100);
        samePair = new KVPair<String, Integer>("Key1", 100);
        diffPair = new KVPair<String, Integer>("Key2", 100);
        sameString = "Key1";
        diffString = "Key2";
    }


    public void testCompareTo() {
        assertEquals(0, stringIntPair.compareTo(samePair));
        assertEquals(-1, stringIntPair.compareTo(diffPair));
    }


    public void testCompareToKey() {
        assertEquals(0, stringIntPair.compareTo(sameString));
        assertEquals(-1, stringIntPair.compareTo(diffString));
    }


    public void testKey() {
        assertEquals("Key1", stringIntPair.key());
    }


    public void testValue() {
        assertEquals(100, stringIntPair.value(), 0.01);
    }


    public void testToString() {
        assertEquals("100", stringIntPair.toString());
    }


    public void testGetKey() {
        assertEquals("Key1", stringIntPair.getKey());
    }


    public void testGetValue() {
        assertEquals(100, stringIntPair.getValue(), 0.01);
    }
}
