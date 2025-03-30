package Assignment3;

import Assignment3.Test.HeapTest;
import Assignment3.Test.PriorityQueueTest;


public class Main {
    public static void main(String[] args) {
        System.out.println("Assignment3: Heap and priority queue implementation and performance comparison");
        
        // Run heap implementation tests
        HeapTest heapTest = new HeapTest();
        heapTest.testHeapCorrectness();
        
        // Run priority queue implementation tests
        PriorityQueueTest pqTest = new PriorityQueueTest();
        pqTest.testPriorityQueueCorrectness();
        
        // Run performance tests - automatically run without user choice
        System.out.println("\nStart performance test...");
        heapTest.testHeapPerformance();
        pqTest.testPriorityQueuePerformance();
        
        System.out.println("\nTest finished!");
    }
}
