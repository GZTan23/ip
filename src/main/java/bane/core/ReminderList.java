package bane.core;

import bane.task.Task;
import java.util.ArrayList;

public class ReminderList {
    private ArrayList<Task> reminders;

    /**
     * Constructor for the ReminderList class
     * @param tasks List of tasks
     */
    ReminderList(ArrayList<Task> tasks) {
        ArrayList<Task> reminders = new ArrayList<>();
        for (Task task : tasks) {
            if (task.isTaskReminder()) {
                reminders.add(task);
            }
        }

        this.reminders = reminders;
    }

    /**
     * Prints out all the reminders currently in the list
     * @return List of reminders as String
     */
    public String listReminders() {
        StringBuilder sb = new StringBuilder();

        if (reminders.isEmpty()) {
            return Ui.replyToReminder("empty");
        }
        sb.append(Ui.replyToReminder("success"));
        for (int i = 1; i <= reminders.size(); i++) {
            sb.append(displayReminder(i));
        }

        return sb.toString();
    }

    /**
     * Prints out a specific reminder on the list
     * @param idx Index of the reminder to display from the list
     */
    public String displayReminder(int idx) {
        Task reminder= reminders.get(idx - 1);
        return String.format("    %d. %s\n", idx, reminder);
    }

    /**
     * Removes reminder from reminder list
     * @param reminder Reminder to be removed
     */
    public String removeReminder(Task reminder) {
        reminder.setReminder(false);
        this.reminders.remove(reminder);
        return Ui.replyToReminder("remove_success");
    }

    /**
     * Adds reminder to reminder list
     * @param task Index of task on the task list to add to reminder list
     */
    public String addReminder(Task task) {
        task.setReminder(true);
        this.reminders.add(task);
        return Ui.replyToReminder("add_success");
    }

}
