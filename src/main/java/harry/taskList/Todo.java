package harry.taskList;

/**
 * It corresponds to a to-do type of task in the user's list of tasks.
 * Todo is inherited from the Task class, but with a clearly defined printTask for todos specifically.
 */

public class Todo extends Task {
    public Todo(String taskName, boolean isCompleted, String type) {
        super(taskName, isCompleted, type);
    }

    static final String TASK_INDICATOR = "[T]";

    @Override
    public void printTask() {
        System.out.print(TASK_INDICATOR);
        if (isCompleted) {
            System.out.print(COMPLETED_TO_PRINT);
        } else {
            System.out.print(NOT_COMPLETED_TO_PRINT);
        }
        System.out.println(taskName);
    }
}
