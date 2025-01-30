public class ToDo extends Task {
	public ToDo(String name) {
		super(name.trim());
	}

	@Override
	public String toString() {
		return "[T]" + super.toString();
	}
}
