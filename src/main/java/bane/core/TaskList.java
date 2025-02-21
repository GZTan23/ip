package bane.core;

import java.util.ArrayList;

import bane.task.Task;


/**
 * Keeps track of the task list and operates on the tasks
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private ReminderList reminders;

    /**
     * Constructor for TaskList class
     *
     * @param tasks ArrayList for storing of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.reminders = new ReminderList(tasks);
    }

    /**
     * Marks the task with the specified index on the list as done
     *
     * @param idx Index of the task on the list.
     */
    public void markTask(int idx) {
        tasks.get(idx - 1).changeTaskStatus(true);
    }

    /**
     * Unmarks the task with the specified index on the list
     *
     * @param idx Index of the task on the list.
     */
    public void unmarkTask(int idx) {
        tasks.get(idx - 1).changeTaskStatus(false);
    }

    /**
     * Adds a task to the list
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes a task from the list with the specified index
     *
     * @param idx Index of the task to delete from the list.
     */
    public void deleteTask(int idx) {
        tasks.remove(idx - 1);
    }

    /**
     * Prints out all the tasks currently in the list
     *
     * @return List of tasks as String.
     */
    public String listTasks() {
        StringBuilder sb = new StringBuilder();

        if (tasks.isEmpty()) {
            return Ui.replyToList("empty");
        }
        sb.append(Ui.replyToList("success"));
        for (int i = 1; i <= tasks.size(); i++) {
            sb.append(displayTask(i));
        }

        return sb.toString();
    }

    /**
     * Prints out all the reminders currently in the list
     *
     * @return List of reminders as String.
     */
    public String listReminders() {
        return reminders.listReminders();
    }

    /**
     * Prints out a specific task on the list
     *
     * @param idx Index of the task to display from the list.
     * @return String representation of task to be printed.
     */
    public String displayTask(int idx) {
        Task task = tasks.get(idx - 1);
        return String.format("    %d. %s\n", idx, task);
    }

    /**
     * Removes reminder from reminder list
     *
     * @param idx Index of reminder on the task list to be removed.
     */
    public void removeReminder(int idx) {
        Task task = tasks.get(idx - 1);
        reminders.removeReminder(task);
    }

    /**
     * Adds reminder to reminder list
     *
     * @param idx Index of task on the task list to add to reminder list.
     */
    public void addReminder(int idx) {
        Task task = tasks.get(idx - 1);
        reminders.addReminder(task);
    }

    /**
     * Finds task in list using keyword
     *
     * @param keyword String to match with the task description.
     * @return String representation of task found.
     */
    public String findTask(String keyword) {
        int count = 0;
        StringBuilder string = new StringBuilder();

        for (Task task : tasks) {
            boolean containsKeyword = task.getName().contains(keyword);
            if (containsKeyword) {
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
     *
     * @return boolean True if the list is empty, False if it isn't.
     */
    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    public int getTaskSize() {
        return this.tasks.size();
    }

    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

}
