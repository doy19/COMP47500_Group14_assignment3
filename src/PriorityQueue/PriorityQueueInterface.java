package Assignment3.PriorityQueue;

/**
 * Priority Queue Interface Definition
 * Defines basic operations for priority queues
 * @param <T> Any comparable type
 */
public interface PriorityQueueInterface<T extends Comparable<T>> {
    /**
     * Add an element to the priority queue
     * @param element Element to add
     */
    void enqueue(T element);
    
    /**
     * Remove and return the element with highest priority
     * @return Highest priority element
     */
    T dequeue();
    
    /**
     * View the element with highest priority without removing it
     * @return Highest priority element
     */
    T peek();

    boolean isEmpty();

    int size();
} 