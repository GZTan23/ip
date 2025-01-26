public class Event extends Task {
    private String start, end;

    public Event(String dialogue) throws TaskExecuteException {
        super(dialogue.split("/")[0]);
        try {
            String[] diagParts = dialogue.split("/");
            this.start = diagParts[1].split(" ", 2)[1];
            this.end =  diagParts[2].split(" ", 2)[1] ;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new TaskExecuteException("Format: event [task] //from [time] //to [time]");
        }
    } 

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (from: %sto: %s)", start, end);
    }
}
