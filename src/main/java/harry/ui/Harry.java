package harry.ui;

import harry.exceptions.HandleNullTaskException;
import harry.exceptions.MissingDateException;
import harry.exceptions.MissingDateIndicatorException;
import harry.exceptions.MissingTaskException;

import java.util.Scanner;

public class Harry {

    public static void processAdding (TaskManager list, String[] commands) {
        try {
            list.addTask(commands, commands[0]);
        } catch (MissingTaskException e) {
            Printer.printError("There's no task??? What do you want me to do lol");
        } catch (MissingDateIndicatorException e) {
            Printer.printError("You are missing some indicators... so what date do you want");
        } catch (MissingDateException e) {
            Printer.printError("Where's your date... does time not affect you or—");
        }
    }

    public static void processMarking (TaskManager list, String[] commands) {
        try {
            int index = Integer.parseInt(commands[1]) - 1;
            if (commands[0].equals("mark")) {
                list.markAsCompleted(index, true);
            } else {
                list.markAsCompleted(index, false);
            }
        } catch (NumberFormatException e) {
            Printer.printError("That thing after mark is not a number," +
                    " put something like 'mark 2', instead of... whatever that was");
        } catch (ArrayIndexOutOfBoundsException e) {
            Printer.printError("Can you specify what number task to mark, i can't do anything");
        } catch (HandleNullTaskException e) {
            Printer.printError("We don't have that task to mark... are you okay");
        }
    }

    public static void processDeleting (TaskManager list, String[] commands) {
        try {
            int index = Integer.parseInt(commands[1]) - 1;
            list.deleteTask(index);
        } catch (NumberFormatException e) {
            Printer.printError("That thing after delete is not a number," +
                    " put something like 'delete 2', instead of... whatever that was");
        } catch (ArrayIndexOutOfBoundsException e) {
            Printer.printError("Can you specify what number task to delete, i can't do anything");
        } catch (HandleNullTaskException e) {
            Printer.printError("We don't have that task to delete... are you okay");
        }
    }

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

    public static void main(String[] args) {
        TaskManager taskList = new TaskManager();

        Printer.printStartingPage();

        processInput(taskList);

        Printer.printGoodbye();
    }
}
