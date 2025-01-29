import java.util.ArrayList;
import java.util.Scanner;

public class Bane {     
	public static ArrayList<Task> al = new ArrayList<>(); 
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		greeting(); 
		loadTasks();
		String input; 
		do {  
			input = sc.nextLine();
			response(input);

		} while(!input.startsWith("bye"));
		
		sc.close();
	}
	
	
	public static void response(String dialogue) {

		System.out.println("___________________________________________________\n");
		if (dialogue.startsWith("bye")) {
			System.out.println("Bye, hope to not see you again.\n");
		
		} else if (dialogue.startsWith("list")) {

			if (al.isEmpty()) {
				System.out.println("what were you expecting? A present? It's empty!");
			} else {
				System.out.println("Reminding you of things you have already forgetten\n");
				for (int i = 1; i <= al.size(); i++) {
					System.out.println("    " + i + "." + al.get(i - 1));
				}
			
			}
		} else if (dialogue.startsWith("mark")) {
			String[] arr = dialogue.split(" ");
			int num = Integer.parseInt(arr[1]);
			al.get(num - 1).taskStatus(true);
			System.out.println("Finally getting work done eh?\n"); 
			System.out.println("    " + num + "." + al.get(num - 1));

		} else if (dialogue.startsWith("unmark")) {
			String[] arr = dialogue.split(" ");
			int num = Integer.parseInt(arr[1]);
			al.get(num - 1).taskStatus(false);;
			System.out.println("As expected, you didn't do it and tried to cheat\n");
			System.out.println("    " + num + "." + al.get(num - 1));

		} else if ((dialogue.startsWith("todo")) ||
				(dialogue.startsWith("deadline")) ||
				(dialogue.startsWith("event"))) {
					try {  
						executeTasks(dialogue);
					} catch (TaskExecuteException e) {
						System.out.println(e.toString());
						System.out.println("Wow, you're bad at this. Try again.");
					}
		}else if (dialogue.startsWith("delete")) {
			try {
				int index = Integer.parseInt(dialogue.split(" ")[1]);
				al.remove(index-1);
				System.out.println("Giving up are we? You disappoint me.");
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Format: delete [integer]");
				System.out.println("I do not understand how it is so hard to be correct");
			} catch (IndexOutOfBoundsException e) {
				System.out.println("You are trying to delete something that isn't even there.");
			} 

		} else {
			System.out.println("I fail to comprehend the inner machinations of the \nthing you call a brain. Try again");
		}
		System.out.println("___________________________________________________\n");

	}
	

	public static void greeting() {
		String logo ="\n\t▄▄▄▄    ▄▄▄       ███▄    █ ▓█████  \n"
					+  "\t▓█████▄ ▒████▄     ██ ▀█   █ ▓█   ▀ \n"
					+  "\t▒██▒ ▄██▒██  ▀█▄  ▓██  ▀█ ██▒▒███   \n"
					+  "\t▒██░█▀  ░██▄▄▄▄██ ▓██▒  ▐▌██▒▒▓█  ▄ \n"
					+  "\t░▓█  ▀█▓ ▓█   ▓██▒▒██░   ▓██░░▒████▒\n"
					+  "\t░▒▓███▀▒ ▒▒   ▓▒█░░ ▒░   ▒ ▒ ░░ ▒░ ░\n"
					+  "\t▒░▒   ░   ▒   ▒▒ ░░ ░░   ░ ▒░ ░ ░  ░\n"
					+  "\t░    ░   ░   ▒      ░   ░ ░    ░    \n"
					+  "\t░            ░  ░         ░    ░  ░ \n"
					+  "\t      ░                             \n";
		System.out.println(logo);
		System.out.println("___________________________________________________\n");
		System.out.println("Hello, it is me, Bane.");
		System.out.println("Why have you called upon me?\n");
		System.out.println("___________________________________________________\n");
	}

	public static void replyToTasks(Task t) {
		System.out.println("Added to list of things to \"forget\",\n");
		System.out.println("  " + t);
		System.out.println(String.format("\nwhich makes the total: %d\n",al.size()));
	}
	
	public static void executeTasks(String dialogue) throws TaskExecuteException {
		String[] diagParts = dialogue.split(" ", 2);
		if (diagParts.length < 2) {
			System.out.println("""
					You have to input something else other than the command itself,
					just in case you have forgotten. Format: [command] [task] <duration if applicable> """);
		} else {
			switch (diagParts[0]) {
			case "todo":
				ToDo t = new ToDo(diagParts[1]);
				al.add(t);
				replyToTasks(t);
				break;
			case "event":
				Event e = new Event(diagParts[1]);
				al.add(e);
				replyToTasks(e);
				break;
			case "deadline":
				Deadline d = new Deadline(diagParts[1]);
				al.add(d);
				replyToTasks(d);
				break;
			}
		}
	}

	public static void saveTasks() {
	
	}

	public static void loadTasks() {
	
	}
}
