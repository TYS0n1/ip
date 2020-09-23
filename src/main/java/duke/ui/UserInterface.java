package duke.ui;

import duke.task.Deadline;
import duke.task.Task;
import duke.common.Messages;

import java.time.LocalDate;
import java.util.ArrayList;

import static duke.storage.StorageHandler.listInputs;


/**
 * Holds the methods and global variables required for displaying information
 * about Duke program to the user.
 */
public class UserInterface {
    /**
     * Global variable isPrintMessageEnabled used to control printMessage function's
     * ability to print information to the user.
     * Global variable isLoading used during setup of the Duke program to hide background
     * methods' messages from the user.
     */
    public static boolean isPrintMessageEnabled;
    public static boolean isLoading;

    /**
     * Constructor. Sets UserInterface variables to respective states
     * and prints the logo of Duke program.
     */
    public UserInterface() {
        isPrintMessageEnabled = true;
        isLoading = false;
        printLogo();
    }

    /**
     * Prints message to the user inside dash boxes unless isPrintMessageEnabled is
     * set to false.
     *
     * @param message a message the program displays for the user.
     */
    public static void printMessage(String message) {
        if (isPrintMessageEnabled == false) {
            return;
        }

        System.out.println("____________________________________________________________");
        System.out.println(message);
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints out all Tasks found in the Task ArrayList of Duke program.
     */
    public static void printList() {
        System.out.println("____________________________________________________________");
        System.out.println(Messages.LIST_HEADER);
        for (int i = 0; i < listInputs.size(); i++) {
            System.out.printf(" %d.%s\n", i + 1, listInputs.get(i).toString());
        }
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints out all Tasks in the Task ArrayList matching search String.
     *
     * @param matchingTasks a Task ArrayList containing Tasks matching searched String.
     */
    public static void printSearchedTasks(ArrayList<Task> matchingTasks) {
        System.out.println("____________________________________________________________");
        System.out.println(Messages.SEARCH_HEADER);
        for (int i = 0; i < matchingTasks.size(); i++) {
            System.out.printf(" %d.%s\n", i + 1, matchingTasks.get(i).toString());
        }
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints out all Tasks in the Task ArrayList matching input date.
     *
     * @param tasksOnTargetDate a Task ArrayList containing Tasks matching input date.
     * @param targetDate a LocalDate the user inputted in the command line.
     */
    public static void printOccurences(ArrayList<Deadline> tasksOnTargetDate, LocalDate targetDate) {
        System.out.println("____________________________________________________________");
        System.out.println(Messages.OCCUR_HEADER + targetDate.toString());
        for (int i = 0; i < tasksOnTargetDate.size(); i++) {
            System.out.printf(" %d.%s\n", i + 1, tasksOnTargetDate.get(i).toString());
        }
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints out a message to show Task was added successfully. Then prints out
     * a message showing how many Tasks are there in the Task ArrayList of Duke program.
     */
    public static void printAddedTaskMessage(int taskIndex) {
        String outputMessage = " Got it. I've added this task: \n" + "   " +
                listInputs.get(taskIndex).toString() + "\n" +
                " Now you have " + Integer.toString(listInputs.size()) + " tasks in the list.";
        printMessage(outputMessage);
    }

    /**
     * Prints out a message to show Task was set as done successfully.
     */
    public static void printDoneStatement(Task taskObject) {
        String outputMessage = Messages.DONE_HEADER + "\n" +
                "   " + taskObject.toString();
        printMessage(outputMessage);
    }

    /**
     * Prints out a message to show Task was deleted successfully. Then prints out
     * a message showing how many Tasks are there in the Task ArrayList of Duke program.
     */
    public static void printDeleteStatement(String data) {
        String outputMessage = " Noted. I've removed this task: \n" + "   " +
                data + "\n" +
                " Now you have " + Integer.toString(listInputs.size()) + " tasks in the list.";
        printMessage(outputMessage);
    }

    /**
     * Prints out an Ascii art for the Duke program and a greeting message.
     */
    public static void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }
}
