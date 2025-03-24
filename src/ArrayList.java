import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A simplified ArrayList implementation that supports basic operations
 * needed for the SkipList.
 * 
 * @author Rushil, Kush
 * @version 1.0
 */
public class ArrayList {

    private static final int DEFAULT_CAPACITY = 10;
    private Point[] elements;
    private int size;

    /**
     * Constructs an empty list with an initial capacity of 10.
     */
    public ArrayList() {
        elements = new Point[DEFAULT_CAPACITY];
        size = 0;
    }


    /**
     * Appends the specified element to the end of this list.
     * 
     * @param pt
     *            the point to be added
     *
     */
    public void add(Point pt) {
        ensureCapacity(size + 1);
        elements[size++] = pt;
    }


    /**
     * Appends all elements of another list to this list
     * 
     * @param otherList
     *            list of elements being added
     */
    public void addAll(ArrayList otherList) {
        if (otherList == null || otherList.size == 0) {
            return;
        }
        for (int i = 0; i < otherList.size(); i++) {
            this.add(otherList.get(i));
        }
    }


    /**
     * Removes a specific point from the list
     * 
     * @param pt
     *            the point to remove
     * @return the removed point
     */
    public Point remove(Point pt) {
        int index = search(pt);
        if (index == -1) {
            return null;
        }
        Point removedPt = get(index);
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[size - 1] = null;
        size--;
        return removedPt;
    }


    /**
     * Searches for a specific point
     * 
     * @param pt
     *            the point to search for
     * @return index of point or -1 if not found
     */
    private int search(Point pt) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(pt)) {
                return i;
            }
        }
        return -1;
    }


    /**
     * Returns the point at a specific index
     * 
     * @param index
     *            the index to search at
     * @return the point at index or throws exception
     */
    public Point get(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: "
                + size);
        }
        return elements[index];
    }


    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    public int size() {
        return size;
    }


    /**
     * Returns true if this list contains no elements.
     *
     * @return true if this list contains no elements
     */
    public boolean isEmpty() {
        return size == 0;
    }


    /**
     * Increases the capacity of this ArrayList instance, if necessary,
     * to ensure that it can hold at least the number of elements
     * specified by the minimum capacity argument.
     *
     * @param minCapacity
     *            the desired minimum capacity
     */
    private void ensureCapacity(int minCapacity) {
        if (minCapacity > elements.length) {
            int newCapacity = Math.max(elements.length * 2, minCapacity);
            elements = Arrays.copyOf(elements, newCapacity);
        }
    }


    /**
     * Outputs the values in the list
     * 
     * @return to string of values
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            builder.append(elements[i].toString());
            if (i < size - 1) {
                builder.append(", ");
            }
        }
        return builder.toString();
    }


    /**
     * Creates the iterator object
     * 
     * @return the iterator object
     */
    public Iterator<Object> iterator() {
        return new ArrayListIterator();
    }

    /**
     * Class for the iterator
     */
    private class ArrayListIterator implements Iterator<Object> {
        private int currentIndex = 0;

        /**
         * Checks if iterator has a next elements
         */
        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }


        /**
         * Returns the next elements in the list
         */
        @Override
        public Point next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return elements[currentIndex++];
        }

    }
}
