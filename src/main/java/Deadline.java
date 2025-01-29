public class Deadline extends Task{
    private String deadline;
    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return this.deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", this.deadline);
    }
}
