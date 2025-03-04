package harry.TaskList;

/**
 * It corresponds to a to-do type of task in the user's list of tasks.
 * Todo is inherited from the Task class, but with a clearly defined printTask for todos specifically.
 */

public class Todo extends Task {
    public Todo (String taskName, boolean isCompleted, String type) {
        super(taskName, isCompleted, type);
    }

    @Override
    public void printTask() {
        System.out.print("[T]");
        if (isCompleted) {
            System.out.print("[X] ");
        } else {
            System.out.print("[ ] ");
        }
        System.out.println(taskName);
    }
}
