package bane.core;

import bane.gui.DialogBox;

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
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadTasks());
        parser = new Parser(tasks);
        run();
    }

    /**
     * Runs the chatbot
     */
    public void run() {
        String s = Ui.greetUser();
    }

    /**
     * Stops the chatbot
     */
    public void stop() {
        storage.saveTasks(tasks.getList());
    }

    public String getResponse(String input) {
        return parser.parseDialogue(input);
    }
}
