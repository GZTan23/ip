import java.util.ArrayList;
import java.util.Scanner;
public class Bane {     
	public static ArrayList<Task> al = new ArrayList<>(); 
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		greeting(); 

		String input; 
		do {  
			input = sc.nextLine();
			response(input);

		} while(!input.startsWith("bye"));
		
		sc.close();
	}
	
	
	public static void response(String dialogue) {

		if (dialogue.startsWith("bye")) {
			responseTemplate("Bye, hope to not see you again.\n");
		
		} else if (dialogue.startsWith("list")) {

			if (al.isEmpty()) {
				responseTemplate("what were you expecting? A present? It's empty!");
			} else {
				StringBuilder sb = new StringBuilder("Reminding you of things you have already forgetten:\n");
				for (int i = 1; i <= al.size(); i++) {
					sb.append(String.format("    %d. %s\n", i, al.get(i - 1)));
				}
				responseTemplate(sb.toString());
			
			
			}
		} else if ((dialogue.startsWith("mark")) |
				(dialogue.startsWith("unmark"))) {
			
			try {
				String[] arr = dialogue.split(" ");
				int num = Integer.parseInt(arr[1]);
				StringBuilder sb;

				if (arr[0].equals("mark")) {
					al.get(num - 1).taskStatus(true);
					sb = new StringBuilder("Finally getting work done eh?\n");
					
				} else {
					al.get(num - 1).taskStatus(false);
					sb = new StringBuilder("As expected, you didn't do it and tried to cheat.\n");

				}

				sb.append("    " + num + "." + al.get(num - 1));
				responseTemplate(sb.toString());
			} catch (IndexOutOfBoundsException e) {
				responseTemplate("""
						You're trying to unmark/mark something that doesn't exist!
						Format: unmark/mark [task index]""");
			}

		} else if ((dialogue.startsWith("todo")) ||
				(dialogue.startsWith("deadline")) ||
				(dialogue.startsWith("event"))) {
					try {  
						executeTasks(dialogue);
					} catch (TaskExecuteException e) {
						responseTemplate(e.toString() + "\nWow, you're bad at this. Try again.");
					}
		}else if (dialogue.startsWith("delete")) {
			try {
				int index = Integer.parseInt(dialogue.split(" ")[1]);
				al.remove(index-1);
				responseTemplate("Giving up are we? You disappoint me.");
			} catch (ArrayIndexOutOfBoundsException e) {
				responseTemplate("""
						Format: delete [integer]
						I do not understand how it is so hard to be correct.""");
			} catch (IndexOutOfBoundsException e) {
				responseTemplate("You are trying to delete something that isn't even there.");
			} 

		} else {
			responseTemplate("I fail to comprehend the inner machinations of the \nthing you call a brain. Try again");
		}

	}

	public static void responseTemplate(String message) {
		System.out.println("________________________________________________________________\n");
		System.out.println(message);	
		System.out.println("________________________________________________________________\n");
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
		responseTemplate("""
				Hello, it is me, Bane.
				Why have you called upon me?\n""");
	}

	public static void replyToTasks(Task t) {
		StringBuilder sb = new StringBuilder("Added to list of things to \"forget\",\n");
		sb.append("  " + t);
		sb.append(String.format("\nwhich makes the total: %d.\n",al.size()));
		responseTemplate(sb.toString());
	}
	
	public static void executeTasks(String dialogue) throws TaskExecuteException {
		String[] diagParts = dialogue.split(" ", 2);
		if (diagParts.length < 2) {
			responseTemplate("""
					You have to input something else other than the command itself,
					just in case you have forgotten. 
					Format: [command] [task] <duration if applicable> """);
		} else {
			switch (diagParts[0]) {
			case "todo":
				ToDo tTask = new ToDo(diagParts[1]);
				al.add(tTask);
				replyToTasks(tTask);
				break;
			case "event":
       			try {
					//split the rest of the string without the command in front
       				String[] taskParts = diagParts[1].split("/");
					
					//check if user has entered strictly following the format
					if (!((taskParts[1].startsWith("from")) && (taskParts[2].startsWith("to")))) {
						System.out.println("hi");
						throw new TaskExecuteException("Format: event [task] /from [time] /to [time]");
					}
       			    String start = taskParts[1].split(" ", 2)[1];
       			    String end =  taskParts[2].split(" ", 2)[1] ;
					
					Event eTask = new Event(taskParts[0], start, end);
					al.add(eTask);
					replyToTasks(eTask);
					} catch (ArrayIndexOutOfBoundsException e) {
						throw new TaskExecuteException("Format: event [task] /from [time] /to [time]");
					}
					break;
			case "deadline":
				try {
					String[] taskParts = diagParts[1].split("/");
					String deadline = taskParts[1].split(" ", 2)[1];
					
					Deadline dTask = new Deadline(taskParts[0], deadline);
					al.add(dTask);
					replyToTasks(dTask);
					
				} catch (ArrayIndexOutOfBoundsException e) {
					throw new TaskExecuteException("Format: deadline [task] /by [deadline]");
				}
				break;
			}
		}
	}


}
