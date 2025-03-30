package Assignment3.PriorityQueue;

import Assignment3.Heap.MaxHeap;
import Assignment3.Heap.MinHeap;

/**
 * Heap-based priority queue implementation
 * Can be implemented using max heap or min heap, default is min heap (min priority)
 * @param <T> Any comparable type
 */
public class HeapPriorityQueue<T extends Comparable<T>> implements PriorityQueueInterface<T> {
    private final boolean isMaxPriority; // Whether it is max priority (using max heap)
    private final MaxHeap<T> maxHeap;
    private final MinHeap<T> minHeap;
    
    /**
     * Default constructor, creates a min priority queue
     */
    public HeapPriorityQueue() {
        this(false);
    }
    
    /**
     * Constructor with priority type
     * @param isMaxPriority true for max priority, false for min priority
     */
    public HeapPriorityQueue(boolean isMaxPriority) {
        this.isMaxPriority = isMaxPriority;
        if (isMaxPriority) {
            this.maxHeap = new MaxHeap<>();
            this.minHeap = null;
        } else {
            this.maxHeap = null;
            this.minHeap = new MinHeap<>();
        }
    }

    @Override
    public void enqueue(T element) {
        if (isMaxPriority) {
            maxHeap.insert(element);
        } else {
            minHeap.insert(element);
        }
    }

    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Priority queue is empty");
        }
        
        if (isMaxPriority) {
            return maxHeap.extractTop();
        } else {
            return minHeap.extractTop();
        }
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Priority queue is empty");
        }
        
        if (isMaxPriority) {
            return maxHeap.peekTop();
        } else {
            return minHeap.peekTop();
        }
    }

    @Override
    public boolean isEmpty() {
        if (isMaxPriority) {
            return maxHeap.isEmpty();
        } else {
            return minHeap.isEmpty();
        }
    }

    @Override
    public int size() {
        if (isMaxPriority) {
            return maxHeap.size();
        } else {
            return minHeap.size();
        }
    }
} 