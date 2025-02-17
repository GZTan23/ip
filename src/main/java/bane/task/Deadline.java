package bane.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;

import bane.enums.DateTimeFormat;

/**
 * Class for tasks with a deadline
 */
public class Deadline implements Task {
    private TemporalAccessor deadline;
    private String name;
    private boolean isDone;
    private boolean isReminder;


    /**
     * Constructor for the Deadline class
     * @param name Name of the task
     * @param deadline Deadline of the task
     * @throws DateTimeParseException if the string given is not of the correct format
     */
    public Deadline(String name, String deadline) throws DateTimeParseException {
        this.name = name.trim();
        this.isDone = false;
        this.isReminder = false;
        this.deadline = PARSER.parseBest(deadline.trim(), LocalDateTime::from,
                LocalDate::from, LocalTime::from);
    }

    public TemporalAccessor getDeadline() {
        return this.deadline;
    }

    public String getName() {
        return this.name;
    }

    public boolean isTaskDone() {
        return this.isDone;
    }

    public boolean isTaskReminder() {
        return this.isReminder;
    }

    public void changeTaskStatus(boolean isDone) {
        this.isDone = isDone;
    }

    public void setReminder(boolean isReminder) {
        this.isReminder = isReminder;
    }

    @Override
    public String toString() {
        String formattedDeadline = DISPLAYER.format(getDeadline());
        String mark = this.isDone ? "X" : " ";
        String reminder = this.isReminder ? "!" : " ";
        return String.format("[D] [%s] [%s] %s (by: %s)", mark, reminder, this.name,
                formattedDeadline);
    }

    /**
     * Checks if two Deadlines are equal
     * @param obj Deadline to be compared to
     * @return true if name is equal, marked and reminder status are the same,
     * and deadline is the same
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Deadline deadline)) {
            return false;
        }

        boolean isNameEqual = this.name.equals(deadline.name);
        boolean isMarkSame = this.isDone == (deadline.isDone);
        boolean isReminder = this.isReminder == (deadline.isReminder);
        boolean isSameDate = this.deadline.equals(deadline.deadline);

        return isNameEqual && isMarkSame && isReminder && isSameDate;
    }
}
