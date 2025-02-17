package bane.task;

/**
 * Class for tasks that do not have date or time
 */
public class ToDo implements Task {
    private String name;
    private boolean isDone;
    private boolean isReminder;

    /**
     * Constructor for the ToDo class
     * @param name Name of the task
     */
    public ToDo(String name) {
        this.name = name.trim();
        this.isDone = false;
        this.isReminder = false;
    }
    public String getName() {
        return this.name;
    }

    public boolean isTaskDone() {
        return this.isDone;
    }

    public boolean isTaskReminder() {
        return this.isReminder;
    }

    public void changeTaskStatus(boolean isDone) {
        this.isDone = isDone;
    }

    public void setReminder(boolean isReminder) {
        this.isReminder = isReminder;
    }

    @Override
    public String toString() {
        String mark = this.isDone ? "X" : " ";
        String reminder = this.isReminder ? "!" : " ";
        return String.format("[T] [%s] [%s] %s", mark, reminder, this.name);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ToDo todo)) {
            return false;
        }

        boolean isNameEqual = this.name.equals(todo.name);
        boolean isMarkSame = this.isDone == todo.isDone;
        boolean isReminder = this.isReminder == todo.isReminder;

        return isNameEqual && isMarkSame && isReminder;
    }
}
