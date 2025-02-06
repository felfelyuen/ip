import java.util.Scanner;

public class Harry {

    public static void processAdding (TaskManager list, String line) {
        int firstSpace = line.indexOf(' ');
        String type = line.substring(0, firstSpace);

        line = (line.substring(firstSpace + 1)).trim();
        String task;
        String date;
        if (type.equals("todo")) {
            list.addTask(line, "todo", "");
        } else if (type.equals("deadline")) {
            int byIndex = line.indexOf("/by");
            String newDate = "by: " + (line.substring(byIndex + 3)).trim();
            list.addTask((line.substring(0, byIndex)).trim(), "deadline", newDate);
        } else if (type.equals("event")) {
            int fromIndex = line.indexOf("/from");
            int toIndex = line.indexOf("/to");
            String newDateFrom = (line.substring(fromIndex + 5, toIndex)).trim();
            String newDateTo = (line.substring(toIndex + 3)).trim();
            String newDate = "from: " + (newDateFrom) + " to: " + (newDateTo);
            list.addTask(line.substring(0, fromIndex).trim(), "event", newDate);
        }

        PrintLine.printLine();
        System.out.println("Okay, I added it here, should I add \'touch grass\' to the list as well?");
        int counter = list.getTaskCounter();
        list.printTask(counter - 1);
        System.out.print("Now you have " + counter + " task");
        if (counter != 1) {
            System.out.print("s");
        }
        System.out.println(" in the list");
        PrintLine.printLine();
    }

    public static void processMarkingOrAdding(TaskManager list, String line) {
        line = line.trim();
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

        processAdding(list, line);
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
        String logo = "\n" +
                " ____  __  ___ ___      __ __   ____  ____   ____   __ __ \n" +
                "|    ||  ||   |   |    |  |  | /    ||    \\ |    \\ |  |  |\n" +
                " |  | |_ || _   _ |    |  |  ||  o  ||  D  )|  D  )|  |  |\n" +
                " |  |   \\||  \\_/  |    |  _  ||     ||    / |    / |  ~  |\n" +
                " |  |     |   |   |    |  |  ||  _  ||    \\ |    \\ |___, |\n" +
                " |  |     |   |   |    |  |  ||  |  ||  .  \\|  .  \\|     |\n" +
                "|____|    |___|___|    |__|__||__|__||__|\\_||__|\\_||____/ \n" +
                "                                                          \n";

        PrintLine.printLine();
        System.out.println(logo);
        System.out.println("Hello, I'm Harry!\n"
                + "Your personal helper for... whatever i guess\n"
                + "What you want?\n"
                + "P.S: type \"bye\" to exit"
        );
        PrintLine.printLine();
        processInput(TaskList);
        System.out.println("Bye. Please don't contact me again i'm tired");
        PrintLine.printLine();
    }
}
