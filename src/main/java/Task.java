
public abstract class Task {
  private boolean isDone; 
  private String name;

  public Task(String dialogue) {
    this.name = dialogue;
    this.isDone = false; 
  }
  public void taskStatus(boolean status) {
    this.isDone = status;
  }

  @Override 
  public String toString() {
    return "[" + (isDone ? "X" : " ") + "] " + name;
  }
}
