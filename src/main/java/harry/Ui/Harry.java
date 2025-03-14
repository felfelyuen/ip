package harry.ui;

import harry.parser.Parser;
import harry.printer.Printer;
import harry.storage.HandleFile;
import harry.taskList.TaskManager;

/**
 * Main UI of the CLI.
 */
public class Harry {
    public static void main(String[] args) {
        Printer.printStartingPage();

        TaskManager taskList = HandleFile.retrieveTasks();

        Parser.processInput(taskList);

        Printer.printGoodbye();
    }
}