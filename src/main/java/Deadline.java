public class Deadline extends Task{
    private String deadline;
    public Deadline(String dialogue) throws TaskExecuteException {
        super(dialogue.split("/")[0]);
        try {
            this.deadline = dialogue.split("/")[1].split(" ", 2)[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new TaskExecuteException("Format: deadline [task] /by [deadline]");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", this.deadline);
    }
}
