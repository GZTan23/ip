package bane.core;

import java.util.ArrayList;
import bane.exception.StorageException;

/**
 * Main class for the Bane chatbot
 */
public class Bane {

    private Parser parser;
    private Storage storage;
    private TaskList tasks;
    private String filePath = "./data/Bane.txt";

    /**
     * Constructor for the Bane class
     * Initialises the Storage, TaskList and Parser
     */
    public Bane() {
    }

    /**
     * Runs the chatbot
     */
    public ArrayList<String> run() throws StorageException {
        assert filePath != null;

        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadTasks());
        parser = new Parser(tasks);

        assert storage != null && tasks != null && parser != null;

        String s = Ui.greetUser();
        ArrayList<String> replyList = new ArrayList<String>();
        replyList.add(s);
        String reminderReply = tasks.listReminders();
        replyList.add(reminderReply);

        return replyList;
    }

    /**
     * Stops the chatbot
     * @return String if exception occurs when saving
     */
    public String stop() throws StorageException{
        return storage.saveTasks(tasks.getTaskList());
    }

    public String getResponse(String input) {
        assert input != null;
        return parser.parseDialogue(input);
    }
}
