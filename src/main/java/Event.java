public class Event extends Task {
    private String start, end;

    public Event(String name, String start, String end) throws TaskExecuteException {
        super(name);
        this.start = start;
        this.end = end;
    } 

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (from: %sto: %s)", start, end);
    }
}
