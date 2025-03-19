
public class Point {
    private String name;
    private int x;
    private int y;

    public Point(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }


    public String getName() {
        return name;
    }


    public int getXCoor() {
        return x;
    }


    public int getYCoor() {
        return y;
    }


    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (this.getClass() == obj.getClass()) {
            Point otherPoint = (Point)obj;
            if (this.x == otherPoint.x) {
                if (this.y == otherPoint.y) {
                    return true;
                }
            }
        }
        return false;
    }


    public String toString() {
        return "(" + name + ", " + x + ", " + y + ")";
    }
}
