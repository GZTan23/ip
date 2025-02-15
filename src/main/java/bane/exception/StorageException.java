package bane.exception;

/**
 * Custom exception for handling exceptions generated by Storage.java
 */
public class StorageException extends Exception{

    /**
     * Constructor for the StorageException class
     * @param message Message to be printed out
     */
    public StorageException(String message) {
        super(message);
    }
}
