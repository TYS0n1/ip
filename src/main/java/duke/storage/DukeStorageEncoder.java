package duke.storage;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.FileWriter;
import java.io.IOException;

import static duke.storage.StorageHandler.listInputs;
import static duke.storage.StorageHandler.listPath;

/**
 * Holds the methods required for encoding data into the txt file.
 */
public class DukeStorageEncoder {

    /**
     * Writes Tasks' information into the txt file.
     *
     * @throws IOException if no txt file found at listPath.
     */
    public static void writeListToFile() throws IOException {
        Task task = listInputs.get(0);
        String formatedTaskData;
        formatedTaskData = formatTaskForTxt(task);
        FileWriter writer = new FileWriter(listPath);
        writer.write(formatedTaskData); //Override existing file
        writer.close();

        for (int i = 1; i < listInputs.size(); i++) {
            task = listInputs.get(i);
            formatedTaskData = formatTaskForTxt(task);
            appendToFile(formatedTaskData);
        }
    }

    /**
     * Adds a Task's information into the txt file.
     *
     * @throws IOException if no txt file found at listPath.
     */
    public static void appendToFile(String textToAppend) throws IOException {
        FileWriter writer = new FileWriter(listPath, true);
        writer.write(textToAppend);
        writer.close();
    }

    /**
     * Reformats a Task's information into a language that the Duke program
     * decoder can understand.
     *
     * @return a String of the Task's reformatted information.
     */
    public static String formatTaskForTxt(Task task){
        String outputString;
        String taskData = task.getData();
        boolean isTaskDone = task.getIsDone();
        String taskDoneString;
        if(isTaskDone){
            taskDoneString = "1";
        }else{
            taskDoneString = "0";
        }

        if(task instanceof Event) {
            outputString = "E | " + taskDoneString + " | " + taskData
                    + " /at " + ((Event) task).getDateDue() + "\n";
        }else if(task instanceof Deadline) {
            outputString = "D | " + taskDoneString + " | " + taskData +
                    " /by " + ((Deadline) task).getDateDue() + "\n";
        }else if(task instanceof Todo){
            outputString = "T | " + taskDoneString  + " | " + taskData + "\n";
        }else{
            outputString = "? | " + taskDoneString  + " | " + taskData + "\n";
        }

        return outputString;
    }
}
