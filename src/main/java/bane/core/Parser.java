package bane.core;

import java.time.format.DateTimeParseException;

import bane.exception.TaskException;
import bane.task.Deadline;
import bane.task.Event;
import bane.task.ToDo;

/**
 * Parses and handles user input
 */
public class Parser {
    private TaskList tasks;

    /**
     * Constructor for Parser class
     *
     * @param tasks TaskList to add tasks to the list.
     */
    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Parses the command part of the user input
     *
     * @param dialogue User input.
     * @return String to be printed out.
     */
    public String parseDialogue(String dialogue) {
        StringBuilder sb = new StringBuilder();

        assert dialogue != null;

        if (dialogue.startsWith("bye")) {
            sb.append(Ui.sayFarewell());

        } else if (dialogue.startsWith("list")) {
            sb.append(parseList(dialogue));

        } else if ((dialogue.startsWith("mark"))
                | (dialogue.startsWith("unmark"))) {

            sb.append(parseMark(dialogue));

        } else if ((dialogue.startsWith("todo"))
                || (dialogue.startsWith("deadline"))
                || (dialogue.startsWith("event"))) {

            sb.append(parseTasks(dialogue));

        } else if (dialogue.startsWith("delete")) {
            sb.append(parseDelete(dialogue));

        } else if (dialogue.startsWith("find")) {
            sb.append(parseFind(dialogue));

        } else {
            sb.append(Ui.replyToUnknownInput());
        }

        return sb.toString();
    }


    /**
     * Parses user input for the task name and date if any
     *
     * @param dialogue User input.
     * @return String to be printed out.
     */
    public String parseTasks(String dialogue) {
        StringBuilder sb = new StringBuilder();


        try {
            String[] diagParts = dialogue.split(" ", 2);

            if (diagParts.length < 2 || diagParts[1].isEmpty()) {
                sb.append(Ui.replyToTasks("empty command"));
                return sb.toString();
            }
            switch (diagParts[0]) {
            case "todo":
                ToDo tTask = new ToDo(diagParts[1]);
                tasks.addTask(tTask);
                sb.append(Ui.replyToTasks("success", tTask, tasks.getTaskSize()));
                break;

            case "event":
                sb.append(parseEvent(diagParts));
                break;

            case "deadline":
                sb.append(parseDeadline(diagParts));
                break;

            default:
                throw new TaskException("Unknown Command.\n");
            }
        } catch (TaskException exception) {
            sb.append(exception.getMessage());
            sb.append(Ui.replyToTasks("fail"));

        }

        return sb.toString();
    }

    /**
     * Parses user input for the Deadline task
     *
     * @param diagParts Array of strings split from the user input.
     * @return Reply of whether the command has been completed successfully.
     * @throws TaskException if the input date and command is of wrong format.
     */
    private StringBuilder parseDeadline(String[] diagParts) throws TaskException {
        StringBuilder sb = new StringBuilder();

        try {
            String[] taskParts = diagParts[1].split("/");
            String deadline = taskParts[1].split(" ", 2)[1];

            Deadline dTask = new Deadline(taskParts[0], deadline);
            tasks.addTask(dTask);

            sb.append(Ui.replyToTasks("success", dTask, tasks.getTaskSize()));

        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new TaskException("Wrong Format.\n\nFormat: deadline [task] /by [deadline]");

        } catch (DateTimeParseException exception) {
            throw new TaskException(
                    "Wrong Date Format.\n\nFormat for time: [DD-MM-YYYY] [HH:mm].\nCan be date or both.");
        }

        return sb;
    }

    /**
     * Parses user input for the Event task
     *
     * @param diagParts Array of strings split from the user input.
     * @return Reply of whether the command has been completed successfully.
     * @throws TaskException if the input date and command is of wrong format.
     */
    private StringBuilder parseEvent(String[] diagParts) throws TaskException {
        StringBuilder sb = new StringBuilder();

        try {
            //split the rest of the string without the command in front
            String[] taskParts = diagParts[1].split("/");

            //check if user has entered strictly following the format
            boolean startsWithFrom = taskParts[1].startsWith("from");
            boolean startsWithTo = taskParts[2].startsWith("to");

            if (!(startsWithFrom && startsWithTo)) {
                throw new TaskException(
                        "Wrong Format.\n\nFormat: event [task] /from [time] /to [time]");
            }
            String start = taskParts[1].split(" ", 2)[1];
            String end = taskParts[2].split(" ", 2)[1];

            Event eTask = new Event(taskParts[0], start, end);
            tasks.addTask(eTask);
            sb.append(Ui.replyToTasks("success", eTask, tasks.getTaskSize()));

        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new TaskException("Wrong Format.\n\nFormat: event [task] /from [time] /to [time]");

        } catch (DateTimeParseException exception) {
            throw new TaskException(
                    "Wrong Date Format.\n\nFormat for time: [DD-MM-YYYY] [HH:mm].\nCan be date or both.");
        }

        return sb;
    }

    /**
     * Parses user input for the mark/unmark commands
     *
     * @param dialogue User input.
     * @return String to be printed out.
     */
    public String parseMark(String dialogue) {
        StringBuilder sb = new StringBuilder();

        try {
            String[] arr = dialogue.split(" ");
            boolean arrLenLess3 = arr.length < 3;

            if (arrLenLess3) {
                return Ui.replyToMark("wrong_format");

            } else {
                boolean isNotCorrectType = !(arr[1].equals("task") || arr[1].equals("reminder"));
                boolean isNotDigit = arr[2].matches("\\D*");

                if (isNotDigit || isNotCorrectType) {
                    return Ui.replyToMark("wrong_format");
                }
            }

            assert arr[2].matches("\\d+") : "mark should be followed by a number.";

            int idx = Integer.parseInt(arr[2]);
            String type = arr[1];
            assert idx > 0 : "idx should be more than 0";

            if (arr[0].equals("mark")) {
                if (type.equals("task")) {
                    tasks.markTask(idx);
                    String reply = Ui.replyToMark("marked");
                    sb.append(reply);

                } else {
                    tasks.addReminder(idx);
                    String reply = Ui.replyToReminder("add_success");
                    sb.append(reply);
                }
            } else {
                if (type.equals("task")) {
                    tasks.unmarkTask(idx);
                    String reply = Ui.replyToMark("unmarked");
                    sb.append(reply);

                } else {
                    tasks.removeReminder(idx);
                    String reply = Ui.replyToReminder("remove_success");
                    sb.append(reply);
                }
            }

            sb.append(tasks.displayTask(idx));

        } catch (IndexOutOfBoundsException exception) {
            String reply = Ui.replyToMark("index_out_of_bounds");
            sb.append(reply);
        }

        return sb.toString();
    }

    /**
     * Parses user input for the delete command
     *
     * @param dialogue User input.
     * @return String to be printed out.
     */
    public String parseDelete(String dialogue) {
        StringBuilder sb = new StringBuilder();

        try {
            String[] diagParts = dialogue.split(" ");

            int idx = Integer.parseInt(diagParts[1]);
            assert idx > 0 : "idx should be more than 0";

            tasks.deleteTask(idx);
            String reply = Ui.replyToDelete("success");
            sb.append(reply);

        } catch (ArrayIndexOutOfBoundsException exception) {
            String reply = Ui.replyToDelete("empty_command");
            sb.append(reply);

        } catch (IndexOutOfBoundsException exception) {
            String reply = Ui.replyToDelete("delete_out_of_bounds");
            sb.append(reply);
        }

        return sb.toString();
    }

    /**
     * Parses user input for the find command
     *
     * @param dialogue User input.
     * @return String to be printed out.
     */
    public String parseFind(String dialogue) {
        String[] diagParts = dialogue.split(" ", 2);

        if (diagParts.length < 2) {
            return Ui.replyToFind("empty_command");
        }

        String findTaskReply = tasks.findTask(diagParts[1]);
        return findTaskReply;
    }

    /**
     * Parse user input for the find command
     *
     * @param dialogue User input.
     * @return String to be printed out.
     */
    public String parseList(String dialogue) {
        String[] diagParts = dialogue.split(" ", 2);

        if (diagParts.length != 2) {
            return Ui.replyToList("wrong_format");
        } else {
            boolean isNotCorrectType = !(diagParts[1].equals("tasks")
                    || diagParts[1].equals("reminders"));
            if (isNotCorrectType) {
                return Ui.replyToList("wrong_format");
            }
        }

        String type = diagParts[1];
        String successReply = "";
        String list = "";

        switch (type) {
        case "tasks":
            list = tasks.listTasks();
            break;

        case "reminders":
            list = tasks.listReminders();
            break;

        default:
            assert false : "Something is wrong with parseList";
        }

        return successReply + list;
    }
}
