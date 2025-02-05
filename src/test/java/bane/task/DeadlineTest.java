package bane.task;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAccessor;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class DeadlineTest {

    @Test
    public void testStringConversion() {
        Deadline deadline = new Deadline("return book", "19-08-2024 17:00");
        assertEquals("[D][ ] return book (by: Aug 19 2024 05:00pm)", deadline.toString());

    }

    @Test
    public void getDeadlineTest_validDate_TemporalAccessor() {
        Deadline deadline = new Deadline("return book", "19-08-2024 17:00");
        TemporalAccessor expected = DateTimeFormatter.ofPattern("dd-MM-uuuu[ HH:mm]").parse("19-08-2024 17:00");
        assertInstanceOf(TemporalAccessor.class, deadline.getDeadline());
    }
}
