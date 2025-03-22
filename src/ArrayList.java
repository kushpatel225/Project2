import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A simplified ArrayList implementation that supports basic operations
 * needed for the SkipList.
 * 
 * @author Rushil, Kush
 * @version 1.0
 * @param <E>
 *            the type of elements in this list
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
     * @param e
     *            element to be appended to this list
     */
    public void add(Point pt) {
        ensureCapacity(size + 1);
        elements[size++] = pt;
    }


    public void addAll(ArrayList otherList) {
        if (otherList == null || otherList.size == 0) {
            return;
        }
        for (int i = 0; i < otherList.size(); i++) {
            this.add(otherList.get(i));
        }
    }


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


    private int search(Point pt) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(pt)) {
                return i;
            }
        }
        return -1;
    }


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


    public Iterator iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }


        @Override
        public Point next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return elements[currentIndex++];
        }


        @Override
        public void remove() {
            if (currentIndex == 0 || currentIndex > size) {
                throw new IllegalStateException();
            }
            ArrayList.this.remove(elements[currentIndex - 1]);
            currentIndex--;
        }
    }
}
