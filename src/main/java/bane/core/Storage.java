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

public class Storage {
    private String filePath;
    private ArrayList<Task> al;

    public Storage(String filePath) {
        this.filePath = filePath;
    }   

	public void saveTasks(ArrayList<Task> al) {	
		DateTimeFormatter saver = DateTimeFormat.SAVE_FORMAT.formatter();
		BufferedWriter bw;

		try {
			bw = Files.newBufferedWriter(Paths.get(filePath));

			for (Task task : al) {
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

	public ArrayList<Task> loadTasks() {
        al = new ArrayList<>();
		try {
			//if file/directory does not exist
			if (Files.notExists(Paths.get(filePath))) {
				if (Files.notExists(Paths.get(filePath))) {
					Files.createDirectory(Paths.get(filePath));
				}
				Files.createFile(Paths.get(filePath));
				Ui.loadReply("success");

			}
			try {
				BufferedReader br = Files.newBufferedReader(Paths.get(filePath));
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
						tTask.changeTaskStatus(isDone);
						al.add(tTask);					
						break;

					case "D":
						Deadline dTask = new Deadline(lineParts[2], lineParts[3]);
						dTask.changeTaskStatus(isDone);
						al.add(dTask);
						break;

					case "E":
						Event eTask = new Event(lineParts[2], lineParts[3], lineParts[4]);
						eTask.changeTaskStatus(isDone);
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
        return al;
	}
}
