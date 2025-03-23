/**
 * Class to hold the result of a remove operation
 * 
 * @author Rushil, Kush
 * @version 1.0
 */
public class RemoveResult {

    private QuadNode node;
    private Point removedPoint;

    /**
     * Constructor for a remove result
     * 
     * @param node
     *            the updated node after removal
     * @param removedPoint
     *            the point that was removed (null if none)
     */
    public RemoveResult(QuadNode node, Point removedPoint) {
        this.node = node;
        this.removedPoint = removedPoint;
    }


    /**
     * Get the updated node
     * 
     * @return the updated node
     */
    public QuadNode getNode() {
        return node;
    }


    /**
     * Get the removed point
     * 
     * @return the removed point (null if none)
     */
    public Point getRemovedPoint() {
        return removedPoint;
    }

}
