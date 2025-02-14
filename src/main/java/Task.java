public class Task {
    protected String task;
    protected boolean isCompleted;
    protected String type;
    //protected String date;

    public Task(String task, String type) {
        this.task = task;
        this.isCompleted = false;
        this.type = type;
        //this.date = date;
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

    public void setType(String type) { this.type = type; }

    public void printTask() {}
}
