package bane.core;

import bane.task.Task;

/**
 * Handles most of the UI elements of the chatbot
 */
public class Ui {

    /**
     * Prints a separator between the chatbot and user input
     * @return String to be printed out
     */
    public static String separateLine() {
        //return "________________________________________________________________\n";
        return "";
    }

    /**
     * Prints the greeting message
     * @return String to be printed out
     */
    public static String greetUser() {
        StringBuilder sb = new StringBuilder();
        sb.append(separateLine());
        sb.append("""
                Hello, it is me, Bane.
                Why have you called upon me?
                """);
        sb.append(separateLine());
        return sb.toString();
    }

    /**
     * Prints the sayFarewell message
     * @return String to be printed out
     */
    public static String sayFarewell() {
        StringBuilder sb = new StringBuilder();
        sb.append(separateLine());
        sb.append(("Bye, hope to not see you again.\n"));
        sb.append(separateLine());
        return sb.toString();
    }

    /**
     * Prints a reply to the list command
     * @param type Type of reply to be printed
     * @return String to be printed out
     */
    public static String replyToList(String type) {
        String string = "";
        switch (type) {
        case "empty":
            string = "What were you expecting? A present? It's empty!\n";
            break;

        case "success":
            string = "Reminding you of things you have already forgotten:\n";
            break;

        case "wrong_format":
            string = """
                    Wrong Format.
                    Do you even remember the format for this command?
                    Format: list [reminders/tasks]
                    """;
        default:
            break;
        }
        return string;
    }

    /**
     * Prints a reply to the mark/unmark command
     * @param type Type of reply to be printed
     * @return String to be printed out
     */
    public static String replyToMark(String type) {
        String string = "";

        switch (type) {
        case "marked":
            string = "Finally getting work done eh?\n";
            break;

        case "unmarked":
            string = "As expected, you didn't do it and tried to cheat.\n";
            break;

        case "index_out_of_bounds":
            string = """
                Mark Non-Existent Entry.
                You're trying to unmark/mark something that doesn't exist!
                """;
            break;

        case "wrong_format":
            string = """
                Wrong Format.
                Nobody understood that instruction.
                Format: mark/unmark task/reminder [task index]
                """;
            break;
        default:
            break;
        }

        return string;
    }

    /**
     * Prints a reply to the various task commands
     * @param type Type of reply to be printed
     * @return String to be printed out
     */
    public static String replyToTasks(String type) {
        String string = "";

        switch (type) {
        case "fail":
            string = "Wow, you're bad at this. Try again.\n";
            break;

        case "empty command":
            string = """
                Empty Command.
                You have to input something else other than the command itself,
                just in case you have forgotten.
                Format: [command] [task] <duration if applicable>
                """;
            break;
        default:
            break;
        }

        return string;
    }

    /**
     * Prints a reply to the various task commands
     * @param type Type of reply to be printed
     * @param task Task to be printed out
     * @param alSize Size of the task list
     * @return String to be printed out
     */
    public static String replyToTasks(String type, Task task, int alSize) {
        if (type.equals("success")) {
            StringBuilder sb = new StringBuilder("Added to list of things to \"forget\",\n\n");
            sb.append("  ");
            sb.append(task);
            sb.append(String.format("\n\nwhich makes the total: %d.\n", alSize));
            return sb.toString();
        }
        return "";
    }

    /**
     * Prints a reply to the delete command
     * @param type Type of reply to be printed
     * @return String to be printed out
     */
    public static String replyToDelete(String type) {
        String string = "";

        switch (type) {
        case "success":
            string = "Giving up are we? You disappoint me.\n";
            break;

        case "empty_command":
            string = """
                I do not understand how it is so hard to be correct.
                Format: delete [integer]
                """;
            break;

        case "delete_out_of_bounds":
            string = "You are trying to delete something that isn't even there.\n";
            break;
        default:
        }

        return string;
    }

    /**
     * Prints a reply when the command is unknown
     * @return String to be printed out
     */
    public static String replyToUnknownInput() {
        return """
            Unknown Input.
            I fail to comprehend the inner machinations of the
            thing you call a brain. Try again
            """;
    }

    /**
     * Prints a reply when loading the tasks from the file
     * @param type Type of reply to be printed
     * @return String to be printed out
     */
    public static String replyToLoadFile(String type) {
        String string = "";

        switch (type) {
        case "success":
            string = """
                    Added new file "./data/Bane.txt" because I
                    clearly have to do everything for you.
                    """;
            break;

        case "file_creation_fail":
            string = """
                    It seems that you're on your own.
                    Create "./data/Bane.txt" and wake me up when done.
                    """;
            break;

        case "read_file_error":
            string = """
                Gahhhh! Something went wrong with the file!
                Fix it and get back to me dirtbag!
                """;
            break;
        default:
        }

        return string;
    }

    /**
     * Prints a reply when saving the tasks to the file
     * @param type Type of reply to be printed
     * @return String to be printed out
     */
    public static String replyToSaveFile(String type) {
        String string = "";

        switch (type) {
        case "write_error":
            string = """
                    Looks like there was an error whilst saving
                    Heh! I'll leave you to handle it :P
                    """;
            break;

        case "file_open_error":
            string = """
                    Looks like your files aren't working like they used to.
                    """;
            break;

        case "success":
            string = """
                    Saved current tasks because you will forget it
                    """;
            break;

        default:
        }

        return string;
    }

    /**
     * Prints a reply to finding a task
     * @param type Type of reply to be printed
     * @return String to be printed out
     */
    public static String replyToFind(String type) {
        String string = "";

        switch (type) {
        case "success":
            string = "Looking for these?\n";
            break;

        case "empty_command":
            string = """
                Empty Command.
                You still don't get it do you?
                Format: find [keyword/phrase]
                """;
            break;

        case "not_found":
            string = "Can't seem to find anything...\n";
            break;

        default:
        }

        return string;
    }

    /**
     * Prints a reply to reminders
     * @param type Type of reply to be printed
     * @return String to be printed out
     */
    public static String replyToReminder(String type) {
        String string = "";
        switch (type) {
        case "success":
            string = """
                    I don't get paid enough for this but here are
                    your reminders:
                    """;
            break;

        case "empty_reminders":
            string = """
                    Nothing for you to do eh?
                    """;
            break;

        case "add_success":
            string = """
                    You're asking me to nag you?
                    """;
            break;

        case "remove_success":
            string = """
                    Now you want me to stop nagging?
                    """;
            break;

        default:
        }

        return string;
    }


}
