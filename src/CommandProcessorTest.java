import java.io.File;
import java.io.FileNotFoundException;
import student.TestCase;

/**
 * The test class for CommandProcessor
 * 
 * @author Rushil, Kush
 * @version 1.0
 */
public class CommandProcessorTest extends TestCase {
    private CommandProcessor processor;
    private Database db;

    /**
     * Sets up the variables for use in this class
     */
    public void setUp() {
        processor = new CommandProcessor();
        db = processor.getData();
    }


    /**
     * Tests the constructors
     */
    public void testExistingDB() {
        Database db2 = new Database();
        processor = new CommandProcessor(db2);
        assertEquals(0, db2.getSkipList().size());
        Database nullDB = null;
        processor = new CommandProcessor(nullDB);
    }


    /**
     * Tests the file read method
     * 
     * @throws FileNotFoundException
     */
    public void testFileRead() throws FileNotFoundException {
        File inputFile = new File("SyntaxTest1.txt");
        processor.readCmdFile(inputFile);
    }


    /**
     * Tests the insert method
     */
    public void testInsert() {
        processor.processCommand("insert apple 200 300");
        assertEquals(1, db.getSkipList().size());

        processor.processCommand("insert apple -10 -100");
        assertEquals(1, db.getSkipList().size());
    }


    /**
     * Tests the insert method for invalid entries
     */
    public void testInvalidInsert() {
        processor.processCommand("insert apple 100");
        assertEquals(0, db.getSkipList().size());
    }


    /**
     * Tests the remove by name method
     */
    public void testRemoveName() {
        processor.processCommand("insert apple 200 300");
        processor.processCommand("remove apple");
        assertEquals(0, db.getSkipList().size());

        processor.processCommand("remove banana");
        assertEquals(0, db.getSkipList().size());
    }


    /**
     * Tests the remove with invalid entries
     */
    public void testInvalidRemoveName() {
        processor.processCommand("remove apple 500 500");
        assertEquals(0, db.getSkipList().size());
    }


    /**
     * Tests the remove by coordinates
     */
    public void testRemoveCoordinates() {
        processor.processCommand("insert apple 200 300");
        processor.processCommand("remove 200 300");
        assertEquals(0, db.getSkipList().size());

        processor.processCommand("remove 300 200");
        assertEquals(0, db.getSkipList().size());
    }


    /**
     * Tests the remove with invalid entries
     */
    public void testInvalidRemoveCoor() {
        processor.processCommand("remove 200 400 500");
        assertEquals(0, db.getSkipList().size());
    }


    /**
     * Tests the regionSearch method
     */
    public void testRegionSearch() {
        processor.processCommand("insert apple 200 300");
        processor.processCommand("insert banana 200 400");
        processor.processCommand("insert orange 500 500");
        processor.processCommand("regionsearch 0 0 250 450");
        assertEquals(1, db.getSkipList().search("apple").size());
    }


    /**
     * Tests regionSearch with invalid entries
     */
    public void testInvalidRegionSearch() {
        processor.processCommand("regionsearch 0 0 0 0 0 0");
        assertEquals(0, db.getSkipList().size());
    }


    /**
     * Tests the duplicates method
     */
    public void testDuplicates() {
        processor.processCommand("duplicates");
        processor.processCommand("insert apple 200 300");
        processor.processCommand("insert banana 200 300");
        processor.processCommand("insert orange 300 300");
        processor.processCommand("insert watermelon 300 300");
        processor.processCommand("duplicates");
        assertEquals(4, db.getSkipList().size());
    }


    /**
     * Tests the search method
     */
    public void testSearch() {
        processor.processCommand("insert apple 200 300");
        processor.processCommand("insert apple 400 300");
        processor.processCommand("insert banana 200 300");
        processor.processCommand("search apple");
        processor.processCommand("search orange");
        assertEquals(3, db.getSkipList().size());
    }


    /**
     * Tests the search with invalid entries
     */
    public void testInvalidSearch() {
        processor.processCommand("search apple apple");
        assertEquals(0, db.getSkipList().size());
    }


    /**
     * Tests the dump method
     */
    public void testDump() {
        processor.processCommand("dump");
        processor.processCommand("insert apple 200 300");
        processor.processCommand("insert apple 400 300");
        processor.processCommand("insert banana 200 300");
        processor.processCommand("dump");
        assertEquals(3, db.getSkipList().size());
    }


    /**
     * Tests for invalid commands
     */
    public void testInvalidCmd() {
        processor.processCommand("invalid command");
        assertEquals(0, db.getSkipList().size());
    }
}
