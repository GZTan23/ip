package bane.enums;

import java.time.format.DateTimeFormatter;

/**
 * A bunch of date formats used by the chatbot
 */
public enum DateTimeFormat {
    DISPLAY_FORMAT("MMM d yyyy[ hh:mma]"),
    SAVE_FORMAT("d-M-uuuu[ HH:mm]"),
    PARSE_FORMAT("d-M-uuuu[ HH:mm]");

    private final String FORMAT;
    /**
     * Constructor for the DateTimeFormat class
     * @param string String containing the format to be used
     */
    DateTimeFormat(String string) {
        this.FORMAT = string;
    }

    /**
     * Creates a formatter for the specified date time format
     * @return DateTimeFormatter Formatter for the date time strings
     */
    public DateTimeFormatter formatter() {
        return DateTimeFormatter.ofPattern(this.FORMAT);
    }

    public String getFormat() {
        return this.FORMAT;
    }
}
