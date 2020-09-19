package duke;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.IOException;

import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;

import static duke.common.Messages.*;
import static duke.parser.Parser.parseCommand;
import static duke.storage.DukeStorageDecoder.getList;
import static duke.storage.DukeStorageEncoder.writeListToFile;
import static duke.storage.StorageHandler.createNewFile;

public class Duke {
    public static ArrayList<Task> listInputs = new ArrayList<>();
    public static String listPath = "/taskList.txt";
    public static boolean isPrintMessageEnabled = true;
    public static boolean isLoading = true;

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

    public static void printDoneStatement(Task taskObject){
        String outputMessage = DONE_HEADER_MESSAGE + "\n" +
                "   " + taskObject.toString();
        printMessage(outputMessage);
    }

    public static void doneOperation(String input){
        if(listInputs.size() == 0){
            printMessage(" List is empty");
            return;
        }else if(input.length() == 5){
            printMessage(EMPTY_DONE_INPUT_MESSAGE);
            return;
        }

        try{
            int taskNumber = Integer.parseInt(input.substring(5, input.length())) - 1;
            if(taskNumber < listInputs.size() && taskNumber >= 0){
                listInputs.get(taskNumber).setIsDone(true);
                printDoneStatement(listInputs.get(taskNumber));
                saveOperation();
            }else{
                printMessage(" Invalid task number\n " +
                        "done " + VALID_INDEX_RANGE);
            }
        }catch(NumberFormatException e){
            printMessage(" Invalid index for done operation\n " +
                    "done " + VALID_INDEX_RANGE);
        }
    }

    public static void printAddedTaskMessage(int taskIndex){
        String outputMessage = " Got it. I've added this task: \n" + "   " +
                listInputs.get(taskIndex).toString() + "\n" +
                " Now you have " + Integer.toString(listInputs.size()) + " tasks in the list.";
        printMessage(outputMessage);
    }

    public static void todoOperation(String input){
        if(input.length() == 5){
            printMessage(EMPTY_TODO_INPUT_MESSAGE);
            return;
        }

        int indexAdded = listInputs.size();
        String todoData = input.substring(5, input.length());
        listInputs.add(new Todo(todoData, false));
        printAddedTaskMessage(indexAdded);
        saveOperation();
    }

    public static void deadlineOperation(String input){
        if(input.length() == 9){
            printMessage(EMPTY_DEADLINE_INPUT_MESSAGE);
            return;
        }

        input = input.substring(9, input.length());
        if(input.startsWith("/by") == true || input.contains(" /by ") == false){
            printMessage(DEADLINE_FORMAT_MESSAGE);
            return;
        }

        int indexAdded = listInputs.size();
        int separatorIndex = input.indexOf(" /by ");
        String data = input.substring(0, separatorIndex);
        String date = input.substring(separatorIndex + 5, input.length());
        listInputs.add(new Deadline(data, false, date));
        printAddedTaskMessage(indexAdded);
        saveOperation();
    }

    public static void eventOperation(String input){
        if(input.length() == 6){
            printMessage(EMPTY_EVENT_INPUT_MESSAGE);
            return;
        }

        input = input.substring(6, input.length());
        if(input.startsWith("/at") == true || input.contains(" /at ") == false){
            printMessage(EVENT_FORMAT_MESSAGE);
            return;
        }

        int indexAdded = listInputs.size();
        int separatorIndex = input.indexOf(" /at ");
        String data = input.substring(0, separatorIndex);
        String dateAndTime = input.substring(separatorIndex + 5, input.length());
        listInputs.add(new Event(data, false, dateAndTime));
        printAddedTaskMessage(indexAdded);
        saveOperation();
    }

    /**
     * Save operation triggers for the following actions
     * todoOperation: adds todo task to the list
     * deadlineOperation: add deadline task to the list
     * eventOperation: add event task to the list
     * deleteOperation: removes a task from the list
     * doneOperation: appends isDone variable of task
     * save command triggered: manually save tasks in list
     */
    public static void saveOperation(){
        if(listInputs.size() <= 0){
            printMessage(" List is empty");
            return;
        }else if(isLoading == true){
            return;
        }

        isPrintMessageEnabled = false;
        try{
            writeListToFile(listPath);
        }catch(IOException e){
            createNewFile(listPath);
            saveOperation();
        }finally{
            isPrintMessageEnabled = true;
        }
    }

    public static void deleteOperation(String input){
        if(listInputs.size() == 0){
            printMessage(" List is empty");
            return;
        }else if(input.length() == 7){
            printMessage(EMPTY_DELETE_INPUT_MESSAGE);
            return;
        }

        try{
            int taskNumber = Integer.parseInt(input.substring(7, input.length())) - 1;
            if(taskNumber < listInputs.size() && taskNumber >= 0){
                String taskData = listInputs.get(taskNumber).toString();
                listInputs.remove(taskNumber);
                printDeleteStatement(taskData);
                saveOperation();
            }else{
                printMessage(" Invalid task number\n " +
                        "delete " + VALID_INDEX_RANGE);
            }
        }catch(NumberFormatException e){
            printMessage(" Invalid index for delete operation\n " +
                    "delete " + VALID_INDEX_RANGE);
        }

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

    public static void main(String[] args) {
        String currentWorkingDir = System.getProperty("user.dir");
        listPath = currentWorkingDir + listPath;
        getList(listPath);

        printLogo();

        String input = "empty";
        Scanner in = new Scanner(System.in);
        int isRunning = 1;

        /**
         * Special condition for each commands done, todo, deadline, event.
         * Each command are required to have a space after the command to identify itself to not cause
         * conflict with other commands with similar names.
         * For example, current "event" command creates a event object and adds it to the list. However, if
         * required in the future to create a "events" command to return all events on the list, "event"
         * command would be triggered instead of "events" due to sequential code. A space would differentiate
         * commands of similar naming.
         * A temporary check for these commands without spacing is implemented in case of conflict with
         * marking rubric or algorithm.
         */
        while(isRunning == 1){
            input = in.nextLine();
            isRunning = parseCommand(input);
        }
    }
}
