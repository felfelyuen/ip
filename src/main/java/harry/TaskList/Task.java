package harry.TaskList;

/**
 * Represents a task in the user's list of tasks. The Task object includes the taskName,
 * whether it is completed, and the type of task.
 */

public class Task {
    protected String taskName;
    protected boolean isCompleted;
    protected String type;

    public Task(String taskName, boolean isCompleted, String type) {
        this.taskName = taskName;
        this.isCompleted = false;
        this.type = type;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getType() { return type; }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public void printTask() {}
}
