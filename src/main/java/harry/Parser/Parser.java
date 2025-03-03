package harry.Parser;

import harry.Storage.HandleFile;
import harry.TaskList.TaskManager;
import harry.Exceptions.HandleNullTaskException;
import harry.Exceptions.MissingDateException;
import harry.Exceptions.MissingDateIndicatorException;
import harry.Exceptions.MissingTaskException;
import harry.Printer.Printer;

import java.util.Scanner;

/**
 * Class of methods to handle input from the user and executes corresponding actions.
 */

public class Parser {

    /**
     * Processes adding of a task into the list.
     * Uses addTask from TaskManager class, and handles exceptions thrown.
     * @param list TaskManager object that corresponds to list of tasks.
     * @param commands Input of the user.
     */
    public static void processAdding (TaskManager list, String[] commands) {
        try {
            list.addTask(commands, commands[0]);
            HandleFile.SaveList(list);
        } catch (MissingTaskException e) {
            Printer.printError("There's no task??? What do you want me to do lol");
        } catch (MissingDateIndicatorException e) {
            Printer.printError("You are missing some indicators... so what date do you want");
        } catch (MissingDateException e) {
            Printer.printError("Where's your date... does time not affect you or—");
        }
    }

    /**
     * Processes marking and unmarking of a task into the list.
     * Uses markAsCompleted from TaskManager class, and handles exceptions thrown.
     * @param list TaskManager object that corresponds to list of tasks.
     * @param commands Input of the user.
     */
    public static void processMarking (TaskManager list, String[] commands) {
        try {
            int index = Integer.parseInt(commands[1]) - 1;
            list.markAsCompleted(index, commands[0].equals("mark"));
            HandleFile.SaveList(list);
        } catch (NumberFormatException e) {
            Printer.printError("That thing after mark is not a number," +
                    " put something like 'mark 2', instead of... whatever that was");
        } catch (ArrayIndexOutOfBoundsException e) {
            Printer.printError("Can you specify what number task to mark, i can't do anything");
        } catch (HandleNullTaskException e) {
            Printer.printError("We don't have that task to mark... are you okay");
        }
    }

    /**
     * Processes deleting of a task into the list.
     * Uses deleteTask from TaskManager class, and handles exceptions thrown.
     * @param list TaskManager object that corresponds to list of tasks.
     * @param commands Input of the user.
     */
    public static void processDeleting (TaskManager list, String[] commands) {
        try {
            int index = Integer.parseInt(commands[1]) - 1;
            list.deleteTask(index);
            HandleFile.SaveList(list);
        } catch (NumberFormatException e) {
            Printer.printError("That thing after delete is not a number," +
                    " put something like 'delete 2', instead of... whatever that was");
        } catch (ArrayIndexOutOfBoundsException e) {
            Printer.printError("Can you specify what number task to delete, i can't do anything");
        } catch (HandleNullTaskException e) {
            Printer.printError("We don't have that task to delete... are you okay");
        }
    }

    /**
     * Processes input of the user.
     * Turns input into array of Strings called commands.
     * Determines what action the user wants to do and calls the relevant method.
     * @param list TaskManager object that corresponds to list of tasks.
     */
    public static void processInput(TaskManager list) {
        while ( true ) {
            Scanner in = new Scanner(System.in);
            String line = in.nextLine();
            String [] commands = line.split(" ");
            switch (commands[0]) {
            case "bye" :
                return;
            case "list" :
                list.printTasks();
                break;
            case "mark":
            case "unmark":
                if (commands.length <= 2) {
                    processMarking(list, commands);
                } else {
                    Printer.printError("write 'mark <number>' instead, i don't know what you are doing");
                }
                break;
            case "delete":
                if (commands.length <= 2) {
                    processDeleting(list, commands);
                } else {
                    Printer.printError("write 'delete <number>' instead, i don't know what you are doing");
                }
                break;
            case "todo":
            case "deadline":
            case "event":
                processAdding(list, commands);
                break;
            default:
                Printer.printError("That's not a valid command, try pressing something like " +
                        "list or bye or todo or whatever");
                break;
            }
        }
    }
}
