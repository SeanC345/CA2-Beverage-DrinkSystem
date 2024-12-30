package junit;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import data.CustomHashTable;

public class CustomHashTableTest {

    @Test
    public void testPutAndGet() {
        CustomHashTable<String, String> hashTable = new CustomHashTable<>(10);
        hashTable.put("key1", "value1");
        assertEquals("value1", hashTable.get("key1"));
    }

    @Test
    public void testRemove() {
        CustomHashTable<String, String> hashTable = new CustomHashTable<>(10);
        hashTable.put("key1", "value1");
        hashTable.remove("key1");
        assertNull(hashTable.get("key1"));
    }

    @Test
    public void testKeySet() {
        CustomHashTable<String, String> hashTable = new CustomHashTable<>(10);
        hashTable.put("key1", "value1");
        hashTable.put("key2", "value2");
        assertTrue(hashTable.keySet().contains("key1"));
        assertTrue(hashTable.keySet().contains("key2"));
    }
}
