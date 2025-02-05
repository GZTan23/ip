package bane.task;

public class ToDo implements Task {
	private String name;
	private boolean isDone;

	public ToDo(String name) {
		this.name = name.trim();
		this.isDone = false;
	}
	public String getName() {
		return this.name;
	}

	public boolean isTaskDone() {
		return this.isDone;
	}

	public void changeTaskStatus(boolean isDone) {
		this.isDone = isDone;
	}

	@Override
	public String toString() {
		return String.format("[T][%s] %s", (this.isDone ? "X" : " "), this.name);
	}
}
