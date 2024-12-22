package data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import utils.CustomLinkedList;

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
    }

    private CustomLinkedList<Node<K, V>> table;
    private int size; // number of key-value pairs
    private int capacity; // number of buckets

    // Constructor
    public CustomHashTable(int initialCapacity) {
        this.capacity = initialCapacity;
        this.size = 0;
        this.table = new CustomLinkedList<>();
        initializeBuckets();
    }

    // Initialize the buckets
    private void initializeBuckets() {
        table.clear();
        for (int i = 0; i < capacity; i++) {
<<<<<<< HEAD
            table.add(null);
=======
            table.add(null); // Add `capacity` null nodes
>>>>>>> ea7a0f975e0f06049f795a5e5e67c54e88e570ab
        }
    }

    // Reset the hash table
    public void reset() {
        table.clear();
        size = 0;
        initializeBuckets();
    }

    // Primary hash function to calculate the bucket index
    private int primaryHash(K key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    // Secondary hash function to calculate the step size
    private int secondaryHash(K key) {
        return 1 + (Math.abs(key.hashCode()) % (capacity - 1));
    }

    // Find the bucket index using double hashing
    private int findBucketIndex(K key) {
        int primaryIndex = primaryHash(key);
        int stepSize = secondaryHash(key);

        int index = primaryIndex;
        int attempts = 0;

        while (attempts < capacity) {
            if (index < 0 || index >= table.size()) {
                throw new IndexOutOfBoundsException("Bucket index out of bounds: " + index);
            }

            Node<K, V> node = table.get(index);

            if (node == null || node.key.equals(key)) {
                return index;
            }

            index = (index + stepSize) % capacity;
            attempts++;
        }

        return -1; // Table is full
    }

    // Add a key-value pair
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }

        int index = findBucketIndex(key);

        if (index == -1) {
            throw new IllegalStateException("Hash table is full");
        }

        if (index >= table.size()) {
            throw new IndexOutOfBoundsException("Invalid index for put: " + index);
        }

        Node<K, V> node = table.get(index);
        if (node == null) {
            table.set(index, new Node<>(key, value));
            size++;
        } else {
            node.value = value; // Update existing value
        }
    }

    // Get a value by key
    public V get(K key) {
        int index = findBucketIndex(key);
        Node<K, V> node = table.get(index);

        if (node != null && node.key.equals(key)) {
            return node.value;
        }

        return null; // Key not found
    }

    // Remove a key-value pair by key
    public void remove(K key) {
        int index = findBucketIndex(key);
        Node<K, V> node = table.get(index);

        if (node != null && node.key.equals(key)) {
            table.set(index, null);
            size--;
        }
    }

    // Get the size of the hash table
    public int size() {
        return size;
    }

    // Check if the hash table is empty
    public boolean isEmpty() {
        return size == 0;
    }

    //Get a set of all keys in hash tbale
    public Set<K> keySet() {
    Set<K> keys = new HashSet<>();
    for (Node<K, V> node : table) { // Iterate through the hash table
        keys.add(node.getKey());
    }
    return keys;
    }

    //Get a set of all values in hashTable
    public Set<V> valueSet() {
        Set<V> values = new HashSet<>();
        for (Node<K, V> node : table) { // Iterate through the hash table
            if (node != null) { // Skip null nodes
                values.add(node.value);
            }
        }
        return values;
    }

    //Iterator for looping through the hash table
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
            return table.get(currentIndex++);
        }
    }
}
