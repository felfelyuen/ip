public class Task {
    private String task;
    private boolean isCompleted;
    private String type;
    private String date;

    public Task(String task, String type, String date) {
        this.task = task;
        this.isCompleted = false;
        this.type = type;
        this.date = date;
    }

    public String getTask() {
        return task;
    }

    public String getType() { return type; }

    public String getDate() { return date; }

    public boolean getCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public void setType(String type) { this.type = type; }

    public void setDate(String date) { this.date = date; }

    public void printTask() {
        //print the task type
        if (type.equals("todo")) {
            System.out.print("[T]"); //to-do
        } else if (type.equals("event")) {
            System.out.print("[E]"); //event
        } else {
            System.out.print("[D]"); //deadline
        }

        //print completed? state
        if (isCompleted) {
            System.out.print("[X] ");
        } else {
            System.out.print("[ ] ");
        }

        System.out.print(task);
        System.out.println(" " + date);
    }
}
