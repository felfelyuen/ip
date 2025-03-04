package harry.TaskList;

/**
 * It corresponds to a deadline in the user's list of tasks.
 * Deadline is inherited from the Task class, but also has the date attribute.
 * It also clearly defines the printTask for deadlines specifically.
 */

import java.time.LocalDateTime;

public class Deadline extends Task {
    protected LocalDateTime date;

    public Deadline (String taskName, boolean isCompleted, String type, LocalDateTime date) {
        super(taskName, isCompleted, type);
        this.date = date;
    }

    public LocalDateTime getDate() { return date; }

    @Override
    public void printTask() {
        System.out.print("[D]");
        if (isCompleted) {
            System.out.print("[X] ");
        } else {
            System.out.print("[ ] ");
        }
        System.out.print(taskName + " by: ");
        HandleDate.PrintDate(date);
        System.out.println();
    }
}
