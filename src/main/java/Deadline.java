public class Deadline extends Task{
    private String deadline;
    public Deadline(String name, String deadline) throws TaskExecuteException {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", this.deadline);
    }
}
