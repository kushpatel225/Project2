import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.Random;
import student.TestableRandom;

/**
 * This class implements SkipList data structure and contains an inner SkipNode
 * class which the SkipList will make an array of to store data.
 * 
 * @author Rushil, Kush
 * 
 * @version 1.0
 * @param <K>
 *            Key
 * @param <V>
 *            Value
 */
public class SkipList<K extends Comparable<K>, V>
    implements Iterable<KVPair<K, V>> {

    private SkipNode head; // First element (Sentinel Node)

    private int size; // number of entries in the Skip List
    private Random rng; // Random number generator for determining node levels

    /**
     * Constructs an empty SkipList.
     */
    public SkipList() {
        head = new SkipNode(null, 1); // Initialize with level 1 instead of 0
        size = 0;
        this.rng = new TestableRandom();
    }


    /**
     * Generates a random level for a new node.
     * 
     * @return The randomly generated level.
     */
    public int randomLevel() {
        int level = 1; // Start at level 1
        while (rng.nextBoolean())
            level++;
        return level;
    }


    /**
     * Returns First element (Sentinel Node)
     * 
     * @return head SkipNode
     */
    public SkipNode getHead() {
        return head;
    }


    /**
     * Searches for a key in the SkipList and returns all associated values.
     * 
     * @param key
     *            The key to search for.
     * @return A list of key-value pairs with the specified key.
     * @throws IllegalArgumentException
     *             if key is null.
     */
    public MyArrayList<KVPair<K, V>> search(K key) {
        MyArrayList<KVPair<K, V>> result = new MyArrayList<>();
        SkipNode current = head;

        // Start from the highest level and move down to level 1
        for (int i = head.level; i >= 1; i--) {
            while (current.forward[i] != null && current.forward[i]
                .element() != null && current.forward[i].element().getKey()
                    .compareTo(key) < 0) {
                current = current.forward[i];
            }
        }

        // Move to the first node of level 1
        current = current.forward[1];

        while (current != null && current.element() != null && current.element()
            .getKey().compareTo(key) == 0) {
            result.add(current.element());
            current = current.forward[1];
        }

        return result;
    }


    /**
     * Returns the number of elements in the SkipList.
     * 
     * @return The size of the SkipList.
     */
    public int size() {
        return size;
    }


    /**
     * Inserts the KVPair in the SkipList at its appropriate spot as designated
     * by its lexicoragraphical order.
     * 
     * @param it
     *            The key-value pair to insert.
     ** @throws IllegalArgumentException
     *             if KV Pair is null or Key of the KV Pair is null or empty.
     */
    @SuppressWarnings("unchecked")
    public void insert(KVPair<K, V> it) {
        if (it == null) {
            throw new IllegalArgumentException("KV pair is null");
        }

        int newLevel = randomLevel();

        if (newLevel > head.level) {
            adjustHead(newLevel);
        }

        SkipNode[] update = (SkipNode[])Array.newInstance(
            SkipList.SkipNode.class, head.level + 1);
        SkipNode current = head;

        for (int i = head.level; i >= 1; i--) {
            while (current.forward[i] != null && current.forward[i]
                .element() != null && current.forward[i].element().getKey()
                    .compareTo(it.getKey()) < 0) {
                current = current.forward[i];
            }
            update[i] = current;
        }

        SkipNode newNode = new SkipNode(it, newLevel);
        newNode.pair = it;

        // Insert the node at each level, starting from 1
        for (int i = 1; i <= newLevel; i++) {
            newNode.forward[i] = update[i].forward[i];
            update[i].forward[i] = newNode;
        }

        size++;
        // System.out.println("Point inserted: " + newNode.pair.toString());
    }


    /**
     * Increases the number of levels in head so that no element has more
     * indices than the head.
     * 
     * @param newLevel
     *            The new maximum level.
     */
    public void adjustHead(int newLevel) {
        SkipNode newHead = new SkipNode(null, newLevel);
        // Copy existing forward references, starting from level 1
        for (int i = 1; i <= head.level; i++) {
            newHead.forward[i] = head.forward[i];
        }
        // Set remaining forward references to null
        for (int i = head.level + 1; i <= newLevel; i++) {
            newHead.forward[i] = null;
        }
        head = newHead;
    }


    /**
     * Removes the first occurrence of a key from the SkipList.
     * 
     * @param key
     *            The key to remove.
     * @return The removed key-value pair, or null if not found.
     */
    @SuppressWarnings("unchecked")
    public KVPair<K, V> remove(K key) {

        SkipNode[] update = (SkipNode[])Array.newInstance(SkipNode.class,
            head.level + 1);
        SkipNode current = head;

        for (int i = head.level; i >= 1; i--) {
            while (current.forward[i] != null && current.forward[i]
                .element() != null && current.forward[i].element().getKey()
                    .compareTo(key) < 0) {
                current = current.forward[i];
            }
            update[i] = current;
        }

        current = current.forward[1];

        if (current != null && current.element() != null && current.element()
            .getKey().compareTo(key) == 0) {
            for (int i = 1; i <= head.level; i++) {
                // Only update if this is the node we're removing
                if (update[i].forward[i] == current) {
                    update[i].forward[i] = current.forward[i];
                }
            }

            size--;
            return current.element();
        }

        return null;
    }


    /**
     * Removes a KVPair with the specified value.
     * 
     * @param val
     *            the value of the KVPair to be removed
     * @return returns the removed KVPair, or null if not found
     */
    public KVPair<K, V> removeByValue(V val) {

        // Find the first node with matching value
        SkipNode current = head;

        while (current.forward[1] != null) {
            if (current.forward[1].pair.getValue().equals(val)) {
                // Get the key to remove
                K keyToRemove = current.forward[1].element().getKey();
                // Use the regular remove method to handle the removal
                return remove(keyToRemove);
            }
            current = current.forward[1];
        }

        return null;
    }


    /**
     * Prints out the SkipList in a human readable format to the console.
     */
    public void dump() {
        System.out.println("SkipList dump:");
        if (size == 0) {
            System.out.println("Node has depth 1, Value (null)");
            System.out.println("SkipList size is: 0");
            return;
        }

        // Print the highest level null node if it exists
        System.out.println("Node has depth " + head.level + ", value null");

        // Collect all nodes and their maximum depths
        MyArrayList<SkipNode> allNodes = new MyArrayList<>();
        int[] nodeDepths = new int[size];

        for (int i = 1; i <= head.level; i++) {
            SkipNode current = head.forward[i];
            while (current != null) {
                if (current.pair != null) {
                    boolean found = false;
                    for (int j = 0; j < allNodes.size(); j++) {
                        if (allNodes.get(j) == current) {
                            found = true;
                            nodeDepths[j] = Math.max(nodeDepths[j], i);
                            break;
                        }
                    }
                    if (!found) {
                        allNodes.add(current);
                        nodeDepths[allNodes.size() - 1] = i;
                    }
                }
                current = current.forward[i];
            }
        }

// // Sort nodes by key
// for (int i = 0; i < allNodes.size() - 1; i++) {
// for (int j = i + 1; j < allNodes.size(); j++) {
// if (allNodes.get(i).element().getKey().compareTo(allNodes.get(j)
// .element().getKey()) > 0) {
// // Swap nodes
// SkipNode tempNode = allNodes.get(i);
// allNodes.set(i, allNodes.get(j));
// allNodes.set(j, tempNode);
// // Swap depths
// int tempDepth = nodeDepths[i];
// nodeDepths[i] = nodeDepths[j];
// nodeDepths[j] = tempDepth;
// }
// }
// }

        // Print nodes in lexicographical order
        for (int i = 0; i < allNodes.size(); i++) {
            System.out.println("Node has depth " + nodeDepths[i] + ", value "
                + allNodes.get(i).element().toString());
        }

        System.out.println("SkipList size is: " + size);
    }

    /**
     * This class implements a SkipNode for the SkipList data structure.
     * 
     * @author CS Staff
     * 
     * @version 2016-01-30
     */
    class SkipNode {
        // the KVPair to hold
        private KVPair<K, V> pair;
        // An array of pointers to subsequent nodes
        private SkipNode[] forward;
        // the level of the node
        private int level;

        /**
         * Initializes the fields with the required KVPair and the number of
         * levels from the random level method in the SkipList.
         * 
         * @param tempPair
         *            the KVPair to be inserted
         * @param level
         *            the number of levels that the SkipNode should have
         */
        @SuppressWarnings("unchecked")
        public SkipNode(KVPair<K, V> tempPair, int level) {
            pair = tempPair;
            // Add 1 to array size since we're starting at level 1
            forward = (SkipNode[])Array.newInstance(SkipList.SkipNode.class,
                level + 1);
            this.level = level;

            for (int i = 1; i <= level; i++) {
                this.forward[i] = null;
            }
        }


        /**
         * this return the level of the Skipnode
         * 
         * @return level
         */
        public int getNodeLevel() {
            return level;
        }


        /**
         * Returns the KVPair stored in the SkipList.
         * 
         * @return the KVPair
         */
        public KVPair<K, V> element() {
            return pair;
        }
    }


    /**
     * Iterator class for traversing the SkipList.
     */
    private class SkipListIterator implements Iterator<KVPair<K, V>> {
        private SkipNode current;

        public SkipListIterator() {
            current = head;
        }


        @Override
        public boolean hasNext() {
            return current.forward[1] != null;
        }


        @Override
        public KVPair<K, V> next() {
            KVPair<K, V> elem = current.forward[1].element();
            current = current.forward[1];
            return elem;
        }
    }

    /**
     * Returns an iterator over elements of the SkipList.
     * 
     * @return An iterator over key-value pairs.
     */
    @Override
    public Iterator<KVPair<K, V>> iterator() {
        return new SkipListIterator();
    }
}
