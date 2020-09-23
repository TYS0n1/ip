package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static duke.ui.UserInterface.isPrintMessageEnabled;
import static duke.ui.UserInterface.isLoading;
import static duke.storage.StorageHandler.listInputs;
import static duke.storage.StorageHandler.listPath;
import static duke.taskList.taskListOperations.*;
import static duke.storage.StorageHandler.createNewFile;

/**
 * Holds the methods required for decoding data from the txt file.
 */
public class DukeStorageDecoder {
    /**
     * Sets Duke into decoding mode and runs decoder.
     */
    public static void getList(){
        isPrintMessageEnabled = false;
        isLoading = true;
        try {
            fileToList();
        } catch (FileNotFoundException e) {
            createNewFile();
        }finally{
            isPrintMessageEnabled = true;
            isLoading = false;
        }
    }

    /**
     * Reads Task from the txt file.
     *
     * @throws if no txt file found at listPath.
     */
    public static void fileToList() throws FileNotFoundException {
        File file = new File(listPath); // create a File for the given file path
        Scanner output = new Scanner(file); // create a Scanner using the File as the source
        String line;
        while (output.hasNext()) {
            line = output.nextLine();
            //System.out.println(line);
            addNewTask(line);
        }
    }

    /**
     * Decodes and adds Task from txt file to Duke's Task ArrayList.
     */
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
            todoOperation("todo " + inputData);
        }else if(input.startsWith("D ") == true) {
            deadlineOperation("deadline " + inputData);
        }else if(input.startsWith("E ") == true) {
            eventOperation("event " + inputData);
        }else{
            System.out.println("Invalid task read.");
        }

        if(inputDoneData.contains("1")){
            listInputs.get(listInputs.size() - 1).setIsDone(true);
        }
    }
}
