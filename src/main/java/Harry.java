import java.util.Scanner;

public class Harry {

    public static void printLine() {
        for (int i = 0; i < 40; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public static void echo() {
        while ( true ) {
            Scanner in = new Scanner(System.in);
            String line = in.nextLine();
            if (line.equals("bye")) {
                printLine();
                return;
            }
            printLine();
            System.out.println(line);
            printLine();
        }
    }

    public static void main(String[] args) {
        printLine();
        System.out.println("Wassup! I'm Harry!\n"
                + "Your personal helper for... whatever i guess\n"
                + "What can I do for you?\n"
                + "P.S: type \"bye\" to exit"
        );
        printLine();
        echo();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }
}
