public class TaskManager {
    private Task[] tasks = new Task[100];
    private int taskCounter = 0;

    public void addTask(String task) {
        tasks[taskCounter] = new Task(task);
        taskCounter++;
    }

    public void printTasks () {
        PrintLine.printLine();
        for (int i = 0; i < taskCounter; i++) {
            System.out.print(i + 1);
            System.out.println(". " + tasks[i].getTask());
        }
        PrintLine.printLine();
    }

}
