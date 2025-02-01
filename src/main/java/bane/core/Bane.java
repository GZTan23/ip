package core;

import java.util.Scanner;

public class Bane {     

	private static Parser parser;
	private static Storage storage;
	private static TaskList tasks;

	public Bane(String filePath) {
		storage = new Storage(filePath);
		tasks = new TaskList(storage.loadTasks());
		parser = new Parser(tasks);
	}

	public void run() {
		try (Scanner sc = new Scanner(System.in)) {
			Ui.greeting();
			
			String input;
			do {
				input = sc.nextLine();
				parser.parseDialog(input);
				
			} while(!input.startsWith("bye"));
			
			storage.saveTasks(tasks.getList());
		}
	}

	public static void main(String[] args) {
		new Bane("./data/Bane.txt").run();
	}
	
}
