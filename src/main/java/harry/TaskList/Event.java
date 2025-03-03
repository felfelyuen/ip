package harry.TaskList;

/**
 * It corresponds to an event type of task in the user's list of tasks.
 * Event is inherited from the Task class, but also has the date attribute.
 * It also clearly defines the printTask for events specifically.
 */

public class Event extends Task {
    protected String date;

    public Event (String taskName, boolean isCompleted, String type, String date) {
        super(taskName, isCompleted, type);
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
        System.out.print(taskName);
        System.out.println(" " + date);
    }
}
