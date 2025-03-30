package Assignment3.PriorityQueue;

import java.util.ArrayList;
import java.util.List;

/**
 * Unsorted sequence-based priority queue implementation
 * Inserts directly at the end, removes by traversing to find the highest priority element
 * @param <T> Any comparable type
 */
public class UnsortedSeqPriorityQueue<T extends Comparable<T>> implements PriorityQueueInterface<T> {
    private final List<T> elements;
    private final boolean isMaxPriority; // Whether it is max priority

    /**
     * Default constructor, creates a min priority queue
     */
    public UnsortedSeqPriorityQueue() {
        this(false);
    }

    /**
     * Constructor with priority type
     * @param isMaxPriority true for max priority, false for min priority
     */
    public UnsortedSeqPriorityQueue(boolean isMaxPriority) {
        this.elements = new ArrayList<>();
        this.isMaxPriority = isMaxPriority;
    }

    @Override
    public void enqueue(T element) {
        // Add directly to the end, O(1) operation
        elements.add(element);
    }

    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Priority queue is empty");
        }
        
        // Find index of highest priority element
        int highestPriorityIndex = findHighestPriorityIndex();
        
        // Remove and return that element
        return elements.remove(highestPriorityIndex);
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Priority queue is empty");
        }
        
        // Find index of highest priority element
        int highestPriorityIndex = findHighestPriorityIndex();
        
        // Return but don't remove that element
        return elements.get(highestPriorityIndex);
    }

    @Override
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    @Override
    public int size() {
        return elements.size();
    }
    
    /**
     * Find the index of the highest priority element
     * @return Index of highest priority element
     */
    private int findHighestPriorityIndex() {
        int result = 0;
        
        for (int i = 1; i < elements.size(); i++) {
            if (isMaxPriority) {
                // Max priority, find largest element
                if (elements.get(i).compareTo(elements.get(result)) > 0) {
                    result = i;
                }
            } else {
                // Min priority, find smallest element
                if (elements.get(i).compareTo(elements.get(result)) < 0) {
                    result = i;
                }
            }
        }
        
        return result;
    }
} 