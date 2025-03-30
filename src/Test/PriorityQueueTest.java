package Assignment3.Test;

import Assignment3.PriorityQueue.HeapPriorityQueue;
import Assignment3.PriorityQueue.SortedSeqPriorityQueue;
import Assignment3.PriorityQueue.UnsortedSeqPriorityQueue;
import Assignment3.util.DataGenerator;
import Assignment3.util.Song;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Priority Queue Test Class
 * Compare performance differences between three priority queue implementations
 */
public class PriorityQueueTest {
    // Store performance test results
    private Map<String, Map<Integer, Double>> enqueueTimeResults = new HashMap<>();
    private Map<String, Map<Integer, Double>> dequeueTimeResults = new HashMap<>();
    
    /**
     * Test priority queue correctness
     */
    public void testPriorityQueueCorrectness() {
        System.out.println("\n================ Testing Priority Queue Correctness ================");
        
        // Prepare test data
        List<Integer> testData = new ArrayList<>();
        testData.add(10);
        testData.add(5);
        testData.add(15);
        testData.add(2);
        testData.add(20);
        
        // Test heap-based priority queue
        System.out.println("\n--- Heap-based Priority Queue ---");
        HeapPriorityQueue<Integer> heapPQ = new HeapPriorityQueue<>(true); // Max priority
        testPriorityQueue(heapPQ, testData);
        
        // Test sorted sequence-based priority queue
        System.out.println("\n--- Sorted Sequence Priority Queue ---");
        SortedSeqPriorityQueue<Integer> sortedPQ = new SortedSeqPriorityQueue<>(true); // Max priority
        testPriorityQueue(sortedPQ, testData);
        
        // Test unsorted sequence-based priority queue
        System.out.println("\n--- Unsorted Sequence Priority Queue ---");
        UnsortedSeqPriorityQueue<Integer> unsortedPQ = new UnsortedSeqPriorityQueue<>(true); // Max priority
        testPriorityQueue(unsortedPQ, testData);
    }
    
    /**
     * Test priority queue performance - simplified to only test random data distribution
     */
    public void testPriorityQueuePerformance() {
        System.out.println("\n================ Testing Priority Queue Performance ================");
        
        // Initialize result maps
        initializeResultMaps();
        
        // Test different data scales
        int[] sizes = {100, 1000, 10000, 100000};
        
        for (int size : sizes) {
            System.out.println("\n--- Data Size: " + size + " ---");
            
            // Generate only random data
            List<Song> randomSongs = DataGenerator.generateSongs(
                    size, DataGenerator.DistributionType.RANDOM);
            
            // Test performance of various priority queues with random data only
            testPQPerformance("HeapPQ", new HeapPriorityQueue<>(), randomSongs, size);
            testPQPerformance("SortedPQ", new SortedSeqPriorityQueue<>(), randomSongs, size);
            testPQPerformance("UnsortedPQ", new UnsortedSeqPriorityQueue<>(), randomSongs, size);
        }
        
        // Print summary results
        printSummaryResults(sizes);
    }
    
    /**
     * Initialize result maps
     */
    private void initializeResultMaps() {
        // Enqueue time results
        enqueueTimeResults.put("HeapPQ", new HashMap<>());
        enqueueTimeResults.put("SortedPQ", new HashMap<>());
        enqueueTimeResults.put("UnsortedPQ", new HashMap<>());
        
        // Dequeue time results
        dequeueTimeResults.put("HeapPQ", new HashMap<>());
        dequeueTimeResults.put("SortedPQ", new HashMap<>());
        dequeueTimeResults.put("UnsortedPQ", new HashMap<>());
    }
    
    /**
     * Generic priority queue test method
     * @param pq Priority queue
     * @param data Test data
     */
    private <T extends Comparable<T>> void testPriorityQueue(Object pq, List<T> data) {
        if (pq instanceof HeapPriorityQueue) {
            HeapPriorityQueue<T> queue = (HeapPriorityQueue<T>) pq;
            
            // Enqueue elements
            for (T item : data) {
                queue.enqueue(item);
            }
            
            // Dequeue elements and print
            System.out.println("Dequeue order: ");
            while (!queue.isEmpty()) {
                System.out.print(queue.dequeue() + " ");
            }
            System.out.println();
        } else if (pq instanceof SortedSeqPriorityQueue) {
            SortedSeqPriorityQueue<T> queue = (SortedSeqPriorityQueue<T>) pq;
            
            // Enqueue elements
            for (T item : data) {
                queue.enqueue(item);
            }
            
            // Dequeue elements and print
            System.out.println("Dequeue order: ");
            while (!queue.isEmpty()) {
                System.out.print(queue.dequeue() + " ");
            }
            System.out.println();
        } else if (pq instanceof UnsortedSeqPriorityQueue) {
            UnsortedSeqPriorityQueue<T> queue = (UnsortedSeqPriorityQueue<T>) pq;
            
            // Enqueue elements
            for (T item : data) {
                queue.enqueue(item);
            }
            
            // Dequeue elements and print
            System.out.println("Dequeue order: ");
            while (!queue.isEmpty()) {
                System.out.print(queue.dequeue() + " ");
            }
            System.out.println();
        }
    }
    
    /**
     * Test priority queue performance
     * @param queueName Queue name (for result identification)
     * @param pq Priority queue object
     * @param songs Song data
     * @param size Data size
     */
    private void testPQPerformance(String queueName, Object pq, List<Song> songs, int size) {
        // Enqueue time test
        long startTime = System.nanoTime();
        
        if (pq instanceof HeapPriorityQueue) {
            HeapPriorityQueue<Song> queue = (HeapPriorityQueue<Song>) pq;
            for (Song song : songs) {
                queue.enqueue(song);
            }
        } else if (pq instanceof SortedSeqPriorityQueue) {
            SortedSeqPriorityQueue<Song> queue = (SortedSeqPriorityQueue<Song>) pq;
            for (Song song : songs) {
                queue.enqueue(song);
            }
        } else if (pq instanceof UnsortedSeqPriorityQueue) {
            UnsortedSeqPriorityQueue<Song> queue = (UnsortedSeqPriorityQueue<Song>) pq;
            for (Song song : songs) {
                queue.enqueue(song);
            }
        }
        
        long endTime = System.nanoTime();
        double enqueueTime = (endTime - startTime) / 1_000_000.0; // Convert to milliseconds
        
        // Save enqueue time results
        enqueueTimeResults.get(queueName).put(size, enqueueTime);
        
        // Dequeue time test
        startTime = System.nanoTime();
        
        if (pq instanceof HeapPriorityQueue) {
            HeapPriorityQueue<Song> queue = (HeapPriorityQueue<Song>) pq;
            while (!queue.isEmpty()) {
                queue.dequeue();
            }
        } else if (pq instanceof SortedSeqPriorityQueue) {
            SortedSeqPriorityQueue<Song> queue = (SortedSeqPriorityQueue<Song>) pq;
            while (!queue.isEmpty()) {
                queue.dequeue();
            }
        } else if (pq instanceof UnsortedSeqPriorityQueue) {
            UnsortedSeqPriorityQueue<Song> queue = (UnsortedSeqPriorityQueue<Song>) pq;
            while (!queue.isEmpty()) {
                queue.dequeue();
            }
        }
        
        endTime = System.nanoTime();
        double dequeueTime = (endTime - startTime) / 1_000_000.0; // Convert to milliseconds
        
        // Save dequeue time results
        dequeueTimeResults.get(queueName).put(size, dequeueTime);
        
        // Print current test results
        System.out.println(queueName + " - Size: " + size);
        System.out.println("  Enqueue time: " + enqueueTime + " ms");
        System.out.println("  Dequeue time: " + dequeueTime + " ms");
    }
    
    /**
     * Print summary results
     * @param sizes Array of data sizes tested
     */
    private void printSummaryResults(int[] sizes) {
        System.out.println("\n================ Performance Test Summary ================");
        
        // Print enqueue time summary
        System.out.println("\n--- Enqueue Time Summary (ms) ---");
        printTable(enqueueTimeResults, sizes);
        
        // Print dequeue time summary
        System.out.println("\n--- Dequeue Time Summary (ms) ---");
        printTable(dequeueTimeResults, sizes);
    }
    
    /**
     * Print performance results in table format
     * @param results Results map
     * @param sizes Array of data sizes
     */
    private void printTable(Map<String, Map<Integer, Double>> results, int[] sizes) {
        // Print header
        System.out.print("Implementation\\Size");
        for (int size : sizes) {
            System.out.printf("%12d", size);
        }
        System.out.println();
        
        // Print results for each implementation
        for (String impl : results.keySet()) {
            System.out.printf("%-20s", impl);
            for (int size : sizes) {
                System.out.printf("%12.2f", results.get(impl).get(size));
            }
            System.out.println();
        }
    }
} 