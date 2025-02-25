package harry.TaskList;

public class Todo extends Task {
    public Todo (String task, boolean isCompleted, String type) {
        super(task, isCompleted, type);
    }

    @Override
    public void printTask() {
        System.out.print("[T]");
        if (isCompleted) {
            System.out.print("[X] ");
        } else {
            System.out.print("[ ] ");
        }
        System.out.println(task);
    }
}
