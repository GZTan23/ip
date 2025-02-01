import java.time.format.DateTimeParseException;

public class Parser {
    private TaskList tasks;

    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

	public void parseDialog(String dialogue) {

		if (dialogue.startsWith("bye")) {
			Ui.farewell();
		
		} else if (dialogue.startsWith("list")) {
			Ui.separatorLine();
			
			if (tasks.isEmpty()) {
				Ui.listReply("empty");
			} else {
				Ui.listReply("success");
                tasks.listTasks();
			}

			Ui.separatorLine();	
		} else if ((dialogue.startsWith("mark")) |
				(dialogue.startsWith("unmark"))) {
                    
                Ui.separatorLine();
                parseMark(dialogue);
                Ui.separatorLine();

		} else if ((dialogue.startsWith("todo")) ||
				(dialogue.startsWith("deadline")) ||
				(dialogue.startsWith("event"))) {
    
				Ui.separatorLine();
                parseEvent(dialogue);	
				Ui.separatorLine();

		} else if (dialogue.startsWith("delete")) {
			Ui.separatorLine();
            parseDelete(dialogue);
			Ui.separatorLine();

		} else {
			Ui.separatorLine();
			Ui.unknownInputReply();
			Ui.separatorLine();
		}

	}

    public void parseEvent(String dialogue) {
        try {  
            String[] diagParts = dialogue.split(" ", 2);
            if (diagParts.length < 2) {
                Ui.taskReply("empty command");
            
            } else {
                switch (diagParts[0]) {
                case "todo":
                    ToDo tTask = new ToDo(diagParts[1]);
                    tasks.addTask(tTask);
                    //Ui.taskReply("success", tTask, tasks.getSize());
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
                        //Ui.taskReply("success", eTask, tasks.getSize());
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
                        tasks.addTask(dTask);
                        //Ui.taskReply("success", eTask, tasks.getSize());
                        
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
        } catch (TaskExecuteException e) {
            System.out.println(e.getMessage());
            Ui.taskReply("fail");

        }
    }

    public void parseMark(String dialogue) {
        try {
            String[] arr = dialogue.split(" ");
            if (arr.length < 2) {
                Ui.markReply("empty_command");
                return;
            }
            int idx = Integer.parseInt(arr[1]);

            if (arr[0].equals("mark")) {
                tasks.markTask(idx);				
            } else {
                tasks.unmarkTask(idx);
            }

            tasks.displayTask(idx);

        } catch (IndexOutOfBoundsException e) {
            Ui.markReply("index_out_of_bounds");
        }
    }

    public void parseDelete(String dialogue) {
        try {
            int idx = Integer.parseInt(dialogue.split(" ")[1]);
            tasks.deleteTask(idx);
            Ui.deleteReply("success");

        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.deleteReply("empty_command");
            
        } catch (IndexOutOfBoundsException e) {
            Ui.deleteReply("delete_out_of_bounds");
        } 
    }
}
