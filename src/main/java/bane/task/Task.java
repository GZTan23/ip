package bane.task;

/**
 * Interface for the general Task type
 */
public interface Task {

    /**
     * Changes the task status
     * @param status True or False depending on whether the task is done
     */
    void changeTaskStatus(boolean status);

    /**
     * Checks whether the task is done
     * @return True or False depending on the completion of the task
     */
    boolean isTaskDone();
    String getName();
}
