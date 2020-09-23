package duke.taskList;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.io.IOException;

import static duke.storage.StorageHandler.listInputs;
import static duke.common.Messages.*;
import static duke.storage.DukeStorageEncoder.writeListToFile;
import static duke.storage.StorageHandler.createNewFile;
import static duke.ui.UserInterface.*;

/**
 * Holds the methods for adding, removing, checking as done and saving
 * tasks in Duke Program.
 */
public class taskListOperations {
    /**
     * Marks a task as done and saves changes into the txt file.
     *
     * @param input a command the user entered into console
     */
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

    /**
     * Creates a Todo Task, adds it to the current list of Tasks
     * and saves data into the txt file.
     *
     * @param input a command the user entered into console
     */
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

    /**
     * Creates a Deadline Task, adds it to the current list of Tasks
     * and saves data into the txt file.
     *
     * @param input a command the user entered into console
     */
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

    /**
     * Creates a Event Task, adds it to the current list of Tasks
     * and saves data into the txt file.
     *
     * @param input a command the user entered into console
     */
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
     * Saves the current list of Tasks into a txt file.
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
            writeListToFile();
        }catch(IOException e){
            createNewFile();
            saveOperation();
        }finally{
            isPrintMessageEnabled = true;
        }
    }

    /**
     * Deletes a Task from the current list of Tasks
     * and saves changes into the txt file.
     *
     * @param input a command the user entered into console
     */
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

}
