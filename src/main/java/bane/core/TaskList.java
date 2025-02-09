package bane.core;

import java.util.ArrayList;

import bane.task.Task;


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
    }

    /**
     * Unmarks the task with the specified index on the list
     * @param idx Index of the task on the list
     */
    public void unmarkTask(int idx) {
        tasks.get(idx - 1).changeTaskStatus(false);
    }

    /**
     * Adds a task to the list
     * @param task Task to be added
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes a task from the list with the specified index
     * @param idx Index of the task to delete from the list
     */
    public void deleteTask(int idx) {
        tasks.remove(idx - 1);
    }

    /**
     * Prints out all the tasks currently in the list
     * @return List of tasks as String
     */
    public String listTasks() {
        String string = "";

        for (int i = 1; i <= tasks.size(); i++) {
            string += displayTask(i);
        }

        return string;
    }

    /**
     * Prints out a specific task on the list
     * @param idx Index of the task to display from the list
     */
    public String displayTask(int idx) {
        return String.format("    %d. %s\n", idx, tasks.get(idx - 1));
    }

    /**
     * Finds task in list using keyword
     * @param keyword String to match with the task description
     * @return String representation of task found
     */
    public String findTask(String keyword) {
        int count = 0;
        StringBuilder string = new StringBuilder();

        for (Task task : tasks) {
            if (task.getName().contains(keyword)) {
                count++;
                string.append(count);
                string.append(". ");
                string.append(task);
                string.append("\n");
            }
        }
        if (count == 0) {
            return Ui.replyToFind("not_found");
        } else {
            string.insert(0, Ui.replyToFind("success"));
        }

        return string.toString();
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

    public ArrayList<Task> getList() {
        return this.tasks;
    }

}
