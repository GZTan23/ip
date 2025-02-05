package bane.enums;

import java.time.format.DateTimeFormatter;

/**
 * A bunch of date formats used by the chatbot
 */
public enum DateTimeFormat {
    DISPLAY_FORMAT ("MMM d yyyy[ hh:mma]"),
    SAVE_FORMAT ("dd-MM-uuuu[ HH:mm]"),
    PARSE_FORMAT ("dd-MM-uuuu[ HH:mm]");

    private final String FORMAT;
    /**
     * Constructor for the DateTimeFormat class
     * @param s String containing the format to be used
     */
    private DateTimeFormat(String s) {
        this.FORMAT = s;
    }

    /**
     * Creates a formmatter for the specified date time format
     * @return DateTimeFormatter Formatter for the date time strings
     */
    public DateTimeFormatter formatter() {
        return DateTimeFormatter.ofPattern(this.FORMAT);
    }

    public String getFormat() {
        return this.FORMAT;
    }
}
