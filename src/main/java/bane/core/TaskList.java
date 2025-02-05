package bane.core;

import bane.task.Task;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> al;

    public TaskList(ArrayList<Task> al) {
        this.al = al;
    }

    public void markTask(int idx) {
        al.get(idx - 1).changeTaskStatus(true);
        Ui.markReply("marked");
    }

    public void unmarkTask(int idx) {
		al.get(idx - 1).changeTaskStatus(false);
		Ui.markReply("unmarked");
    }

    public void addTask(Task t) {
        this.al.add(t);
		Ui.taskReply("success", t, al.size());
    }

    public void deleteTask(int idx) {
		al.remove(idx-1);
    }

    public void listTasks() {
        for (int i = 1; i <= al.size(); i++) {
            displayTask(i);
        }	
    }

    public void displayTask(int idx) {
        System.out.print(String.format("    %d. %s\n", idx, al.get(idx - 1)));
    }

    public boolean isEmpty() {
        return this.al.isEmpty();
    }

    public int getSize() {
        return this.al.size();
    }

    public  ArrayList<Task> getList() {
        return this.al;
    }

}
