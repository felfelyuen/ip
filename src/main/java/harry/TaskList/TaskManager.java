package harry.TaskList;

import harry.Exceptions.*;
import harry.Printer.Printer;

import java.util.ArrayList;

/**
 * TaskManager represents the array of tasks that the user has.
 * Contains methods for the user to use to edit and view the tasklist.
 */

public class TaskManager {

    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Takes in an array of strings, and adds spaces between the words
     * The output is a sentence.
     * @param array Strings to be spaced
     * @param startIndex starting word of output
     * @param endIndex ending word of output
     * @return outputted sentence
     */
    public static String addSpaces(String[] array, int startIndex, int endIndex) {
        String result = "";
        for (int i = startIndex; i < endIndex; i++) {
            result += array[i] + " ";
        }
        result += array[endIndex];
        return result;
    }

    /**
     * Searches for a keyword within the function
     * @param array target to search for the keyword
     * @param keyword the keyword
     * @return the index of the keyword, returns -1 if keyword is not in array
     */
    public static int search (String[] array, String keyword) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(keyword)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Adds a task into the list, based off the type of task it is.
     * After adding, prints out corresponding messages to the user.
     * @param commands What the user has inputted
     * @param type What the type of the task is
     * @throws MissingTaskException If there is no task
     * @throws MissingDateIndicatorException If there are no indicators for the dates eg: "/by"
     * @throws MissingDateException If there is no date to be inputted
     */
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

    /**
     * An alternative to addTask, where there is a Task object to be added already.
     * Used by some methods in HandleFile class, for saving and loading the tasklist.
     * @param task the task to be added
     */
    public void addTask (Task task) {
        tasks.add(task);
    }

    /**
     * Marks task as completed, and prints out corresponding messages to user.
     * @param index index of task to be deleted
     * @param completed to mark whether it is completed
     * @throws HandleNullTaskException If the list does not have the task to be marked
     */
    public void markAsCompleted (int index, boolean completed) throws HandleNullTaskException {
        if (index + 1 > tasks.size()) {
            throw new HandleNullTaskException();
        }
        tasks.get(index).setCompleted(completed);
        if (completed) {
            Printer.printLine();
            System.out.println("Nice! I've marked this task as done:");
            tasks.get(index).printTask();
            Printer.printLine();
        } else {
            Printer.printLine();
            System.out.println("OK, I've marked this task as not done yet:");
            tasks.get(index).printTask();
            System.out.println("Get to work!");
            Printer.printLine();
        }
    }

    /**
     * Deletes task from list, and prints corresponding messages to user.
     * @param index index of task to be deleted
     * @throws HandleNullTaskException If the list does not have the task to be deleted
     */
    public void deleteTask(int index) throws HandleNullTaskException {
        if (index + 1 > tasks.size()) {
            throw new HandleNullTaskException();
        }
        Printer.printLine();
        System.out.println("Oh wow congrats, I've deleted this task");
        tasks.get(index).printTask();
        System.out.println("And many happy returns to you too");
        Printer.printLine();

        tasks.remove(index);
    }

    /**
     * Prints all the tasks in the list in sequential order.
     */
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


    public Task getTask (int i) {
        return tasks.get(i);
    }

    public int getTaskCounter () {
        return tasks.size();
    }

}
