package Assignment3.Test;

import Assignment3.Heap.MaxHeap;
import Assignment3.Heap.MinHeap;
import Assignment3.util.DataGenerator;
import Assignment3.util.Song;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Heap implementation test class
 * Test various operations of max heap and min heap
 */
public class HeapTest {
    private Map<String, Map<Integer, Double>> heapifyTimeResults = new HashMap<>();
    private Map<String, Map<Integer, Double>> insertTimeResults = new HashMap<>();
    private Map<String, Map<Integer, Double>> extractTimeResults = new HashMap<>();
    
    /**
     * Test heap correctness
     */
    public void testHeapCorrectness() {
        System.out.println("\n================ Testing Heap Correctness ================");
        
        // Test min heap
        System.out.println("\n--- Testing Min Heap ---");
        MinHeap<Integer> minHeap = new MinHeap<>();
        
        // Test insertion and extraction
        minHeap.insert(10);
        minHeap.insert(5);
        minHeap.insert(15);
        minHeap.insert(2);
        minHeap.insert(20);
        
        System.out.println("Min heap extraction order should be ascending: ");
        while (!minHeap.isEmpty()) {
            System.out.print(minHeap.extractTop() + " ");
        }
        System.out.println();
        
        // Test max heap
        System.out.println("\n--- Testing Max Heap ---");
        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        
        // Test insertion and extraction
        maxHeap.insert(10);
        maxHeap.insert(5);
        maxHeap.insert(15);
        maxHeap.insert(2);
        maxHeap.insert(20);
        
        System.out.println("Max heap extraction order should be descending: ");
        while (!maxHeap.isEmpty()) {
            System.out.print(maxHeap.extractTop() + " ");
        }
        System.out.println();
        
        // Test heapify operation
        System.out.println("\n--- Testing Heapify Operation ---");
        Integer[] array = {10, 5, 15, 2, 20};
        
        MinHeap<Integer> heapifiedMinHeap = new MinHeap<>();
        heapifiedMinHeap.heapify(array);
        
        System.out.println("Min heap extraction order after heapify: ");
        while (!heapifiedMinHeap.isEmpty()) {
            System.out.print(heapifiedMinHeap.extractTop() + " ");
        }
        System.out.println();
    }
    
    /**
     * Test heap performance - simplified to only test random data distribution
     */
    public void testHeapPerformance() {
        initializeResultMaps();
        System.out.println("\n================ Testing Heap Performance ================");
        
        // Test different data scales
        int[] sizes = {100, 1000, 10000, 100000};
        
        for (int size : sizes) {
            System.out.println("\n--- Data Size: " + size + " ---");
            
            // Generate test data - only random data
            List<Song> randomSongs = DataGenerator.generateSongs(
                    size, DataGenerator.DistributionType.RANDOM);
            
            // Test min heap performance - only random data
            testMinHeapPerformance(randomSongs, "Random", size);
            
            // Test max heap performance - only random data
            testMaxHeapPerformance(randomSongs, "Random", size);
        }
        
        // Print performance summary
        printPerformanceSummary(sizes);
    }
    
    /**
     * Initialize result maps for performance tracking
     */
    private void initializeResultMaps() {
        String[] heapTypes = {"MinHeap-Random", "MaxHeap-Random"};
        
        for (String heapType : heapTypes) {
            heapifyTimeResults.put(heapType, new HashMap<>());
            insertTimeResults.put(heapType, new HashMap<>());
            extractTimeResults.put(heapType, new HashMap<>());
        }
    }
    
    /**
     * Test min heap performance
     * @param songs Test data
     * @param dataType Data type description
     * @param size Data size
     */
    private void testMinHeapPerformance(List<Song> songs, String dataType, int size) {
        String key = "MinHeap-" + dataType;
        System.out.println("\nMin Heap - " + dataType + " Data - Size: " + size);
        
        // Prepare test data
        Song[] songsArray = songs.toArray(new Song[0]);
        MinHeap<Song> minHeap = new MinHeap<>();
        
        // Test heapify performance
        long startTime = System.nanoTime();
        minHeap.heapify(songsArray);
        long endTime = System.nanoTime();
        double heapifyTime = (endTime - startTime) / 1_000_000.0; // Convert to milliseconds
        heapifyTimeResults.get(key).put(size, heapifyTime);
        System.out.println("  Heapify time: " + heapifyTime + " ms");
        
        // Create new heap to test insertion performance
        minHeap = new MinHeap<>();
        startTime = System.nanoTime();
        for (Song song : songs) {
            minHeap.insert(song);
        }
        endTime = System.nanoTime();
        double insertTime = (endTime - startTime) / 1_000_000.0; // Convert to milliseconds
        insertTimeResults.get(key).put(size, insertTime);
        System.out.println("  Individual insertion time: " + insertTime + " ms");
        
        // Test extraction performance
        startTime = System.nanoTime();
        while (!minHeap.isEmpty()) {
            minHeap.extractTop();
        }
        endTime = System.nanoTime();
        double extractTime = (endTime - startTime) / 1_000_000.0; // Convert to milliseconds
        extractTimeResults.get(key).put(size, extractTime);
        System.out.println("  Extract all elements time: " + extractTime + " ms");
    }
    
    /**
     * Test max heap performance
     * @param songs Test data
     * @param dataType Data type description
     * @param size Data size
     */
    private void testMaxHeapPerformance(List<Song> songs, String dataType, int size) {
        String key = "MaxHeap-" + dataType;
        System.out.println("\nMax Heap - " + dataType + " Data - Size: " + size);
        
        // Prepare test data
        Song[] songsArray = songs.toArray(new Song[0]);
        MaxHeap<Song> maxHeap = new MaxHeap<>();
        
        // Test heapify performance
        long startTime = System.nanoTime();
        maxHeap.heapify(songsArray);
        long endTime = System.nanoTime();
        double heapifyTime = (endTime - startTime) / 1_000_000.0; // Convert to milliseconds
        heapifyTimeResults.get(key).put(size, heapifyTime);
        System.out.println("  Heapify time: " + heapifyTime + " ms");
        
        // Create new heap to test insertion performance
        maxHeap = new MaxHeap<>();
        startTime = System.nanoTime();
        for (Song song : songs) {
            maxHeap.insert(song);
        }
        endTime = System.nanoTime();
        double insertTime = (endTime - startTime) / 1_000_000.0; // Convert to milliseconds
        insertTimeResults.get(key).put(size, insertTime);
        System.out.println("  Individual insertion time: " + insertTime + " ms");
        
        // Test extraction performance
        startTime = System.nanoTime();
        while (!maxHeap.isEmpty()) {
            maxHeap.extractTop();
        }
        endTime = System.nanoTime();
        double extractTime = (endTime - startTime) / 1_000_000.0; // Convert to milliseconds
        extractTimeResults.get(key).put(size, extractTime);
        System.out.println("  Extract all elements time: " + extractTime + " ms");
    }
    
    /**
     * Print performance summary in table format
     */
    private void printPerformanceSummary(int[] sizes) {
        System.out.println("\n================ Heap Performance Summary ================");
        
        // Print heapify time summary
        System.out.println("\n--- Heapify Time Summary (ms) ---");
        printTable(heapifyTimeResults, sizes);
        
        // Print insertion time summary
        System.out.println("\n--- Insertion Time Summary (ms) ---");
        printTable(insertTimeResults, sizes);
        
        // Print extraction time summary
        System.out.println("\n--- Extraction Time Summary (ms) ---");
        printTable(extractTimeResults, sizes);
    }
    
    /**
     * Print table of performance results
     */
    private void printTable(Map<String, Map<Integer, Double>> results, int[] sizes) {
        // Print header
        System.out.print("Implementation\\Size");
        for (int size : sizes) {
            System.out.printf("%12d", size);
        }
        System.out.println();
        
        // Print each implementation's results
        for (String impl : results.keySet()) {
            System.out.printf("%-20s", impl);
            for (int size : sizes) {
                if (results.get(impl).containsKey(size)) {
                    System.out.printf("%12.2f", results.get(impl).get(size));
                } else {
                    System.out.printf("%12s", "N/A");
                }
            }
            System.out.println();
        }
    }
} 