
public class InternalNode implements QuadNode {

    private QuadNode NW;
    private QuadNode NE;
    private QuadNode SW;
    private QuadNode SE;

    public InternalNode() {
        this.NW = EmptyNode.getInstance();
        this.NE = EmptyNode.getInstance();
        this.SW = EmptyNode.getInstance();
        this.SE = EmptyNode.getInstance();
    }


    public QuadNode getNorthwest() {
        return NW;
    }


    public QuadNode getNortheast() {
        return NE;
    }


    public QuadNode getSouthwest() {
        return SW;
    }


    public QuadNode getSoutheast() {
        return SE;
    }


    public void setNorthwest(QuadNode nw) {
        this.NW = nw;
    }


    public void setNortheast(QuadNode ne) {
        this.NE = ne;
    }


    public void setSouthwest(QuadNode sw) {
        this.SW = sw;
    }


    public void setSoutheast(QuadNode se) {
        this.SE = se;
    }


    public void insert(Point point, int xMin, int yMin, int xMax, int yMax) {
        int midX = (xMin + xMax) / 2;
        int midY = (yMin + yMax) / 2;

        if (point.getXCoor() < midX && point.getYCoor() < midY) {
            NW.insert(point, xMin, yMin, xMax, yMax);
        }
        else if (point.getXCoor() >= midX && point.getYCoor() < midY) {
            NE.insert(point, xMin, yMin, xMax, yMax);
        }
        else if (point.getXCoor() < midX && point.getYCoor() >= midY) {
            SW.insert(point, xMin, yMin, xMax, yMax);
        }
        else {
            SE.insert(point, xMin, yMin, xMax, yMax);
        }

        checkSplit(xMin, yMin, xMax, yMax);
    }


    private void checkSplit(int xMin, int yMin, int xMax, int yMax) {
        if (NW.isLeaf() && ((LeafNode)NW).getPoints().size() > 3) {
            split(xMin, yMin, xMax, yMax);
        }
    }
}
