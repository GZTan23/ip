package bane.task;

public interface Task {

	void changeTaskStatus(boolean status);
	boolean isTaskDone();
	String getName();
}
