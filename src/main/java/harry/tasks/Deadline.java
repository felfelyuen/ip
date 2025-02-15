package harry.tasks;

public class Deadline extends Task {
    protected String date;

    public Deadline (String task, String type,String date) {
        super(task, type);
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
        System.out.print(task);
        System.out.println(" " + date);
    }
}
