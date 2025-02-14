public class TaskManager {

    private Task[] tasks = new Task[100];
    private int taskCounter = 0;

    public static String addStrings (String[] array, int startIndex, int endIndex) {
        String result = "";
        for (int i = startIndex; i < endIndex - 1; i++) {
            result += array[startIndex] + " ";
        }
        result += array[endIndex];
        return result;
    }

    public static int search (String[] array, String item) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    public void addTask(String[] commands, String type) {
        String task;
        String date;
        switch (type) {
        case "todo":
            task = addStrings(commands, 1, commands.length -1);
            tasks[taskCounter] = new Todo(task, type);
            break;
        case "deadline":
            int byIndex = search(commands, "/by");
            task = addStrings(commands, 1, byIndex - 1);
            date = "(by: " + addStrings(commands, byIndex + 1, commands.length -1) + ")";
            tasks[taskCounter] = new Deadline(task, type, date);
            break;
        case "event":
            int fromIndex = search(commands, "/from");
            int toIndex = search(commands, "/to");
            task = addStrings(commands, 1, fromIndex - 1);
            date = "(from: " + addStrings(commands, fromIndex + 1, toIndex -1)
                    + " to: " + addStrings(commands, toIndex + 1, commands.length -1) + ")";
            tasks[taskCounter] = new Event(task, type, date);
        }

        taskCounter++;
    }

    public void markAsCompleted(int i, boolean completed) {
        tasks[i].setCompleted(completed);
        if (completed) {
            Printer.printLine();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("[X] " + tasks[i].getTask());
            Printer.printLine();
        } else {
            Printer.printLine();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("[ ] " + tasks[i].getTask());
            System.out.println("Get to work!");
            Printer.printLine();
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
        Printer.printLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCounter; i++) {
            System.out.print((i + 1) + ".");
            tasks[i].printTask();
        }
        Printer.printLine();
    }

    public void printTask (int i) {
        tasks[i].printTask();
    }

    public int getTaskCounter () {
        return taskCounter;
    }

}
