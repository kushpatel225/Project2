
public interface QuadNode {
    public boolean isLeaf();


    public void insert(Point point, int xMin, int yMin, int xMax, int yMax);


    public boolean remove();


    public void dump();


    public Point search();


    public Point regionSearch();


    public Point duplicates();

}
