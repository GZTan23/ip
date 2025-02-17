package bane.task;

import java.time.format.DateTimeFormatter;

import bane.enums.DateTimeFormat;

/**
 * Interface for the general Task type
 */
public interface Task {
    DateTimeFormatter PARSER = DateTimeFormat.PARSE_FORMAT.formatter();
    DateTimeFormatter DISPLAYER = DateTimeFormat.DISPLAY_FORMAT.formatter();
    /**
     * Changes the task status
     * @param status True or False depending on whether the task is done
     */
    void changeTaskStatus(boolean status);
    void setReminder(boolean isReminder);
    /**
     * Checks whether the task is done
     * @return True or False depending on the completion of the task
     */
    boolean isTaskDone();
    boolean isTaskReminder();
    String getName();

    @Override
    boolean equals(Object obj);
}
