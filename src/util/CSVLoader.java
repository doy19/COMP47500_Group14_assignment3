package Assignment3.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * CSV file loader utility
 * Used to load song data from CSV files
 */
public class CSVLoader {
    // CSV file should be placed in the project root directory
    private static final String DEFAULT_CSV_PATH = "MusicMoodFinal.csv";
    
    /**
     * Load songs from CSV file with a specified limit
     */
    public static List<Song> loadCSV(String filePath, int limit) {
        List<Song> songs = new ArrayList<>();
        int loadedCount = 0;
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Skip header line
            br.readLine();
            
            while ((line = br.readLine()) != null && loadedCount < limit) {
                String[] values = parseCsvLine(line);
                
                // Ensure data is complete (at least id and name)
                if (values.length >= 2) {
                    // The CSV format has: id, name, popularity, etc.
                    String id = values[0];
                    String name = values[1];
                    
                    // Get artist from the artists field (column 5)
                    String artist = (values.length > 5) ? cleanArtistString(values[5]) : "Unknown";
                    
                    // Get priority from popularity (column 2) or generate random
                    int priority;
                    if (values.length > 2 && tryParseInt(values[2])) {
                        priority = Integer.parseInt(values[2]);
                    } else {
                        priority = new Random().nextInt(100);
                    }
                    
                    // Use the provided id or index
                    int songId = tryParseInt(id) ? Integer.parseInt(id) : loadedCount + 1;
                    
                    Song song = new Song(songId, name, artist, priority);
                    songs.add(song);
                    loadedCount++;
                }
            }
            
            if (songs.isEmpty()) {
                System.err.println("Warning: No songs loaded from CSV file.");
            } else {
                System.out.println("Successfully loaded " + songs.size() + " songs from " + filePath);
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
            System.err.println("Please place your CSV file at: " + System.getProperty("user.dir") + "/" + DEFAULT_CSV_PATH);
            throw new RuntimeException("Failed to load CSV file", e);
        }
        
        return songs;
    }
    
    /**
     * Generate random songs (used when CSV file is not available)
     */
    public static List<Song> generateRandomSongs(int count) {
        // Just load from default CSV path
        return loadCSV(DEFAULT_CSV_PATH, count);
    }
    
    /**
     * Parse CSV line, handling commas within quotes and brackets
     */
    private static String[] parseCsvLine(String line) {
        List<String> result = new ArrayList<>();
        StringBuilder currentField = new StringBuilder();
        boolean inQuotes = false;
        boolean inBrackets = false;
        
        for (char c : line.toCharArray()) {
            if (c == '\"') {
                inQuotes = !inQuotes;
                currentField.append(c);
            } else if (c == '[') {
                inBrackets = true;
                currentField.append(c);
            } else if (c == ']') {
                inBrackets = false;
                currentField.append(c);
            } else if (c == ',' && !inQuotes && !inBrackets) {
                result.add(currentField.toString().trim());
                currentField = new StringBuilder();
            } else {
                currentField.append(c);
            }
        }
        
        result.add(currentField.toString().trim());
        return result.toArray(new String[0]);
    }
    
    /**
     * Clean artist string from format like ['Artist Name']
     */
    private static String cleanArtistString(String artistField) {
        if (artistField == null || artistField.isEmpty()) {
            return "Unknown";
        }
        
        // Remove brackets and quotes
        String cleaned = artistField.replaceAll("[\\[\\]'\"]", "");
        
        // If there are multiple artists, take the first one
        if (cleaned.contains(",")) {
            cleaned = cleaned.split(",")[0].trim();
        }
        
        return cleaned.isEmpty() ? "Unknown" : cleaned;
    }
    
    /**
     * Try to parse integer
     */
    private static boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
} 