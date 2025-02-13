public class Printer {
    public static void printLine() {
        for (int i = 0; i < 40; i++) {
            System.out.print("-");
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
        System.out.println("Hello, I'm Harry!\n"
                + "Your personal helper for... whatever i guess\n"
                + "What you want?\n"
                + "P.S: type \"bye\" to exit"
        );
        printLine();
    }

    public static void printGoodbye () {
        printLine();
        System.out.println("Bye. Please don't contact me again i'm tired");
        printLine();
    }
}
