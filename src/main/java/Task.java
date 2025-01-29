public abstract class Task {
private boolean isDone; 
private String name;

public Task(String name) {
	this.name = name;
	this.isDone = false; 
}
public void taskStatus(boolean status) {
	this.isDone = status;
}

public boolean getTaskStatus() {
	return this.isDone; 
}

public String getName() {
	return this.name;
}

@Override 
public String toString() {
	return "[" + (isDone ? "X" : " ") + "] " + name;
}
}
