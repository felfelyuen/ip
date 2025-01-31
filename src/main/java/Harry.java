public class Harry {
    public static void printLine() {
        for (int i = 0; i < 40; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        printLine();
        System.out.println("Hello! I'm Harry! \n" + "What can I do for you?");
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
        //System.out.println("Hello from\n" + logo);
    }
}
