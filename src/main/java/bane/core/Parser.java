package bane.core;

import bane.exception.TaskExecuteException;
import java.time.format.DateTimeParseException;
import bane.task.Deadline;
import bane.task.Event;
import bane.task.ToDo;

/**
 * Parses and handles user input
 */
public class Parser {
    private TaskList tasks;

    /**
     * Constructor for Parser class
     * @param tasks TaskList to add tasks to the list
     */
    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Parses the command part of the user input
     * @param dialogue User input
     */
	public void parseDialogue(String dialogue) {

		if (dialogue.startsWith("bye")) {
			Ui.sayFarewell();
		
		} else if (dialogue.startsWith("list")) {
			Ui.separateLine();
			
			if (tasks.isEmpty()) {
				Ui.replyToList("empty");
			} else {
				Ui.replyToList("success");
                tasks.listTasks();
			}

			Ui.separateLine();
		} else if ((dialogue.startsWith("mark")) |
				(dialogue.startsWith("unmark"))) {
                    
                Ui.separateLine();
                parseMark(dialogue);
                Ui.separateLine();

		} else if ((dialogue.startsWith("todo")) ||
				(dialogue.startsWith("deadline")) ||
				(dialogue.startsWith("event"))) {
    
				Ui.separateLine();
                parseEvent(dialogue);	
				Ui.separateLine();

		} else if (dialogue.startsWith("delete")) {
			Ui.separateLine();
            parseDelete(dialogue);
			Ui.separateLine();

		} else {
			Ui.separateLine();
			Ui.replyToUnknownInput();
			Ui.separateLine();
		}

	}


    /**
     * Parses user input for the task name and date if any
     * @param dialogue User input
     */
    public void parseEvent(String dialogue) {
        try {  
            String[] diagParts = dialogue.split(" ", 2);
            if (diagParts.length < 2) {
                Ui.replyToTasks("empty command");
            
            } else {
                switch (diagParts[0]) {
                case "todo":
                    ToDo tTask = new ToDo(diagParts[1]);
                    tasks.addTask(tTask);
                    //Ui.replyToTasks("success", tTask, tasks.getSize());
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
                        tasks.addTask(eTask);

                    } catch (ArrayIndexOutOfBoundsException exception) {
                        throw new TaskExecuteException("Wrong Format.\n\nFormat: event [task] /from [time] /to [time]");

                    } catch (DateTimeParseException exception) {
                        throw new TaskExecuteException(
                                "Wrong Date Format.\n\nFormat for time: [DD-MM-YYYY] [HH:mm].\nCan be date or both.");

                    }
                    break;

                case "deadline":
                    try {
                        String[] taskParts = diagParts[1].split("/");
                        String deadline = taskParts[1].split(" ", 2)[1];
                        
                        Deadline dTask = new Deadline(taskParts[0], deadline);
                        tasks.addTask(dTask);

                    } catch (ArrayIndexOutOfBoundsException exception) {
                        throw new TaskExecuteException("Wrong Format.\n\nFormat: deadline [task] /by [deadline]");

                    } catch (DateTimeParseException exception) {
                        throw new TaskExecuteException(
                            "Wrong Date Format.\n\nFormat for time: [DD-MM-YYYY] [HH:mm].\nCan be date or both.");
                    }
                    break;

                default:
                    throw new TaskExecuteException("Unknown Command.\n");
                }
		    }
        } catch (TaskExecuteException exception) {
            System.out.println(exception.getMessage());
            Ui.replyToTasks("fail");

        }
    }

    /**
     * Parses user input for the mark/unmark commands
     * @param dialogue User input
     */
    public void parseMark(String dialogue) {
        try {
            String[] arr = dialogue.split(" ");
            if (arr.length < 2) {
                Ui.replyToMark("empty_command");
                return;
            }
            int idx = Integer.parseInt(arr[1]);

            if (arr[0].equals("mark")) {
                tasks.markTask(idx);				
            } else {
                tasks.unmarkTask(idx);
            }

            tasks.displayTask(idx);

        } catch (IndexOutOfBoundsException exception) {
            Ui.replyToMark("index_out_of_bounds");
        }
    }

    /**
     * Parses user input for the delete command
     * @param dialogue User input
     */
    public void parseDelete(String dialogue) {
        try {
            int idx = Integer.parseInt(dialogue.split(" ")[1]);
            tasks.deleteTask(idx);
            Ui.replyToDelete("success");

        } catch (ArrayIndexOutOfBoundsException exception) {
            Ui.replyToDelete("empty_command");
            
        } catch (IndexOutOfBoundsException exception) {
            Ui.replyToDelete("delete_out_of_bounds");
        } 
    }
}
