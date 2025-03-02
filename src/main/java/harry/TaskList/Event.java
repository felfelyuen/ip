package harry.TaskList;

import java.time.LocalDateTime;

public class Event extends Task {
    protected LocalDateTime fromDate;
    protected LocalDateTime toDate;

    public Event (String task, boolean isCompleted, String type, LocalDateTime fromDate, LocalDateTime toDate) {
        super(task, isCompleted, type);
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
        System.out.print(task + " from: ");
        HandleDate.PrintDate(fromDate);
        System.out.print("  to: ");
        HandleDate.PrintDate(toDate);
        System.out.println();
    }
}
