package data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

public class DataPersistence implements Serializable {
    
    // Save hash table to file
    public static <K, V> void saveData(CustomHashTable<K, V> hashTable, String filename) {
        try (OutputStream os = new FileOutputStream(filename);
             ObjectOutputStream oos = new ObjectOutputStream(os)) {
            oos.writeObject(hashTable);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load hash table from file
    @SuppressWarnings("unchecked")
    public static <K, V> void loadData(CustomHashTable<K, V> hashTable, String filename) {
    try (InputStream is = new FileInputStream(filename);
         ObjectInputStream ois = new ObjectInputStream(is)) {
         
        // Load the saved hash table
        CustomHashTable<K, V> loadedHashTable = (CustomHashTable<K, V>) ois.readObject();
        
        // Reset the current hash table
        hashTable.reset();
        
        // Iterate over the keys and reinsert them with their values
        for (K key : loadedHashTable.keySet()) {    //THIS MAY BE CAUSING LOAD ERROR
            V value = loadedHashTable.get(key);  
            hashTable.put(key, value);     
        }
    } catch (IOException e) {
        e.printStackTrace();
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
}
}