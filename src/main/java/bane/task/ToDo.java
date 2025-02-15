package bane.task;

/**
 * Class for tasks that do not have date or time
 */
public class ToDo implements Task {
    private String name;
    private boolean isDone;

    /**
     * Constructor for the ToDo class
     * @param name Name of the task
     */
    public ToDo(String name) {
        this.name = name.trim();
        this.isDone = false;
    }
    public String getName() {
        return this.name;
    }

    public boolean isTaskDone() {
        return this.isDone;
    }

    public void changeTaskStatus(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        String mark = this.isDone ? "X" : " ";
        return String.format("[T][%s] %s", mark, this.name);
    }
}
