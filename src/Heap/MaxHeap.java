package Assignment3.Heap;

import java.util.Arrays;

/**
 * Max Heap implementation
 * Top element is the maximum value
 * @param <T> Any comparable type
 */
public class MaxHeap<T extends Comparable<T>> implements HeapInterface<T> {
    // Array to store the heap
    private Object[] elements;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;
    
    /**
     * Default constructor, creates an empty heap with default capacity
     */
    public MaxHeap() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }
    
    /**
     * Constructor with initial capacity
     * @param capacity Initial capacity
     */
    public MaxHeap(int capacity) {
        elements = new Object[capacity];
        size = 0;
    }
    
    /**
     * Create a heap from an array
     * @param array Initial data array
     */
    public MaxHeap(T[] array) {
        elements = Arrays.copyOf(array, array.length);
        size = array.length;
        heapify(array);
    }
    
    @Override
    public void insert(T element) {
        // Check and expand capacity
        ensureCapacity();
        
        // Add element to the end of array
        elements[size] = element;
        
        // Sift up the new element
        siftUp(size);
        
        // Increase heap size
        size++;
    }

    @Override
    public T extractTop() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        
        // Save the top element
        T max = (T) elements[0];
        
        // Move the last element to the top
        elements[0] = elements[size - 1];
        elements[size - 1] = null; // Help GC
        size--;
        
        // If heap is not empty, sift down the top element
        if (size > 0) {
            siftDown(0);
        }
        
        return max;
    }

    @Override
    public T peekTop() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        return (T) elements[0];
    }

    @Override
    public void heapify(T[] array) {
        // Create a new heap for the array
        elements = Arrays.copyOf(array, array.length);
        size = array.length;
        
        // Start sifting down from the last non-leaf node
        for (int i = (size / 2) - 1; i >= 0; i--) {
            siftDown(i);
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }
    
    /**
     * Sift up operation, maintains heap property after insertion
     * @param index Index of the node to sift up
     */
    private void siftUp(int index) {
        // Get the inserted element
        T element = (T) elements[index];
        
        // Sift up until root or heap property is satisfied
        while (index > 0) {
            // Calculate parent index
            int parentIndex = (index - 1) / 2;
            T parent = (T) elements[parentIndex];
            
            // If current element is less than or equal to parent, heap property is satisfied
            if (element.compareTo(parent) <= 0) {
                break;
            }
            
            // Otherwise, swap elements and continue sifting up
            elements[index] = parent;
            index = parentIndex;
        }
        
        elements[index] = element;
    }
    
    /**
     * Sift down operation, maintains heap property after removing top element
     * @param index Index of the node to sift down
     */
    private void siftDown(int index) {
        T element = (T) elements[index];
        
        // Sift down until leaf node
        int half = size / 2; // Index of the last non-leaf node
        
        while (index < half) {
            // Get left child index
            int childIndex = 2 * index + 1;
            T child = (T) elements[childIndex];
            
            // Get right child (if exists)
            int rightIndex = childIndex + 1;
            
            // If right child exists and is greater than left child, use right child
            if (rightIndex < size && 
                ((T) elements[rightIndex]).compareTo(child) > 0) {
                childIndex = rightIndex;
                child = (T) elements[rightIndex];
            }
            
            // If current element is greater than or equal to child, heap property is satisfied
            if (element.compareTo(child) >= 0) {
                break;
            }
            
            // Otherwise, swap elements and continue sifting down
            elements[index] = child;
            index = childIndex;
        }
        
        elements[index] = element;
    }
    
    /**
     * Ensure array capacity is sufficient, expand if necessary
     */
    private void ensureCapacity() {
        if (size == elements.length) {
            int newCapacity = elements.length * 2;
            elements = Arrays.copyOf(elements, newCapacity);
        }
    }
} 