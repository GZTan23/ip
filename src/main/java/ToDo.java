public class ToDo extends Task {
  public ToDo(String dialogue) {
    super(dialogue);
  }
  @Override
  public String toString() {
    return "[T]" + super.toString();
  }
}
