package bane.core;

import bane.task.Task;

public class DeadlineStub implements Task{
    private String name;
    private boolean isDone;

    public DeadlineStub(String name, boolean isDone) {
       this.name = name;
       this.isDone = isDone;
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
}
