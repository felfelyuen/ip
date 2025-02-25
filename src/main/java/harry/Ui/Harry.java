package harry.Ui;

import harry.Parser.Parser;
import harry.Printer.Printer;
import harry.Storage.HandleFile;
import harry.TaskList.TaskManager;

public class Harry {
    public static void main(String[] args) {
        Printer.printStartingPage();

        TaskManager taskList = HandleFile.retrieveTasks();

        Parser.processInput(taskList);

        Printer.printGoodbye();
    }
}
