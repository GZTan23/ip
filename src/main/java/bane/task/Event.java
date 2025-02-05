package bane.task;

import bane.enums.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;

public class Event extends Task {
    private TemporalAccessor start, end;
    private final DateTimeFormatter PARSER = DateTimeFormat.PARSE_FORMAT.formatter(); 
    private final DateTimeFormatter DISPLAYER = DateTimeFormat.DISPLAY_FORMAT.formatter(); 


    public Event(String name, String start, String end) throws DateTimeParseException {
        super(name.trim());
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

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(), DISPLAYER.format(getStart()), DISPLAYER.format(getEnd())); 
    }
}
