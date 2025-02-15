package bane.exception;

/**
 * Custom exception for any exceptions during execution of Tasks
 */
public class TaskException extends Exception {
    /**
     * Constructor for the TaskException class
     * @param message Message to be printed by the exception
     */
    public TaskException(String message) {
        super(message);
        assert message != null : "TaskException message should not be null";
    }

}
