package duke;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;
import duke.task.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;


public class Duke {
    static ArrayList<Task> listInputs = new ArrayList<>();
    public static String listPath = "/src/main/java/taskList.txt";
    public static boolean isPrintMessageEnabled = true;
    //static int listPosition = 0;

    private static final String BYE_MESSAGE = " " + "Bye. Hope to see you again soon!";
    private static final String LIST_HEADER_MESSAGE = " "  + "Here are the tasks in your list:";
    private static final String DONE_HEADER_MESSAGE = " " + "Nice! I've marked this task as done: ";
    private static final String DEADLINE_FORMAT_MESSAGE = " " + "Invalid deadline declaration\n" +
            " " + "deadline {info} /by {date}";
    private static final String EVENT_FORMAT_MESSAGE = " " + "Invalid event declaration\n" +
            " " + "event {info} /by {date} {time}";
    private static final String INVALID_INPUT_MAIN_MESSAGE =
            " " + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    private static final String EMPTY_DONE_INPUT_MESSAGE = " " + "☹ OOPS!!! The description of a done cannot be empty.";
    private static final String EMPTY_TODO_INPUT_MESSAGE = " " + "☹ OOPS!!! The description of a todo cannot be empty.";
    private static final String EMPTY_DEADLINE_INPUT_MESSAGE =
            " " + "☹ OOPS!!! The description of a deadline cannot be empty.";
    private static final String EMPTY_EVENT_INPUT_MESSAGE =
            " " + "☹ OOPS!!! The description of a event cannot be empty.";

    private static final String VALID_INDEX_RANGE =
            "{valid index from 1 to " + Integer.toString(listInputs.size()) + "}";

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

    public static void todoOperation(String input, int mode){
        if(input.length() == 5){
            printMessage(EMPTY_TODO_INPUT_MESSAGE);
            return;
        }

        int indexAdded = listInputs.size();
        String todoData = input.substring(5, input.length());
        listInputs.add(new Todo(todoData, false));
        printAddedTaskMessage(indexAdded);
    }


    public static void deadlineOperation(String input, int mode){
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
    }

    public static void eventOperation(String input, int mode){
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
    }

    public static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        writer.write(textToAdd);
        writer.close();
    }

    public static void fileToList(String filePath) throws FileNotFoundException {
        File file = new File(filePath); // create a File for the given file path
        Scanner output = new Scanner(file); // create a Scanner using the File as the source
        String line;
        while (output.hasNext()) {
            line = output.nextLine();
            //System.out.println(line);
            addNewTask(line);
        }
    }

    public static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter writer = new FileWriter(filePath, true);
        writer.write(textToAppend);
        writer.close();
    }

    public static void createNewFile(String filePath){
        try{
            File newFile = new File(filePath);
            if(newFile.createNewFile() == true){
                System.out.println("A new file created");
            }
        }catch(IOException e) {
            System.out.println("An error occurred.");
        }
    }

    public static void getList(String taskListPath){
        isPrintMessageEnabled = false;
        try {
            fileToList(taskListPath);
        } catch (FileNotFoundException e) {
            createNewFile(taskListPath);
        }finally{
            isPrintMessageEnabled = true;
        }
    }

    public static void addNewTask(String input){
        if(input.length() <= 8){
            System.out.println("Invalid task read.");
            return;
        }

        String inputHeader = input.substring(0, 2);
        if(inputHeader.equals("T ") == true || inputHeader.equals("D ") == true ||
                inputHeader.equals("E ") == true){
            //Successful reading of data
        }else{
            System.out.println("Invalid task read.");
            return;
        }

        String inputDoneData = input.substring(2, 8);
        if(inputDoneData.equals("| 1 | ") == true || inputDoneData.equals("| 0 | ") == true){
            //Successful reading of data
        }else{
            System.out.println("Invalid task read.");
            return;
        }

        String inputData = input.substring(8, input.length());

        if(input.startsWith("T ") == true) {
            todoOperation("todo " + inputData, 1);
        }else if(input.startsWith("D ") == true) {
            deadlineOperation("deadline " + inputData, 1);
        }else if(input.startsWith("E ") == true) {
            eventOperation("event " + inputData, 1);
        }else{
            System.out.println("Invalid task read.");
        }

        if(inputDoneData.contains("1")){
            listInputs.get(listInputs.size() - 1).setIsDone(true);
        }

    }

    public static void main(String[] args) {
        String currentWorkingDir = System.getProperty("user.dir");
        listPath = currentWorkingDir + listPath;
        getList(listPath);

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

        String input = "empty";
        Scanner in = new Scanner(System.in);
        boolean isRunning = true;

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
        while(isRunning == true){
            input = in.nextLine();
            if(input.equals("bye")) {
                printMessage(BYE_MESSAGE);
                isRunning = false;
            }else if (input.equals("list")){
                printList();
            }else if(input.startsWith("done ") == true) {
                doneOperation(input);
            }else if(input.startsWith("todo ") == true) {
                todoOperation(input, 0);
            }else if(input.startsWith("deadline ") == true) {
                deadlineOperation(input, 0);
            }else if(input.startsWith("event ") == true) {
                eventOperation(input, 0);
            }else if(input.equals("done") || input.equals("todo") ||
                    input.equals("deadline") || input.equals("event")) {
                printMessage(" ☹ OOPS!!! The description of a " + input + " cannot be empty.");
            }else {
                printMessage(INVALID_INPUT_MAIN_MESSAGE);
            }
        }
    }
}
