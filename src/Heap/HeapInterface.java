package Assignment3.Heap;

/**
 * Heap interface definition, supports basic operations for max heap and min heap
 * @param <T> Any comparable type
 */
public interface HeapInterface<T extends Comparable<T>> {
    /**
     * Insert an element into the heap
     * @param element Element to insert
     */
    void insert(T element);
    
    /**
     * Extract the top element (max value from max heap or min value from min heap)
     * @return Top element
     */
    T extractTop();
    
    /**
     * View the top element without removing it
     * @return Top element
     */
    T peekTop();
    
    /**
     * Build a heap from an array
     * @param array Array to build heap from
     */
    void heapify(T[] array);
    
    /**
     * Check if the heap is empty
     * @return true if heap is empty, false otherwise
     */
    boolean isEmpty();
    
    /**
     * Return the number of elements in the heap
     * @return Size of the heap
     */
    int size();
} 