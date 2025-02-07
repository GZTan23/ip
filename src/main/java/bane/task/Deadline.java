package bane.task;

import bane.enums.DateTimeFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;

/**
 * Class for tasks with a deadline
 */
public class Deadline implements Task{
    private TemporalAccessor deadline;
    private String name;
    private boolean isDone;
    private final DateTimeFormatter PARSER = DateTimeFormat.PARSE_FORMAT.formatter();
    private final DateTimeFormatter DISPLAYER = DateTimeFormat.DISPLAY_FORMAT.formatter();

    /**
     * Constructor for the Deadline class
     * @param name Name of the task
     * @param deadline Deadline of the task
     * @throws DateTimeParseException if the string given is not of the correct format
     */
    public Deadline(String name, String deadline) throws DateTimeParseException {
        this.name = name.trim();
        this.isDone = false;
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

    public void changeTaskStatus(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", (this.isDone ? "X" : " "), this.name,
                DISPLAYER.format(getDeadline()));
    }
}
