package bane.core;

import java.util.ArrayList;

import bane.task.Task;

/**
 * Class that is part of TaskList, containing just the reminders
 */
class ReminderList {
    private ArrayList<Task> reminders;

    /**
     * Constructor for the ReminderList class
     *
     * @param tasks List of tasks.
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

    String listReminders() {
        StringBuilder sb = new StringBuilder();

        if (reminders.isEmpty()) {
            return Ui.replyToReminder("empty_reminders");
        }
        sb.append(Ui.replyToReminder("success"));
        for (int i = 1; i <= reminders.size(); i++) {
            sb.append(displayReminder(i));
        }

        return sb.toString();
    }

    String displayReminder(int idx) {
        Task reminder = reminders.get(idx - 1);
        return String.format("    %d. %s\n", idx, reminder);
    }

    void removeReminder(Task reminder) {
        this.reminders.remove(reminder);
        reminder.setReminder(false);

    }

    void addReminder(Task task) {
        task.setReminder(true);
        if (!this.reminders.contains(task)) {
            this.reminders.add(task);
        }
    }

}
