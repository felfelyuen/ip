package harry.taskList;

import java.time.LocalDateTime;

/**
 * It corresponds to a deadline in the user's list of tasks.
 * Deadline is inherited from the Task class, but also has the date attribute.
 * It also clearly defines the printTask for deadlines specifically.
 */
public class Deadline extends Task {
    protected LocalDateTime date;
    static final String DATE_INDICATOR = "[D]";

    public Deadline(String taskName, boolean isCompleted, String type, LocalDateTime date) {
        super(taskName, isCompleted, type);
        this.date = date;
    }

    public LocalDateTime getDate() { return date; }

    @Override
    public void printTask() {
        System.out.print(DATE_INDICATOR);
        if (isCompleted) {
            System.out.print(COMPLETED_TO_PRINT);
        } else {
            System.out.print(NOT_COMPLETED_TO_PRINT);
        }
        System.out.print(taskName + " by: ");
        HandleDate.PrintDate(date);
        System.out.println();
    }
}
