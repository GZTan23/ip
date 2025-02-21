package bane.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import bane.enums.DateTimeFormat;
import bane.exception.StorageException;
import bane.task.Deadline;
import bane.task.Event;
import bane.task.Task;
import bane.task.ToDo;



/**
 * Loads the tasks from a specified file and saves to it after execution
 */
public class Storage {
    private String filePath;
    private ArrayList<Task> tasks;

    /**
     * Constructor for the Storage class
     *
     * @param filePath File path of the place to store and load the tasks from.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves tasks from the list into the file before closing the program
     * If unable to create directory/file, the program exits with status of 1
     *
     * @param arrayList ArrayList to extract and save the tasks from.
     * @throws StorageException if there is a problem while saving to the file.
     */
    public String saveTasks(ArrayList<Task> arrayList) throws StorageException {
        DateTimeFormatter saver = DateTimeFormat.SAVE_FORMAT.formatter();
        BufferedWriter bw;

        try {
            bw = Files.newBufferedWriter(Paths.get(filePath));

            writeToFile(arrayList, saver, bw);
            bw.close();

        } catch (IOException e) {
            String exceptionMessage = "File Open Error.\n"
                    + Ui.replyToSaveFile("file_open_error");
            throw new StorageException(exceptionMessage);

        }

        return Ui.replyToSaveFile("success");
    }

    /**
     * Saves to file
     *
     * @param arrayList List of tasks to save.
     * @param saver Date format to save as.
     * @param bw Writer to read from.
     * @throws StorageException If there is an error while saving.
     */
    private static void writeToFile(ArrayList<Task> arrayList, DateTimeFormatter saver,
                                    BufferedWriter bw) throws StorageException {
        for (Task task : arrayList) {
            String input = "";
            String reminderStatus = (task.isTaskReminder()) ? "1" : "0";
            String taskStatus = (task.isTaskDone()) ? "1" : "0";
            String taskName = task.getName();

            if (task instanceof ToDo todo) {
                input = String.format("%s, %s, %s, %s", "T", taskStatus,
                        reminderStatus, taskName);

            } else if (task instanceof Deadline deadline) {
                String formattedDate = saver.format(deadline.getDeadline());
                input = String.format("%s, %s, %s, %s, %s", "D", taskStatus,
                        reminderStatus, taskName, formattedDate);

            } else if (task instanceof Event event) {
                String startDateFormatted = saver.format(event.getStart());
                String endDateFormatted = saver.format(event.getEnd());
                input = String.format("%s, %s, %s, %s, %s, %s", "E", taskStatus,
                        reminderStatus, taskName, startDateFormatted, endDateFormatted);
            }

            try {
                bw.write(input, 0, input.length());
                bw.newLine();
            } catch (IOException exception) {

                String exceptionMessage = "File Write Error.\n"
                        + Ui.replyToSaveFile("write_error");
                throw new StorageException(exceptionMessage);
            }
        }
    }

    /**
     * Loads the tasks from the specified path
     * If the specified file does not exist, creates the file and returns an empty list
     *
     * @return The list of tasks that have been loaded from the file.
     * @throws StorageException if there is a problem while loading from the file.
     */
    public ArrayList<Task> loadTasks() throws StorageException {
        tasks = new ArrayList<>();
        try {
            //if file/directory does not exist
            Path file = Paths.get(filePath);

            boolean doesFileNotExist = Files.notExists(file);
            if (doesFileNotExist) {
                Files.createDirectory(file.getParent());
                Files.createFile(file);
            }
            readFromFile(file);
        } catch (IOException e) {
            String exceptionMessage = "File Creation Fail.\n"
                    + Ui.replyToLoadFile("file_creation_fail");
            throw new StorageException(exceptionMessage);
        }
        return tasks;
    }

    /**
     * Reads from file
     *
     * @param file File to read from
     * @throws StorageException If there is an error while reading from file
     */
    private void readFromFile(Path file) throws StorageException {
        try (BufferedReader reader = Files.newBufferedReader(file)) {
            String line = reader.readLine();

            //check whether there is still more in the file
            while (line != null) {
                String[] lineParts = line.split(",");
                if (lineParts.length < 4 || lineParts.length > 6) {
                    throw new IOException();
                }

                boolean isDone = lineParts[1].trim().equals("1");
                boolean isReminder = lineParts[2].trim().equals("1");

                String name = lineParts[3].trim();

                switch (lineParts[0]) {
                case "T":
                    ToDo tTask = new ToDo(name);
                    tTask.changeTaskStatus(isDone);
                    tTask.setReminder(isReminder);
                    tasks.add(tTask);
                    break;

                case "D":
                    Deadline dTask = new Deadline(name , lineParts[4]);
                    dTask.changeTaskStatus(isDone);
                    dTask.setReminder(isReminder);
                    tasks.add(dTask);
                    break;

                case "E":
                    Event eTask = new Event(name , lineParts[4], lineParts[5]);
                    eTask.changeTaskStatus(isDone);
                    eTask.setReminder(isReminder);
                    tasks.add(eTask);
                    break;
                default:
                }
                line = reader.readLine();
            }
        } catch (IOException | DateTimeParseException e) {
            String exceptionMessage = "Read File Error.\n"
                    + Ui.replyToLoadFile("read_file_error");
            throw new StorageException(exceptionMessage);
        }
    }
}
