package bane.exception;

/**
 * Custom exception for any exceptions during execution of Tasks
 */
public class TaskExecuteException extends Exception{
    /**
     * Constructor for the TaskExecuteException class
     * @param message Message to be printed by the exception
     */
    public TaskExecuteException(String message) {
        super(message);
    } 
}
