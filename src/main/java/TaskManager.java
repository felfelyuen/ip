public class TaskManager {
    private Task[] tasks = new Task[100];
    private int taskCounter = 0;

    public void addTask(String task) {
        tasks[taskCounter] = new Task(task);
        taskCounter++;
    }

    public void markAsCompleted(int i, boolean completed) {
        tasks[i].setCompleted(completed);
        if (completed) {
            PrintLine.printLine();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("[X] " + tasks[i].getTask());
            PrintLine.printLine();
        } else {
            PrintLine.printLine();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("[ ] " + tasks[i].getTask());
            System.out.println("Get to work!");
            PrintLine.printLine();
        }
    }

    public void printCompleted(boolean completed) {
        if (completed) {
            System.out.print("[X]");
        } else {
            System.out.print("[ ]");
        }
    }

    public void printTasks () {
        PrintLine.printLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCounter; i++) {
            System.out.print((i + 1) + ".");
            printCompleted(tasks[i].getCompleted());
            System.out.println(" " + tasks[i].getTask());
        }
        PrintLine.printLine();
    }

}
