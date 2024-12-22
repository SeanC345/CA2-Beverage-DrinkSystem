package utils;

import java.io.Serializable;
import java.util.Iterator;

public class CustomLinkedList<T extends Serializable> implements Iterable<T>, Serializable {
    private Node<T> head;
    private int size;

    private static final long serialVersionUID = 1L;



    private static class Node<T> implements Serializable{
        T data;
        Node<T> next;

        public Node(T data){
            this.data = data;
            this.next = null;
        }
    }

    public CustomLinkedList(){
        this.head = null;
        this.size = 0;
    }

    public int size(){
        return size;
    }

    public Node<T> getHead(){
        return head;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index + ", List size: " + size);
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }
<<<<<<< HEAD
    
=======

>>>>>>> ea7a0f975e0f06049f795a5e5e67c54e88e570ab
    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    public void remove(T data){
        if ( head == null ) return;   //If list is empty nothing gets removed
        // In case the head contains data needed to be removed
        if(head.data.equals(data)) {
            head = head.next;  //updates the head to the next node
            size--; //Decrease the size of the list
            return;
        }
        // Find the node to be removed
        Node<T> current = head; // Start from the head
        while (current.next != null){
            if ( current.next.data.equals(data) ){ // Check if the next node contains the data to be removed
                current.next = current.next.next;  //Skip the node with the matching data
                size--;
                return;
            }
            current = current.next;
        }
    }

    public void set(int index, T data){
        if ( index < 0 || index >= size ){
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        Node<T> current = head;
        for(int i =0; i < index; i++){
            current = current.next;
        }
        current.data = data;
    }

    public void clear(){
        head = null;
        size = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new CustomLinkedListIterator();
    }

    private class CustomLinkedListIterator implements Iterator<T> {
        private Node<T> current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T data = current.data;
            current = current.next;
            return data;
        }
    }

}
