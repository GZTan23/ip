import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;

public class Deadline extends Task{
    private TemporalAccessor deadline;
    private final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd-MM-uuuu[ HH:mm]");

    
    public Deadline(String name, String deadline) throws DateTimeParseException {
        super(name);
        this.deadline = DATE_TIME_FORMAT.parseBest(deadline, LocalDateTime::from,
                LocalDate::from, LocalTime::from);
    }

    public String getDeadline() {
        return DateTimeFormatter.ofPattern("MMM d yyyy[ HH:mm]").format(deadline);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", getDeadline());
    }
}
