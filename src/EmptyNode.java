
public class EmptyNode implements QuadNode {

    private static EmptyNode flyweight = null;

    private EmptyNode() {

    }


    public static EmptyNode getInstance() {
        if (flyweight == null) {
            flyweight = new EmptyNode();
        }
        return flyweight;
    }
}
