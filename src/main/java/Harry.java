import java.util.Scanner;

public class Harry {

    public static void processAdding (TaskManager list, String[] commands) {
        list.addTask(commands, commands[0]);

        Printer.printLine();
        System.out.println("Okay, I added it here, should I add 'touch grass' to the list as well?");
        int counter = list.getTaskCounter();
        list.printTask(counter - 1);
        System.out.print("Now you have " + counter + " task");
        if (counter != 1) {
            System.out.print("s");
        }
        System.out.println(" in the list");
        Printer.printLine();
    }

    public static void processInput(TaskManager list) {
        while ( true ) {
            Scanner in = new Scanner(System.in);
            String line = in.nextLine();
            String [] commands = line.split(" ");
            switch (commands[0]) {
            case "bye" :
                Printer.printGoodbye();
                return;
            case "list" :
                list.printTasks();
                break;
            //mark and unmark assumes that the number is a number aka "2" and not "2words"
            //TODO add throw exception for if non-number is in commands[1]
            case "mark":
                if (commands.length == 2) {
                    list.markAsCompleted(Integer.parseInt(commands[1]), true);
                }
                break;
            case "unmark":
                if (commands.length == 2) {
                    list.markAsCompleted(Integer.parseInt(commands[1]), false);
                }
                break;
            default:
                processAdding(list, commands);
            break;
            }
        }
    }

    public static void main(String[] args) {
        TaskManager taskList = new TaskManager();

        Printer.printStartingPage();

        processInput(taskList);
    }
}
