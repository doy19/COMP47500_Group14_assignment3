package Assignment3.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Test data generator
 * Used to generate test data with different distribution characteristics
 */
public class DataGenerator {
    private static final Random random = new Random();
    
    /**
     * Data distribution types
     */
    public enum DistributionType {
        RANDOM,     // Random distribution
        ASCENDING,  // Ascending order
        DESCENDING, // Descending order
        NEARLY_SORTED // Nearly sorted
    }
    
    /**
     * Generate songs with specified size and distribution type
     */
    public static List<Song> generateSongs(int size, DistributionType type) {
        List<Song> songs = new ArrayList<>();
        
        // Generate data based on distribution type
        switch (type) {
            case RANDOM:
                songs = generateRandomSongs(size);
                break;
            case ASCENDING:
                songs = generateSortedSongs(size, true);
                break;
            case DESCENDING:
                songs = generateSortedSongs(size, false);
                break;
            case NEARLY_SORTED:
                songs = generateNearlySortedSongs(size);
                break;
        }
        
        return songs;
    }
    
    /**
     * Generate randomly distributed songs
     */
    private static List<Song> generateRandomSongs(int size) {
        return CSVLoader.generateRandomSongs(size);
    }
    
    /**
     * Generate songs in sorted order
     */
    private static List<Song> generateSortedSongs(int size, boolean ascending) {
        List<Song> songs = generateRandomSongs(size);
        
        // Set priorities in order
        for (int i = 0; i < songs.size(); i++) {
            int priority = ascending ? i : (size - i - 1);
            songs.get(i).setPriority(priority);
        }
        
        return songs;
    }
    
    /**
     * Generate nearly sorted songs (with a few out-of-order elements)
     */
    private static List<Song> generateNearlySortedSongs(int size) {
        List<Song> songs = generateSortedSongs(size, true);
        
        // Randomly swap about 10% of elements
        int swapCount = size / 10;
        for (int i = 0; i < swapCount; i++) {
            int index1 = random.nextInt(size);
            int index2 = random.nextInt(size);
            
            // Swap priorities
            int temp = songs.get(index1).getPriority();
            songs.get(index1).setPriority(songs.get(index2).getPriority());
            songs.get(index2).setPriority(temp);
        }
        
        return songs;
    }
} 