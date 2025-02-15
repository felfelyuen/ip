package harry.tasks;

public class Event extends Task {
    protected String date;

    public Event (String task, String type, String date) {
        super(task, type);
        this.date = date;
    }

    public String getDate() { return date; }

    public void setDate(String date) { this.date = date; }

    @Override
    public void printTask() {
        System.out.print("[E]");
        if (isCompleted) {
            System.out.print("[X] ");
        } else {
            System.out.print("[ ] ");
        }
        System.out.print(task);
        System.out.println(" " + date);
    }
}
