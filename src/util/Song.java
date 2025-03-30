package Assignment3.util;

/**
 * Song entity class
 * Represents a song that implements PriorityComparable interface for use in priority queues
 */
public class Song implements Comparable<Song>, PriorityComparable {
    private int id;
    private String name;
    private String artist;
    private int priority; // Song priority (could be popularity, user rating, etc.)
    
    /**
     * Constructor
     */
    public Song(int id, String name, String artist, int priority) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.priority = priority;
    }
    
    /**
     * Get song ID
     */
    public int getId() {
        return id;
    }
    
    /**
     * Set song ID
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Get song name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Set song name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Get artist
     */
    public String getArtist() {
        return artist;
    }
    
    /**
     * Set artist
     */
    public void setArtist(String artist) {
        this.artist = artist;
    }
    
    /**
     * Get song priority
     */
    @Override
    public int getPriority() {
        return priority;
    }
    
    /**
     * Set song priority
     */
    @Override
    public void setPriority(int newPriority) {
        this.priority = newPriority;
    }
    
    /**
     * Compare songs by priority
     */
    @Override
    public int compareTo(Song other) {
        return Integer.compare(this.priority, other.priority);
    }
    
    /**
     * String representation
     */
    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", artist='" + artist + '\'' +
                ", priority=" + priority +
                '}';
    }
} 