package task;

import enums.DateTimeFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;

public class Deadline extends Task{
    private TemporalAccessor deadline;
    private final DateTimeFormatter PARSER = DateTimeFormat.PARSE_FORMAT.formatter(); 
    private final DateTimeFormatter DISPLAYER = DateTimeFormat.DISPLAY_FORMAT.formatter();

    
    public Deadline(String name, String deadline) throws DateTimeParseException {
        super(name.trim());
        this.deadline = PARSER.parseBest(deadline.trim(), LocalDateTime::from,
                LocalDate::from, LocalTime::from);
    }

    public TemporalAccessor getDeadline() {
        return this.deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", 
                DISPLAYER.format(getDeadline()));
    }
}
