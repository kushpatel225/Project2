import java.util.ArrayList;

public class LeafNode implements QuadNode {
    private ArrayList<Point> points;

    public LeafNode() {
        this.points = new ArrayList<>();
    }


    public ArrayList<Point> getPoints() {
        return points;
    }


    public void insert(Point point, int xMin, int yMin, int xMax, int yMax) {
        points.add(point);
        if (points.size() > 3) {
            split(xMin, yMin, xMax, yMax);
        }
    }


    private void split(int xMin, int yMin, int xMax, int yMax) {
        InternalNode parent = new InternalNode();

        for (Point pt : points) {
            int midX = (xMin + xMax) / 2;
            int midY = (yMin + yMax) / 2;

            if (pt.getXCoor() < midX && pt.getYCoor() < midY) {
                parent.setNorthwest(new LeafNode());
                ((LeafNode)parent.getNorthwest()).insert(pt, xMin, yMin, xMax,
                    yMax);
            }
            else if (pt.getXCoor() >= midX && pt.getYCoor() < midY) {
                parent.setNortheast(new LeafNode());
                ((LeafNode)parent.getNortheast()).insert(pt, xMin, yMin, xMax,
                    yMax);
            }
            else if (pt.getXCoor() < midX && pt.getYCoor() >= midY) {
                parent.setSouthwest(new LeafNode());
                ((LeafNode)parent.getSouthwest()).insert(pt, xMin, yMin, xMax,
                    yMax);
            }
            else {
                parent.setSoutheast(new LeafNode());
                ((LeafNode)parent.getSoutheast()).insert(pt, xMin, yMin, xMax,
                    yMax);
            }
        }
    }


    public boolean remove(String name) {
        for (int i = 0; i < points.size(); i++) {
            if (points.get(i).getName().equals(name)) {
                points.remove(i);
                return true;
            }
        }
        return false;
    }


    public boolean isLeaf() {
        return true;
    }


    public boolean isEmpty() {
        return points.isEmpty();
    }
}
