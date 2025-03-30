package Assignment3.PriorityQueue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Sorted sequence-based priority queue implementation
 * Maintains an always sorted sequence, inserts while maintaining order, removes directly from front/end
 * @param <T> Any comparable type
 */
public class SortedSeqPriorityQueue<T extends Comparable<T>> implements PriorityQueueInterface<T> {
    private final List<T> elements;
    private final boolean isMaxPriority; // Whether it is max priority

    /**
     * Default constructor, creates a min priority queue
     */
    public SortedSeqPriorityQueue() {
        this(false);
    }

    /**
     * Constructor with priority type
     * @param isMaxPriority true for max priority, false for min priority
     */
    public SortedSeqPriorityQueue(boolean isMaxPriority) {
        this.elements = new ArrayList<>();
        this.isMaxPriority = isMaxPriority;
    }

    @Override
    public void enqueue(T element) {
        // Use binary search to find insertion position
        int index = findInsertPosition(element);
        elements.add(index, element);
    }

    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Priority queue is empty");
        }
        
        if (isMaxPriority) {
            // Max priority, remove from end of list
            return elements.remove(elements.size() - 1);
        } else {
            // Min priority, remove from front of list
            return elements.remove(0);
        }
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Priority queue is empty");
        }
        
        if (isMaxPriority) {
            // Max priority, look at end of list
            return elements.get(elements.size() - 1);
        } else {
            // Min priority, look at front of list
            return elements.get(0);
        }
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
     * Use binary search to find insertion position for an element
     * @param element Element to insert
     * @return Index of insertion position
     */
    private int findInsertPosition(T element) {
        if (elements.isEmpty()) {
            return 0;
        }
        
        int low = 0;
        int high = elements.size() - 1;
        
        while (low <= high) {
            int mid = (low + high) / 2;
            int compare = element.compareTo(elements.get(mid));
            
            if (isMaxPriority) {
                // In max priority queue, larger elements go to the end
                if (compare > 0) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            } else {
                // In min priority queue, smaller elements go to the front
                if (compare < 0) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
        }
        
        return low;
    }
} 