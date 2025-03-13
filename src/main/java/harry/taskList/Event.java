package harry.taskList;

import java.time.LocalDateTime;

/**
 * It corresponds to an event type of task in the user's list of tasks.
 * Event is inherited from the Task class, but also has the date attribute.
 * It also clearly defines the printTask for events specifically.
 */
public class Event extends Task {
    protected LocalDateTime fromDate;
    protected LocalDateTime toDate;
    static final String EVENT_INDICATOR = "[E]";

    public Event(String taskName, boolean isCompleted, String type, LocalDateTime fromDate, LocalDateTime toDate) {
        super(taskName, isCompleted, type);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public LocalDateTime getFromDate() { return fromDate; }

    public LocalDateTime getToDate() { return toDate; }

    @Override
    public void printTask() {
        System.out.print(EVENT_INDICATOR);
        if (isCompleted) {
            System.out.print(COMPLETED_TO_PRINT);
        } else {
            System.out.print(NOT_COMPLETED_TO_PRINT);
        }
        System.out.print(taskName + " from: ");
        HandleDate.PrintDate(fromDate);
        System.out.print("  to: ");
        HandleDate.PrintDate(toDate);
        System.out.println();
    }
}
