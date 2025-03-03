package harry.Storage;

import harry.Exceptions.CorruptedFileException;
import harry.TaskList.Deadline;
import harry.TaskList.Event;
import harry.TaskList.Task;
import harry.TaskList.Todo;
import harry.Printer.Printer;
import harry.TaskList.TaskManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Class of methods to handle saving and loading of a file that contains the task list.
 * The tasks in the File format is:
 * TYPE//COMPLETED//TASK//DATE
 */

public class HandleFile {
    /**
     * Retrieves tasks from the saved file, and reformats it with reformatFile.
     * @return TaskManager object, which contains the list of tasks
     */
    public static TaskManager retrieveTasks() {
        TaskManager tasks;
        File f = new File("./saved.txt");
        tasks = reformatFile(f);
        return tasks;
    }

    /**
     * Reformats the file into an array of tasks in Task format.
     * @param f file to be reformatted.
     * @return array of tasks.
     */
    public static TaskManager reformatFile (File f) {
        TaskManager tasks = new TaskManager();
        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String line = s.nextLine();
                String[] parts = line.split("//");
                boolean isCompleted = parts[1].equals("X");
                switch (parts[0]) {
                case "todo":
                    Todo todo = new Todo(parts[2], isCompleted, "todo");
                    tasks.addTask(todo);
                    break;
                case "deadline":
                    if (!(parts[3].contains("by:"))) {
                        throw new CorruptedFileException();
                    }
                    Deadline deadline = new Deadline(parts[2], isCompleted, "deadline", parts[3]);
                    tasks.addTask(deadline);
                    break;
                case "event":
                    if ((!parts[3].contains("from:")) || (!parts[3].contains("to:"))) {
                        throw new CorruptedFileException();
                    }
                    Event event = new Event(parts[2], isCompleted,"event", parts[3]);
                    tasks.addTask(event);
                    break;
                default:
                    throw new CorruptedFileException();
                }
            }
            return tasks;
        } catch (FileNotFoundException e) {
            return tasks;
        } catch (ArrayIndexOutOfBoundsException e) {
            Printer.printError("Error reading file, so no save file is loaded");
            return new TaskManager();
        } catch (CorruptedFileException e) {
            Printer.printError("The file might be corrupted, no saved list will be loaded");
            return new TaskManager();
        }
    }

    /**
     * Saves the list of tasks into the file saved.txt
     * Formats the tasks first to be in the File format.
     * @param tasks tasks to be saved
     */
    public static void SaveList(TaskManager tasks) {
        File f = new File("./saved.txt");
        try {
            f.createNewFile();
            String formattedTasks = formatTasks(tasks);
            FileWriter fw = new FileWriter("./saved.txt");
            fw.write(formattedTasks);
            fw.close();
        } catch (IOException e) {
            Printer.printError("uh I can't really save the file lol");
        }
    }

    /**
     * Formats tasks into the File format to be saved.
     * @param tasks list of tasks to be formatted
     * @return String of formatted tasks to be saved.
     */
    public static String formatTasks (TaskManager tasks) {
        String result = "";
        String completed;
        for (int i = 0; i < tasks.getTaskCounter(); i++) {
            Task task = tasks.getTask(i);
            if (task.isCompleted()) {
                completed = "X";
            } else {
                completed = "0";
            }
            result += task.getType() + "//" + completed + "//" + task.getTaskName();
            switch (task.getType()) {
            case "deadline" :
                Deadline deadline = (Deadline) task;
                result += "//" + deadline.getDate();
                break;
            case "event" :
                Event event = (Event) task;
                result += "//" + event.getDate();
                break;
            }
            result += System.lineSeparator();
        }
        return result;
    }
}


