import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;

public class Event extends Task {
    private TemporalAccessor start, end;
    private final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd-MM-uuuu[ HH:mm]");

    public Event(String name, String start, String end) throws DateTimeParseException {
        super(name);
        this.start = DATE_TIME_FORMAT.parseBest(start, LocalDateTime::from,
                LocalDate::from, LocalTime::from);
        this.end = DATE_TIME_FORMAT.parseBest(end, LocalDateTime::from,
                LocalDate::from, LocalTime::from);
    } 

    public String getStart() {
        return DateTimeFormatter.ofPattern("MMM d yyyy[ HH:mm]").format(start);
    }

    public String getEnd() {
        return DateTimeFormatter.ofPattern("MMM d yyyy[ HH:mm]").format(end);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (from: %s to: %s)", getStart(), getEnd());
    }
}
