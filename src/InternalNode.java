
public class InternalNode implements QuadNode {

    private QuadNode NW;
    private QuadNode NE;
    private QuadNode SW;
    private QuadNode SE;

    public InternalNode(
        QuadNode NWNode,
        QuadNode NENode,
        QuadNode SWNode,
        QuadNode SENode) {
        NW = NWNode;
        NE = NENode;
        SW = SWNode;
        SE = SENode;

    }


    public QuadNode getNWchild() {
        return NW;
    }


    public QuadNode getNEchild() {
        return NE;
    }


    public QuadNode getSWchild() {
        return SW;
    }


    public QuadNode getSEchild() {
        return SE;
    }


    public void traverse(QuadNode node) {

    }
}
