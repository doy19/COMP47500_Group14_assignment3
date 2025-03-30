package Assignment3.util;

/**
 * Priority comparable interface
 * Classes implementing this interface must provide methods to get and set priority
 */
public interface PriorityComparable {
    /**
     * Get element priority
     */
    int getPriority();
    
    /**
     * Set element priority
     */
    void setPriority(int newPriority);
} 