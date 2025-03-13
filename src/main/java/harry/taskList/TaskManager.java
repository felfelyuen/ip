package harry.taskList;

import harry.exceptions.InvalidDateFormatException;
import harry.exceptions.MissingDateException;
import harry.exceptions.MissingDateIndicatorException;
import harry.exceptions.MissingTaskException;
import harry.exceptions.InvalidDateException;
import harry.exceptions.HandleNullTaskException;

import harry.printer.Printer;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.time.LocalDateTime;


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
    public static int search(String[] array, String keyword) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(keyword)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Adds a new Todo task to the task list.
     *
     * @param commands An array of strings representing the user input command.
     *                 The first element should be "todo", followed by the task description.
     * @throws MissingTaskException If the task description is missing.
     */
    public void addTodo(String [] commands) throws MissingTaskException {
        if (commands.length == 1) {
            throw new MissingTaskException();
        }
        String task = addSpaces(commands, 1, commands.length - 1);
        tasks.add(new Todo(task, false, "todo"));
    }

    /**
     * Adds a new Deadline task to the task list.
     *
     * @param commands An array of strings representing the user input command.
     *                 It should include "/by" followed by a valid date.
     * @throws MissingTaskException If the task description is missing.
     * @throws MissingDateIndicatorException If the "/by" keyword is missing.
     * @throws MissingDateException If the date is not provided after "/by".
     * @throws InvalidDateFormatException If the date format is incorrect.
     * @throws InvalidDateException If the date provided is invalid.
     */
    public void addDeadline(String [] commands)
            throws MissingTaskException,
            MissingDateIndicatorException,
            MissingDateException,
            InvalidDateFormatException,
            InvalidDateException {
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

        String task = addSpaces(commands, 1, byIndex - 1);
        LocalDateTime date = HandleDate.parseDate(commands[byIndex + 1] + " " + commands[byIndex + 2]);
        tasks.add(new Deadline(task, false, "deadline", date));
    }

    /**
     * Adds a new Event task to the task list.
     *
     * @param commands An array of strings representing the user input command.
     *                 It should include "/from" followed by a start date and "/to" followed by an end date.
     * @throws MissingTaskException If the task description is missing.
     * @throws MissingDateIndicatorException If the "/from" or "/to" keyword is missing.
     * @throws MissingDateException If the dates are not provided after "/from" or "/to".
     * @throws InvalidDateFormatException If the date format is incorrect.
     * @throws InvalidDateException If the date provided is invalid.
     */
    public void addEvent(String[] commands)
            throws MissingTaskException,
            MissingDateIndicatorException,
            InvalidDateFormatException,
            InvalidDateException,
            MissingDateException {
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
        String task = addSpaces(commands, 1, fromIndex - 1);
        LocalDateTime fromDate = HandleDate.parseDate(commands[fromIndex + 1] + " " +  commands[fromIndex + 2]);
        LocalDateTime toDate = HandleDate.parseDate(commands[toIndex + 1] + " " + commands[toIndex + 2]);
        tasks.add(new Event(task, false, "event", fromDate, toDate));
    }

    /**
     * Adds a task into the list, based off the type of task it is.
     * After adding, prints out corresponding messages to the user.
     * @param commands What the user has inputted
     * @param type What the type of the task is
     * @throws MissingTaskException If there is no task
     * @throws MissingDateIndicatorException If there are no indicators for the dates eg: "/by"
     * @throws MissingDateException If there is no date to be inputted
     * @throws DateTimeParseException If the String to parse into DateTime contains irregularities.
     * @throws ArrayIndexOutOfBoundsException If the String has a smaller length than expected
     * @throws InvalidDateFormatException If the String is of a different format, ie: YYYY/MM/DD is not accepted.
     * @throws InvalidDateException If an invalid date is submitted, ie: month is 16.
     * @throws NumberFormatException If a String cannot be formatted into a number.
     */
    public void addTask(String[] commands, String type)
            throws MissingTaskException,
            MissingDateIndicatorException,
            MissingDateException,
            DateTimeParseException,
            ArrayIndexOutOfBoundsException,
            InvalidDateFormatException,
            InvalidDateException,
            NumberFormatException
            {
        switch (type) {
        case "todo":
            addTodo(commands);
            break;
        case "deadline":
            addDeadline(commands);
            break;
        case "event":
            addEvent(commands);
            break;
        }
        printTaskAdded();
    }

    /**
     * An alternative to addTask, where there is a Task object to be added already.
     * Used by some methods in HandleFile class, for saving and loading the tasklist.
     * @param task the task to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    public void printTaskAdded() {
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
     * Marks task as completed, and prints out corresponding messages to user.
     * @param index index of task to be deleted
     * @param completed to mark whether it is completed
     * @throws HandleNullTaskException If the list does not have the task to be marked
     */
    public void markAsCompleted(int index, boolean completed) throws HandleNullTaskException {
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
    public void printTasks() {
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

    public Task getTask(int i) {
        return tasks.get(i);
    }

    public int getTaskCounter() {
        return tasks.size();
    }

    /**
     * Prints tasks with the keyword
     * @param keyword keyword to be searched for
     */
    public void printFoundTasks(String keyword) {
        Printer.printLine();

        int i = 1;
        if (tasks.isEmpty()) {
            System.out.println("Your list is empty bro lol");
            Printer.printLine();
            return;
        }
        System.out.println("Here's your tasks that has that keyword:");
        for (Task a : tasks) {
            String task = a.getTaskName();
            if (task.contains(keyword)) {
                System.out.print(i + ".");
                a.printTask();
                i++;
            }
        }
        if (i == 1) {
            System.out.println("Just kidding there is nothing to find idk");
        }
        Printer.printLine();
    }
}
