package bane.core;

import bane.task.Task;
import java.util.ArrayList;
import java.lang.StringBuilder;

/**
 * Keeps track of the task list and operates on the tasks
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor for TaskList class
     * @param tasks ArrayList for storing of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Marks the task with the specified index on the list as done
     * @param idx Index of the task on the list
     */
    public void markTask(int idx) {
        tasks.get(idx - 1).changeTaskStatus(true);
        Ui.replyToMark("marked");
    }

    /**
     * Unmarks the task with the specified index on the list
     * @param idx Index of the task on the list
     */
    public void unmarkTask(int idx) {
		tasks.get(idx - 1).changeTaskStatus(false);
		Ui.replyToMark("unmarked");
    }

    /**
     * Adds a task to the list
     * @param task Task to be added
     */
    public void addTask(Task task) {
        this.tasks.add(task);
		Ui.replyToTasks("success", task, tasks.size());
    }

    /**
     * Deletes a task from the list with the specified index
     * @param idx Index of the task to delete from the list
     */
    public void deleteTask(int idx) {
		tasks.remove(idx-1);
    }

    /**
     * Prints out all the tasks currently in the list
     */
    public void listTasks() {
        for (int i = 1; i <= tasks.size(); i++) {
            displayTask(i);
        }	
    }

    /**
     * Prints out a specific task on the list
     * @param idx Index of the task to display from the list
     */
    public void displayTask(int idx) {
        System.out.print(String.format("    %d. %s\n", idx, tasks.get(idx - 1)));
    }

    public void findTask(String keyword) {
        int count = 0;
        StringBuilder string = new StringBuilder();
        for (Task task : tasks) {
            if (task.getName().contains(keyword)) {count++;
                string.append(count);
                string.append(". ");
                string.append(task.toString());
                string.append("\n");
            }
        }
        if (count == 0) {
            Ui.replyToFind("not_found");
        } else {
            Ui.replyToFind("success");
            System.out.println(string.toString());
        }
    }

    /**
     * Checks whether the list is empty
     * @return boolean True if the list is empty, False if it isn't
     */
    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    public int getSize() {
        return this.tasks.size();
    }

    public  ArrayList<Task> getList() {
        return this.tasks;
    }

}
