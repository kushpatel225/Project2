public class Point {
    private String name;
    private int x, y;

    public Point(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }


    public String getName() {
        return name;
    }


    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }


    public String toString() {
        return "(" + name + ", " + x + ", " + y + ")";
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
}
