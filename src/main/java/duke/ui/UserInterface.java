package duke.ui;

import duke.task.Deadline;
import duke.task.Task;

import java.time.LocalDate;
import java.util.ArrayList;

import static duke.storage.StorageHandler.listInputs;
import static duke.common.Messages.*;

public class UserInterface {
    public static boolean isPrintMessageEnabled;
    public static boolean isLoading;

    public UserInterface(){
        isPrintMessageEnabled = true;
        isLoading = false;
        printLogo();
    }

    public static void printMessage(String message){
        if(isPrintMessageEnabled == false){
            return;
        }

        System.out.println("____________________________________________________________");
        System.out.println(message);
        System.out.println("____________________________________________________________");
    }

    public static void printList(){
        System.out.println("____________________________________________________________");
        System.out.println(LIST_HEADER_MESSAGE);
        for(int i = 0; i < listInputs.size(); i++){
            System.out.printf(" %d.%s\n", i + 1, listInputs.get(i).toString());
        }
        System.out.println("____________________________________________________________");
    }

    public static void printOccurences(ArrayList<Deadline> tasksOnTargetDate, LocalDate targetDate){
        System.out.println("____________________________________________________________");
        System.out.println(OCCUR_HEADER_MESSAGE + targetDate.toString());
        for(int i = 0; i < tasksOnTargetDate.size(); i++){
            System.out.printf(" %d.%s\n", i + 1, tasksOnTargetDate.get(i).toString());
        }
        System.out.println("____________________________________________________________");
    }

    public static void printAddedTaskMessage(int taskIndex){
        String outputMessage = " Got it. I've added this task: \n" + "   " +
                listInputs.get(taskIndex).toString() + "\n" +
                " Now you have " + Integer.toString(listInputs.size()) + " tasks in the list.";
        printMessage(outputMessage);
    }

    public static void printDoneStatement(Task taskObject){
        String outputMessage = DONE_HEADER_MESSAGE + "\n" +
                "   " + taskObject.toString();
        printMessage(outputMessage);
    }

    public static void printDeleteStatement(String data){
        String outputMessage = " Noted. I've removed this task: \n" + "   " +
                data + "\n" +
                " Now you have " + Integer.toString(listInputs.size()) + " tasks in the list.";
        printMessage(outputMessage);
    }

    public static void printLogo(){
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