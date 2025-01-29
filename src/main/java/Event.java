public class Event extends Task {
    private String start, end;

    public Event(String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;
    } 

    public String getStart() {
        return this.start;
    }

    public String getEnd() {
        return this.end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (from: %s to: %s)", start, end);
    }
}
