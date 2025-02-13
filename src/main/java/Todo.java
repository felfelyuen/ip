public class Todo extends Task{
    public Todo (String task, String type) {
        super(task, type);
    }

    @Override
    public void printTask() {
        System.out.print("[T]");
        if (isCompleted) {
            System.out.print("[X] ");
        } else {
            System.out.print("[ ] ");
        }
        System.out.println(task);
    }
}
