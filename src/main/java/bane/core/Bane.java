package bane.core;

import java.util.Scanner;

/**
 * Main class for the Bane chatbot
 */
public class Bane {

	private static Parser parser;
	private static Storage storage;
	private static TaskList tasks;

	/**
	 * Constructor for the Bane class
	 * Initialises the Storage, TaskList and Parser
	 * @param filePath File path to load and save the tasks from
	 */
	public Bane(String filePath) {
		storage = new Storage(filePath);
		tasks = new TaskList(storage.loadTasks());
		parser = new Parser(tasks);
	}

	/**
	 * Runs the chatbot
	 */
	public void run() {
		try (Scanner scanner = new Scanner(System.in)) {
			Ui.greetUser();
			
			String input;
			do {
				input = scanner.nextLine();
				parser.parseDialogue(input);
				
			} while(!input.startsWith("bye"));
			
			storage.saveTasks(tasks.getList());
		}
	}

	public static void main(String[] args) {
		new Bane("./data/Bane.txt").run();
	}
	
}
