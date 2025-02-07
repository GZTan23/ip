package bane.task;

import bane.enums.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;

/**
 * Class for tasks with a start and end time
 */
public class Event implements Task {
    private String name;
    private boolean isDone;
    private TemporalAccessor start, end;
    private final DateTimeFormatter PARSER = DateTimeFormat.PARSE_FORMAT.formatter();
    private final DateTimeFormatter DISPLAYER = DateTimeFormat.DISPLAY_FORMAT.formatter();

    /**
     * Constructor for the Event class
     * @param name Name of the event
     * @param start Start date time of the event
     * @param end End date time of the event
     * @throws DateTimeParseException if the given string is not of correct format
     */
    public Event(String name, String start, String end) throws DateTimeParseException {
        this.name = name.trim();
        this.isDone = false;
        this.start = PARSER.parseBest(start.trim(), LocalDateTime::from,
                LocalDate::from, LocalTime::from);
        this.end = PARSER.parseBest(end.trim(), LocalDateTime::from,
                LocalDate::from, LocalTime::from);
    }

    public TemporalAccessor getStart() {
        return this.start;
    }

    public TemporalAccessor getEnd() {
        return this.end;
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
        return String.format("[E][%s] %s (from: %s to: %s)", (isDone ? "X" : " "), this.name,
                DISPLAYER.format(getStart()), DISPLAYER.format(getEnd()));
    }
}
