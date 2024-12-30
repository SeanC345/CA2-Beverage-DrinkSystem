package data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

import utils.CustomLinkedList;

/**
 * Custom implementation of a hash table with double hashing for collision resolution.
 * Uses a custom linked list as the underlying data structure for the buckets.
 */
public class CustomHashTable<K, V> implements Iterable<CustomHashTable.Node<K, V>>, Serializable {
    private static final long serialVersionUID = 1L;

    // Node to hold key-value pairs
    public static class Node<K, V> implements Serializable {
        K key;
        V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    private CustomLinkedList<Node<K, V>> table; // Linked list for storing hash table nodes
    private int size; // Number of key-value pairs in the hash table
    private int capacity; // Number of buckets in the hash table

    /**
     * Constructor to initialize the hash table with a given capacity.
     *
     * @param initialCapacity the number of buckets in the hash table
     */
    public CustomHashTable(int initialCapacity) {
        this.capacity = initialCapacity;
        this.size = 0;
        this.table = new CustomLinkedList<>();
        initializeBuckets(); // Create empty buckets
    }

    /**
     * Initializes the buckets in the hash table.
     */
    private void initializeBuckets() {
        for (int i = 0; i < capacity; i++) {
            table.add(null); // Add `capacity` null nodes
        }
    }

    /**
     * Resets the hash table by clearing all buckets and resetting the size.
     */
    public void reset() {
        table.clear(); // Clear all buckets
        size = 0; // Reset size
        initializeBuckets(); // Reinitialize buckets
    }

    /**
     * Primary hash function to calculate the bucket index.
     *
     * @param key the key to hash
     * @return the primary bucket index
     */
    private int primaryHash(K key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    /**
     * Secondary hash function to calculate the step size for double hashing.
     *
     * @param key the key to hash
     * @return the step size for probing
     */
    private int secondaryHash(K key) {
        return 1 + (Math.abs(key.hashCode()) % (capacity - 1));
    }

    /**
     * Finds the bucket index for a given key using double hashing.
     *
     * @param key the key to search for
     * @return the bucket index, or -1 if the table is full
     */
    private int findBucketIndex(K key) {
        int primaryIndex = primaryHash(key); // Compute primary index
        int stepSize = secondaryHash(key); // Compute step size

        int index = primaryIndex;
        int attempts = 0;

        while (attempts < capacity) {
            if (index < 0 || index >= table.size()) {
                throw new IndexOutOfBoundsException("Bucket index out of bounds: " + index);
            }

            Node<K, V> node = table.get(index);

            // Return the index if the bucket is empty or the key matches
            if (node == null || node.key.equals(key)) {
                return index;
            }

            // Move to the next bucket using the step size
            index = (index + stepSize) % capacity;
            attempts++;
        }

        return -1; // Table is full
    }

    /**
     * Adds a key-value pair to the hash table. Updates the value if the key already exists.
     *
     * @param key   the key to add
     * @param value the value to associate with the key
     * @throws IllegalArgumentException if the key is null
     * @throws IllegalStateException    if the table is full
     */
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }

        int index = findBucketIndex(key);

        if (index == -1) {
            throw new IllegalStateException("Hash table is full");
        }

        Node<K, V> node = table.get(index);
        if (node == null) {
            table.set(index, new Node<>(key, value)); // Add new node
            size++;
        } else {
            node.value = value; // Update existing value
        }
    }

    /**
     * Retrieves the value associated with a given key.
     *
     * @param key the key to search for
     * @return the associated value, or null if the key is not found
     */
    public V get(K key) {
        int index = findBucketIndex(key);
        Node<K, V> node = table.get(index);

        return (node != null && node.key.equals(key)) ? node.value : null;
    }

    /**
     * Removes a key-value pair from the hash table.
     *
     * @param key the key to remove
     */
    public void remove(K key) {
        int index = findBucketIndex(key);
        Node<K, V> node = table.get(index);

        if (node != null && node.key.equals(key)) {
            table.set(index, null); // Remove the node
            size--;
        }
    }

    /**
     * Returns the number of key-value pairs in the hash table.
     *
     * @return the size of the hash table
     */
    public int size() {
        return size;
    }

    /**
     * Checks if the hash table is empty.
     *
     * @return true if the table is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns a set of all keys in the hash table.
     *
     * @return a set of keys
     */
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        for (Node<K, V> node : table) {
            if (node != null) {
                keys.add(node.getKey());
            }
        }
        return keys;
    }

    /**
     * Returns a set of all values in the hash table.
     *
     * @return a set of values
     */
    public Set<V> valueSet() {
        Set<V> values = new HashSet<>();
        for (Node<K, V> node : table) {
            if (node != null) {
                values.add(node.value);
            }
        }
        return values;
    }

    /**
     * Provides an iterator to iterate through the hash table.
     *
     * @return an iterator over the nodes in the hash table
     */
    @Override
    public Iterator<Node<K, V>> iterator() {
        return new CustomHashTableIterator();
    }

    private class CustomHashTableIterator implements Iterator<Node<K, V>> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            while (currentIndex < table.size() && table.get(currentIndex) == null) {
                currentIndex++;
            }
            return currentIndex < table.size();
        }

        @Override
        public Node<K, V> next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in hash table");
            }
            return table.get(currentIndex++);
        }
    }
}
