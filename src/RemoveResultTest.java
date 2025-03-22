import student.TestCase;

public class RemoveResultTest extends TestCase {
    private RemoveResult result;
    private RemoveResult result2;
    private RemoveResult result3;
    private QuadNode leafNode;
    private QuadNode internalNode;
    private QuadNode emptyNode;
    private Point pt;

    public void setUp() {
        leafNode = new LeafNode();
        internalNode = new InternalNode();
        emptyNode = EmptyNode.getInstance();
        pt = new Point("Apple", 200, 400);
        result = new RemoveResult(leafNode, pt);
        result2 = new RemoveResult(internalNode, pt);
        result3 = new RemoveResult(emptyNode, pt);
    }


    public void testGetNode() {
        assertEquals(leafNode, result.getNode());
        assertEquals(internalNode, result2.getNode());
        assertEquals(emptyNode, result3.getNode());
    }


    public void testGetRemovedPoint() {
        assertEquals(pt, result.getRemovedPoint());
        assertEquals(pt, result2.getRemovedPoint());
        assertEquals(pt, result3.getRemovedPoint());
    }
}
