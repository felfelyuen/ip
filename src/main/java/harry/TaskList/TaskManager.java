package harry.TaskList;

import harry.Exceptions.*;
import harry.Printer.Printer;

import java.util.ArrayList;

public class TaskManager {

    private ArrayList<Task> tasks = new ArrayList<>();
    //private int taskCounter = 0;

    public static String addSpaces(String[] array, int startIndex, int endIndex) {
        String result = "";
        for (int i = startIndex; i < endIndex; i++) {
            result += array[i] + " ";
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
                task = addSpaces(commands, 1, commands.length - 1);
                tasks.add(new Todo(task, false, type));
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

                task = addSpaces(commands, 1, byIndex - 1);
                date = "(by: " + addSpaces(commands, byIndex + 1, commands.length - 1) + ")";
                tasks.add(new Deadline(task, false, type, date));
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
                task = addSpaces(commands, 1, fromIndex - 1);
                date = "(from: " + addSpaces(commands, fromIndex + 1, toIndex - 1)
                        + " to: " + addSpaces(commands, toIndex + 1, commands.length - 1) + ")";
                tasks.add(new Event(task, false, type, date));
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

    public void addTask (Task task) {
        tasks.add(task);
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

    public void deleteTask(int i) throws HandleNullTaskException {
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

    public void printCompleted(boolean completed) {
        if (completed) {
            System.out.print("[X]");
        } else {
            System.out.print("[ ]");
        }
    }

    public void printTasks () {
        Printer.printLine();
        if (tasks.isEmpty()) {
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

    public Task getTask (int i) {
        return tasks.get(i);
    }

    public int getTaskCounter () {
        return tasks.size();
    }

}
