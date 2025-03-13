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

//todo mayhaps move printing messages all to printer? (from taskmanager)
//todo recheck all the code quality and code standards
//todo recheck OOP and abstraction
//todo okay after allat should be okay for release