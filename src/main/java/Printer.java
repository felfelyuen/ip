public class Printer {
    public static void printLine() {
        for (int i = 0; i < 40; i++) {
            System.out.print("―");
        }
        System.out.println();
    }

    public static void printLogo() {
        String logo = "\n" +
                " ____  __  ___ ___      __ __   ____  ____   ____   __ __ \n" +
                "|    ||  ||   |   |    |  |  | /    ||    \\ |    \\ |  |  |\n" +
                " |  | |_ || _   _ |    |  |  ||  o  ||  D  )|  D  )|  |  |\n" +
                " |  |   \\||  \\_/  |    |  _  ||     ||    / |    / |  ~  |\n" +
                " |  |     |   |   |    |  |  ||  _  ||    \\ |    \\ |___, |\n" +
                " |  |     |   |   |    |  |  ||  |  ||  .  \\|  .  \\|     |\n" +
                "|____|    |___|___|    |__|__||__|__||__|\\_||__|\\_||____/ \n" +
                "                                                          \n";
        System.out.println(logo);
    }

    public static void printStartingPage () {
        printLine();
        printLogo();
        printLine();
        System.out.println("Hello, I'm Harry!\n"
                + "Your personal helper for... whatever i guess\n"
                + "What you want?\n"
                + "P.S: type \"bye\" to exit"
        );
        printLine();
    }

    public static void printGoodbye () {
        printLine();
        System.out.println("\uD835\uDCD6 \uD835\uDCDE \uD835\uDCDE \uD835\uDCD3 " +
                "\uD835\uDCD1 \uD835\uDCE8 \uD835\uDCD4 ‼‼‼‼‼\n" +
                "..finally i was wondering when you would shut u―");
        printLine();
    }

    public static void printError (String message) {
        printLine();
        System.out.println("Oh no you made a whoopsie: " + message);
        printLine();
    }

    public static void printTaskAdded (TaskManager list) {
        printLine();
        System.out.println("Okay, I added it here, should I add 'touch grass' to the list as well?");
        int counter = list.getTaskCounter();
        list.printTask(counter - 1);
        System.out.print("Now you have " + counter + " task");
        if (counter != 1) {
            System.out.print("s");
        }
        System.out.println(" in the list");
        printLine();
    }
}
