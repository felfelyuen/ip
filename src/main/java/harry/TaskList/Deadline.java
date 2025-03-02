package harry.TaskList;

import java.time.LocalDateTime;

public class Deadline extends Task {
    protected LocalDateTime date;

    public Deadline (String task, boolean isCompleted, String type, LocalDateTime date) {
        super(task, isCompleted, type);
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
        System.out.print(task + " by: ");
        HandleDate.PrintDate(date);
        System.out.println();
    }
}
