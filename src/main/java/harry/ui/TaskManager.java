package harry.ui;

import harry.exceptions.*;
import harry.tasks.Deadline;
import harry.tasks.Event;
import harry.tasks.Task;
import harry.tasks.Todo;
import java.util.ArrayList;

public class TaskManager {

    private ArrayList<Task> tasks = new ArrayList<>();
    //private int taskCounter = 0;

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

    public void addTask(String[] commands, String type)
            throws MissingTaskException, MissingDateIndicatorException, MissingDateException{
        String task;
        String date;
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

            Printer.printLine();
            System.out.println("Okay, I added it here, should I add 'touch grass' to the list as well?");
            tasks.get(tasks.size() - 1).printTask();
            System.out.print("Now you have " + tasks.size() + " task");
            if (tasks.size() != 1) {
                System.out.print("s");
            }
            System.out.println(" in the list");
            Printer.printLine();
    }

    public void markAsCompleted (int i, boolean completed) throws HandleNullTaskException {
            if (i + 1 > tasks.size()) {
                throw new HandleNullTaskException();
            }
            tasks.get(i).setCompleted(completed);
            if (completed) {
                Printer.printLine();
                System.out.println("Nice! I've marked this task as done:");
                tasks.get(i).printTask();
                Printer.printLine();
            } else {
                Printer.printLine();
                System.out.println("OK, I've marked this task as not done yet:");
                tasks.get(i).printTask();
                System.out.println("Get to work!");
                Printer.printLine();
            }
    }

    public void deleteTask(int i) throws HandleNullTaskException{
        if (i + 1 > tasks.size()) {
            throw new HandleNullTaskException();
        }

        Printer.printLine();
        System.out.println("Oh wow congrats, I've deleted this task");
        tasks.get(i).printTask();
        System.out.println("And many happy returns to you too");
        Printer.printLine();

        tasks.remove(i);
    }

    public void printTasks () {
        Printer.printLine();
        if (tasks.size() == 0) {
            System.out.println("There is nothing in the list lol");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.print((i + 1) + ".");
                tasks.get(i).printTask();
            }
        }
        Printer.printLine();
    }

    public void printTask (int i) {
        tasks.get(i).printTask();
    }

}
