
public class Point {

    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public void setXCoor(int newX) {
        x = newX;
    }


    public void setYCoor(int newY) {
        y = newY;
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
}
