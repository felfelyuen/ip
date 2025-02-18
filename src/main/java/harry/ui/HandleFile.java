package harry.ui;

import harry.tasks.Deadline;
import harry.tasks.Event;
import harry.tasks.Task;
import harry.tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class HandleFile {

    /*
    The file will be formatted as:
    type//1//TASK//DATE
     */

    /*
    TODO: RETRIEVETASKS DOESNT ACTUALLY RETRIEVE ???
    TODO: SAVELIST DOESNT WORK EXCEPT FOR SOMEHOW ONLY, BYE??? AND ONLY THE FIRST TASK
     */

    public static TaskManager retrieveTasks() {
        TaskManager tasks;
        File f = new File("./data/saved.txt");
        tasks = reformatFile(f);
        return tasks;
    }

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
                    Deadline deadline = new Deadline(parts[2], isCompleted, "deadline", parts[3]);
                    tasks.addTask(deadline);
                    break;
                case "event":
                    Event event = new Event(parts[2], isCompleted,"event", parts[3]);
                    tasks.addTask(event);
                    break;
                }
            }
            return tasks;
        } catch (FileNotFoundException e) {
            return tasks;
        }
    }

    public static void SaveList(TaskManager tasks) {
        File f = new File("./data/saved.txt");
        try {
            f.createNewFile();
            String formattedTasks = formatTasks(tasks);
            FileWriter fw = new FileWriter("./data/saved.txt");
            fw.write(formattedTasks);
            fw.close();
        } catch (IOException e) {
            Printer.printError("uh I can't really save the file lol");
        }
    }

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
            result += task.getType() + "//" + completed + "//" + task.getTask();
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


