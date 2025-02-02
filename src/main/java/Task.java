public class Task {
    private String task;
    private boolean isCompleted;

    public Task(String task) {
        this.task = task;
        this.isCompleted = false;
    }

    public String getTask() {
        return task;
    }

    public boolean getCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
