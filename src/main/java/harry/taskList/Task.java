package harry.taskList;

/**
 * Represents a task in the user's list of tasks. The Task object includes the taskName,
 * whether it is completed, and the type of task.
 */

public class Task {
    protected String taskName;
    protected boolean isCompleted;
    protected String type;
    static final String COMPLETED = "X";
    static final String NOT_COMPLETED = "0";
    static final String COMPLETED_TO_PRINT = "[X] ";
    static final String NOT_COMPLETED_TO_PRINT = "[ ] ";

    public Task(String taskName, boolean isCompleted, String type) {
        this.taskName = taskName;
        this.isCompleted = isCompleted;
        this.type = type;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getType() {
        return this.type;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public void printTask() {}

    public String getCompleted() {
        if (isCompleted) {
            return COMPLETED;
        } else {
            return NOT_COMPLETED;
        }
    }
}
