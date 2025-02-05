package bane.enums;

import java.time.format.DateTimeFormatter;

public enum DateTimeFormat {
    DISPLAY_FORMAT ("MMM d yyyy[ hh:mma]"),
    SAVE_FORMAT ("dd-MM-uuuu[ HH:mm]"),
    PARSE_FORMAT ("dd-MM-uuuu[ HH:mm]");

    private DateTimeFormat(String s) {
        this.FORMAT = s;
    }
    private final String FORMAT;

    public DateTimeFormatter formatter() {
        return DateTimeFormatter.ofPattern(this.FORMAT);
    }

    public String getFormat() {
        return this.FORMAT;
    }
}
