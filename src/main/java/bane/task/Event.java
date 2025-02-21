package bane.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;

/**
 * Class for tasks with a start and end time
 */
public class Event implements Task {
    private String name;

    private boolean isDone;
    private boolean isReminder;

    private TemporalAccessor start;
    private TemporalAccessor end;

    /**
     * Constructor for the Event class
     *
     * @param name Name of the event.
     * @param start Start date time of the event.
     * @param end End date time of the event.
     * @throws DateTimeParseException if the given string is not of correct format.
     */
    public Event(String name, String start, String end) throws DateTimeParseException {
        this.name = name.trim();
        this.isDone = false;
        this.isReminder = false;

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
        String formattedStartDate = DISPLAYER.format(getStart());
        String formattedEndDate = DISPLAYER.format(getEnd());
        String mark = isDone ? "X" : " ";
        String reminder = isReminder ? "!" : " ";
        return String.format("[E] [%s] [%s] %s (from: %s to: %s)", mark, reminder, this.name,
                formattedStartDate, formattedEndDate);
    }

    /**
     * Checks if two Events are equal
     *
     * @param obj Event to be compared to.
     * @return true if name is equal, marked and reminder status are the same,
     *      and start and end dates are the same.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Event event)) {
            return false;
        }

        boolean isNameEqual = this.name.equals(event.name);
        boolean isMarkSame = this.isDone == (event.isDone);
        boolean isReminder = this.isReminder == (event.isReminder);
        boolean isStartSame = this.start.equals(event.start);
        boolean isEndSame = this.end.equals(event.end);

        return isNameEqual && isMarkSame && isReminder && isStartSame && isEndSame;
    }
}
