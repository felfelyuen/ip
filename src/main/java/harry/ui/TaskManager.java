package harry.ui;

import harry.exceptions.*;
import harry.tasks.Deadline;
import harry.tasks.Event;
import harry.tasks.Task;
import harry.tasks.Todo;
import java.util.ArrayList;

public class TaskManager {

    private ArrayList<Task> tasks = new ArrayList<>();
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
        try {
            switch (type) {
            case "todo":
                if (commands.length == 1) {
                    throw new MissingTaskException();
                }
                task = addStrings(commands, 1, commands.length - 1);
                tasks.add(new Todo(task, type));
                break;
            case "deadline":
                int byIndex = search(commands, "/by");
                if (byIndex == 1) {
                    throw new MissingTaskException();
                }
                if (byIndex == -1) {
                    throw new MissingDateIndicatorException();
                }
                if (byIndex == commands.length - 1) {
                    throw new MissingDateException();
                }

                task = addStrings(commands, 1, byIndex - 1);
                date = "(by: " + addStrings(commands, byIndex + 1, commands.length - 1) + ")";
                tasks.add(new Deadline(task, type, date));
                break;
            case "event":
                int fromIndex = search(commands, "/from");
                int toIndex = search(commands, "/to");
                if (fromIndex == 1) {
                    throw new MissingTaskException();
                }
                if (fromIndex == -1 || toIndex == -1) {
                    throw new MissingDateIndicatorException();
                }
                if (toIndex == commands.length - 1 || fromIndex == toIndex - 1) {
                    throw new MissingDateException();
                }
                task = addStrings(commands, 1, fromIndex - 1);
                date = "(from: " + addStrings(commands, fromIndex + 1, toIndex - 1)
                        + " to: " + addStrings(commands, toIndex + 1, commands.length - 1) + ")";
                tasks.add(new Event(task, type, date));
                break;
            }
            taskCounter++;

            Printer.printLine();
            System.out.println("Okay, I added it here, should I add 'touch grass' to the list as well?");
            printTask(taskCounter - 1);
            System.out.print("Now you have " + taskCounter + " task");
            if (taskCounter != 1) {
                System.out.print("s");
            }
            System.out.println(" in the list");
            Printer.printLine();

        } catch (MissingTaskException e) {
            Printer.printError("There's no task??? What do you want me to do lol");
        } catch (MissingDateIndicatorException e) {
            Printer.printError("You are missing some indicators... so what date do you want");
        } catch (MissingDateException e) {
            Printer.printError("Where's your date... does time not affect you or—");
        }

    }

    public void markAsCompleted(int i, boolean completed) {
        try {
            if (i + 1 > taskCounter) {
                throw new MarkNullTaskException();
            }
            tasks.get(i).setCompleted(completed);
            if (completed) {
                Printer.printLine();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[X] " + tasks.get(i).getTask());
                Printer.printLine();
            } else {
                Printer.printLine();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("[ ] " + tasks.get(i).getTask());
                System.out.println("Get to work!");
                Printer.printLine();
            }
        } catch (MarkNullTaskException e) {
            Printer.printError("We don't have that task to mark... are you okay");
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
        if (taskCounter == 0) {
            System.out.println("There is nothing in the list lol");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskCounter; i++) {
                System.out.print((i + 1) + ".");
                tasks.get(i).printTask();
            }
        }
        Printer.printLine();
    }

    public void printTask (int i) {
        tasks.get(i).printTask();
    }

    public int getTaskCounter () {
        return taskCounter;
    }

}
