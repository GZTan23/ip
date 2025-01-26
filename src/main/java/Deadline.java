public class Deadline extends Task{
    private String deadline;
    public Deadline(String dialogue) {
        super(dialogue);
        this.deadline = dialogue.split("/")[1].split(" ", 2)[1];
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", this.deadline);
    }
}
