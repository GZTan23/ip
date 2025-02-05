package bane.core;

import bane.enums.DateTimeFormat;
import bane.task.Deadline;
import bane.task.Event;
import bane.task.Task;
import bane.task.ToDo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Loads the tasks from a specified file and saves to it after execution
 */
public class Storage {
    private String filePath;
    private ArrayList<Task> tasks;

	/**
	 * Constructor for the Storage class
	 * @param filePath File path of the place to store and load the tasks from
	 */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

	/**
	 * Saves tasks from the list into the file before closing the program
	 * If unable to create directory/file, the program exits with status of 1
	 * @param arrayList ArrayList to extract and save the tasks from
	 */
	public void saveTasks(ArrayList<Task> arrayList) {
		DateTimeFormatter saver = DateTimeFormat.SAVE_FORMAT.formatter();
		BufferedWriter bw;

		try {
			bw = Files.newBufferedWriter(Paths.get(filePath));

			for (Task task : arrayList) {
				String input = "";
				String taskStatus = (task.isTaskDone()) ? "1" : "0";
                if (task instanceof ToDo todo) {
                    input = String.format("%s, %s, %s", "T", 
						    taskStatus, todo.getName());
                            
                } else if (task instanceof Deadline deadline) {
                    input = String.format("%s, %s, %s, %s", "D",
                            taskStatus, deadline.getName(), saver.format(deadline.getDeadline()));

                } else if (task instanceof Event event) {
                    input = String.format("%s, %s, %s, %s, %s", "E",
                            taskStatus, event.getName(), saver.format(event.getStart()), saver.format(event.getEnd()));
                }

				try {
					bw.write(input, 0, input.length());
					bw.newLine();
				} catch (IOException exception) {
					Ui.separateLine();
					System.out.println("File Write Error.\n");
					Ui.replyToSaveFile("write_error");
					Ui.separateLine();

					System.exit(1);
				}
			}
			bw.close();

		} catch (IOException e) {
			Ui.separateLine();
			System.out.println("File Open Error.\n");
			Ui.replyToSaveFile("file_open_error");
			Ui.separateLine();

			System.exit(1);
		}
	}

	/**
	 * Loads the tasks from the specified path
	 * If the specified file does not exist, creates the file and returns an empty list
	 * @return ArrayList<Task> The list of tasks that have been loaded from the file
	 */
	public ArrayList<Task> loadTasks() {
        tasks = new ArrayList<>();
		try {
			//if file/directory does not exist
			if (Files.notExists(Paths.get(filePath))) {
				Files.createDirectory(Paths.get(filePath).getParent());
				Files.createFile(Paths.get(filePath));
				Ui.replyToLoadFile("success");
			}
			try {
				BufferedReader reader = Files.newBufferedReader(Paths.get(filePath));
				String line = reader.readLine();
			
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
						tTask.changeTaskStatus(isDone);
						tasks.add(tTask);
						break;

					case "D":
						Deadline dTask = new Deadline(lineParts[2], lineParts[3]);
						dTask.changeTaskStatus(isDone);
						tasks.add(dTask);
						break;

					case "E":
						Event eTask = new Event(lineParts[2], lineParts[3], lineParts[4]);
						eTask.changeTaskStatus(isDone);
						tasks.add(eTask);
						break;
					}
					line = reader.readLine();
				}
			} catch (IOException e) {
				Ui.separateLine();
				System.out.println("Read File Error\n");
				Ui.replyToLoadFile("read_file_error");
				Ui.separateLine();

				System.exit(1);
			}
		} catch (IOException e) {
			Ui.separateLine();
			System.out.println("File Creation Fail\n");
			Ui.replyToLoadFile("file_creation_fail");
			Ui.separateLine();

			System.exit(1);

		}
        return tasks;
	}
}
