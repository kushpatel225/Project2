
import java.util.Arrays;

/**
 * A simplified ArrayList implementation that supports basic operations
 * needed for the SkipList.
 * 
 * @author Rushil, Kush
 * @version 1.0
 * @param <E>
 *            the type of elements in this list
 */
public class MyArrayList<E> {

    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    /**
     * Constructs an empty list with an initial capacity of 10.
     */
    public MyArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }


    /**
     * Constructs an empty list with the specified initial capacity.
     *
     * @param initialCapacity
     *            the initial capacity of the list
     * @throws IllegalArgumentException
     *             if the specified initial capacity is negative
     */
    public MyArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal Capacity: "
                + initialCapacity);
        }
        elements = new Object[initialCapacity];
        size = 0;
    }


    /**
     * Appends the specified element to the end of this list.
     *
     * @param e
     *            element to be appended to this list
     */
    public void add(E e) {
        ensureCapacity(size + 1);
        elements[size++] = e;
    }


    /**
     * Returns the element at the specified position in this list.
     *
     * @param index
     *            index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException
     *             if the index is out of range
     */
    @SuppressWarnings("unchecked")
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: "
                + size);
        }
        return (E)elements[index];
    }


    /**
     * Replaces the element at the specified position in this list with
     * the specified element.
     *
     * @param index
     *            index of the element to replace
     * @param element
     *            element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException
     *             if the index is out of range
     */
    @SuppressWarnings("unchecked")
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: "
                + size);
        }
        E oldValue = (E)elements[index];
        elements[index] = element;
        return oldValue;
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
     * Removes all of the elements from this list.
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }


    /**
     * Returns the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     *
     * @param o
     *            element to search for
     * @return the index of the first occurrence of the specified element in
     *         this list, or -1 if this list does not contain the element
     */
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {
                    return i;
                }
            }
        }
        else {
            for (int i = 0; i < size; i++) {
                if (o.equals(elements[i])) {
                    return i;
                }
            }
        }
        return -1;
    }
}
