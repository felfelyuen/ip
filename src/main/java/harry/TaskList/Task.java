package harry.TaskList;

public class Task {
    protected String task;
    protected boolean isCompleted;
    protected String type;

    public Task(String task, boolean isCompleted, String type) {
        this.task = task;
        this.isCompleted = isCompleted;
        this.type = type;
    }

    public String getTask() {
        return task;
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
