package harry.ui;

import java.util.Scanner;

public class Harry {

    public static void processAdding (TaskManager list, String[] commands) {
        list.addTask(commands, commands[0]);
        /*
        harry.ui.Printer.printLine();
        System.out.println("Okay, I added it here, should I add 'touch grass' to the list as well?");
        int counter = list.getTaskCounter();
        list.printTask(counter - 1);
        System.out.print("Now you have " + counter + " task");
        if (counter != 1) {
            System.out.print("s");
        }
        System.out.println(" in the list");
        harry.ui.Printer.printLine();
        */
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
            Printer.printError("Can you specify what number to mark, i can't do anything");
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
