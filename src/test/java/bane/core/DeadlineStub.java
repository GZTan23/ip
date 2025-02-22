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

    @Override
    public boolean isTaskReminder() {
        return false;
    }

    public void changeTaskStatus(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public void setReminder(boolean isReminder) {
        //do something
    }

    @Override
    public String toString() {
        String mark = this.isDone ? "X" : " ";
        return String.format("[D] [%s] [%s] %s", mark, " ", this.name);
    }
}
