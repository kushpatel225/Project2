
public class Database {
    public boolean insert(String name, int xCoor, int yCoor) {
        // Need to call both quadtree and skiplist
        return false;
    }


    public KVPair remove(String name) {
        // remove first from skiplist then find the point in quadtree to remove
        return null;
    }


    public Point remove(int xCoor, int yCoor) {
        // remove first from quadtree then using point name remove from skiplist
        return null;
    }


    public Point[] regionSearch(int xCoor, int yCoor, int width, int height) {
        // Call on quadtree
        return null;
    }


    public Point[] duplicates() {
        // Call on quadtree
        return null;
    }


    public KVPair search(String name) {
        // Call on skiplist
        return null;
    }


    public void dump() {
        // Call on skiplist and quadtree (print according to indentation based
        // on level of tree)

    }
}
