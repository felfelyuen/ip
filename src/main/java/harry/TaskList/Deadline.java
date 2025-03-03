package harry.TaskList;

/**
 * It corresponds to a deadline in the user's list of tasks.
 * Deadline is inherited from the Task class, but also has the date attribute.
 * It also clearly defines the printTask for deadlines specifically.
 */

public class Deadline extends Task {
    protected String date;

    public Deadline (String taskName, boolean isCompleted, String type, String date) {
        super(taskName, isCompleted, type);
        this.date = date;
    }

    public String getDate() { return date; }

    public void setDate(String date) { this.date = date; }

    @Override
    public void printTask() {
        System.out.print("[D]");
        if (isCompleted) {
            System.out.print("[X] ");
        } else {
            System.out.print("[ ] ");
        }
        System.out.print(taskName);
        System.out.println(" " + date);
    }
}
