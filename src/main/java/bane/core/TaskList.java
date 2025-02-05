package bane.core;

import bane.task.Task;
import java.util.ArrayList;

/**
 * Keeps track of the task list and operates on the tasks
 */
public class TaskList {
    private ArrayList<Task> arraylist;

    /**
     * Constructor for TaskList class
     * @param arrayList ArrayList for storing of tasks
     */
    public TaskList(ArrayList<Task> arrayList) {
        this.arraylist = arrayList;
    }

    /**
     * Marks the task with the specified index on the list as done
     * @param idx Index of the task on the list
     */
    public void markTask(int idx) {
        arraylist.get(idx - 1).changeTaskStatus(true);
        Ui.markReply("marked");
    }

    /**
     * Unmarks the task with the specified index on the list
     * @param idx Index of the task on the list
     */
    public void unmarkTask(int idx) {
		arraylist.get(idx - 1).changeTaskStatus(false);
		Ui.markReply("unmarked");
    }

    /**
     * Adds a task to the list
     * @param task Task to be added
     */
    public void addTask(Task task) {
        this.arraylist.add(task);
		Ui.taskReply("success", task, arraylist.size());
    }

    /**
     * Deletes a task from the list with the specified index
     * @param idx Index of the task to delete from the list
     */
    public void deleteTask(int idx) {
		arraylist.remove(idx-1);
    }

    /**
     * Prints out all the tasks currently in the list
     */
    public void listTasks() {
        for (int i = 1; i <= arraylist.size(); i++) {
            displayTask(i);
        }	
    }

    /**
     * Prints out a specific task on the list
     * @param idx Index of the task to display from the list
     */
    public void displayTask(int idx) {
        System.out.print(String.format("    %d. %s\n", idx, arraylist.get(idx - 1)));
    }

    /**
     * Checks whether the list is empty
     * @return boolean True if the list is empty, False if it isn't
     */
    public boolean isEmpty() {
        return this.arraylist.isEmpty();
    }

    public int getSize() {
        return this.arraylist.size();
    }

    public  ArrayList<Task> getList() {
        return this.arraylist;
    }

}
