package duke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The TaskList class contains a list of tasks and keeps track of it while Duke is running.
 */
public class TaskList {

    private static String border = "-----------------------------------------------------------";
    private static String indentation = "    ";
    private List<Task> tasks;

    /**
     * Constructor to initialise a new list of tasks.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructor which takes in a list of tasks and stores it as a field member.
     *
     * @param tasks list of tasks
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Prints the formatting for the Duke application.
     */
    public static void printBorder() {
        System.out.println(indentation + border);
    }

    /**
     * Returns a list of tasks.
     *
     * @return a list of tasks
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds a task to the list of tasks.
     *
     * @param task the task to be added
     * @throws IOException produced by failed or interrupted I/O operations
     */
    public void add(Task task) throws IOException {
        printBorder();
        tasks.add(task);
        String textToAppend = task.getSymbol() + " @ " + task.getStatusIcon() + " @ "
                + task.getDescription() + " @ " + task.getDate() + "\n";
        Storage.appendToFile(Storage.getFilePath(), textToAppend);

        System.out.println(indentation + "Got it. I've added this task:");
        System.out.println(indentation + indentation + task);
        System.out.println(indentation + "Now you have " + (tasks.size() != 1
                ? tasks.size() + " tasks in the list."
                : tasks.size() + " task in the list."));
        printBorder();
    }

    /**
     * Removes a task from the list of tasks.
     *
     * @param index position of the task in the list of tasks to be deleted
     * @throws IOException produced by failed or interrupted I/O operations
     */
    public void delete(int index) throws IOException {
        Task task = tasks.get(index);
        tasks.remove(task);
        Storage.updateFile(Storage.getFilePath(), this);

        printBorder();
        System.out.println(indentation + "Noted. I've removed this task:");
        System.out.println(indentation + indentation + task);
        System.out.println(indentation + "Now you have " + (tasks.size() != 1
                ? tasks.size() + " tasks in the list."
                : tasks.size() + " task in the list."));
        printBorder();
    }

    /**
     * Replaces a task with another task in the list of tasks.
     *
     * @param oldTask old task to be replaced
     * @param newTask new task to replace the old task
     */
    public void replace(Task oldTask, Task newTask) {
        int index = tasks.indexOf(oldTask);
        tasks.set(index, newTask);
    }
}
