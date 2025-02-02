import java.util.Scanner;

public class Harry {

    public static void processMarkingOrAdding(TaskManager list, String line) {
        if (line.startsWith("mark ") || line.startsWith("unmark ")) {
            try {
                if (line.startsWith("mark ")) {
                    int index = Integer.parseInt(line.substring(5)) - 1;
                    list.markAsCompleted(index, true);
                    return;
                } else {
                    int index = Integer.parseInt(line.substring(7)) - 1;
                    list.markAsCompleted(index, false);
                    return;
                }
            } catch (NumberFormatException e) {}
        }
        PrintLine.printLine();
        list.addTask(line);
        System.out.println("added: " + line);
        PrintLine.printLine();
    }

    public static void processInput(TaskManager list) {
        while ( true ) {
            Scanner in = new Scanner(System.in);
            String line = in.nextLine();

            if (line.equals("bye")) {
                PrintLine.printLine();
                return;
            }
            if (line.equals("list")) {
                list.printTasks();
            } else {
                processMarkingOrAdding(list, line);
            }
        }
    }

    public static void main(String[] args) {
        TaskManager TaskList = new TaskManager();

        PrintLine.printLine();
        System.out.println("What's up! I'm Harry!\n"
                + "Your personal helper for... whatever i guess\n"
                + "What can I do for you?\n"
                + "P.S: type \"bye\" to exit"
        );
        PrintLine.printLine();
        processInput(TaskList);
        System.out.println("Bye. Hope to see you again soon!");
        PrintLine.printLine();
    }
}
