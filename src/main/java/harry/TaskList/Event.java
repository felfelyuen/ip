package harry.TaskList;

import java.time.LocalDateTime;

/**
 * It corresponds to an event type of task in the user's list of tasks.
 * Event is inherited from the Task class, but also has the date attribute.
 * It also clearly defines the printTask for events specifically.
 */

public class Event extends Task {
    protected LocalDateTime fromDate;
    protected LocalDateTime toDate;

    public Event (String taskName, boolean isCompleted, String type, LocalDateTime fromDate, LocalDateTime toDate) {
        super(taskName, isCompleted, type);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public LocalDateTime getFromDate() { return fromDate; }

    public LocalDateTime getToDate() { return toDate; }

    //public void setDate(LocalDateTime date) { this.date = date; }

    @Override
    public void printTask() {
        System.out.print("[E]");
        if (isCompleted) {
            System.out.print("[X] ");
        } else {
            System.out.print("[ ] ");
        }
        System.out.print(taskName + " from: ");
        HandleDate.PrintDate(fromDate);
        System.out.print("  to: ");
        HandleDate.PrintDate(toDate);
        System.out.println();
    }
}
