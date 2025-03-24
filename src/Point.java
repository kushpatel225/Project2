/**
 * The point class
 * 
 * @author Rushil, Kush
 * @version 1.0
 */
public class Point {
    private String name;
    private int x;
    private int y;

    /**
     * The constructor of point
     * 
     * @param name
     *            name of point
     * @param x
     *            x coordinate of point
     * @param y
     *            y coordinate of point
     */
    public Point(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }


    /**
     * Returns name of point
     * 
     * @return name of point
     */
    public String getName() {
        return name;
    }


    /**
     * Gets the x value
     * 
     * @return x value
     */
    public int getX() {
        return x;
    }


    /**
     * Gets the y value
     * 
     * @return y value
     */
    public int getY() {
        return y;
    }


    /**
     * Converts the point into a string
     * 
     * @return the string equivalent of the object
     */
    public String toString() {
        return "(" + name + ", " + x + ", " + y + ")";
    }


    /**
     * Checks if two objects are equal
     * 
     * @param obj
     *            the object to compare
     * @return if the objects are equals
     */
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
