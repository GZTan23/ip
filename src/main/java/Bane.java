import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Bane {     
	public static ArrayList<Task> al = new ArrayList<>(); 
	public static void main(String[] args) {

		try (Scanner sc = new Scanner(System.in)) {
			Ui.greeting();
			loadTasks();
			
			String input;
			do {
				input = sc.nextLine();
				response(input);
				
			} while(!input.startsWith("bye"));
			
			saveTasks();
		}


	
	}
	
	
	public static void response(String dialogue) {

		if (dialogue.startsWith("bye")) {
			Ui.farewell();
		
		} else if (dialogue.startsWith("list")) {
			Ui.separatorLine();
			
			if (al.isEmpty()) {
				
				Ui.listReply("empty");
			} else {

				Ui.listReply("success");
				for (int i = 1; i <= al.size(); i++) {
					System.out.print(String.format("    %d. %s\n", i, al.get(i - 1)));
				}	
			}

			Ui.separatorLine();	
		} else if ((dialogue.startsWith("mark")) |
				(dialogue.startsWith("unmark"))) {
			
			try {
				String[] arr = dialogue.split(" ");
				int idx = Integer.parseInt(arr[1]);

				Ui.separatorLine();

				if (arr[0].equals("mark")) {
					al.get(idx - 1).taskStatus(true);
					Ui.markReply("marked");	
				} else {
					al.get(idx - 1).taskStatus(false);
					Ui.markReply("unmarked");
				}

				System.out.println("    " + idx + "." + al.get(idx - 1));
				Ui.separatorLine();

			} catch (IndexOutOfBoundsException e) {
				Ui.separatorLine();
				System.out.println("Mark Non-Existent Entry.\n");
				Ui.markReply("index_out_of_bounds");
				Ui.separatorLine();

			}

		} else if ((dialogue.startsWith("todo")) ||
				(dialogue.startsWith("deadline")) ||
				(dialogue.startsWith("event"))) {
				Ui.separatorLine();
					
				try {  
					executeTasks(dialogue);

				} catch (TaskExecuteException e) {
					System.out.println(e.getMessage());
					Ui.taskReply("fail");

				}
				
				Ui.separatorLine();

		} else if (dialogue.startsWith("delete")) {
			Ui.separatorLine();

			try {
				int index = Integer.parseInt(dialogue.split(" ")[1]);
				al.remove(index-1);
				Ui.deleteReply("success");

			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Empty Command.\n");
				Ui.deleteReply("empty_command");
				
			} catch (IndexOutOfBoundsException e) {
				System.out.println("Delete Non-Existing Entry.\n");
				Ui.deleteReply("delete_out_of_bounds");
			} 
			
			Ui.separatorLine();

		} else {
			Ui.separatorLine();
			Ui.unknownInputReply();
			Ui.separatorLine();
		}

	}

	

	
	public static void executeTasks(String dialogue) throws TaskExecuteException {
		String[] diagParts = dialogue.split(" ", 2);
		if (diagParts.length < 2) {
			Ui.taskReply("empty command");
		} else {
			switch (diagParts[0]) {
			case "todo":
				ToDo tTask = new ToDo(diagParts[1]);
				al.add(tTask);
				Ui.taskReply("success", tTask, al.size());
				break;
			case "event":
       			try {
					//split the rest of the string without the command in front
       				String[] taskParts = diagParts[1].split("/");
					
					//check if user has entered strictly following the format
					if (!((taskParts[1].startsWith("from")) && (taskParts[2].startsWith("to")))) {
						throw new TaskExecuteException("Wrong Format.\n\nFormat: event [task] /from [time] /to [time]");
					}
       			    String start = taskParts[1].split(" ", 2)[1];
       			    String end =  taskParts[2].split(" ", 2)[1];
					
					Event eTask = new Event(taskParts[0], start, end);
					al.add(eTask);
					Ui.taskReply("success", eTask, al.size());
				} catch (ArrayIndexOutOfBoundsException e) {
					throw new TaskExecuteException("Wrong Format.\n\nFormat: event [task] /from [time] /to [time]");

				} catch (DateTimeParseException e) {
					throw new TaskExecuteException(
							"Wrong Date Format.\n\nFormat for time: [DD-MM-YYYY] [HH:mm].\nCan be date or both.");

				}
				break;
			case "deadline":
				try {
					String[] taskParts = diagParts[1].split("/");
					String deadline = taskParts[1].split(" ", 2)[1];
					
					Deadline dTask = new Deadline(taskParts[0], deadline);
					al.add(dTask);
					Ui.taskReply("success", dTask, al.size());
					
				} catch (ArrayIndexOutOfBoundsException e) {
					throw new TaskExecuteException("Wrong Format.\n\nFormat: deadline [task] /by [deadline]");

				} catch (DateTimeParseException e) {
					throw new TaskExecuteException(
						"Wrong Date Format.\n\nFormat for time: [DD-MM-YYYY] [HH:mm].\nCan be date or both.");
				}
				break;
			default:
				throw new TaskExecuteException("Unknown Command.\n");
			}
		}
	}

	public static void saveTasks() {	
		DateTimeFormatter saver = DateTimeFormat.SAVE_FORMAT.formatter();
		BufferedWriter bw;
		try {
			bw = Files.newBufferedWriter(Paths.get("./data/Bane.txt"));

			for (Task task : al) {
				String input = "";
				String taskStatus = (task.getTaskStatus()) ? "1" : "0";
				switch (task) {
				case ToDo todo -> input = String.format("%s, %s, %s", "T", 
						taskStatus, todo.getName());

				case Deadline dTask -> input = String.format("%s, %s, %s, %s", "D",
						taskStatus, dTask.getName(), saver.format(dTask.getDeadline()));

				case Event eTask -> input = String.format("%s, %s, %s, %s, %s", "E", 
						taskStatus, eTask.getName(), saver.format(eTask.getStart()), saver.format(eTask.getEnd()));
				default -> {}
				} 
				try {
					bw.write(input, 0, input.length());
					bw.newLine();
				} catch (IOException e) {
					Ui.separatorLine();
					System.out.println("File Write Error.\n");
					Ui.saveReply("write_error");
					Ui.separatorLine();

					System.exit(1);
				}
			}
			bw.close();

		} catch (IOException e) {
			Ui.separatorLine();
			System.out.println("File Open Error.\n");
			Ui.saveReply("file_open_error");
			Ui.separatorLine();

			System.exit(1);
		}
	}

	//TODO: Add checks for corruption and invalid inputs

	public static void loadTasks() {
		try {
			//if file/directory does not exist
			if (Files.notExists(Paths.get("./data/Bane.txt"))) {
				if (Files.notExists(Paths.get("./data"))) {
					Files.createDirectory(Paths.get("./data"));
				}
				Files.createFile(Paths.get("./data/Bane.txt"));
				Ui.loadReply("success");

			}
			try {
				BufferedReader br = Files.newBufferedReader(Paths.get("./data/Bane.txt"));
				String line = br.readLine();
			
				//check whether there is still more in the file
				while (line != null) {
					String[] lineParts = line.split(",");
					if (lineParts.length < 3) {
						throw new IOException();
					}

					boolean isDone = lineParts[1].trim().equals("1");
					switch (lineParts[0]) {

					case "T":
						ToDo tTask = new ToDo(lineParts[2]);
						tTask.taskStatus(isDone);
						al.add(tTask);					
						break;

					case "D":
						Deadline dTask = new Deadline(lineParts[2], lineParts[3]);
						dTask.taskStatus(isDone);
						al.add(dTask);
						break;

					case "E":
						Event eTask = new Event(lineParts[2], lineParts[3], lineParts[4]);
						eTask.taskStatus(isDone);
						al.add(eTask);
						break;
					}
					line = br.readLine();
				}
			} catch (IOException e) {
				Ui.separatorLine();
				System.out.println("Read File Error\n");
				Ui.loadReply("read_file_error");
				Ui.separatorLine();

				System.exit(1);
			}
		} catch (IOException e) {
			Ui.separatorLine();
			System.out.println("File Creation Fail\n");
			Ui.loadReply("file_creation_fail");
			Ui.separatorLine();

			System.exit(1);

		}
	}
}
